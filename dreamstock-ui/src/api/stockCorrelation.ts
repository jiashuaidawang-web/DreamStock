import api from '.'

export interface CorrTrendPoint {
  tradeDate: string; stockPctChg: number; indexPctChg: number; emotionScore: number
}
export interface StockCorrelationData {
  stockCode: string; stockName: string; windowSize: number
  indexCorr: number; emotionCorr: number; themeCorr: number
  rsRank: number; positionTag: string; divergenceTag: string
  explanation: string; corrTrend: CorrTrendPoint[]
}
export function fetchStockCorrelation(stockCode?: string, windowSize?: number) {
  return api.get('/stock-correlation', { params: { stockCode, windowSize } })
}
