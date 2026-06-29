<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import Badge from '@/components/ui/Badge.vue'
import { formatPct, pctColorClass } from '@/utils/format'
import type { PolicyEventData, EventItem } from '@/api/policyEvent'
import { fetchPolicyEvents } from '@/api/policyEvent'

const data = ref<PolicyEventData | null>(null)
const loading = ref(true)
const filterType = ref<string | null>(null)
const expandedId = ref<number | null>(null)

const eventTypes = [
  { key: 'POLICY', label: '政策', variant: 'up' as const },
  { key: 'REGULATORY', label: '监管', variant: 'warning' as const },
  { key: 'INDUSTRY', label: '产业', variant: 'info' as const },
  { key: 'EXTERNAL', label: '外部', variant: 'default' as const }
]

onMounted(async () => {
  try { const res = await fetchPolicyEvents(); data.value = (res as { data: PolicyEventData }).data } catch { data.value = null }
  loading.value = false
})

function filteredEvents(): EventItem[] {
  if (!data.value) return []
  if (!filterType.value) return data.value.events
  return data.value.events.filter(e => e.eventType === filterType.value)
}

function eventTypeVariant(type: string): 'up' | 'warning' | 'info' | 'default' {
  const found = eventTypes.find(t => t.key === type)
  return found ? found.variant : 'default'
}

function eventTypeLabel(type: string): string {
  const found = eventTypes.find(t => t.key === type)
  return found ? found.label : type
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <div class="flex items-center gap-2">
      <button :class="['px-3 py-1.5 text-xs rounded-lg transition-colors', !filterType ? 'bg-accent-yellow/10 text-accent-yellow' : 'bg-bg-secondary text-text-secondary hover:text-text-primary']" @click="filterType = null">全部</button>
      <button v-for="t in eventTypes" :key="t.key" :class="['px-3 py-1.5 text-xs rounded-lg transition-colors', filterType === t.key ? 'bg-accent-yellow/10 text-accent-yellow' : 'bg-bg-secondary text-text-secondary hover:text-text-primary']" @click="filterType = t.key">{{ t.label }}</button>
    </div>
    <div class="space-y-3">
      <Card v-for="evt in filteredEvents()" :key="evt.id" class="cursor-pointer hover:border-border-secondary transition-colors" @click="expandedId = expandedId === evt.id ? null : evt.id">
        <div class="flex items-start justify-between mb-2">
          <div class="flex items-center gap-3">
            <Badge :variant="eventTypeVariant(evt.eventType)">{{ eventTypeLabel(evt.eventType) }}</Badge>
            <span class="text-text-primary font-medium text-sm">{{ evt.title }}</span>
          </div>
          <div class="flex items-center gap-2">
            <Badge :variant="evt.impactDirection === 'UP' ? 'up' : 'down'">{{ evt.impactDirection === 'UP' ? '利好' : '利空' }}</Badge>
            <span class="text-text-tertiary text-xs">{{ evt.eventDate }}</span>
          </div>
        </div>
        <div class="flex items-center gap-4 text-xs">
          <span class="text-text-secondary">影响范围: {{ evt.impactScope }}</span>
          <span :class="pctColorClass(evt.return1d)">1日: {{ formatPct(evt.return1d) }}</span>
          <span :class="pctColorClass(evt.return3d)">3日: {{ formatPct(evt.return3d) }}</span>
          <span :class="pctColorClass(evt.return5d)">5日: {{ formatPct(evt.return5d) }}</span>
          <Badge :variant="evt.verificationStatus === '已验证' ? 'info' : 'warning'">{{ evt.verificationStatus }}</Badge>
        </div>
        <div v-if="expandedId === evt.id" class="mt-3 pt-3 border-t border-border-primary">
          <p class="text-text-secondary text-sm mb-2">{{ evt.content }}</p>
          <div class="flex flex-wrap gap-2">
            <Badge v-for="theme in evt.relatedThemes" :key="theme" variant="info">{{ theme }}</Badge>
          </div>
        </div>
      </Card>
    </div>
  </div>
</template>
