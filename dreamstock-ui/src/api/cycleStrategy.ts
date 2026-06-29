import api from '.'

export interface StageStrategy {
  stage: string; stageName: string; current: boolean; focusPoint: string
  filterRules: string[]; positionAdvice: string; riskAdvice: string; candidateStocks: string[]
}
export interface CycleStrategyData {
  currentStage: string; currentStageName: string; strategies: StageStrategy[]
}
export function fetchCycleStrategy() {
  return api.get('/cycle-strategy')
}
