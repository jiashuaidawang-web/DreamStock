<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import { formatPct, pctColorClass } from '@/utils/format'
import type { CycleReplayData } from '@/api/cycleReplay'
import { fetchCycleReplay } from '@/api/cycleReplay'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { LineChart, BarChart } from 'echarts/charts'
import { GridComponent, TooltipComponent, DataZoomComponent } from 'echarts/components'
import VChart from 'vue-echarts'

use([CanvasRenderer, LineChart, BarChart, GridComponent, TooltipComponent, DataZoomComponent])

const data = ref<CycleReplayData | null>(null)
const loading = ref(true)

onMounted(async () => {
  try { const res = await fetchCycleReplay(); data.value = (res as { data: CycleReplayData }).data } catch { data.value = null }
  loading.value = false
})

function stageColor(stage: string): string {
  const map: Record<string, string> = { '冰点': '#1890ff', '修复': '#06b6d4', '启动': '#0ecb81', '发酵': '#fcd535', '高潮': '#f6465d', '分歧': '#f97316', '退潮': '#a855f7' }
  return map[stage] || '#848e9c'
}

function getTimelineOption() {
  if (!data.value) return {}
  const snapshots = data.value.snapshots
  return {
    backgroundColor: 'transparent',
    tooltip: { trigger: 'axis', backgroundColor: '#1e2329', borderColor: '#2b3139', textStyle: { color: '#eaecef', fontSize: 12 } },
    grid: [{ left: 60, right: 30, top: 20, height: '40%' }, { left: 60, right: 30, bottom: 30, height: '25%' }],
    xAxis: [
      { type: 'category', data: snapshots.map(s => s.tradeDate.slice(5)), gridIndex: 0, axisLabel: { show: false }, axisLine: { lineStyle: { color: '#2b3139' } } },
      { type: 'category', data: snapshots.map(s => s.tradeDate.slice(5)), gridIndex: 1, axisLabel: { color: '#848e9c', fontSize: 10 }, axisLine: { lineStyle: { color: '#2b3139' } } }
    ],
    yAxis: [
      { type: 'value', gridIndex: 0, splitLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c' } },
      { type: 'value', gridIndex: 1, splitLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c' } }
    ],
    dataZoom: [{ type: 'inside', xAxisIndex: [0, 1] }],
    series: [
      { type: 'line', data: snapshots.map(s => s.emotionScore), xAxisIndex: 0, yAxisIndex: 0, smooth: true,
        lineStyle: { color: '#fcd535' }, itemStyle: { color: (p: { dataIndex: number }) => stageColor(snapshots[p.dataIndex].emotionStageName) }, areaStyle: { color: 'rgba(252,213,53,0.1)' } },
      { type: 'bar', data: snapshots.map(s => ({ value: s.limitUpCount, itemStyle: { color: '#f6465d' } })), xAxisIndex: 1, yAxisIndex: 1, name: '涨停' }
    ]
  }
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <Card v-if="data.reportSummary">
      <h3 class="text-sm font-medium text-text-secondary mb-3">周期复盘报告</h3>
      <div class="grid grid-cols-2 md:grid-cols-3 gap-4 mb-4">
        <div><p class="text-xs text-text-tertiary">分析区间</p><p class="text-sm text-text-primary">{{ data.startDate }} ~ {{ data.endDate }}</p></div>
        <div><p class="text-xs text-text-tertiary">总交易日</p><p class="text-sm text-text-primary">{{ data.snapshots.length }}天</p></div>
        <div><p class="text-xs text-text-tertiary">总盈亏</p><p class="text-sm text-accent-yellow">{{ data.reportSummary.totalPnl.toFixed(2) }}%</p></div>
      </div>
      <div class="space-y-2">
        <div><p class="text-xs text-text-tertiary mb-1">周期变化</p><p class="text-sm text-text-primary">{{ data.reportSummary.cycleChanges }}</p></div>
        <div><p class="text-xs text-text-tertiary mb-1">题材轮动</p><p class="text-sm text-text-primary">{{ data.reportSummary.themeRotation }}</p></div>
        <div><p class="text-xs text-text-tertiary mb-1">龙头生命线</p><p class="text-sm text-text-primary">{{ data.reportSummary.dragonLifecycle }}</p></div>
        <div><p class="text-xs text-text-tertiary mb-1">核心结论</p><p class="text-sm text-accent-yellow">{{ data.reportSummary.periodConclusion }}</p></div>
      </div>
    </Card>
    <Card><h3 class="text-sm font-medium text-text-secondary mb-4">情绪周期时间轴</h3><VChart :option="getTimelineOption()" style="height: 400px" autoresize /></Card>
    <Card>
      <h3 class="text-sm font-medium text-text-secondary mb-4">每日快照明细</h3>
      <div class="overflow-x-auto">
        <table class="w-full text-sm">
          <thead><tr class="border-b border-border-primary text-text-tertiary text-left">
            <th class="py-2 px-2 font-medium">日期</th><th class="py-2 px-2 font-medium">阶段</th>
            <th class="py-2 px-2 font-medium text-right">情绪分</th><th class="py-2 px-2 font-medium text-right">指数</th>
            <th class="py-2 px-2 font-medium text-right">涨停</th><th class="py-2 px-2 font-medium text-right">成交额</th>
            <th class="py-2 px-2 font-medium">主线</th><th class="py-2 px-2 font-medium">龙头</th>
          </tr></thead>
          <tbody>
            <tr v-for="s in data.snapshots" :key="s.tradeDate" class="border-b border-border-primary/50 hover:bg-bg-hover transition-colors">
              <td class="py-2 px-2 text-text-primary font-mono text-xs">{{ s.tradeDate }}</td>
              <td class="py-2 px-2"><span class="px-2 py-0.5 rounded text-xs font-medium" :style="{ backgroundColor: stageColor(s.emotionStageName) + '22', color: stageColor(s.emotionStageName) }">{{ s.emotionStageName }}</span></td>
              <td class="py-2 px-2 text-right font-mono text-accent-yellow">{{ s.emotionScore }}</td>
              <td :class="['py-2 px-2 text-right font-mono', pctColorClass(s.indexPctChg)]">{{ formatPct(s.indexPctChg) }}</td>
              <td class="py-2 px-2 text-right font-mono text-up">{{ s.limitUpCount }}</td>
              <td class="py-2 px-2 text-right font-mono text-text-secondary">{{ (s.totalAmount / 1e8).toFixed(0) }}亿</td>
              <td class="py-2 px-2 text-text-secondary text-xs">{{ s.mainTheme }}</td>
              <td class="py-2 px-2 text-text-secondary text-xs">{{ s.topDragon }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </Card>
  </div>
</template>
