<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import Badge from '@/components/ui/Badge.vue'
import { formatAmount, formatPct, pctColorClass } from '@/utils/format'
import type { ThemeMatrixData } from '@/api/themeMatrix'
import { fetchThemeMatrix } from '@/api/themeMatrix'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { BarChart } from 'echarts/charts'
import { GridComponent, TooltipComponent } from 'echarts/components'
import VChart from 'vue-echarts'

use([CanvasRenderer, BarChart, GridComponent, TooltipComponent])

const data = ref<ThemeMatrixData | null>(null)
const loading = ref(true)

onMounted(async () => {
  try { const res = await fetchThemeMatrix(); data.value = (res as { data: ThemeMatrixData }).data } catch { data.value = null }
  loading.value = false
})

function stageColor(stage: string): string {
  const map: Record<string, string> = { '冰点': 'text-accent-blue', '修复': 'text-accent-cyan', '启动': 'text-down', '发酵': 'text-accent-yellow', '高潮': 'text-up', '分歧': 'text-accent-orange', '退潮': 'text-accent-purple' }
  return map[stage] || 'text-text-secondary'
}

function getHeatOption() {
  if (!data.value) return {}
  const themes = data.value.themes
  return {
    backgroundColor: 'transparent',
    tooltip: { trigger: 'axis', backgroundColor: '#1e2329', borderColor: '#2b3139', textStyle: { color: '#eaecef', fontSize: 12 } },
    grid: { left: 100, right: 30, top: 10, bottom: 30 },
    xAxis: { type: 'value', max: 100, splitLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c' } },
    yAxis: { type: 'category', data: themes.map(t => t.themeName), axisLabel: { color: '#eaecef', fontSize: 11 }, axisTick: { show: false }, axisLine: { lineStyle: { color: '#2b3139' } } },
    series: [{ type: 'bar', data: themes.map(t => ({ value: t.heatScore, itemStyle: { color: t.heatScore >= 80 ? '#f6465d' : t.heatScore >= 60 ? '#fcd535' : t.heatScore >= 40 ? '#1890ff' : '#0ecb81' } })), barWidth: 20, label: { show: true, position: 'right', color: '#eaecef', fontSize: 11, formatter: '{c}' } }]
  }
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <Card><h3 class="text-sm font-medium text-text-secondary mb-4">题材热度排名</h3><VChart :option="getHeatOption()" style="height: 320px" autoresize /></Card>
    <Card>
      <h3 class="text-sm font-medium text-text-secondary mb-4">题材主线矩阵</h3>
      <div class="overflow-x-auto">
        <table class="w-full text-sm">
          <thead><tr class="border-b border-border-primary text-text-tertiary text-left">
            <th class="py-2 px-2 font-medium">#</th><th class="py-2 px-2 font-medium">题材</th><th class="py-2 px-2 font-medium">热度</th>
            <th class="py-2 px-2 font-medium">涨停</th><th class="py-2 px-2 font-medium">连板</th><th class="py-2 px-2 font-medium">龙头</th>
            <th class="py-2 px-2 font-medium">梯队</th><th class="py-2 px-2 font-medium">持续</th><th class="py-2 px-2 font-medium">阶段</th>
            <th class="py-2 px-2 font-medium text-right">均涨幅</th><th class="py-2 px-2 font-medium text-right">净流入</th><th class="py-2 px-2 font-medium">风险</th>
          </tr></thead>
          <tbody>
            <tr v-for="t in data.themes" :key="t.themeId" class="border-b border-border-primary/50 hover:bg-bg-hover transition-colors">
              <td class="py-2 px-2"><Badge :variant="t.rank <= 3 ? 'up' : 'default'">{{ t.rank }}</Badge></td>
              <td class="py-2 px-2 text-text-primary font-medium">{{ t.themeName }}</td>
              <td class="py-2 px-2 text-accent-yellow font-mono">{{ t.heatScore }}</td>
              <td class="py-2 px-2 text-up font-mono">{{ t.limitUpCount }}</td>
              <td class="py-2 px-2 font-mono">{{ t.consecutiveCount }}</td>
              <td class="py-2 px-2"><span class="text-text-primary">{{ t.leaderStockName }}</span><span class="text-text-tertiary text-xs ml-1">{{ t.leaderStockCode }}</span></td>
              <td class="py-2 px-2"><div class="w-full bg-bg-secondary rounded-full h-2"><div class="bg-accent-yellow h-2 rounded-full" :style="{ width: t.ladderCompleteness + '%' }"></div></div></td>
              <td class="py-2 px-2 font-mono">{{ t.continuityDays }}天</td>
              <td class="py-2 px-2"><span :class="stageColor(t.emotionStage)">{{ t.emotionStage }}</span></td>
              <td :class="['py-2 px-2 text-right font-mono', pctColorClass(t.avgPctChg)]">{{ formatPct(t.avgPctChg) }}</td>
              <td :class="['py-2 px-2 text-right font-mono', pctColorClass(t.netInflow)]">{{ formatAmount(t.netInflow) }}</td>
              <td class="py-2 px-2"><Badge :variant="t.riskTag.includes('风险') ? 'down' : 'info'">{{ t.riskTag }}</Badge></td>
            </tr>
          </tbody>
        </table>
      </div>
    </Card>
  </div>
</template>
