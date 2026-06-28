package com.dreamstock.domain.enums;

public enum EmotionStage {
    ICE_POINT(1, "冰点"),
    REPAIR(2, "修复"),
    LAUNCH(3, "启动"),
    FERMENT(4, "发酵"),
    CLIMAX(5, "高潮"),
    DIVERGE(6, "分歧"),
    EBB(7, "退潮"),
    CHAOS(8, "混沌");

    private final int code;
    private final String label;

    EmotionStage(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() { return code; }
    public String getLabel() { return label; }

    public static EmotionStage fromCode(int code) {
        for (EmotionStage s : values()) {
            if (s.code == code) return s;
        }
        return CHAOS;
    }
}
