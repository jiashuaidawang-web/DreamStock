<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import Badge from '@/components/ui/Badge.vue'
import type { CycleStrategyData } from '@/api/cycleStrategy'
import { fetchCycleStrategy } from '@/api/cycleStrategy'

const data = ref<CycleStrategyData | null>(null)
const loading = ref(true)

const stageColors: Record<string, string> = {
  '冰点': '#1890ff', '修复': '#06b6d4', '启动': '#0ecb81', '发酵': '#fcd535',
  '高潮': '#f6465d', '分歧': '#f97316', '退潮': '#a855f7'
}

onMounted(async () => {
  try { const res = await fetchCycleStrategy(); data.value = (res as { data: CycleStrategyData }).data } catch { data.value = null }
  loading.value = false
})
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <Card>
      <div class="flex items-center gap-4">
        <span class="text-text-secondary text-sm">当前阶段:</span>
        <span class="text-2xl font-bold text-accent-yellow">{{ data.currentStageName }}</span>
      </div>
    </Card>
    <div class="space-y-4">
      <Card v-for="s in data.strategies" :key="s.stage" :class="'transition-all ' + (s.current ? 'ring-2 ring-accent-yellow' : '')">
        <div class="flex items-center justify-between mb-3">
          <div class="flex items-center gap-3">
            <div class="w-4 h-4 rounded-full" :style="{ backgroundColor: stageColors[s.stageName] || '#848e9c' }"></div>
            <span class="text-lg font-bold text-text-primary">{{ s.stageName }}</span>
            <Badge v-if="s.current" variant="warning">当前阶段</Badge>
          </div>
        </div>
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-4">
          <div>
            <p class="text-xs text-text-tertiary mb-1">关注重点</p>
            <p class="text-sm text-text-primary">{{ s.focusPoint }}</p>
          </div>
          <div>
            <p class="text-xs text-text-tertiary mb-1">仓位建议</p>
            <p class="text-sm text-accent-yellow">{{ s.positionAdvice }}</p>
          </div>
        </div>
        <div class="mt-3">
          <p class="text-xs text-text-tertiary mb-1">筛选规则</p>
          <div class="flex flex-wrap gap-2">
            <Badge v-for="(rule, i) in s.filterRules" :key="i" variant="info">{{ rule }}</Badge>
          </div>
        </div>
        <div class="mt-3">
          <p class="text-xs text-text-tertiary mb-1">风险提示</p>
          <p class="text-sm text-accent-orange">{{ s.riskAdvice }}</p>
        </div>
        <div v-if="s.candidateStocks.length > 0" class="mt-3">
          <p class="text-xs text-text-tertiary mb-1">候选标的</p>
          <div class="flex flex-wrap gap-2">
            <Badge v-for="(stock, i) in s.candidateStocks" :key="i" variant="up">{{ stock }}</Badge>
          </div>
        </div>
      </Card>
    </div>
  </div>
</template>
