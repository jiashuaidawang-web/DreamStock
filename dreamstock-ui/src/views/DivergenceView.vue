<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import Badge from '@/components/ui/Badge.vue'
import { formatPct, pctColorClass } from '@/utils/format'
import type { DivergenceData } from '@/api/divergence'
import { fetchDivergence } from '@/api/divergence'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { ScatterChart } from 'echarts/charts'
import { GridComponent, TooltipComponent } from 'echarts/components'
import VChart from 'vue-echarts'

use([CanvasRenderer, ScatterChart, GridComponent, TooltipComponent])

const data = ref<DivergenceData | null>(null)
const loading = ref(true)

onMounted(async () => {
  try { const res = await fetchDivergence(); data.value = (res as { data: DivergenceData }).data } catch { data.value = null }
  loading.value = false
})

function divergenceVariant(status: string): 'up' | 'warning' | 'down' | 'info' | 'default' {
  if (status.includes('一致')) return 'up'
  if (status.includes('轻微')) return 'info'
  if (status.includes('明显')) return 'warning'
  return 'down'
}

function riskVariant(tag: string): 'up' | 'warning' | 'down' | 'default' {
  if (tag === '低风险') return 'up'
  if (tag === '中风险') return 'warning'
  return 'down'
}

function getScatterOption() {
  if (!data.value) return {}
  const items = data.value.items
  return {
    backgroundColor: 'transparent',
    tooltip: { backgroundColor: '#1e2329', borderColor: '#2b3139', textStyle: { color: '#eaecef', fontSize: 12 },
      formatter: (p: { data: number[] }) => `封单强度: ${p.data[0]}%<br/>换手率: ${p.data[1]}%<br/>次日溢价: ${p.data[2]}%` },
    grid: { left: 60, right: 30, top: 20, bottom: 40 },
    xAxis: { type: 'value', name: '封单强度(%)', splitLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c' }, nameTextStyle: { color: '#848e9c' } },
    yAxis: { type: 'value', name: '换手率(%)', splitLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c' }, nameTextStyle: { color: '#848e9c' } },
    series: [{ type: 'scatter', data: items.map(i => [i.sealStrength, i.turnoverRate, i.nextDayPremium]),
      symbolSize: (val: number[]) => Math.max(10, Math.abs(val[2]) * 8),
      itemStyle: { color: (p: { data: number[] }) => p.data[2] >= 0 ? '#f6465d' : '#0ecb81' } }]
  }
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <Card><h3 class="text-sm font-medium text-text-secondary mb-4">分歧一致散点图 (封单强度 vs 换手率)</h3><VChart :option="getScatterOption()" style="height: 320px" autoresize /></Card>
    <Card>
      <h3 class="text-sm font-medium text-text-secondary mb-4">分歧一致明细</h3>
      <div class="overflow-x-auto">
        <table class="w-full text-sm">
          <thead><tr class="border-b border-border-primary text-text-tertiary text-left">
            <th class="py-2 px-2 font-medium">代码</th><th class="py-2 px-2 font-medium">名称</th>
            <th class="py-2 px-2 font-medium text-center">连板</th><th class="py-2 px-2 font-medium text-center">开板</th>
            <th class="py-2 px-2 font-medium text-right">封单强度</th><th class="py-2 px-2 font-medium text-right">换手率</th>
            <th class="py-2 px-2 font-medium">状态</th><th class="py-2 px-2 font-medium text-right">次日溢价</th>
            <th class="py-2 px-2 font-medium">题材</th><th class="py-2 px-2 font-medium">风险</th>
          </tr></thead>
          <tbody>
            <tr v-for="item in data.items" :key="item.stockCode" class="border-b border-border-primary/50 hover:bg-bg-hover transition-colors">
              <td class="py-2 px-2 text-text-tertiary font-mono text-xs">{{ item.stockCode }}</td>
              <td class="py-2 px-2 text-text-primary font-medium">{{ item.stockName }}</td>
              <td class="py-2 px-2 text-center"><Badge variant="up">{{ item.boardCount }}板</Badge></td>
              <td class="py-2 px-2 text-center" :class="item.openCount > 0 ? 'text-accent-orange' : 'text-text-tertiary'">{{ item.openCount }}</td>
              <td class="py-2 px-2 text-right font-mono text-accent-yellow">{{ item.sealStrength }}%</td>
              <td class="py-2 px-2 text-right font-mono text-text-secondary">{{ item.turnoverRate }}%</td>
              <td class="py-2 px-2"><Badge :variant="divergenceVariant(item.divergenceStatus)">{{ item.divergenceStatus }}</Badge></td>
              <td :class="['py-2 px-2 text-right font-mono', pctColorClass(item.nextDayPremium)]">{{ formatPct(item.nextDayPremium) }}</td>
              <td class="py-2 px-2 text-text-secondary text-xs">{{ item.themeName }}</td>
              <td class="py-2 px-2"><Badge :variant="riskVariant(item.riskTag)">{{ item.riskTag }}</Badge></td>
            </tr>
          </tbody>
        </table>
      </div>
    </Card>
  </div>
</template>
