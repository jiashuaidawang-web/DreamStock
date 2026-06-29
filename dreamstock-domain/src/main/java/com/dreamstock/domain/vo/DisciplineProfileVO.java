package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.util.List;

public class DisciplineProfileVO {

    private List<DimensionScore> dimensions;
    private List<String> weekTopIssues;
    private List<String> monthTopMistakes;
    private List<StagePnlItem> stagePnl;
    private List<String> nextWeekReminders;

    public static class DimensionScore {
        private String dimension;
        private String dimensionName;
        private BigDecimal score;
        private Integer count;
        private String trend;

        public String getDimension() { return dimension; }
        public void setDimension(String dimension) { this.dimension = dimension; }
        public String getDimensionName() { return dimensionName; }
        public void setDimensionName(String dimensionName) { this.dimensionName = dimensionName; }
        public BigDecimal getScore() { return score; }
        public void setScore(BigDecimal score) { this.score = score; }
        public Integer getCount() { return count; }
        public void setCount(Integer count) { this.count = count; }
        public String getTrend() { return trend; }
        public void setTrend(String trend) { this.trend = trend; }
    }

    public static class StagePnlItem {
        private String stageName;
        private BigDecimal totalPnl;
        private Integer tradeCount;
        private BigDecimal winRate;

        public String getStageName() { return stageName; }
        public void setStageName(String stageName) { this.stageName = stageName; }
        public BigDecimal getTotalPnl() { return totalPnl; }
        public void setTotalPnl(BigDecimal totalPnl) { this.totalPnl = totalPnl; }
        public Integer getTradeCount() { return tradeCount; }
        public void setTradeCount(Integer tradeCount) { this.tradeCount = tradeCount; }
        public BigDecimal getWinRate() { return winRate; }
        public void setWinRate(BigDecimal winRate) { this.winRate = winRate; }
    }

    public List<DimensionScore> getDimensions() { return dimensions; }
    public void setDimensions(List<DimensionScore> dimensions) { this.dimensions = dimensions; }
    public List<String> getWeekTopIssues() { return weekTopIssues; }
    public void setWeekTopIssues(List<String> weekTopIssues) { this.weekTopIssues = weekTopIssues; }
    public List<String> getMonthTopMistakes() { return monthTopMistakes; }
    public void setMonthTopMistakes(List<String> monthTopMistakes) { this.monthTopMistakes = monthTopMistakes; }
    public List<StagePnlItem> getStagePnl() { return stagePnl; }
    public void setStagePnl(List<StagePnlItem> stagePnl) { this.stagePnl = stagePnl; }
    public List<String> getNextWeekReminders() { return nextWeekReminders; }
    public void setNextWeekReminders(List<String> nextWeekReminders) { this.nextWeekReminders = nextWeekReminders; }
}
