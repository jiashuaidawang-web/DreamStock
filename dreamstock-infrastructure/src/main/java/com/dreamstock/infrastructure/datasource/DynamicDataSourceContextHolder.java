package com.dreamstock.infrastructure.datasource;

public final class DynamicDataSourceContextHolder {

    private static final ThreadLocal<DataSourceType> CONTEXT = ThreadLocal.withInitial(() -> DataSourceType.MYSQL);

    private DynamicDataSourceContextHolder() {}

    public static void setDataSourceType(DataSourceType type) {
        CONTEXT.set(type);
    }

    public static DataSourceType getDataSourceType() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
