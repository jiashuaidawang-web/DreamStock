import api from '.'

export interface JobStatusItem {
  id: number; jobName: string; jobType: string; lastRunStatus: string
  lastRunTime: string; lastRowsAffected: number; nextScheduledTime: string
  errorMessage: string | null
}
export interface QualityCheckResult {
  id: number; checkTarget: string; checkType: string; checkCondition: string
  resultStatus: string; actualValue: number; thresholdValue: number; checkTime: string
}
export interface DataQualityData {
  jobs: JobStatusItem[]; qualityChecks: QualityCheckResult[]; overallHealthStatus: string
}
export function fetchDataQuality() {
  return api.get('/data-quality')
}
