package com.dreamstock.domain.vo;

import java.time.LocalDateTime;
import java.util.List;

public class UserPermissionVO {

    private List<UserItem> users;
    private List<AuditLogItem> auditLogs;

    public static class UserItem {
        private Long id;
        private String username;
        private String displayName;
        private String role;
        private Integer status;
        private String lastLoginTime;
        private List<String> permissions;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getDisplayName() { return displayName; }
        public void setDisplayName(String displayName) { this.displayName = displayName; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
        public Integer getStatus() { return status; }
        public void setStatus(Integer status) { this.status = status; }
        public String getLastLoginTime() { return lastLoginTime; }
        public void setLastLoginTime(String lastLoginTime) { this.lastLoginTime = lastLoginTime; }
        public List<String> getPermissions() { return permissions; }
        public void setPermissions(List<String> permissions) { this.permissions = permissions; }
    }

    public static class AuditLogItem {
        private Long id;
        private String operator;
        private String action;
        private String target;
        private LocalDateTime operateTime;
        private String ipAddress;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getOperator() { return operator; }
        public void setOperator(String operator) { this.operator = operator; }
        public String getAction() { return action; }
        public void setAction(String action) { this.action = action; }
        public String getTarget() { return target; }
        public void setTarget(String target) { this.target = target; }
        public LocalDateTime getOperateTime() { return operateTime; }
        public void setOperateTime(LocalDateTime operateTime) { this.operateTime = operateTime; }
        public String getIpAddress() { return ipAddress; }
        public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    }

    public List<UserItem> getUsers() { return users; }
    public void setUsers(List<UserItem> users) { this.users = users; }
    public List<AuditLogItem> getAuditLogs() { return auditLogs; }
    public void setAuditLogs(List<AuditLogItem> auditLogs) { this.auditLogs = auditLogs; }
}
