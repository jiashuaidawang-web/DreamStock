<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import Badge from '@/components/ui/Badge.vue'
import { formatAmount, formatPct, pctColorClass } from '@/utils/format'
import type { TrendDragonData } from '@/api/trendDragon'
import { fetchTrendDragons } from '@/api/trendDragon'

const data = ref<TrendDragonData | null>(null)
const loading = ref(true)

onMounted(async () => {
  try { const res = await fetchTrendDragons(); data.value = (res as { data: TrendDragonData }).data } catch { data.value = null }
  loading.value = false
})

function riskVariant(tag: string): 'up' | 'warning' | 'down' | 'default' {
  if (tag === '低风险') return 'up'
  if (tag === '中风险') return 'warning'
  return 'down'
}

function scoreColor(score: number): string {
  if (score >= 80) return 'text-up'
  if (score >= 60) return 'text-accent-yellow'
  if (score >= 40) return 'text-accent-blue'
  return 'text-down'
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
      <Card v-for="stock in data.stocks" :key="stock.stockCode" class="hover:border-border-secondary transition-colors">
        <div class="flex justify-between items-start mb-3">
          <div>
            <span class="font-bold text-text-primary">{{ stock.stockName }}</span>
            <span class="text-text-tertiary text-xs ml-2">{{ stock.stockCode }}</span>
          </div>
          <Badge :variant="riskVariant(stock.riskTag)">{{ stock.riskTag }}</Badge>
        </div>
        <div class="grid grid-cols-2 gap-3 mb-3">
          <div><p class="text-xs text-text-tertiary">现价</p><p class="text-text-primary font-mono font-medium">{{ stock.close }}</p></div>
          <div><p class="text-xs text-text-tertiary">涨跌</p><p :class="['font-mono font-medium', pctColorClass(stock.pctChg)]">{{ formatPct(stock.pctChg) }}</p></div>
          <div><p class="text-xs text-text-tertiary">趋势分</p><p :class="['font-mono font-bold', scoreColor(stock.trendScore)]">{{ stock.trendScore }}</p></div>
          <div><p class="text-xs text-text-tertiary">RS排名</p><p class="text-accent-yellow font-mono">前{{ stock.rsRank }}%</p></div>
        </div>
        <div class="flex flex-wrap gap-2 mb-3">
          <Badge variant="info">{{ stock.maStatus }}</Badge>
          <Badge :variant="stock.breakoutStatus.includes('突破') ? 'up' : stock.breakoutStatus.includes('破位') ? 'down' : 'default'">{{ stock.breakoutStatus }}</Badge>
          <Badge>{{ stock.weeklyTrendStatus }}</Badge>
        </div>
        <div class="flex justify-between text-xs text-text-secondary">
          <span>{{ stock.industrySector }}</span>
          <span>市值: {{ formatAmount(stock.totalMarketValue) }}</span>
          <span>量比: {{ stock.volumeRatio }}x</span>
        </div>
      </Card>
    </div>
  </div>
</template>
