<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Card from '@/components/ui/Card.vue'
import MetricCard from '@/components/ui/MetricCard.vue'
import Badge from '@/components/ui/Badge.vue'
import { formatAmount, formatPct, pctColorClass } from '@/utils/format'
import type { DashboardData, EmotionTrendPoint } from '@/api/dashboard'
import { fetchDashboard } from '@/api/dashboard'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { LineChart, BarChart } from 'echarts/charts'
import { GridComponent, TooltipComponent, LegendComponent } from 'echarts/components'
import VChart from 'vue-echarts'

use([CanvasRenderer, LineChart, BarChart, GridComponent, TooltipComponent, LegendComponent])

const dashboard = ref<DashboardData | null>(null)
const loading = ref(true)

const mockData: DashboardData = {
  tradeDate: '2026-06-28',
  emotionSummary: {
    emotionScore: 64,
    emotionStageName: '退潮',
    limitUpCount: 72,
    limitDownCount: 31,
    indexPctChg: 1.24,
    indexName: '上证综合指数'
  },
  marketOverview: {
    upCount: 2856,
    downCount: 1890,
    above5pctCount: 215,
    belowMinus5pctCount: 87,
    totalAmount: 1125600000000,
    consecutiveLimitHigh: 10
  },
  personalPerformance: {
    todayPnl: 12480,
    cumulativeReturnPct: 38.6,
    recentWinRatePct: 62.5
  },
  topThemes: [
    { rank: 1, themeName: '人工智能算力', leaderStockName: '中际旭创', leaderPctChg: 9.98 },
    { rank: 2, themeName: '固态电池', leaderStockName: '三祥新材', leaderPctChg: 10.01 },
    { rank: 3, themeName: '可控核聚变', leaderStockName: '国光电气', leaderPctChg: 7.34 },
    { rank: 4, themeName: '机器人', leaderStockName: '鸣志电器', leaderPctChg: 5.62 },
    { rank: 5, themeName: '低空经济', leaderStockName: '万丰奥威', leaderPctChg: -2.18 },
    { rank: 6, themeName: '光伏设备', leaderStockName: '阳光电源', leaderPctChg: -3.45 }
  ],
  emotionTrend: Array.from({ length: 30 }, (_, i) => {
    const scores = [15, 22, 30, 38, 45, 55, 62, 70, 78, 85, 80, 72, 65, 58, 50, 45, 40, 35, 30, 25, 20, 28, 38, 50, 60, 70, 80, 75, 65, 55]
    const stages = [1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 6, 6, 7, 7, 7, 1, 1, 1, 1, 1, 2, 3, 3, 4, 4, 5, 6, 7, 7]
    const d = new Date('2026-05-29')
    d.setDate(d.getDate() + i)
    return {
      tradeDate: d.toISOString().split('T')[0],
      emotionScore: scores[i],
      emotionStage: stages[i]
    }
  })
}

onMounted(async () => {
  try {
    const res = await fetchDashboard()
    dashboard.value = (res as { data: DashboardData }).data
  } catch {
    dashboard.value = mockData
  }
  loading.value = false
})

const _stageColors: Record<string, string> = {
  '冰点': '#1890ff',
  '修复': '#06b6d4',
  '启动': '#0ecb81',
  '发酵': '#fcd535',
  '高潮': '#f6465d',
  '分歧': '#f97316',
  '退潮': '#a855f7',
  '混沌': '#848e9c'
}
void _stageColors

function getEmotionChartOption() {
  if (!dashboard.value) return {}
  const trend = dashboard.value.emotionTrend
  return {
    backgroundColor: 'transparent',
    grid: { top: 30, right: 20, bottom: 30, left: 50 },
    tooltip: {
      trigger: 'axis',
      backgroundColor: '#1e2329',
      borderColor: '#2b3139',
      textStyle: { color: '#eaecef', fontSize: 12 }
    },
    xAxis: {
      type: 'category',
      data: trend.map((p: EmotionTrendPoint) => p.tradeDate.slice(5)),
      axisLine: { lineStyle: { color: '#2b3139' } },
      axisLabel: { color: '#848e9c', fontSize: 10 },
      axisTick: { show: false }
    },
    yAxis: {
      type: 'value',
      min: 0,
      max: 100,
      splitLine: { lineStyle: { color: '#2b3139' } },
      axisLabel: { color: '#848e9c', fontSize: 10 }
    },
    series: [{
      type: 'line',
      data: trend.map((p: EmotionTrendPoint) => p.emotionScore),
      smooth: true,
      lineStyle: { color: '#fcd535', width: 2 },
      itemStyle: { color: '#fcd535' },
      areaStyle: {
        color: {
          type: 'linear',
          x: 0, y: 0, x2: 0, y2: 1,
          colorStops: [
            { offset: 0, color: 'rgba(252, 213, 53, 0.3)' },
            { offset: 1, color: 'rgba(252, 213, 53, 0)' }
          ]
        }
      },
      symbol: 'none'
    }]
  }
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64">
    <p class="text-text-secondary">Loading...</p>
  </div>

  <div v-else-if="dashboard" class="space-y-6">
    <!-- Top Metrics Row -->
    <div class="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-6 gap-4">
      <MetricCard
        label="情绪分数"
        :value="dashboard.emotionSummary.emotionScore"
        value-class="text-accent-yellow"
        :sub-value="dashboard.emotionSummary.emotionStageName"
      />
      <MetricCard
        label="涨停家数"
        :value="dashboard.emotionSummary.limitUpCount"
        value-class="text-up"
      />
      <MetricCard
        label="跌停家数"
        :value="dashboard.emotionSummary.limitDownCount"
        value-class="text-down"
      />
      <MetricCard
        label="最高连板"
        :value="dashboard.marketOverview.consecutiveLimitHigh + '板'"
        value-class="text-up"
      />
      <MetricCard
        label="成交额"
        :value="formatAmount(dashboard.marketOverview.totalAmount)"
        value-class="text-text-primary"
      />
      <MetricCard
        label="涨跌比"
        :value="dashboard.marketOverview.upCount + ' : ' + dashboard.marketOverview.downCount"
        :value-class="dashboard.marketOverview.upCount > dashboard.marketOverview.downCount ? 'text-up' : 'text-down'"
      />
    </div>

    <!-- Charts and Theme Table Row -->
    <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
      <!-- Emotion Trend Chart -->
      <Card class="lg:col-span-2">
        <h3 class="text-sm font-medium text-text-secondary mb-4">情绪周期趋势 (近30日)</h3>
        <VChart :option="getEmotionChartOption()" style="height: 280px" autoresize />
      </Card>

      <!-- Personal Performance -->
      <Card>
        <h3 class="text-sm font-medium text-text-secondary mb-4">个人表现</h3>
        <div class="space-y-4">
          <div class="flex justify-between items-center py-3 border-b border-border-primary">
            <span class="text-text-secondary text-sm">今日盈亏</span>
            <span :class="[dashboard.personalPerformance.todayPnl >= 0 ? 'text-up' : 'text-down', 'font-bold']">
              {{ dashboard.personalPerformance.todayPnl >= 0 ? '+' : '' }}{{ formatAmount(dashboard.personalPerformance.todayPnl) }}
            </span>
          </div>
          <div class="flex justify-between items-center py-3 border-b border-border-primary">
            <span class="text-text-secondary text-sm">累计收益率</span>
            <span :class="[dashboard.personalPerformance.cumulativeReturnPct >= 0 ? 'text-up' : 'text-down', 'font-bold']">
              {{ formatPct(dashboard.personalPerformance.cumulativeReturnPct) }}
            </span>
          </div>
          <div class="flex justify-between items-center py-3">
            <span class="text-text-secondary text-sm">近期胜率</span>
            <span class="text-accent-yellow font-bold">{{ dashboard.personalPerformance.recentWinRatePct }}%</span>
          </div>
        </div>
      </Card>
    </div>

    <!-- Top Themes Table -->
    <Card>
      <h3 class="text-sm font-medium text-text-secondary mb-4">今日热门主题</h3>
      <div class="overflow-x-auto">
        <table class="w-full text-sm">
          <thead>
            <tr class="border-b border-border-primary text-text-tertiary text-left">
              <th class="py-3 px-2 font-medium">排名</th>
              <th class="py-3 px-2 font-medium">主题</th>
              <th class="py-3 px-2 font-medium">龙头股</th>
              <th class="py-3 px-2 font-medium text-right">龙头涨幅</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="theme in dashboard.topThemes"
              :key="theme.rank"
              class="border-b border-border-primary/50 hover:bg-bg-hover transition-colors"
            >
              <td class="py-3 px-2">
                <Badge :variant="theme.rank <= 3 ? 'up' : 'default'">{{ theme.rank }}</Badge>
              </td>
              <td class="py-3 px-2 text-text-primary font-medium">{{ theme.themeName }}</td>
              <td class="py-3 px-2 text-text-secondary">{{ theme.leaderStockName }}</td>
              <td :class="['py-3 px-2 text-right font-mono font-medium', pctColorClass(theme.leaderPctChg)]">
                {{ formatPct(theme.leaderPctChg) }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </Card>
  </div>
</template>
