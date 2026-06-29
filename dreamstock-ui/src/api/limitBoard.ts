import api from '.'

export interface PoolItem {
  stockCode: string; stockName: string; pctChg: number; amount: number
  boardCount: number; reason: string; sealTime: string; sealAmount: number
  openCount: number; turnoverRate: number
}
export interface LimitBoardData {
  tradeDate: string; firstBoardCount: number; secondBoardCount: number
  thirdBoardCount: number; highBoardCount: number; limitDownCount: number
  failedLimitRate: number; promotionRate: number; maxBoardHeight: number
  limitUpPool: PoolItem[]; limitDownPool: PoolItem[]
  failedBoardPool: PoolItem[]; yesterdayLimitFeedback: PoolItem[]
}
export function fetchLimitBoard(tradeDate?: string) {
  return api.get('/limit-board', { params: { tradeDate } })
}
