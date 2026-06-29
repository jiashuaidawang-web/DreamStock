import api from '.'

export interface EventItem {
  id: number; eventDate: string; eventType: string; title: string; content: string
  impactScope: string; impactDirection: string; relatedThemes: string[]
  relatedStocks: string[]; return1d: number; return3d: number; return5d: number
  verificationStatus: string
}
export interface PolicyEventData { events: EventItem[] }

export function fetchPolicyEvents(startDate?: string, endDate?: string) {
  return api.get('/policy-event', { params: { startDate, endDate } })
}
