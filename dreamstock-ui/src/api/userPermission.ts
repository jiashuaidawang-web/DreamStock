import api from '.'

export interface UserItem {
  id: number; username: string; displayName: string; role: string
  status: number; lastLoginTime: string; permissions: string[]
}
export interface AuditLogItem {
  id: number; operator: string; action: string; target: string
  operateTime: string; ipAddress: string
}
export interface UserPermissionData { users: UserItem[]; auditLogs: AuditLogItem[] }

export function fetchUserPermissions() {
  return api.get('/user-permission')
}
