<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import Badge from '@/components/ui/Badge.vue'
import type { CaseLibraryData, CaseItem } from '@/api/caseLibrary'
import { fetchCases } from '@/api/caseLibrary'

const data = ref<CaseLibraryData | null>(null)
const loading = ref(true)
const expandedId = ref<number | null>(null)
const filterType = ref('ALL')

onMounted(async () => {
  try { const res = await fetchCases(); data.value = (res as { data: CaseLibraryData }).data } catch { data.value = null }
  loading.value = false
})

const typeFilters = [
  { key: 'ALL', label: '全部' },
  { key: 'SUCCESS', label: '成功' },
  { key: 'FAILURE', label: '失败' },
]

function filteredCases(): CaseItem[] {
  if (!data.value) return []
  if (filterType.value === 'ALL') return data.value.cases
  return data.value.cases.filter(c => c.caseType === filterType.value)
}

function typeVariant(type: string): 'up' | 'down' | 'default' {
  if (type === 'SUCCESS') return 'up'
  if (type === 'FAILURE') return 'down'
  return 'default'
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <div class="flex items-center gap-4">
      <div class="flex items-center gap-2">
        <button v-for="f in typeFilters" :key="f.key" :class="['px-3 py-1.5 text-xs rounded-lg transition-colors', filterType === f.key ? 'bg-accent-yellow/10 text-accent-yellow' : 'bg-bg-secondary text-text-secondary hover:text-text-primary']" @click="filterType = f.key">{{ f.label }}</button>
      </div>
      <span class="text-text-tertiary text-xs">共 {{ filteredCases().length }} 条案例</span>
    </div>
    <div class="space-y-4">
      <Card v-for="c in filteredCases()" :key="c.id" class="cursor-pointer hover:border-border-secondary transition-colors" @click="expandedId = expandedId === c.id ? null : c.id">
        <div class="flex items-start justify-between mb-2">
          <div class="flex items-center gap-3">
            <span class="text-text-primary font-medium">{{ c.caseName }}</span>
            <Badge :variant="typeVariant(c.caseType)">{{ c.caseType }}</Badge>
            <Badge variant="info">{{ c.marketStage }}</Badge>
          </div>
          <span class="text-text-tertiary text-xs">{{ c.startDate }} ~ {{ c.endDate }}</span>
        </div>
        <div class="flex flex-wrap gap-2 mb-2">
          <Badge v-for="tag in c.tags" :key="tag">{{ tag }}</Badge>
        </div>
        <p class="text-sm text-text-secondary">核心标的: {{ c.coreStock }} · 主题: {{ c.mainTheme }} · 情绪变化: {{ c.emotionChange }}</p>
        <div v-if="expandedId === c.id" class="mt-4 pt-4 border-t border-border-primary space-y-3">
          <div><p class="text-xs text-text-tertiary mb-1">结论</p><p class="text-sm text-text-primary">{{ c.conclusion }}</p></div>
          <div><p class="text-xs text-text-tertiary mb-1">可复用规则</p>
            <div class="flex flex-wrap gap-1"><Badge v-for="rule in c.reusableRules" :key="rule" variant="info">{{ rule }}</Badge></div>
          </div>
        </div>
      </Card>
    </div>
  </div>
</template>
