package com.dreamstock.domain.enums;

public enum LeaderRole {
    TOTAL_LEADER(1, "总龙头"),
    SECTOR_LEADER(2, "板块龙头"),
    REBOUND_LEADER(3, "补涨龙"),
    TREND_LEADER(4, "趋势龙"),
    REVERSE_LEADER(5, "反包龙"),
    REAR_GUARD(6, "后排"),
    FOLLOWER(7, "跟风"),
    LAGGING(8, "掉队");

    private final int code;
    private final String label;

    LeaderRole(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() { return code; }
    public String getLabel() { return label; }

    public static LeaderRole fromCode(int code) {
        for (LeaderRole r : values()) {
            if (r.code == code) return r;
        }
        return FOLLOWER;
    }
}
