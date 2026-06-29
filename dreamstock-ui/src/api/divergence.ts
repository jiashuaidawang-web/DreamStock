import api from '.'

export interface DivergenceItem {
  stockCode: string; stockName: string; boardCount: number; openCount: number
  sealStrength: number; turnoverRate: number; amount: number; sealAmount: number
  divergenceStatus: string; nextDayPremium: number; themeName: string; riskTag: string
}
export interface DivergenceData { tradeDate: string; items: DivergenceItem[] }

export function fetchDivergence(tradeDate?: string) {
  return api.get('/divergence', { params: { tradeDate } })
}
