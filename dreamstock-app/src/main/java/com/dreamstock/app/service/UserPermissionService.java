package com.dreamstock.app.service;

import com.dreamstock.domain.vo.UserPermissionVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserPermissionService {

    public UserPermissionVO getUserPermissions() {
        UserPermissionVO vo = new UserPermissionVO();

        List<UserPermissionVO.UserItem> users = new ArrayList<>();
        users.add(buildUser(1L, "admin", "系统管理员", "ADMIN", 1, "2026-06-28 10:30:00",
            List.of("ALL")));
        users.add(buildUser(2L, "trader01", "交易员张三", "TRADER", 1, "2026-06-28 09:15:00",
            List.of("DASHBOARD", "EMOTION", "THEME", "LEADER", "TRADE_PLAN", "REVIEW")));
        users.add(buildUser(3L, "analyst01", "分析师李四", "ANALYST", 1, "2026-06-27 16:45:00",
            List.of("DASHBOARD", "EMOTION", "CYCLE", "MARKET", "CAPITAL", "THEME", "CORRELATION")));
        users.add(buildUser(4L, "viewer01", "观察员王五", "VIEWER", 0, "2026-06-20 14:00:00",
            List.of("DASHBOARD")));
        vo.setUsers(users);

        List<UserPermissionVO.AuditLogItem> logs = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        logs.add(buildLog(1L, "admin", "修改用户角色", "trader01: VIEWER→TRADER", now.minusHours(2), "192.168.1.100"));
        logs.add(buildLog(2L, "admin", "创建规则集", "EMOTION_DEFAULT V1.0", now.minusHours(5), "192.168.1.100"));
        logs.add(buildLog(3L, "trader01", "保存交易计划", "2026-06-28交易计划", now.minusHours(8), "192.168.1.105"));
        logs.add(buildLog(4L, "admin", "禁用用户", "viewer01", now.minusDays(1), "192.168.1.100"));
        logs.add(buildLog(5L, "analyst01", "导出数据", "情绪趋势数据60天", now.minusDays(1), "192.168.1.110"));
        vo.setAuditLogs(logs);
        return vo;
    }

    private UserPermissionVO.UserItem buildUser(Long id, String username, String displayName,
            String role, int status, String lastLogin, List<String> permissions) {
        UserPermissionVO.UserItem u = new UserPermissionVO.UserItem();
        u.setId(id); u.setUsername(username); u.setDisplayName(displayName);
        u.setRole(role); u.setStatus(status); u.setLastLoginTime(lastLogin);
        u.setPermissions(permissions);
        return u;
    }

    private UserPermissionVO.AuditLogItem buildLog(Long id, String operator, String action,
            String target, LocalDateTime time, String ip) {
        UserPermissionVO.AuditLogItem l = new UserPermissionVO.AuditLogItem();
        l.setId(id); l.setOperator(operator); l.setAction(action); l.setTarget(target);
        l.setOperateTime(time); l.setIpAddress(ip);
        return l;
    }
}
