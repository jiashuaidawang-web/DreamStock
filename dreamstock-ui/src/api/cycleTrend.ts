import api from '.'

export interface TrendPoint {
  tradeDate: string; emotionScore: number; emotionStage: number; emotionStageName: string
  indexClose: number; indexPctChg: number; totalAmount: number
}
export interface EventMarker {
  tradeDate: string; eventType: string; eventTitle: string; impactDirection: string
}
export interface CycleTrendData { points: TrendPoint[]; events: EventMarker[] }

export function fetchCycleTrend(days?: number) {
  return api.get('/cycle-trend', { params: { days } })
}
