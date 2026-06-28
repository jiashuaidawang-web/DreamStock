import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      redirect: '/dashboard'
    },
    {
      path: '/dashboard',
      name: 'Dashboard',
      component: () => import('@/views/DashboardView.vue'),
      meta: { title: '总览工作台', icon: 'layout-dashboard' }
    },
    {
      path: '/leader-ladder',
      name: 'LeaderLadder',
      component: () => import('@/views/LeaderLadderView.vue'),
      meta: { title: '龙头竞争梯队', icon: 'trophy' }
    },
    {
      path: '/review',
      name: 'Review',
      component: () => import('@/views/ReviewView.vue'),
      meta: { title: '复盘日志', icon: 'notebook-pen' }
    },
    {
      path: '/rules',
      name: 'Rules',
      component: () => import('@/views/RulesView.vue'),
      meta: { title: '规则管理', icon: 'settings' }
    }
  ]
})

export default router
