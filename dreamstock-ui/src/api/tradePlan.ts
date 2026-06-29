import api from '.'

export interface StockCandidate { stockCode: string; stockName: string; reason: string }
export interface PlanItem {
  id: number; planDate: string; marketStageJudgement: string; systemStage: string
  focusThemes: string[]; focusStocks: StockCandidate[]; entryCondition: string
  positionLimit: number; stopLossCondition: string; takeProfitCondition: string
  riskPlan: string; status: string
}
export interface TradePlanData { plans: PlanItem[] }

export function fetchTradePlans(startDate?: string, endDate?: string) {
  return api.get('/trade-plan', { params: { startDate, endDate } })
}
