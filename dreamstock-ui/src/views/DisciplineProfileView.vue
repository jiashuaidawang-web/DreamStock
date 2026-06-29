<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import Badge from '@/components/ui/Badge.vue'
import type { DisciplineProfileData } from '@/api/disciplineProfile'
import { fetchDisciplineProfile } from '@/api/disciplineProfile'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { RadarChart, BarChart } from 'echarts/charts'
import { TooltipComponent, RadarComponent, GridComponent } from 'echarts/components'
import VChart from 'vue-echarts'

use([CanvasRenderer, RadarChart, BarChart, TooltipComponent, RadarComponent, GridComponent])

const data = ref<DisciplineProfileData | null>(null)
const loading = ref(true)

onMounted(async () => {
  try { const res = await fetchDisciplineProfile(); data.value = (res as { data: DisciplineProfileData }).data } catch { data.value = null }
  loading.value = false
})

function scoreColor(score: number): string {
  if (score >= 80) return 'text-up'
  if (score >= 60) return 'text-accent-yellow'
  if (score >= 40) return 'text-accent-orange'
  return 'text-down'
}

function getRadarOption() {
  if (!data.value) return {}
  const dims = data.value.dimensions
  return {
    backgroundColor: 'transparent',
    tooltip: { backgroundColor: '#1e2329', borderColor: '#2b3139', textStyle: { color: '#eaecef' } },
    radar: {
      indicator: dims.map(d => ({ name: d.dimensionName, max: 100 })),
      axisName: { color: '#eaecef', fontSize: 11 },
      splitLine: { lineStyle: { color: '#2b3139' } },
      splitArea: { areaStyle: { color: ['#1e2329', '#181a20'] } },
      axisLine: { lineStyle: { color: '#2b3139' } }
    },
    series: [{ type: 'radar', data: [{ value: dims.map(d => d.score), name: '纪律得分', areaStyle: { color: 'rgba(252,213,53,0.2)' }, lineStyle: { color: '#fcd535' }, itemStyle: { color: '#fcd535' } }] }]
  }
}

function getPnlOption() {
  if (!data.value) return {}
  const items = data.value.stagePnl
  return {
    backgroundColor: 'transparent',
    tooltip: { trigger: 'axis', backgroundColor: '#1e2329', borderColor: '#2b3139', textStyle: { color: '#eaecef', fontSize: 12 } },
    grid: { left: 60, right: 30, top: 10, bottom: 30 },
    xAxis: { type: 'category', data: items.map(i => i.stageName), axisLabel: { color: '#eaecef' }, axisLine: { lineStyle: { color: '#2b3139' } } },
    yAxis: { type: 'value', splitLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c' } },
    series: [{ type: 'bar', data: items.map(i => ({ value: i.totalPnl, itemStyle: { color: i.totalPnl >= 0 ? '#f6465d' : '#0ecb81' } })), barWidth: 40 }]
  }
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-4">
      <Card><h3 class="text-sm font-medium text-text-secondary mb-4">纪律维度雷达图</h3><VChart :option="getRadarOption()" style="height: 300px" autoresize /></Card>
      <Card>
        <h3 class="text-sm font-medium text-text-secondary mb-4">纪律维度明细</h3>
        <div class="space-y-3">
          <div v-for="d in data.dimensions" :key="d.dimension" class="flex items-center gap-3">
            <span class="text-sm text-text-secondary w-24">{{ d.dimensionName }}</span>
            <div class="flex-1 bg-bg-secondary rounded-full h-2"><div class="h-2 rounded-full transition-all" :style="{ width: d.score + '%', backgroundColor: d.score >= 80 ? '#0ecb81' : d.score >= 60 ? '#fcd535' : d.score >= 40 ? '#f97316' : '#f6465d' }"></div></div>
            <span :class="['font-mono text-sm font-medium w-10 text-right', scoreColor(d.score)]">{{ d.score }}</span>
            <span class="text-xs text-text-tertiary w-12">{{ d.count }}次</span>
            <span class="text-xs" :class="d.trend === 'UP' ? 'text-up' : d.trend === 'DOWN' ? 'text-down' : 'text-text-tertiary'">{{ d.trend }}</span>
          </div>
        </div>
      </Card>
    </div>
    <Card v-if="data.weekTopIssues.length > 0 || data.monthTopMistakes.length > 0">
      <h3 class="text-sm font-medium text-text-secondary mb-3">问题与提醒</h3>
      <div class="grid grid-cols-1 lg:grid-cols-3 gap-4">
        <div v-if="data.weekTopIssues.length > 0">
          <p class="text-xs text-text-tertiary mb-1">本周高频问题</p>
          <div class="flex flex-wrap gap-1"><Badge v-for="issue in data.weekTopIssues" :key="issue" variant="warning">{{ issue }}</Badge></div>
        </div>
        <div v-if="data.monthTopMistakes.length > 0">
          <p class="text-xs text-text-tertiary mb-1">本月高频错误</p>
          <div class="flex flex-wrap gap-1"><Badge v-for="m in data.monthTopMistakes" :key="m" variant="down">{{ m }}</Badge></div>
        </div>
        <div v-if="data.nextWeekReminders.length > 0">
          <p class="text-xs text-text-tertiary mb-1">下周提醒</p>
          <div class="flex flex-wrap gap-1"><Badge v-for="r in data.nextWeekReminders" :key="r" variant="info">{{ r }}</Badge></div>
        </div>
      </div>
    </Card>
    <Card><h3 class="text-sm font-medium text-text-secondary mb-4">各阶段盈亏汇总</h3><VChart :option="getPnlOption()" style="height: 280px" autoresize /></Card>
    <Card>
      <h3 class="text-sm font-medium text-text-secondary mb-4">分阶段交易明细</h3>
      <div class="overflow-x-auto">
        <table class="w-full text-sm">
          <thead><tr class="border-b border-border-primary text-text-tertiary text-left">
            <th class="py-2 px-2 font-medium">阶段</th><th class="py-2 px-2 font-medium text-right">交易次数</th>
            <th class="py-2 px-2 font-medium text-right">总盈亏</th><th class="py-2 px-2 font-medium text-right">胜率</th>
          </tr></thead>
          <tbody>
            <tr v-for="item in data.stagePnl" :key="item.stageName" class="border-b border-border-primary/50 hover:bg-bg-hover transition-colors">
              <td class="py-2 px-2 text-text-primary font-medium">{{ item.stageName }}</td>
              <td class="py-2 px-2 text-right font-mono">{{ item.tradeCount }}</td>
              <td class="py-2 px-2 text-right font-mono" :class="item.totalPnl >= 0 ? 'text-up' : 'text-down'">{{ item.totalPnl >= 0 ? '+' : '' }}{{ item.totalPnl.toFixed(2) }}</td>
              <td class="py-2 px-2 text-right font-mono text-accent-yellow">{{ item.winRate }}%</td>
            </tr>
          </tbody>
        </table>
      </div>
    </Card>
  </div>
</template>
