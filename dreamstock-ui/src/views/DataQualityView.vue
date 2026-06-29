<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import MetricCard from '@/components/ui/MetricCard.vue'
import Badge from '@/components/ui/Badge.vue'
import type { DataQualityData } from '@/api/dataQuality'
import { fetchDataQuality } from '@/api/dataQuality'

const data = ref<DataQualityData | null>(null)
const loading = ref(true)

onMounted(async () => {
  try { const res = await fetchDataQuality(); data.value = (res as { data: DataQualityData }).data } catch { data.value = null }
  loading.value = false
})

function statusVariant(status: string): 'up' | 'down' | 'warning' | 'info' | 'default' {
  if (status === 'SUCCESS' || status === 'PASS') return 'up'
  if (status === 'RUNNING') return 'info'
  if (status === 'WARNING' || status === 'WARN') return 'warning'
  if (status === 'FAIL' || status === 'ERROR') return 'down'
  return 'default'
}

function statusLabel(status: string): string {
  const map: Record<string, string> = { SUCCESS: '成功', RUNNING: '运行中', FAIL: '失败', ERROR: '异常', PASS: '通过', WARN: '警告', WARNING: '警告' }
  return map[status] || status
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <div class="grid grid-cols-2 md:grid-cols-3 gap-4">
      <MetricCard label="总任务数" :value="data.jobs.length" value-class="text-text-primary" />
      <MetricCard label="质量检查数" :value="data.qualityChecks.length" value-class="text-accent-yellow" />
      <MetricCard label="整体状态" :value="data.overallHealthStatus" :value-class="data.overallHealthStatus === 'HEALTHY' ? 'text-up' : 'text-down'" />
    </div>
    <Card>
      <h3 class="text-sm font-medium text-text-secondary mb-4">数据任务状态</h3>
      <div class="overflow-x-auto">
        <table class="w-full text-sm">
          <thead><tr class="border-b border-border-primary text-text-tertiary text-left">
            <th class="py-2 px-2 font-medium">任务名</th><th class="py-2 px-2 font-medium">类型</th>
            <th class="py-2 px-2 font-medium">状态</th><th class="py-2 px-2 font-medium text-right">影响行数</th>
            <th class="py-2 px-2 font-medium">最后运行</th><th class="py-2 px-2 font-medium">下次计划</th>
            <th class="py-2 px-2 font-medium">备注</th>
          </tr></thead>
          <tbody>
            <tr v-for="job in data.jobs" :key="job.id" class="border-b border-border-primary/50 hover:bg-bg-hover transition-colors">
              <td class="py-2 px-2 text-text-primary font-medium">{{ job.jobName }}</td>
              <td class="py-2 px-2"><Badge variant="info">{{ job.jobType }}</Badge></td>
              <td class="py-2 px-2"><Badge :variant="statusVariant(job.lastRunStatus)">{{ statusLabel(job.lastRunStatus) }}</Badge></td>
              <td class="py-2 px-2 text-right font-mono text-text-primary">{{ job.lastRowsAffected.toLocaleString() }}</td>
              <td class="py-2 px-2 text-text-secondary text-xs">{{ job.lastRunTime }}</td>
              <td class="py-2 px-2 text-text-secondary text-xs">{{ job.nextScheduledTime }}</td>
              <td class="py-2 px-2 text-text-tertiary text-xs max-w-[150px] truncate">{{ job.errorMessage || '-' }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </Card>
    <Card>
      <h3 class="text-sm font-medium text-text-secondary mb-4">数据质量检查结果</h3>
      <div class="overflow-x-auto">
        <table class="w-full text-sm">
          <thead><tr class="border-b border-border-primary text-text-tertiary text-left">
            <th class="py-2 px-2 font-medium">检查目标</th><th class="py-2 px-2 font-medium">类型</th>
            <th class="py-2 px-2 font-medium">条件</th><th class="py-2 px-2 font-medium">结果</th>
            <th class="py-2 px-2 font-medium text-right">实际值</th><th class="py-2 px-2 font-medium text-right">阈值</th>
            <th class="py-2 px-2 font-medium">检查时间</th>
          </tr></thead>
          <tbody>
            <tr v-for="check in data.qualityChecks" :key="check.id" class="border-b border-border-primary/50 hover:bg-bg-hover transition-colors">
              <td class="py-2 px-2 text-text-primary font-medium">{{ check.checkTarget }}</td>
              <td class="py-2 px-2"><Badge variant="info">{{ check.checkType }}</Badge></td>
              <td class="py-2 px-2 text-text-secondary text-xs">{{ check.checkCondition }}</td>
              <td class="py-2 px-2"><Badge :variant="statusVariant(check.resultStatus)">{{ statusLabel(check.resultStatus) }}</Badge></td>
              <td class="py-2 px-2 text-right font-mono" :class="check.actualValue > check.thresholdValue ? 'text-accent-orange' : 'text-text-primary'">{{ check.actualValue }}</td>
              <td class="py-2 px-2 text-right font-mono text-text-tertiary">{{ check.thresholdValue }}</td>
              <td class="py-2 px-2 text-text-secondary text-xs">{{ check.checkTime }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </Card>
  </div>
</template>
