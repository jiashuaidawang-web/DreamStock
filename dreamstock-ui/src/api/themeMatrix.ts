import api from '.'

export interface ThemeItem {
  themeId: number; themeName: string; heatScore: number; limitUpCount: number
  consecutiveCount: number; leaderStockCode: string; leaderStockName: string
  ladderCompleteness: number; continuityDays: number; capitalConcentration: number
  emotionStage: string; riskTag: string; avgPctChg: number; netInflow: number; rank: number
}
export interface ThemeMatrixData { tradeDate: string; themes: ThemeItem[] }

export function fetchThemeMatrix(tradeDate?: string) {
  return api.get('/theme-matrix', { params: { tradeDate } })
}
