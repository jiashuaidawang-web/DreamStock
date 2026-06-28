import api from '.'

export interface EmotionSummary {
  emotionScore: number
  emotionStageName: string
  limitUpCount: number
  limitDownCount: number
  indexPctChg: number
  indexName: string
}

export interface MarketOverview {
  upCount: number
  downCount: number
  above5pctCount: number
  belowMinus5pctCount: number
  totalAmount: number
  consecutiveLimitHigh: number
}

export interface PersonalPerformance {
  todayPnl: number
  cumulativeReturnPct: number
  recentWinRatePct: number
}

export interface ThemeRankItem {
  rank: number
  themeName: string
  leaderStockName: string
  leaderPctChg: number
}

export interface EmotionTrendPoint {
  tradeDate: string
  emotionScore: number
  emotionStage: number
}

export interface DashboardData {
  tradeDate: string
  emotionSummary: EmotionSummary
  marketOverview: MarketOverview
  personalPerformance: PersonalPerformance
  topThemes: ThemeRankItem[]
  emotionTrend: EmotionTrendPoint[]
}

export function fetchDashboard(tradeDate?: string) {
  return api.get('/dashboard', { params: { tradeDate } })
}
