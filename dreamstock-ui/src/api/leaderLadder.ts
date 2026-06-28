import api from '.'

export interface LeaderCard {
  stockCode: string
  stockName: string
  leaderRoleName: string
  statusName: string
  bidAmount: number
  pctChg: number
  industrySector: string
  riskLevel: number
}

export interface BoardTier {
  boardCount: number
  stockCount: number
  totalBidAmount: number
  leaders: LeaderCard[]
}

export interface LeaderLadderData {
  tradeDate: string
  maxConsecutiveBoard: number
  totalBidAmount: number
  hotStockCount: number
  tiers: BoardTier[]
}

export function fetchLeaderLadder(tradeDate?: string) {
  return api.get('/leader-ladder', { params: { tradeDate } })
}
