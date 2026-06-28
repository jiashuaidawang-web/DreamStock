<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import {
  LayoutDashboard,
  Trophy,
  NotebookPen,
  Settings,
  ChevronLeft,
  ChevronRight
} from 'lucide-vue-next'

const route = useRoute()
const router = useRouter()
const collapsed = ref(false)

const navItems = [
  { path: '/dashboard', label: '总览工作台', icon: LayoutDashboard },
  { path: '/leader-ladder', label: '龙头竞争梯队', icon: Trophy },
  { path: '/review', label: '复盘日志', icon: NotebookPen },
  { path: '/rules', label: '规则管理', icon: Settings },
]

const currentTitle = computed(() => {
  const item = navItems.find(n => n.path === route.path)
  return item?.label || 'DreamStock'
})
</script>

<template>
  <div class="flex h-screen overflow-hidden">
    <!-- Sidebar -->
    <aside
      :class="[
        'flex flex-col bg-bg-secondary border-r border-border-primary transition-all duration-300',
        collapsed ? 'w-16' : 'w-56'
      ]"
    >
      <!-- Logo -->
      <div class="flex items-center h-14 px-4 border-b border-border-primary">
        <div class="w-8 h-8 rounded-lg bg-accent-yellow flex items-center justify-center font-bold text-bg-primary text-sm">
          DS
        </div>
        <span v-if="!collapsed" class="ml-3 font-bold text-text-primary text-sm tracking-wider">
          DreamStock
        </span>
      </div>

      <!-- Navigation -->
      <nav class="flex-1 py-2 space-y-1 px-2">
        <button
          v-for="item in navItems"
          :key="item.path"
          :class="[
            'w-full flex items-center gap-3 px-3 py-2.5 rounded-lg text-sm transition-colors',
            route.path === item.path
              ? 'bg-accent-yellow/10 text-accent-yellow'
              : 'text-text-secondary hover:bg-bg-hover hover:text-text-primary'
          ]"
          @click="router.push(item.path)"
        >
          <component :is="item.icon" :size="20" />
          <span v-if="!collapsed">{{ item.label }}</span>
        </button>
      </nav>

      <!-- Collapse toggle -->
      <button
        class="flex items-center justify-center h-10 border-t border-border-primary text-text-tertiary hover:text-text-primary"
        @click="collapsed = !collapsed"
      >
        <ChevronLeft v-if="!collapsed" :size="16" />
        <ChevronRight v-else :size="16" />
      </button>
    </aside>

    <!-- Main Content -->
    <main class="flex-1 flex flex-col overflow-hidden">
      <!-- Header -->
      <header class="h-14 flex items-center justify-between px-6 border-b border-border-primary bg-bg-secondary">
        <h1 class="text-base font-semibold">{{ currentTitle }}</h1>
        <div class="flex items-center gap-3 text-text-secondary text-sm">
          <span>A股情绪周期复盘</span>
        </div>
      </header>

      <!-- Page content -->
      <div class="flex-1 overflow-auto p-6 bg-bg-primary">
        <router-view />
      </div>
    </main>
  </div>
</template>
