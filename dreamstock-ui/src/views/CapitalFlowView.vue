<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import MetricCard from '@/components/ui/MetricCard.vue'
import { formatAmount, formatPct, pctColorClass } from '@/utils/format'
import type { CapitalFlowData } from '@/api/capitalFlow'
import { fetchCapitalFlow } from '@/api/capitalFlow'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { LineChart, BarChart } from 'echarts/charts'
import { GridComponent, TooltipComponent, LegendComponent } from 'echarts/components'
import VChart from 'vue-echarts'

use([CanvasRenderer, LineChart, BarChart, GridComponent, TooltipComponent, LegendComponent])

const data = ref<CapitalFlowData | null>(null)
const loading = ref(true)

onMounted(async () => {
  try { const res = await fetchCapitalFlow(); data.value = (res as { data: CapitalFlowData }).data } catch { data.value = null }
  loading.value = false
})

function getTrendOption() {
  if (!data.value) return {}
  const t = data.value.trend
  return {
    backgroundColor: 'transparent',
    tooltip: { trigger: 'axis', backgroundColor: '#1e2329', borderColor: '#2b3139', textStyle: { color: '#eaecef', fontSize: 12 } },
    legend: { data: ['成交额', '北向资金', '融资变化'], textStyle: { color: '#848e9c' }, top: 0 },
    grid: { left: 60, right: 60, top: 40, bottom: 30 },
    xAxis: { type: 'category', data: t.map(p => p.tradeDate.slice(5)), axisLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c', fontSize: 10 } },
    yAxis: [
      { type: 'value', name: '成交额(亿)', splitLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c', formatter: (v: number) => (v / 1e8).toFixed(0) }, nameTextStyle: { color: '#848e9c' } },
      { type: 'value', name: '净流入(亿)', position: 'right', splitLine: { show: false }, axisLabel: { color: '#848e9c', formatter: (v: number) => (v / 1e8).toFixed(0) }, nameTextStyle: { color: '#848e9c' } }
    ],
    series: [
      { name: '成交额', type: 'bar', data: t.map(p => p.totalAmount), yAxisIndex: 0, itemStyle: { color: 'rgba(24,144,255,0.4)' } },
      { name: '北向资金', type: 'line', data: t.map(p => p.northMoney), yAxisIndex: 1, lineStyle: { color: '#f6465d' }, itemStyle: { color: '#f6465d' }, symbol: 'none', smooth: true },
      { name: '融资变化', type: 'line', data: t.map(p => p.marginChange), yAxisIndex: 1, lineStyle: { color: '#0ecb81' }, itemStyle: { color: '#0ecb81' }, symbol: 'none', smooth: true }
    ]
  }
}

function getSectorOption() {
  if (!data.value) return {}
  const s = data.value.sectorFlows
  return {
    backgroundColor: 'transparent',
    tooltip: { trigger: 'axis', backgroundColor: '#1e2329', borderColor: '#2b3139', textStyle: { color: '#eaecef', fontSize: 12 } },
    grid: { left: 80, right: 30, top: 10, bottom: 30 },
    xAxis: { type: 'value', splitLine: { lineStyle: { color: '#2b3139' } }, axisLabel: { color: '#848e9c', formatter: (v: number) => (v / 1e8).toFixed(0) + '亿' } },
    yAxis: { type: 'category', data: s.map(i => i.sectorName), axisLabel: { color: '#eaecef', fontSize: 11 }, axisTick: { show: false }, axisLine: { lineStyle: { color: '#2b3139' } } },
    series: [{ type: 'bar', data: s.map(i => ({ value: i.netInflow, itemStyle: { color: i.netInflow >= 0 ? '#f6465d' : '#0ecb81' } })), barWidth: 20 }]
  }
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <div class="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-7 gap-4">
      <MetricCard label="成交额" :value="formatAmount(data.totalAmount)" value-class="text-text-primary" />
      <MetricCard label="分位数" :value="data.amountPercentile + '%'" value-class="text-accent-yellow" />
      <MetricCard label="5日变化" :value="formatPct(data.amountChange5d)" :value-class="pctColorClass(data.amountChange5d)" />
      <MetricCard label="20日变化" :value="formatPct(data.amountChange20d)" :value-class="pctColorClass(data.amountChange20d)" />
      <MetricCard label="北向净流入" :value="formatAmount(data.northMoneyNetInflow)" :value-class="pctColorClass(data.northMoneyNetInflow)" />
      <MetricCard label="融资余额" :value="formatAmount(data.marginBalance)" value-class="text-text-primary" />
      <MetricCard label="供需判断" :value="data.supplyDemandLabel" value-class="text-accent-yellow" />
    </div>
    <Card><h3 class="text-sm font-medium text-text-secondary mb-4">资金趋势 (30日)</h3><VChart :option="getTrendOption()" style="height: 320px" autoresize /></Card>
    <Card><h3 class="text-sm font-medium text-text-secondary mb-4">板块资金流向</h3><VChart :option="getSectorOption()" style="height: 280px" autoresize /></Card>
  </div>
</template>
