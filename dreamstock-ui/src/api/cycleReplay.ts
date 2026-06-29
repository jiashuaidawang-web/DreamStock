import api from '.'

export interface DaySnapshot {
  tradeDate: string; emotionScore: number; emotionStageName: string
  indexClose: number; indexPctChg: number; totalAmount: number
  mainTheme: string; topDragon: string; limitUpCount: number
}
export interface ReportSummary {
  periodConclusion: string; cycleChanges: string; themeRotation: string
  dragonLifecycle: string; totalPnl: number; topBehaviorIssues: string[]
}
export interface CycleReplayData {
  startDate: string; endDate: string; snapshots: DaySnapshot[]; reportSummary: ReportSummary
}
export function fetchCycleReplay(startDate?: string, endDate?: string) {
  return api.get('/cycle-replay', { params: { startDate, endDate } })
}
