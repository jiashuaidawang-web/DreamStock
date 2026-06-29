<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import MetricCard from '@/components/ui/MetricCard.vue'
import type { StockCorrelationData } from '@/api/stockCorrelation'
import { fetchStockCorrelation } from '@/api/stockCorrelation'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { LineChart } from 'echarts/charts'
import { GridComponent, TooltipComponent, LegendComponent } from 'echarts/components'
import VChart from 'vue-echarts'

use([CanvasRenderer, LineChart, GridComponent, TooltipComponent, LegendComponent])

const data = ref<StockCorrelationData | null>(null)
const loading = ref(true)
const stockCode = ref('688981')
const windowSize = ref(20)

async function loadData() {
  loading.value = true
  try { const res = await fetchStockCorrelation(stockCode.value, windowSize.value); data.value = (res as { data: StockCorrelationData }).data } catch { data.value = null }
  loading.value = false
}

onMounted(loadData)

function corrColor(val: number): string {
  if (val >= 0.7) return 'text-up'
  if (val >= 0.4) return 'text-accent-yellow'
  return 'text-down'
}

function getChartOption() {
  if (!data.value) return {}
  const trend = data.value.corrTrend
  return {
    backgroundColor: 'transparent',
    tooltip: { trigger: 'axis', backgroundColor: '#1e2329', borderColor: '#2b3139', textStyle: { color: '#eaecef', fontSize: 12 } },
    legend: { data: ['个股涨跌', '指数涨跌', '情绪分数'], textStyle: { color: '#848e9c' }, top: 0 },
    grid: { left: 60, right: 60, top: 40, bottom: 30 },
    xAxis: { type: 'category', data: trend.map(p => p.tradeDate.slice(5)), axisLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c', fontSize: 10 } },
    yAxis: [
      { type: 'value', name: '涨跌%', splitLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c' }, nameTextStyle: { color: '#848e9c' } },
      { type: 'value', name: '情绪分', position: 'right', min: 0, max: 100, splitLine: { show: false }, axisLabel: { color: '#848e9c' }, nameTextStyle: { color: '#848e9c' } }
    ],
    series: [
      { name: '个股涨跌', type: 'line', data: trend.map(p => p.stockPctChg), lineStyle: { color: '#f6465d' }, itemStyle: { color: '#f6465d' }, symbol: 'none', smooth: true },
      { name: '指数涨跌', type: 'line', data: trend.map(p => p.indexPctChg), lineStyle: { color: '#1890ff' }, itemStyle: { color: '#1890ff' }, symbol: 'none', smooth: true },
      { name: '情绪分数', type: 'line', data: trend.map(p => p.emotionScore), yAxisIndex: 1, lineStyle: { color: '#fcd535', type: 'dashed' }, itemStyle: { color: '#fcd535' }, symbol: 'none', smooth: true }
    ]
  }
}
</script>

<template>
  <div class="space-y-6">
    <div class="flex items-center gap-4">
      <div class="flex items-center gap-2">
        <label class="text-sm text-text-secondary">股票代码:</label>
        <input v-model="stockCode" class="bg-bg-secondary border border-border-primary rounded-lg px-3 py-1.5 text-sm text-text-primary focus:border-accent-yellow focus:outline-none w-32" @keyup.enter="loadData" />
      </div>
      <div class="flex items-center gap-2">
        <label class="text-sm text-text-secondary">窗口:</label>
        <select v-model.number="windowSize" class="bg-bg-secondary border border-border-primary rounded-lg px-3 py-1.5 text-sm text-text-primary focus:border-accent-yellow focus:outline-none" @change="loadData">
          <option :value="10">10日</option><option :value="20">20日</option><option :value="60">60日</option>
        </select>
      </div>
      <button class="px-4 py-1.5 bg-accent-yellow/10 text-accent-yellow text-sm rounded-lg hover:bg-accent-yellow/20 transition-colors" @click="loadData">查询</button>
    </div>
    <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
    <div v-else-if="data" class="space-y-6">
      <div class="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-7 gap-4">
        <MetricCard label="股票" :value="data.stockName" value-class="text-text-primary" :sub-value="data.stockCode" />
        <MetricCard label="指数相关性" :value="data.indexCorr.toFixed(2)" :value-class="corrColor(data.indexCorr)" />
        <MetricCard label="情绪相关性" :value="data.emotionCorr.toFixed(2)" :value-class="corrColor(data.emotionCorr)" />
        <MetricCard label="主题相关性" :value="data.themeCorr.toFixed(2)" :value-class="corrColor(data.themeCorr)" />
        <MetricCard label="RS排名" :value="'前' + data.rsRank + '%'" value-class="text-accent-yellow" />
        <MetricCard label="位置标签" :value="data.positionTag" value-class="text-up" />
        <MetricCard label="分歧标签" :value="data.divergenceTag" value-class="text-accent-blue" />
      </div>
      <Card><p class="text-sm text-text-secondary">{{ data.explanation }}</p></Card>
      <Card><h3 class="text-sm font-medium text-text-secondary mb-4">走势对比 ({{ data.windowSize }}日)</h3><VChart :option="getChartOption()" style="height: 350px" autoresize /></Card>
    </div>
  </div>
</template>
