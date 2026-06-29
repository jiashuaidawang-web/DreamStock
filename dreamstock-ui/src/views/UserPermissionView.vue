<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import MetricCard from '@/components/ui/MetricCard.vue'
import Badge from '@/components/ui/Badge.vue'
import type { UserPermissionData } from '@/api/userPermission'
import { fetchUserPermissions } from '@/api/userPermission'

const data = ref<UserPermissionData | null>(null)
const loading = ref(true)

onMounted(async () => {
  try { const res = await fetchUserPermissions(); data.value = (res as { data: UserPermissionData }).data } catch { data.value = null }
  loading.value = false
})

function roleVariant(role: string): 'up' | 'warning' | 'info' | 'default' {
  if (role === 'ADMIN') return 'up'
  if (role === 'ANALYST') return 'warning'
  if (role === 'TRADER') return 'info'
  return 'default'
}

function statusVariant(status: number): 'up' | 'down' | 'default' {
  if (status === 1) return 'up'
  if (status === 0) return 'down'
  return 'default'
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64"><p class="text-text-secondary">Loading...</p></div>
  <div v-else-if="data" class="space-y-6">
    <div class="grid grid-cols-2 md:grid-cols-3 gap-4">
      <MetricCard label="用户总数" :value="data.users.length" value-class="text-text-primary" />
      <MetricCard label="审计日志" :value="data.auditLogs.length" value-class="text-accent-yellow" />
      <MetricCard label="角色分布" :value="[...new Set(data.users.map(u => u.role))].join(', ')" value-class="text-accent-blue" />
    </div>
    <Card>
      <div class="flex justify-between items-center mb-4">
        <h3 class="text-sm font-medium text-text-secondary">用户管理</h3>
        <button class="px-4 py-1.5 bg-accent-yellow/10 text-accent-yellow text-sm rounded-lg hover:bg-accent-yellow/20 transition-colors">+ 添加用户</button>
      </div>
      <div class="overflow-x-auto">
        <table class="w-full text-sm">
          <thead><tr class="border-b border-border-primary text-text-tertiary text-left">
            <th class="py-2 px-2 font-medium">用户名</th><th class="py-2 px-2 font-medium">显示名</th>
            <th class="py-2 px-2 font-medium">角色</th><th class="py-2 px-2 font-medium">状态</th>
            <th class="py-2 px-2 font-medium">最后登录</th><th class="py-2 px-2 font-medium">权限</th>
            <th class="py-2 px-2 font-medium text-center">操作</th>
          </tr></thead>
          <tbody>
            <tr v-for="u in data.users" :key="u.id" class="border-b border-border-primary/50 hover:bg-bg-hover transition-colors">
              <td class="py-2 px-2 text-text-primary font-medium font-mono text-xs">{{ u.username }}</td>
              <td class="py-2 px-2 text-text-primary">{{ u.displayName }}</td>
              <td class="py-2 px-2"><Badge :variant="roleVariant(u.role)">{{ u.role }}</Badge></td>
              <td class="py-2 px-2"><Badge :variant="statusVariant(u.status)">{{ u.status === 1 ? '活跃' : '禁用' }}</Badge></td>
              <td class="py-2 px-2 text-text-secondary text-xs">{{ u.lastLoginTime }}</td>
              <td class="py-2 px-2"><div class="flex flex-wrap gap-1"><Badge v-for="p in u.permissions" :key="p" variant="info">{{ p }}</Badge></div></td>
              <td class="py-2 px-2 text-center">
                <button class="text-xs text-accent-blue hover:text-accent-cyan mr-2">编辑</button>
                <button class="text-xs text-text-tertiary hover:text-up">权限</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </Card>
    <Card>
      <h3 class="text-sm font-medium text-text-secondary mb-4">操作审计日志</h3>
      <div class="overflow-x-auto">
        <table class="w-full text-sm">
          <thead><tr class="border-b border-border-primary text-text-tertiary text-left">
            <th class="py-2 px-2 font-medium">时间</th><th class="py-2 px-2 font-medium">操作人</th>
            <th class="py-2 px-2 font-medium">操作</th><th class="py-2 px-2 font-medium">目标</th>
            <th class="py-2 px-2 font-medium">IP</th>
          </tr></thead>
          <tbody>
            <tr v-for="log in data.auditLogs" :key="log.id" class="border-b border-border-primary/50 hover:bg-bg-hover transition-colors">
              <td class="py-2 px-2 text-text-secondary text-xs font-mono">{{ log.operateTime }}</td>
              <td class="py-2 px-2 text-text-primary font-medium">{{ log.operator }}</td>
              <td class="py-2 px-2"><Badge :variant="log.action.includes('DELETE') ? 'down' : log.action.includes('CREATE') ? 'up' : 'info'">{{ log.action }}</Badge></td>
              <td class="py-2 px-2 text-text-secondary text-xs">{{ log.target }}</td>
              <td class="py-2 px-2 text-text-tertiary font-mono text-xs">{{ log.ipAddress }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </Card>
  </div>
</template>
