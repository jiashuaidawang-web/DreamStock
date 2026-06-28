package com.dreamstock.domain.enums;

public enum SignalType {
    BUY(1, "买点"),
    SELL(2, "卖点"),
    RISK(3, "风控"),
    WATCH(4, "观察"),
    FORBID_BUY(5, "禁买");

    private final int code;
    private final String label;

    SignalType(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() { return code; }
    public String getLabel() { return label; }
}
