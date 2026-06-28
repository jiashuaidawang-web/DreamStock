package com.dreamstock.domain.enums;

public enum PoolType {
    LIMIT_UP(1, "涨停池"),
    LIMIT_DOWN(2, "跌停池"),
    STRONG(3, "强势股池"),
    SUB_NEW(4, "次新股池"),
    BOARD_BREAK(5, "炸板池"),
    CONSECUTIVE(6, "连板池"),
    RE_SEAL(7, "回封池"),
    HIGH_MARK(8, "高标池");

    private final int code;
    private final String label;

    PoolType(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() { return code; }
    public String getLabel() { return label; }
}
