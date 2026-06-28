<script setup lang="ts">
import { ref } from 'vue'
import Card from '@/components/ui/Card.vue'
import Badge from '@/components/ui/Badge.vue'
import { formatPct, pctColorClass } from '@/utils/format'

const selectedDate = ref('2026-06-28')
const emotionStage = ref('退潮')
const disciplineScore = ref(72)
const mentalityScore = ref(65)
const actionScore = ref(80)
const marketView = ref('')
const operationSummary = ref('')
const mistakeSummary = ref('')
const nextDayPlan = ref('')

const tradeRecords = ref([
  {
    id: 1,
    stockCode: '300308',
    stockName: '中际旭创',
    operationType: 1,
    operationTime: '2026-06-28 09:35:00',
    operationPrice: 128.50,
    operationAmount: 64250,
    buyReason: '板块龙头确认，放量突破平台',
    pnlPct: 5.2,
    pnlAmount: 3341
  },
  {
    id: 2,
    stockCode: '002594',
    stockName: '比亚迪',
    operationType: 2,
    operationTime: '2026-06-28 14:22:00',
    operationPrice: 305.80,
    operationAmount: 152900,
    sellReason: '触发止盈线，分批出局',
    pnlPct: -1.8,
    pnlAmount: -2752
  }
])

const emotionStages = ['冰点', '修复', '启动', '发酵', '高潮', '分歧', '退潮', '混沌']

function scoreColor(score: number): string {
  if (score >= 80) return 'text-up'
  if (score >= 60) return 'text-accent-yellow'
  return 'text-down'
}
</script>

<template>
  <div class="space-y-6">
    <!-- Date & Emotion Stage Selector -->
    <div class="flex items-center gap-4">
      <div class="flex items-center gap-2">
        <label class="text-sm text-text-secondary">交易日:</label>
        <input
          v-model="selectedDate"
          type="date"
          class="bg-bg-secondary border border-border-primary rounded-lg px-3 py-1.5 text-sm text-text-primary focus:border-accent-yellow focus:outline-none"
        />
      </div>
      <div class="flex items-center gap-2">
        <label class="text-sm text-text-secondary">情绪阶段:</label>
        <select
          v-model="emotionStage"
          class="bg-bg-secondary border border-border-primary rounded-lg px-3 py-1.5 text-sm text-text-primary focus:border-accent-yellow focus:outline-none"
        >
          <option v-for="s in emotionStages" :key="s" :value="s">{{ s }}</option>
        </select>
      </div>
    </div>

    <!-- Score Cards -->
    <div class="grid grid-cols-3 gap-4">
      <Card>
        <p class="text-xs text-text-secondary mb-1">纪律分</p>
        <p :class="['text-3xl font-bold', scoreColor(disciplineScore)]">{{ disciplineScore }}</p>
        <input
          v-model.number="disciplineScore"
          type="range"
          min="0"
          max="100"
          class="w-full mt-2 accent-accent-yellow"
        />
      </Card>
      <Card>
        <p class="text-xs text-text-secondary mb-1">心态分</p>
        <p :class="['text-3xl font-bold', scoreColor(mentalityScore)]">{{ mentalityScore }}</p>
        <input
          v-model.number="mentalityScore"
          type="range"
          min="0"
          max="100"
          class="w-full mt-2 accent-accent-yellow"
        />
      </Card>
      <Card>
        <p class="text-xs text-text-secondary mb-1">执行分</p>
        <p :class="['text-3xl font-bold', scoreColor(actionScore)]">{{ actionScore }}</p>
        <input
          v-model.number="actionScore"
          type="range"
          min="0"
          max="100"
          class="w-full mt-2 accent-accent-yellow"
        />
      </Card>
    </div>

    <!-- Text Areas -->
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-4">
      <Card>
        <h3 class="text-sm font-medium text-text-secondary mb-2">市场观点</h3>
        <textarea
          v-model="marketView"
          rows="4"
          placeholder="记录今日市场整体判断..."
          class="w-full bg-bg-secondary border border-border-primary rounded-lg px-3 py-2 text-sm text-text-primary placeholder:text-text-tertiary focus:border-accent-yellow focus:outline-none resize-none"
        />
      </Card>
      <Card>
        <h3 class="text-sm font-medium text-text-secondary mb-2">操作总结</h3>
        <textarea
          v-model="operationSummary"
          rows="4"
          placeholder="今日操作回顾..."
          class="w-full bg-bg-secondary border border-border-primary rounded-lg px-3 py-2 text-sm text-text-primary placeholder:text-text-tertiary focus:border-accent-yellow focus:outline-none resize-none"
        />
      </Card>
      <Card>
        <h3 class="text-sm font-medium text-text-secondary mb-2">失误总结</h3>
        <textarea
          v-model="mistakeSummary"
          rows="4"
          placeholder="哪些操作违反了纪律或判断失误..."
          class="w-full bg-bg-secondary border border-border-primary rounded-lg px-3 py-2 text-sm text-text-primary placeholder:text-text-tertiary focus:border-accent-yellow focus:outline-none resize-none"
        />
      </Card>
      <Card>
        <h3 class="text-sm font-medium text-text-secondary mb-2">明日计划</h3>
        <textarea
          v-model="nextDayPlan"
          rows="4"
          placeholder="明日交易计划和目标..."
          class="w-full bg-bg-secondary border border-border-primary rounded-lg px-3 py-2 text-sm text-text-primary placeholder:text-text-tertiary focus:border-accent-yellow focus:outline-none resize-none"
        />
      </Card>
    </div>

    <!-- Trade Records Table -->
    <Card>
      <div class="flex justify-between items-center mb-4">
        <h3 class="text-sm font-medium text-text-secondary">个人操作记录</h3>
        <button class="px-3 py-1.5 bg-accent-yellow/10 text-accent-yellow text-xs rounded-lg hover:bg-accent-yellow/20 transition-colors">
          + 添加记录
        </button>
      </div>
      <div class="overflow-x-auto">
        <table class="w-full text-sm">
          <thead>
            <tr class="border-b border-border-primary text-text-tertiary text-left">
              <th class="py-2 px-2 font-medium">时间</th>
              <th class="py-2 px-2 font-medium">股票</th>
              <th class="py-2 px-2 font-medium">操作</th>
              <th class="py-2 px-2 font-medium text-right">价格</th>
              <th class="py-2 px-2 font-medium text-right">金额</th>
              <th class="py-2 px-2 font-medium">理由</th>
              <th class="py-2 px-2 font-medium text-right">盈亏%</th>
              <th class="py-2 px-2 font-medium text-right">盈亏额</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="record in tradeRecords"
              :key="record.id"
              class="border-b border-border-primary/50 hover:bg-bg-hover transition-colors"
            >
              <td class="py-2 px-2 text-text-secondary text-xs">{{ record.operationTime.slice(11) }}</td>
              <td class="py-2 px-2">
                <span class="font-medium text-text-primary">{{ record.stockName }}</span>
                <span class="text-text-tertiary text-xs ml-1">{{ record.stockCode }}</span>
              </td>
              <td class="py-2 px-2">
                <Badge :variant="record.operationType === 1 ? 'up' : 'down'">
                  {{ record.operationType === 1 ? '买入' : '卖出' }}
                </Badge>
              </td>
              <td class="py-2 px-2 text-right font-mono">{{ record.operationPrice }}</td>
              <td class="py-2 px-2 text-right font-mono text-text-secondary">{{ record.operationAmount.toLocaleString() }}</td>
              <td class="py-2 px-2 text-text-secondary text-xs max-w-[200px] truncate">
                {{ record.buyReason || record.sellReason }}
              </td>
              <td :class="['py-2 px-2 text-right font-mono font-medium', pctColorClass(record.pnlPct)]">
                {{ formatPct(record.pnlPct) }}
              </td>
              <td :class="['py-2 px-2 text-right font-mono font-medium', pctColorClass(record.pnlAmount)]">
                {{ record.pnlAmount > 0 ? '+' : '' }}{{ record.pnlAmount.toLocaleString() }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </Card>

    <!-- Save Button -->
    <div class="flex justify-end">
      <button class="px-6 py-2.5 bg-accent-yellow text-bg-primary font-medium rounded-lg hover:bg-accent-yellow/90 transition-colors">
        保存复盘日志
      </button>
    </div>
  </div>
</template>
