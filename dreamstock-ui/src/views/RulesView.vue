<script setup lang="ts">
import { ref } from 'vue'
import Card from '@/components/ui/Card.vue'
import Badge from '@/components/ui/Badge.vue'

const ruleSets = ref([
  {
    id: 1, ruleSetCode: 'EMOTION_DEFAULT', ruleSetName: '默认情绪规则集',
    ruleDomain: 'EMOTION', versionNo: 'V1.0', status: 1, description: '默认市场情绪评分规则集'
  },
  {
    id: 2, ruleSetCode: 'THEME_DEFAULT', ruleSetName: '默认主题规则集',
    ruleDomain: 'THEME', versionNo: 'V1.0', status: 1, description: '默认市场主题评分规则集'
  },
  {
    id: 3, ruleSetCode: 'LEADER_DEFAULT', ruleSetName: '默认龙头规则集',
    ruleDomain: 'LEADER', versionNo: 'V1.0', status: 1, description: '默认龙头识别评分规则集'
  },
  {
    id: 4, ruleSetCode: 'TRADE_DEFAULT', ruleSetName: '默认交易规则集',
    ruleDomain: 'TRADE', versionNo: 'V1.0', status: 1, description: '默认交易信号规则集'
  }
])

const selectedDomain = ref<string | null>(null)

function domainLabel(domain: string): string {
  const map: Record<string, string> = {
    EMOTION: '情绪',
    THEME: '主题',
    LEADER: '龙头',
    TRADE: '交易'
  }
  return map[domain] || domain
}

function domainBadgeVariant(domain: string): 'up' | 'warning' | 'info' | 'default' {
  const map: Record<string, 'up' | 'warning' | 'info' | 'default'> = {
    EMOTION: 'warning',
    THEME: 'info',
    LEADER: 'up',
    TRADE: 'default'
  }
  return map[domain] || 'default'
}

const filteredRuleSets = ref(ruleSets.value)

function filterByDomain(domain: string | null) {
  selectedDomain.value = domain
  if (domain) {
    filteredRuleSets.value = ruleSets.value.filter(r => r.ruleDomain === domain)
  } else {
    filteredRuleSets.value = ruleSets.value
  }
}
</script>

<template>
  <div class="space-y-6">
    <!-- Domain Filter -->
    <div class="flex items-center gap-2">
      <button
        :class="[
          'px-3 py-1.5 text-xs rounded-lg transition-colors',
          !selectedDomain ? 'bg-accent-yellow/10 text-accent-yellow' : 'bg-bg-secondary text-text-secondary hover:text-text-primary'
        ]"
        @click="filterByDomain(null)"
      >
        全部
      </button>
      <button
        v-for="d in ['EMOTION', 'THEME', 'LEADER', 'TRADE']"
        :key="d"
        :class="[
          'px-3 py-1.5 text-xs rounded-lg transition-colors',
          selectedDomain === d ? 'bg-accent-yellow/10 text-accent-yellow' : 'bg-bg-secondary text-text-secondary hover:text-text-primary'
        ]"
        @click="filterByDomain(d)"
      >
        {{ domainLabel(d) }}
      </button>
    </div>

    <!-- Rule Set Cards -->
    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
      <Card v-for="rule in filteredRuleSets" :key="rule.id" class="hover:border-border-secondary transition-colors cursor-pointer">
        <div class="flex justify-between items-start mb-3">
          <div>
            <h3 class="text-sm font-bold text-text-primary">{{ rule.ruleSetName }}</h3>
            <p class="text-xs text-text-tertiary mt-0.5">{{ rule.ruleSetCode }}</p>
          </div>
          <Badge :variant="domainBadgeVariant(rule.ruleDomain)">{{ domainLabel(rule.ruleDomain) }}</Badge>
        </div>
        <p class="text-xs text-text-secondary mb-3">{{ rule.description }}</p>
        <div class="flex items-center justify-between text-xs">
          <span class="text-text-tertiary">版本: {{ rule.versionNo }}</span>
          <Badge :variant="rule.status === 1 ? 'info' : 'default'">
            {{ rule.status === 1 ? '启用' : '停用' }}
          </Badge>
        </div>
      </Card>
    </div>
  </div>
</template>
