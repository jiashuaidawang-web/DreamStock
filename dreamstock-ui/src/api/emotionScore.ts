import api from '.'

export interface FactorItem {
  factorCode: string; factorName: string; rawValue: number; score: number; weight: number; direction: string
}
export interface CompareInfo {
  yesterdayScore: number; avg5dScore: number; avg20dScore: number; changeFromYesterday: number
}
export interface LimitDetail {
  stockCode: string; stockName: string; pctChg: number; boardCount: number; reason: string
}
export interface EmotionScoreData {
  tradeDate: string; emotionScore: number; emotionStageName: string; emotionGrade: string; conclusion: string
  factors: FactorItem[]; compareInfo: CompareInfo; limitUpDetails: LimitDetail[]
}
export function fetchEmotionScore(tradeDate?: string) {
  return api.get('/emotion-score', { params: { tradeDate } })
}
