import api from '.'

export interface DimensionScore {
  dimension: string; dimensionName: string; score: number; count: number; trend: string
}
export interface StagePnlItem {
  stageName: string; totalPnl: number; tradeCount: number; winRate: number
}
export interface DisciplineProfileData {
  dimensions: DimensionScore[]; weekTopIssues: string[]; monthTopMistakes: string[]
  stagePnl: StagePnlItem[]; nextWeekReminders: string[]
}
export function fetchDisciplineProfile(userId?: number) {
  return api.get('/discipline-profile', { params: { userId } })
}
