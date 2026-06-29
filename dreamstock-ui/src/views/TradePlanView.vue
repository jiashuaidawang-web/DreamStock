<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import Badge from '@/components/ui/Badge.vue'
import type { TradePlanData } from '@/api/tradePlan'
import { fetchTradePlans } from '@/api/tradePlan'

const data = ref<TradePlanData | null>(null)
const loading = ref(true)
const expandedId = ref<number | null>(null)

onMounted(async () => {
  try { const res = await fetchTradePlans(); data.value = (res as { data: TradePlanData }).data } catch { data.value = null }
  loading.value = false
})

function statusVariant(status: string): 'up' | 'info' | 'warning' | 'default' {
  if (status === 'ACTIVE') return 'warning'
  if (status === 'COMPLETED') return 'up'
  return 'default'
}

function statusLabel(status: string): string {
  if (status === 'ACTIVE') return '执行中'
  if (status === 'COMPLETED') return '已完成'
  return status
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <div class="flex justify-between items-center">
      <h2 class="text-text-primary font-medium">交易计划列表</h2>
      <button class="px-4 py-2 bg-accent-yellow/10 text-accent-yellow text-sm rounded-lg hover:bg-accent-yellow/20 transition-colors">+ 新建计划</button>
    </div>
    <div class="space-y-4">
      <Card v-for="plan in data.plans" :key="plan.id" class="cursor-pointer hover:border-border-secondary transition-colors" @click="expandedId = expandedId === plan.id ? null : plan.id">
        <div class="flex items-start justify-between mb-3">
          <div class="flex items-center gap-3">
            <span class="text-text-primary font-medium">{{ plan.planDate }}</span>
            <Badge :variant="statusVariant(plan.status)">{{ statusLabel(plan.status) }}</Badge>
            <Badge variant="info">{{ plan.systemStage }}</Badge>
          </div>
          <span class="text-text-tertiary text-xs">仓位上限: {{ (plan.positionLimit * 100).toFixed(0) }}%</span>
        </div>
        <p class="text-sm text-text-secondary mb-2">{{ plan.marketStageJudgement }}</p>
        <div class="flex flex-wrap gap-2 mb-2">
          <Badge v-for="theme in plan.focusThemes" :key="theme" variant="info">{{ theme }}</Badge>
        </div>
        <div v-if="expandedId === plan.id" class="mt-4 pt-4 border-t border-border-primary space-y-4">
          <div><p class="text-xs text-text-tertiary mb-1">关注标的</p>
            <div class="space-y-2">
              <div v-for="s in plan.focusStocks" :key="s.stockCode" class="flex items-center gap-3 p-2 rounded bg-bg-secondary">
                <span class="text-text-primary font-medium text-sm">{{ s.stockName }}</span>
                <span class="text-text-tertiary text-xs">{{ s.stockCode }}</span>
                <span class="text-text-secondary text-xs flex-1">{{ s.reason }}</span>
              </div>
            </div>
          </div>
          <div class="grid grid-cols-1 lg:grid-cols-3 gap-4">
            <div><p class="text-xs text-text-tertiary mb-1">入场条件</p><p class="text-sm text-text-primary">{{ plan.entryCondition }}</p></div>
            <div><p class="text-xs text-text-tertiary mb-1">止损条件</p><p class="text-sm text-up">{{ plan.stopLossCondition }}</p></div>
            <div><p class="text-xs text-text-tertiary mb-1">止盈条件</p><p class="text-sm text-down">{{ plan.takeProfitCondition }}</p></div>
          </div>
          <div><p class="text-xs text-text-tertiary mb-1">风险计划</p><p class="text-sm text-accent-orange">{{ plan.riskPlan }}</p></div>
        </div>
      </Card>
    </div>
  </div>
</template>
