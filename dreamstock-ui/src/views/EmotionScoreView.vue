<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import MetricCard from '@/components/ui/MetricCard.vue'
import Badge from '@/components/ui/Badge.vue'
import { formatPct, pctColorClass } from '@/utils/format'
import type { EmotionScoreData } from '@/api/emotionScore'
import { fetchEmotionScore } from '@/api/emotionScore'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { BarChart, RadarChart } from 'echarts/charts'
import { GridComponent, TooltipComponent, RadarComponent } from 'echarts/components'
import VChart from 'vue-echarts'

use([CanvasRenderer, BarChart, RadarChart, GridComponent, TooltipComponent, RadarComponent])

const data = ref<EmotionScoreData | null>(null)
const loading = ref(true)

onMounted(async () => {
  try {
    const res = await fetchEmotionScore()
    data.value = (res as { data: EmotionScoreData }).data
  } catch {
    data.value = null
  }
  loading.value = false
})

function getFactorChartOption() {
  if (!data.value) return {}
  const factors = data.value.factors
  return {
    backgroundColor: 'transparent',
    tooltip: { trigger: 'axis', backgroundColor: '#1e2329', borderColor: '#2b3139', textStyle: { color: '#eaecef', fontSize: 12 } },
    grid: { top: 10, right: 20, bottom: 30, left: 100 },
    xAxis: { type: 'value', max: 100, splitLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c' } },
    yAxis: { type: 'category', data: factors.map(f => f.factorName), axisLabel: { color: '#eaecef', fontSize: 11 }, axisTick: { show: false }, axisLine: { lineStyle: { color: '#2b3139' } } },
    series: [{
      type: 'bar', data: factors.map(f => ({ value: f.score, itemStyle: { color: f.direction === 'UP' ? '#f6465d' : f.direction === 'DOWN' ? '#0ecb81' : '#fcd535' } })),
      barWidth: 18, label: { show: true, position: 'right', color: '#eaecef', fontSize: 11, formatter: '{c}' }
    }]
  }
}

function getRadarChartOption() {
  if (!data.value) return {}
  const factors = data.value.factors
  return {
    backgroundColor: 'transparent',
    tooltip: { backgroundColor: '#1e2329', borderColor: '#2b3139', textStyle: { color: '#eaecef' } },
    radar: {
      indicator: factors.map(f => ({ name: f.factorName, max: 100 })),
      shape: 'polygon', axisName: { color: '#848e9c', fontSize: 10 },
      splitArea: { areaStyle: { color: ['rgba(43,49,57,0.3)', 'rgba(43,49,57,0.1)'] } },
      splitLine: { lineStyle: { color: '#2b3139' } }, axisLine: { lineStyle: { color: '#2b3139' } }
    },
    series: [{ type: 'radar', data: [{ value: factors.map(f => f.score), name: '当日评分', areaStyle: { color: 'rgba(252,213,53,0.2)' }, lineStyle: { color: '#fcd535' }, itemStyle: { color: '#fcd535' } }] }]
  }
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <div class="grid grid-cols-2 md:grid-cols-5 gap-4">
      <MetricCard label="综合情绪分数" :value="data.emotionScore.toFixed(1)" value-class="text-accent-yellow" :sub-value="data.emotionStageName" />
      <MetricCard label="评级" :value="data.emotionGrade" :value-class="data.emotionGrade === 'A' ? 'text-up' : data.emotionGrade === 'D' ? 'text-down' : 'text-accent-yellow'" />
      <MetricCard label="昨日分数" :value="data.compareInfo.yesterdayScore.toFixed(1)" value-class="text-text-primary" />
      <MetricCard label="5日均值" :value="data.compareInfo.avg5dScore.toFixed(1)" value-class="text-text-primary" />
      <MetricCard label="日环比" :value="formatPct(data.compareInfo.changeFromYesterday)" :value-class="pctColorClass(data.compareInfo.changeFromYesterday)" />
    </div>
    <Card><p class="text-sm text-text-secondary mb-1">结论</p><p class="text-text-primary text-sm">{{ data.conclusion }}</p></Card>
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <Card><h3 class="text-sm font-medium text-text-secondary mb-4">因子评分</h3><VChart :option="getFactorChartOption()" style="height: 300px" autoresize /></Card>
      <Card><h3 class="text-sm font-medium text-text-secondary mb-4">因子雷达图</h3><VChart :option="getRadarChartOption()" style="height: 300px" autoresize /></Card>
    </div>
    <Card>
      <h3 class="text-sm font-medium text-text-secondary mb-4">涨停明细 (Top)</h3>
      <table class="w-full text-sm">
        <thead><tr class="border-b border-border-primary text-text-tertiary text-left">
          <th class="py-2 px-2 font-medium">代码</th><th class="py-2 px-2 font-medium">名称</th>
          <th class="py-2 px-2 font-medium text-right">涨幅</th><th class="py-2 px-2 font-medium text-center">连板</th>
          <th class="py-2 px-2 font-medium">原因</th>
        </tr></thead>
        <tbody>
          <tr v-for="d in data.limitUpDetails" :key="d.stockCode" class="border-b border-border-primary/50 hover:bg-bg-hover">
            <td class="py-2 px-2 text-text-tertiary font-mono">{{ d.stockCode }}</td>
            <td class="py-2 px-2 text-text-primary font-medium">{{ d.stockName }}</td>
            <td :class="['py-2 px-2 text-right font-mono', pctColorClass(d.pctChg)]">{{ formatPct(d.pctChg) }}</td>
            <td class="py-2 px-2 text-center"><Badge variant="up">{{ d.boardCount }}板</Badge></td>
            <td class="py-2 px-2 text-text-secondary text-xs">{{ d.reason }}</td>
          </tr>
        </tbody>
      </table>
    </Card>
  </div>
</template>
