import api from '.'

export interface FlowTrendPoint {
  tradeDate: string; totalAmount: number; northMoney: number; marginChange: number
}
export interface SectorFlowItem { sectorName: string; netInflow: number; amountRatio: number }
export interface CapitalFlowData {
  tradeDate: string; totalAmount: number; amountPercentile: number
  amountChange5d: number; amountChange20d: number; northMoneyNetInflow: number
  marginBalance: number; supplyDemandLabel: string
  trend: FlowTrendPoint[]; sectorFlows: SectorFlowItem[]
}
export function fetchCapitalFlow(tradeDate?: string) {
  return api.get('/capital-flow', { params: { tradeDate } })
}
