import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', redirect: '/dashboard' },
    { path: '/dashboard', name: 'Dashboard', component: () => import('@/views/DashboardView.vue'), meta: { title: '总览工作台' } },
    { path: '/emotion-score', name: 'EmotionScore', component: () => import('@/views/EmotionScoreView.vue'), meta: { title: '情绪评分' } },
    { path: '/cycle-trend', name: 'CycleTrend', component: () => import('@/views/CycleTrendView.vue'), meta: { title: '周期趋势' } },
    { path: '/market-trend', name: 'MarketTrend', component: () => import('@/views/MarketTrendView.vue'), meta: { title: '大盘趋势' } },
    { path: '/capital-flow', name: 'CapitalFlow', component: () => import('@/views/CapitalFlowView.vue'), meta: { title: '资金流向' } },
    { path: '/policy-event', name: 'PolicyEvent', component: () => import('@/views/PolicyEventView.vue'), meta: { title: '政策事件' } },
    { path: '/theme-matrix', name: 'ThemeMatrix', component: () => import('@/views/ThemeMatrixView.vue'), meta: { title: '题材主线' } },
    { path: '/limit-board', name: 'LimitBoard', component: () => import('@/views/LimitBoardView.vue'), meta: { title: '涨停复盘' } },
    { path: '/leader-ladder', name: 'LeaderLadder', component: () => import('@/views/LeaderLadderView.vue'), meta: { title: '龙头梯队' } },
    { path: '/divergence', name: 'Divergence', component: () => import('@/views/DivergenceView.vue'), meta: { title: '分歧一致' } },
    { path: '/stock-correlation', name: 'StockCorrelation', component: () => import('@/views/StockCorrelationView.vue'), meta: { title: '个股关联' } },
    { path: '/trend-dragon', name: 'TrendDragon', component: () => import('@/views/TrendDragonView.vue'), meta: { title: '趋势龙头' } },
    { path: '/cycle-strategy', name: 'CycleStrategy', component: () => import('@/views/CycleStrategyView.vue'), meta: { title: '周期策略' } },
    { path: '/trade-plan', name: 'TradePlan', component: () => import('@/views/TradePlanView.vue'), meta: { title: '交易计划' } },
    { path: '/review', name: 'Review', component: () => import('@/views/ReviewView.vue'), meta: { title: '复盘日志' } },
    { path: '/discipline-profile', name: 'DisciplineProfile', component: () => import('@/views/DisciplineProfileView.vue'), meta: { title: '纪律画像' } },
    { path: '/case-library', name: 'CaseLibrary', component: () => import('@/views/CaseLibraryView.vue'), meta: { title: '案例库' } },
    { path: '/cycle-replay', name: 'CycleReplay', component: () => import('@/views/CycleReplayView.vue'), meta: { title: '周期回放' } },
    { path: '/rules', name: 'Rules', component: () => import('@/views/RulesView.vue'), meta: { title: '规则管理' } },
    { path: '/data-quality', name: 'DataQuality', component: () => import('@/views/DataQualityView.vue'), meta: { title: '数据质量' } },
    { path: '/user-permission', name: 'UserPermission', component: () => import('@/views/UserPermissionView.vue'), meta: { title: '用户权限' } },
  ]
})

export default router
