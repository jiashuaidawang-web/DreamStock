import api from '.'

export interface KLinePoint {
  tradeDate: string; open: number; close: number; high: number; low: number; volume: number
}
export interface IndexItem {
  indexCode: string; indexName: string; close: number; pctChg: number; totalAmount: number
  ma5Status: string; ma10Status: string; ma20Status: string; ma60Status: string
  trendLabel: string; kLines: KLinePoint[]
}
export interface KeyLevel { levelType: string; price: number; description: string }
export interface MarketTrendData {
  indices: IndexItem[]; trendStructure: string; technicalCycle: string
  indexEmotionRelation: string; keyLevels: KeyLevel[]
}
export function fetchMarketTrend(tradeDate?: string) {
  return api.get('/market-trend', { params: { tradeDate } })
}
