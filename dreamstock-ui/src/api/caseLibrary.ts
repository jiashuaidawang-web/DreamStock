import api from '.'

export interface CaseItem {
  id: number; caseType: string; caseName: string; startDate: string; endDate: string
  marketStage: string; mainTheme: string; coreStock: string; emotionChange: string
  conclusion: string; reusableRules: string[]; tags: string[]
}
export interface CaseLibraryData { cases: CaseItem[] }

export function fetchCases(caseType?: string) {
  return api.get('/case-library', { params: { caseType } })
}
