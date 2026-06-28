<script setup lang="ts">
import { ref, onMounted } from 'vue'
import MetricCard from '@/components/ui/MetricCard.vue'
import Badge from '@/components/ui/Badge.vue'
import { formatAmount, formatPct, pctColorClass } from '@/utils/format'
import type { LeaderLadderData } from '@/api/leaderLadder'
import { fetchLeaderLadder } from '@/api/leaderLadder'

const data = ref<LeaderLadderData | null>(null)
const loading = ref(true)

const tierBorderColors: Record<number, string> = {
  10: 'border-l-[#f6465d]',
  9: 'border-l-[#f97316]',
  8: 'border-l-[#fcd535]',
  7: 'border-l-[#fde68a]',
  6: 'border-l-[#0ecb81]',
  5: 'border-l-[#06b6d4]',
  4: 'border-l-[#1890ff]',
  3: 'border-l-[#a855f7]',
  2: 'border-l-[#848e9c]',
}

const tierBgColors: Record<number, string> = {
  10: 'bg-[#f6465d]/5',
  9: 'bg-[#f97316]/5',
  8: 'bg-[#fcd535]/5',
  7: 'bg-[#fde68a]/5',
  6: 'bg-[#0ecb81]/5',
  5: 'bg-[#06b6d4]/5',
  4: 'bg-[#1890ff]/5',
  3: 'bg-[#a855f7]/5',
  2: 'bg-[#848e9c]/5',
}

const mockData: LeaderLadderData = {
  tradeDate: '2026-06-28',
  maxConsecutiveBoard: 10,
  totalBidAmount: 2730000000,
  hotStockCount: 9,
  tiers: [
    {
      boardCount: 10, stockCount: 1, totalBidAmount: 382000000,
      leaders: [{ stockCode: '688981', stockName: '中芯国际', leaderRoleName: '市场总龙', statusName: '加速', bidAmount: 382000000, pctChg: 10.0, industrySector: '半导体国产化', riskLevel: 2 }]
    },
    {
      boardCount: 9, stockCount: 2, totalBidAmount: 355000000,
      leaders: [
        { stockCode: '002230', stockName: '科大讯飞', leaderRoleName: '板块龙头', statusName: '确认', bidAmount: 145000000, pctChg: 9.8, industrySector: '人工智能/大模型', riskLevel: 1 },
        { stockCode: '300750', stockName: '宁德时代', leaderRoleName: '趋势龙头', statusName: '确认', bidAmount: 210000000, pctChg: 9.2, industrySector: '新能源/固态电池', riskLevel: 1 }
      ]
    },
    {
      boardCount: 8, stockCount: 2, totalBidAmount: 283000000,
      leaders: [
        { stockCode: '300059', stockName: '东方财富', leaderRoleName: '板块龙头', statusName: '分歧', bidAmount: 188000000, pctChg: 8.5, industrySector: '券商/金融科技', riskLevel: 2 },
        { stockCode: '300760', stockName: '迈瑞医疗', leaderRoleName: '趋势龙头', statusName: '确认', bidAmount: 95000000, pctChg: 8.1, industrySector: '医疗器械/出海', riskLevel: 1 }
      ]
    },
    {
      boardCount: 7, stockCount: 2, totalBidAmount: 785000000,
      leaders: [
        { stockCode: '002594', stockName: '比亚迪', leaderRoleName: '趋势龙头', statusName: '确认', bidAmount: 425000000, pctChg: 7.8, industrySector: '新能源汽车', riskLevel: 1 },
        { stockCode: '601318', stockName: '中国平安', leaderRoleName: '板块龙头', statusName: '竞争', bidAmount: 360000000, pctChg: 7.5, industrySector: '金融/保险', riskLevel: 1 }
      ]
    },
    {
      boardCount: 6, stockCount: 2, totalBidAmount: 190000000,
      leaders: [
        { stockCode: '300308', stockName: '中际旭创', leaderRoleName: '板块龙头', statusName: '确认', bidAmount: 112000000, pctChg: 6.9, industrySector: '光模块/算力网络', riskLevel: 1 },
        { stockCode: '600438', stockName: '通威股份', leaderRoleName: '补涨龙', statusName: '候选', bidAmount: 78000000, pctChg: 6.5, industrySector: '光伏/硅料', riskLevel: 2 }
      ]
    },
    {
      boardCount: 5, stockCount: 2, totalBidAmount: 107000000,
      leaders: [
        { stockCode: '002050', stockName: '三花智控', leaderRoleName: '补涨龙', statusName: '候选', bidAmount: 62000000, pctChg: 5.8, industrySector: '热管理', riskLevel: 1 },
        { stockCode: '002049', stockName: '紫光展锐', leaderRoleName: '板块龙头', statusName: '候选', bidAmount: 45000000, pctChg: 5.2, industrySector: '半导体', riskLevel: 1 }
      ]
    },
    {
      boardCount: 4, stockCount: 2, totalBidAmount: 193000000,
      leaders: [
        { stockCode: '603259', stockName: '药明康德', leaderRoleName: '板块龙头', statusName: '竞争', bidAmount: 105000000, pctChg: 4.8, industrySector: 'CXO/医药外包', riskLevel: 1 },
        { stockCode: '601127', stockName: '赛力斯', leaderRoleName: '补涨龙', statusName: '竞争', bidAmount: 88000000, pctChg: 4.5, industrySector: '鸿蒙智行', riskLevel: 1 }
      ]
    },
    {
      boardCount: 3, stockCount: 2, totalBidAmount: 127000000,
      leaders: [
        { stockCode: '002475', stockName: '立讯精密', leaderRoleName: '板块龙头', statusName: '候选', bidAmount: 72000000, pctChg: 3.8, industrySector: '消费电子', riskLevel: 1 },
        { stockCode: '000157', stockName: '中联重科', leaderRoleName: '补涨龙', statusName: '候选', bidAmount: 55000000, pctChg: 3.5, industrySector: '工程机械', riskLevel: 1 }
      ]
    },
    {
      boardCount: 2, stockCount: 2, totalBidAmount: 307000000,
      leaders: [
        { stockCode: '600276', stockName: '恒瑞医药', leaderRoleName: '趋势龙头', statusName: '候选', bidAmount: 92000000, pctChg: 2.9, industrySector: '创新药', riskLevel: 1 },
        { stockCode: '600036', stockName: '招商银行', leaderRoleName: '趋势龙头', statusName: '竞争', bidAmount: 215000000, pctChg: 2.5, industrySector: '银行/高股息', riskLevel: 1 }
      ]
    }
  ]
}

onMounted(async () => {
  try {
    const res = await fetchLeaderLadder()
    data.value = (res as { data: LeaderLadderData }).data
  } catch {
    data.value = mockData
  }
  loading.value = false
})

function statusBadgeVariant(status: string): 'up' | 'warning' | 'info' | 'default' {
  if (status === '加速') return 'up'
  if (status === '确认') return 'info'
  if (status === '分歧') return 'warning'
  return 'default'
}
</script>

<template>
  <div v-if="loading" class="flex items-center justify-center h-64">
    <p class="text-text-secondary">Loading...</p>
  </div>

  <div v-else-if="data" class="space-y-6">
    <!-- Summary Metrics -->
    <div class="grid grid-cols-2 md:grid-cols-4 gap-4">
      <MetricCard label="最高连板" :value="data.maxConsecutiveBoard + '板'" value-class="text-up" />
      <MetricCard label="高标股数" :value="data.hotStockCount" value-class="text-accent-yellow" />
      <MetricCard label="总封单量" :value="formatAmount(data.totalBidAmount)" value-class="text-text-primary" />
      <MetricCard label="交易日" :value="data.tradeDate" value-class="text-text-primary" />
    </div>

    <!-- Leader Pyramid Tiers -->
    <div class="space-y-3">
      <div
        v-for="tier in data.tiers"
        :key="tier.boardCount"
        :class="[
          'rounded-lg border border-border-primary border-l-4 p-4',
          tierBorderColors[tier.boardCount] || 'border-l-border-secondary',
          tierBgColors[tier.boardCount] || ''
        ]"
      >
        <!-- Tier Header -->
        <div class="flex items-center justify-between mb-3">
          <div class="flex items-center gap-3">
            <span class="text-2xl font-bold text-text-primary">{{ tier.boardCount }}板</span>
            <span class="text-sm text-text-secondary">{{ tier.stockCount }}只</span>
          </div>
          <span class="text-sm text-text-tertiary">封单: {{ formatAmount(tier.totalBidAmount) }}</span>
        </div>

        <!-- Leader Cards Grid -->
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-3">
          <div
            v-for="leader in tier.leaders"
            :key="leader.stockCode"
            class="bg-bg-card rounded-lg p-3 border border-border-primary hover:border-border-secondary transition-colors"
          >
            <div class="flex justify-between items-start mb-2">
              <div>
                <span class="font-bold text-text-primary text-sm">{{ leader.stockName }}</span>
                <span class="text-text-tertiary text-xs ml-2">{{ leader.stockCode }}</span>
              </div>
              <Badge :variant="statusBadgeVariant(leader.statusName)">{{ leader.statusName }}</Badge>
            </div>
            <div class="flex items-center gap-2 text-xs text-text-secondary mb-2">
              <Badge>{{ leader.leaderRoleName }}</Badge>
              <span>{{ leader.industrySector }}</span>
            </div>
            <div class="flex justify-between text-sm">
              <span class="text-text-tertiary">封单: {{ formatAmount(leader.bidAmount) }}</span>
              <span :class="['font-mono font-medium', pctColorClass(leader.pctChg)]">
                {{ formatPct(leader.pctChg) }}
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
