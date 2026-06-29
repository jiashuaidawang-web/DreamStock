import api from '.'

export interface TrendStockItem {
  stockCode: string; stockName: string; close: number; pctChg: number
  trendScore: number; maStatus: string; breakoutStatus: string; rsRank: number
  totalMarketValue: number; industrySector: string; weeklyTrendStatus: string
  volumeRatio: number; riskTag: string
}
export interface TrendDragonData { stocks: TrendStockItem[] }

export function fetchTrendDragons() {
  return api.get('/trend-dragon')
}
