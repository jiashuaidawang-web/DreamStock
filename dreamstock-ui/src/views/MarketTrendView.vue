<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import MetricCard from '@/components/ui/MetricCard.vue'
import Badge from '@/components/ui/Badge.vue'
import { formatAmount, formatPct, pctColorClass } from '@/utils/format'
import type { MarketTrendData, IndexItem } from '@/api/marketTrend'
import { fetchMarketTrend } from '@/api/marketTrend'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { CandlestickChart, LineChart } from 'echarts/charts'
import { GridComponent, TooltipComponent, DataZoomComponent } from 'echarts/components'
import VChart from 'vue-echarts'

use([CanvasRenderer, CandlestickChart, LineChart, GridComponent, TooltipComponent, DataZoomComponent])

const data = ref<MarketTrendData | null>(null)
const loading = ref(true)
const selectedIndex = ref(0)

onMounted(async () => {
  try {
    const res = await fetchMarketTrend()
    data.value = (res as { data: MarketTrendData }).data
  } catch { data.value = null }
  loading.value = false
})

function getKLineOption(idx: IndexItem) {
  const kLines = idx.kLines || []
  return {
    backgroundColor: 'transparent',
    tooltip: { trigger: 'axis', backgroundColor: '#1e2329', borderColor: '#2b3139', textStyle: { color: '#eaecef', fontSize: 12 } },
    grid: { left: 60, right: 20, top: 20, bottom: 50 },
    xAxis: { type: 'category', data: kLines.map(k => k.tradeDate.slice(5)), axisLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c', fontSize: 10 } },
    yAxis: { type: 'value', scale: true, splitLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c' } },
    dataZoom: [{ type: 'inside', start: 60, end: 100 }],
    series: [{
      type: 'candlestick', data: kLines.map(k => [k.open, k.close, k.low, k.high]),
      itemStyle: { color: '#f6465d', color0: '#0ecb81', borderColor: '#f6465d', borderColor0: '#0ecb81' }
    }]
  }
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <div class="flex gap-2 mb-4">
      <button v-for="(idx, i) in data.indices" :key="idx.indexCode"
        :class="['px-4 py-2 rounded-lg text-sm transition-colors', selectedIndex === i ? 'bg-accent-yellow/10 text-accent-yellow' : 'bg-bg-secondary text-text-secondary hover:text-text-primary']"
        @click="selectedIndex = i">{{ idx.indexName }}</button>
    </div>
    <div class="grid grid-cols-2 md:grid-cols-5 gap-4">
      <MetricCard label="最新价" :value="data.indices[selectedIndex].close.toFixed(2)" value-class="text-text-primary" />
      <MetricCard label="涨跌幅" :value="formatPct(data.indices[selectedIndex].pctChg)" :value-class="pctColorClass(data.indices[selectedIndex].pctChg)" />
      <MetricCard label="成交额" :value="formatAmount(data.indices[selectedIndex].totalAmount)" value-class="text-text-primary" />
      <MetricCard label="趋势" :value="data.indices[selectedIndex].trendLabel" value-class="text-accent-yellow" />
      <MetricCard label="技术周期" :value="data.technicalCycle" value-class="text-accent-blue" />
    </div>
    <Card><h3 class="text-sm font-medium text-text-secondary mb-4">{{ data.indices[selectedIndex].indexName }} K线图 (60日)</h3>
      <VChart :option="getKLineOption(data.indices[selectedIndex])" style="height: 380px" autoresize /></Card>
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <Card><h3 class="text-sm font-medium text-text-secondary mb-4">均线状态</h3>
        <div class="space-y-3">
          <div class="flex justify-between items-center py-2 border-b border-border-primary"><span class="text-text-secondary text-sm">MA5</span><Badge variant="up">{{ data.indices[selectedIndex].ma5Status }}</Badge></div>
          <div class="flex justify-between items-center py-2 border-b border-border-primary"><span class="text-text-secondary text-sm">MA10</span><Badge variant="info">{{ data.indices[selectedIndex].ma10Status }}</Badge></div>
          <div class="flex justify-between items-center py-2 border-b border-border-primary"><span class="text-text-secondary text-sm">MA20</span><Badge variant="warning">{{ data.indices[selectedIndex].ma20Status }}</Badge></div>
          <div class="flex justify-between items-center py-2"><span class="text-text-secondary text-sm">MA60</span><Badge>{{ data.indices[selectedIndex].ma60Status }}</Badge></div>
        </div>
      </Card>
      <Card><h3 class="text-sm font-medium text-text-secondary mb-4">关键价位</h3>
        <div class="space-y-3">
          <div v-for="(level, i) in data.keyLevels" :key="i" class="flex justify-between items-center py-2 border-b border-border-primary last:border-0">
            <div class="flex items-center gap-2"><Badge :variant="level.levelType === 'SUPPORT' ? 'up' : 'down'">{{ level.levelType === 'SUPPORT' ? '支撑' : '压力' }}</Badge><span class="text-text-secondary text-sm">{{ level.description }}</span></div>
            <span class="text-text-primary font-mono font-medium">{{ level.price }}</span>
          </div>
        </div>
      </Card>
    </div>
    <Card><p class="text-sm text-text-secondary">{{ data.indexEmotionRelation }}</p></Card>
  </div>
</template>
