<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import Badge from '@/components/ui/Badge.vue'
import type { CycleTrendData } from '@/api/cycleTrend'
import { fetchCycleTrend } from '@/api/cycleTrend'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { LineChart, BarChart } from 'echarts/charts'
import { GridComponent, TooltipComponent, LegendComponent, MarkLineComponent, DataZoomComponent } from 'echarts/components'
import VChart from 'vue-echarts'

use([CanvasRenderer, LineChart, BarChart, GridComponent, TooltipComponent, LegendComponent, MarkLineComponent, DataZoomComponent])

const data = ref<CycleTrendData | null>(null)
const loading = ref(true)

const stageColors: Record<string, string> = {
  '冰点': '#1890ff', '修复': '#06b6d4', '启动': '#0ecb81', '发酵': '#fcd535',
  '高潮': '#f6465d', '分歧': '#f97316', '退潮': '#a855f7', '混沌': '#848e9c'
}

onMounted(async () => {
  try {
    const res = await fetchCycleTrend(60)
    data.value = (res as { data: CycleTrendData }).data
  } catch { data.value = null }
  loading.value = false
})

function getChartOption() {
  if (!data.value) return {}
  const pts = data.value.points
  return {
    backgroundColor: 'transparent',
    tooltip: { trigger: 'axis', backgroundColor: '#1e2329', borderColor: '#2b3139', textStyle: { color: '#eaecef', fontSize: 12 } },
    legend: { data: ['情绪分数', '指数', '成交额'], textStyle: { color: '#848e9c' }, top: 0 },
    grid: [{ left: 60, right: 60, top: 40, height: '35%' }, { left: 60, right: 60, top: '55%', height: '25%' }],
    xAxis: [
      { type: 'category', data: pts.map(p => p.tradeDate.slice(5)), gridIndex: 0, axisLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c', fontSize: 10 } },
      { type: 'category', data: pts.map(p => p.tradeDate.slice(5)), gridIndex: 1, axisLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c', fontSize: 10 } }
    ],
    yAxis: [
      { type: 'value', min: 0, max: 100, name: '情绪', gridIndex: 0, splitLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c' }, nameTextStyle: { color: '#848e9c' } },
      { type: 'value', name: '指数', gridIndex: 0, position: 'right', splitLine: { show: false }, axisLabel: { color: '#848e9c' }, nameTextStyle: { color: '#848e9c' } },
      { type: 'value', name: '成交额(亿)', gridIndex: 1, splitLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c', formatter: (v: number) => (v / 1e8).toFixed(0) }, nameTextStyle: { color: '#848e9c' } }
    ],
    dataZoom: [{ type: 'inside', xAxisIndex: [0, 1], start: 0, end: 100 }],
    series: [
      {
        name: '情绪分数', type: 'line', data: pts.map(p => p.emotionScore), smooth: true,
        lineStyle: { color: '#fcd535', width: 2 }, itemStyle: { color: '#fcd535' },
        areaStyle: { color: { type: 'linear', x: 0, y: 0, x2: 0, y2: 1, colorStops: [{ offset: 0, color: 'rgba(252,213,53,0.3)' }, { offset: 1, color: 'rgba(252,213,53,0)' }] } },
        symbol: 'none', xAxisIndex: 0, yAxisIndex: 0,
        markLine: { silent: true, lineStyle: { color: '#f6465d', type: 'dashed' }, data: [{ yAxis: 80, label: { formatter: '高潮线', color: '#f6465d' } }, { yAxis: 20, label: { formatter: '冰点线', color: '#1890ff' } }] }
      },
      { name: '指数', type: 'line', data: pts.map(p => p.indexClose), smooth: true, lineStyle: { color: '#1890ff', width: 1.5 }, itemStyle: { color: '#1890ff' }, symbol: 'none', xAxisIndex: 0, yAxisIndex: 1 },
      { name: '成交额', type: 'bar', data: pts.map(p => p.totalAmount), xAxisIndex: 1, yAxisIndex: 2, itemStyle: { color: 'rgba(24,144,255,0.4)' } }
    ]
  }
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <Card><h3 class="text-sm font-medium text-text-secondary mb-4">情绪周期波动趋势 (60日)</h3><VChart :option="getChartOption()" style="height: 480px" autoresize /></Card>
    <Card v-if="data.events.length > 0">
      <h3 class="text-sm font-medium text-text-secondary mb-4">重要事件标记</h3>
      <div class="space-y-2">
        <div v-for="(evt, i) in data.events" :key="i" class="flex items-center gap-3 p-3 rounded-lg bg-bg-secondary">
          <Badge :variant="evt.impactDirection === 'UP' ? 'up' : 'down'">{{ evt.eventType }}</Badge>
          <span class="text-text-tertiary text-xs">{{ evt.tradeDate }}</span>
          <span class="text-text-primary text-sm flex-1">{{ evt.eventTitle }}</span>
          <Badge :variant="evt.impactDirection === 'UP' ? 'up' : 'down'">{{ evt.impactDirection === 'UP' ? '利好' : '利空' }}</Badge>
        </div>
      </div>
    </Card>
    <Card>
      <h3 class="text-sm font-medium text-text-secondary mb-4">阶段分布说明</h3>
      <div class="flex flex-wrap gap-3">
        <div v-for="(color, stage) in stageColors" :key="stage" class="flex items-center gap-2">
          <div class="w-3 h-3 rounded-full" :style="{ backgroundColor: color }"></div>
          <span class="text-text-secondary text-xs">{{ stage }}</span>
        </div>
      </div>
    </Card>
  </div>
</template>
