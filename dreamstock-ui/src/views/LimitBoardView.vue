<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import MetricCard from '@/components/ui/MetricCard.vue'
import Badge from '@/components/ui/Badge.vue'
import { formatAmount, formatPct, pctColorClass } from '@/utils/format'
import type { LimitBoardData, PoolItem } from '@/api/limitBoard'
import { fetchLimitBoard } from '@/api/limitBoard'

const data = ref<LimitBoardData | null>(null)
const loading = ref(true)
const activeTab = ref('limitUp')

onMounted(async () => {
  try { const res = await fetchLimitBoard(); data.value = (res as { data: LimitBoardData }).data } catch { data.value = null }
  loading.value = false
})

const tabs = [
  { key: 'limitUp', label: '涨停股' },
  { key: 'limitDown', label: '跌停股' },
  { key: 'failedBoard', label: '炸板股' },
  { key: 'feedback', label: '昨日反馈' },
]

function getPool(): PoolItem[] {
  if (!data.value) return []
  const map: Record<string, PoolItem[]> = {
    limitUp: data.value.limitUpPool,
    limitDown: data.value.limitDownPool,
    failedBoard: data.value.failedBoardPool,
    feedback: data.value.yesterdayLimitFeedback,
  }
  return map[activeTab.value] || []
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <div class="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-8 gap-4">
      <MetricCard label="首板" :value="data.firstBoardCount" value-class="text-text-primary" />
      <MetricCard label="二板" :value="data.secondBoardCount" value-class="text-accent-yellow" />
      <MetricCard label="三板" :value="data.thirdBoardCount" value-class="text-accent-orange" />
      <MetricCard label="高板(4+)" :value="data.highBoardCount" value-class="text-up" />
      <MetricCard label="跌停" :value="data.limitDownCount" value-class="text-down" />
      <MetricCard label="炸板率" :value="data.failedLimitRate + '%'" value-class="text-accent-orange" />
      <MetricCard label="晋级率" :value="data.promotionRate + '%'" value-class="text-accent-cyan" />
      <MetricCard label="最高板" :value="data.maxBoardHeight + '板'" value-class="text-up" />
    </div>
    <Card>
      <div class="flex items-center gap-2 mb-4">
        <button v-for="tab in tabs" :key="tab.key" :class="['px-3 py-1.5 text-xs rounded-lg transition-colors', activeTab === tab.key ? 'bg-accent-yellow/10 text-accent-yellow' : 'bg-bg-secondary text-text-secondary hover:text-text-primary']" @click="activeTab = tab.key">{{ tab.label }}</button>
      </div>
      <div class="overflow-x-auto">
        <table class="w-full text-sm">
          <thead><tr class="border-b border-border-primary text-text-tertiary text-left">
            <th class="py-2 px-2 font-medium">代码</th><th class="py-2 px-2 font-medium">名称</th>
            <th class="py-2 px-2 font-medium text-right">涨幅</th><th class="py-2 px-2 font-medium text-center">连板</th>
            <th class="py-2 px-2 font-medium">封单时间</th><th class="py-2 px-2 font-medium text-right">封单额</th>
            <th class="py-2 px-2 font-medium text-center">开板次数</th><th class="py-2 px-2 font-medium text-right">换手率</th>
            <th class="py-2 px-2 font-medium">原因</th>
          </tr></thead>
          <tbody>
            <tr v-for="item in getPool()" :key="item.stockCode" class="border-b border-border-primary/50 hover:bg-bg-hover transition-colors">
              <td class="py-2 px-2 text-text-tertiary font-mono text-xs">{{ item.stockCode }}</td>
              <td class="py-2 px-2 text-text-primary font-medium">{{ item.stockName }}</td>
              <td :class="['py-2 px-2 text-right font-mono', pctColorClass(item.pctChg)]">{{ formatPct(item.pctChg) }}</td>
              <td class="py-2 px-2 text-center"><Badge variant="up">{{ item.boardCount }}板</Badge></td>
              <td class="py-2 px-2 text-text-secondary text-xs">{{ item.sealTime }}</td>
              <td class="py-2 px-2 text-right font-mono text-text-secondary">{{ formatAmount(item.sealAmount) }}</td>
              <td class="py-2 px-2 text-center"><span :class="item.openCount > 0 ? 'text-accent-orange' : 'text-text-tertiary'">{{ item.openCount }}</span></td>
              <td class="py-2 px-2 text-right font-mono text-text-secondary">{{ item.turnoverRate }}%</td>
              <td class="py-2 px-2 text-text-secondary text-xs max-w-[150px] truncate">{{ item.reason }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </Card>
  </div>
</template>
