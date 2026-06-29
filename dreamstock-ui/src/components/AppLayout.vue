<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import {
  LayoutDashboard, Activity, TrendingUp, BarChart3, Landmark,
  Newspaper, Grid3x3, ArrowUpDown, Trophy, GitFork, Link2,
  Flame, Target, ClipboardList, NotebookPen, UserCheck,
  BookOpen, PlayCircle, Settings, Database, Shield,
  ChevronLeft, ChevronRight
} from 'lucide-vue-next'

const route = useRoute()
const router = useRouter()
const collapsed = ref(false)

const navSections = [
  { title: '市场总览', items: [
    { path: '/dashboard', label: '总览工作台', icon: LayoutDashboard },
    { path: '/emotion-score', label: '情绪评分', icon: Activity },
    { path: '/cycle-trend', label: '周期趋势', icon: TrendingUp },
    { path: '/market-trend', label: '大盘趋势', icon: BarChart3 },
    { path: '/capital-flow', label: '资金流向', icon: Landmark },
    { path: '/policy-event', label: '政策事件', icon: Newspaper },
  ]},
  { title: '题材龙头', items: [
    { path: '/theme-matrix', label: '题材主线', icon: Grid3x3 },
    { path: '/limit-board', label: '涨停复盘', icon: ArrowUpDown },
    { path: '/leader-ladder', label: '龙头梯队', icon: Trophy },
    { path: '/divergence', label: '分歧一致', icon: GitFork },
    { path: '/stock-correlation', label: '个股关联', icon: Link2 },
    { path: '/trend-dragon', label: '趋势龙头', icon: Flame },
  ]},
  { title: '策略交易', items: [
    { path: '/cycle-strategy', label: '周期策略', icon: Target },
    { path: '/trade-plan', label: '交易计划', icon: ClipboardList },
    { path: '/review', label: '复盘日志', icon: NotebookPen },
    { path: '/discipline-profile', label: '纪律画像', icon: UserCheck },
    { path: '/case-library', label: '案例库', icon: BookOpen },
    { path: '/cycle-replay', label: '周期回放', icon: PlayCircle },
  ]},
  { title: '系统管理', items: [
    { path: '/rules', label: '规则管理', icon: Settings },
    { path: '/data-quality', label: '数据质量', icon: Database },
    { path: '/user-permission', label: '用户权限', icon: Shield },
  ]},
]

const allItems = navSections.flatMap(s => s.items)

const currentTitle = computed(() => {
  const item = allItems.find(n => n.path === route.path)
  return item?.label || 'DreamStock'
})
</script>

<template>
  <div class="flex h-screen overflow-hidden">
    <!-- Sidebar -->
    <aside
      :class="[
        'flex flex-col bg-bg-secondary border-r border-border-primary transition-all duration-300 shrink-0',
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
      <nav class="flex-1 overflow-y-auto py-2 px-2 scrollbar-thin">
        <div v-for="section in navSections" :key="section.title" class="mb-2">
          <div v-if="!collapsed" class="px-3 py-1.5 text-[10px] font-semibold text-text-tertiary uppercase tracking-wider">
            {{ section.title }}
          </div>
          <div v-else class="h-px bg-border-primary mx-2 my-1"></div>
          <button
            v-for="item in section.items"
            :key="item.path"
            :class="[
              'w-full flex items-center gap-3 px-3 py-2 rounded-lg text-sm transition-colors',
              route.path === item.path
                ? 'bg-accent-yellow/10 text-accent-yellow'
                : 'text-text-secondary hover:bg-bg-hover hover:text-text-primary'
            ]"
            @click="router.push(item.path)"
            :title="collapsed ? item.label : undefined"
          >
            <component :is="item.icon" :size="18" class="shrink-0" />
            <span v-if="!collapsed" class="truncate">{{ item.label }}</span>
          </button>
        </div>
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
