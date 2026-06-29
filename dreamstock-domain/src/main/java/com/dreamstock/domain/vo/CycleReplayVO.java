package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CycleReplayVO {

    private LocalDate startDate;
    private LocalDate endDate;
    private List<DaySnapshot> snapshots;
    private ReportSummary reportSummary;

    public static class DaySnapshot {
        private LocalDate tradeDate;
        private BigDecimal emotionScore;
        private String emotionStageName;
        private BigDecimal indexClose;
        private BigDecimal indexPctChg;
        private BigDecimal totalAmount;
        private String mainTheme;
        private String topDragon;
        private Integer limitUpCount;

        public LocalDate getTradeDate() { return tradeDate; }
        public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
        public BigDecimal getEmotionScore() { return emotionScore; }
        public void setEmotionScore(BigDecimal emotionScore) { this.emotionScore = emotionScore; }
        public String getEmotionStageName() { return emotionStageName; }
        public void setEmotionStageName(String emotionStageName) { this.emotionStageName = emotionStageName; }
        public BigDecimal getIndexClose() { return indexClose; }
        public void setIndexClose(BigDecimal indexClose) { this.indexClose = indexClose; }
        public BigDecimal getIndexPctChg() { return indexPctChg; }
        public void setIndexPctChg(BigDecimal indexPctChg) { this.indexPctChg = indexPctChg; }
        public BigDecimal getTotalAmount() { return totalAmount; }
        public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
        public String getMainTheme() { return mainTheme; }
        public void setMainTheme(String mainTheme) { this.mainTheme = mainTheme; }
        public String getTopDragon() { return topDragon; }
        public void setTopDragon(String topDragon) { this.topDragon = topDragon; }
        public Integer getLimitUpCount() { return limitUpCount; }
        public void setLimitUpCount(Integer limitUpCount) { this.limitUpCount = limitUpCount; }
    }

    public static class ReportSummary {
        private String periodConclusion;
        private String cycleChanges;
        private String themeRotation;
        private String dragonLifecycle;
        private BigDecimal totalPnl;
        private List<String> topBehaviorIssues;

        public String getPeriodConclusion() { return periodConclusion; }
        public void setPeriodConclusion(String periodConclusion) { this.periodConclusion = periodConclusion; }
        public String getCycleChanges() { return cycleChanges; }
        public void setCycleChanges(String cycleChanges) { this.cycleChanges = cycleChanges; }
        public String getThemeRotation() { return themeRotation; }
        public void setThemeRotation(String themeRotation) { this.themeRotation = themeRotation; }
        public String getDragonLifecycle() { return dragonLifecycle; }
        public void setDragonLifecycle(String dragonLifecycle) { this.dragonLifecycle = dragonLifecycle; }
        public BigDecimal getTotalPnl() { return totalPnl; }
        public void setTotalPnl(BigDecimal totalPnl) { this.totalPnl = totalPnl; }
        public List<String> getTopBehaviorIssues() { return topBehaviorIssues; }
        public void setTopBehaviorIssues(List<String> topBehaviorIssues) { this.topBehaviorIssues = topBehaviorIssues; }
    }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public List<DaySnapshot> getSnapshots() { return snapshots; }
    public void setSnapshots(List<DaySnapshot> snapshots) { this.snapshots = snapshots; }
    public ReportSummary getReportSummary() { return reportSummary; }
    public void setReportSummary(ReportSummary reportSummary) { this.reportSummary = reportSummary; }
}
