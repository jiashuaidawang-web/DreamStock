package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ThemeMatrixVO {

    private LocalDate tradeDate;
    private List<ThemeItem> themes;

    public static class ThemeItem {
        private Long themeId;
        private String themeName;
        private BigDecimal heatScore;
        private Integer limitUpCount;
        private Integer consecutiveCount;
        private String leaderStockCode;
        private String leaderStockName;
        private Integer ladderCompleteness;
        private Integer continuityDays;
        private BigDecimal capitalConcentration;
        private String emotionStage;
        private String riskTag;
        private BigDecimal avgPctChg;
        private BigDecimal netInflow;
        private Integer rank;

        public Long getThemeId() { return themeId; }
        public void setThemeId(Long themeId) { this.themeId = themeId; }
        public String getThemeName() { return themeName; }
        public void setThemeName(String themeName) { this.themeName = themeName; }
        public BigDecimal getHeatScore() { return heatScore; }
        public void setHeatScore(BigDecimal heatScore) { this.heatScore = heatScore; }
        public Integer getLimitUpCount() { return limitUpCount; }
        public void setLimitUpCount(Integer limitUpCount) { this.limitUpCount = limitUpCount; }
        public Integer getConsecutiveCount() { return consecutiveCount; }
        public void setConsecutiveCount(Integer consecutiveCount) { this.consecutiveCount = consecutiveCount; }
        public String getLeaderStockCode() { return leaderStockCode; }
        public void setLeaderStockCode(String leaderStockCode) { this.leaderStockCode = leaderStockCode; }
        public String getLeaderStockName() { return leaderStockName; }
        public void setLeaderStockName(String leaderStockName) { this.leaderStockName = leaderStockName; }
        public Integer getLadderCompleteness() { return ladderCompleteness; }
        public void setLadderCompleteness(Integer ladderCompleteness) { this.ladderCompleteness = ladderCompleteness; }
        public Integer getContinuityDays() { return continuityDays; }
        public void setContinuityDays(Integer continuityDays) { this.continuityDays = continuityDays; }
        public BigDecimal getCapitalConcentration() { return capitalConcentration; }
        public void setCapitalConcentration(BigDecimal capitalConcentration) { this.capitalConcentration = capitalConcentration; }
        public String getEmotionStage() { return emotionStage; }
        public void setEmotionStage(String emotionStage) { this.emotionStage = emotionStage; }
        public String getRiskTag() { return riskTag; }
        public void setRiskTag(String riskTag) { this.riskTag = riskTag; }
        public BigDecimal getAvgPctChg() { return avgPctChg; }
        public void setAvgPctChg(BigDecimal avgPctChg) { this.avgPctChg = avgPctChg; }
        public BigDecimal getNetInflow() { return netInflow; }
        public void setNetInflow(BigDecimal netInflow) { this.netInflow = netInflow; }
        public Integer getRank() { return rank; }
        public void setRank(Integer rank) { this.rank = rank; }
    }

    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
    public List<ThemeItem> getThemes() { return themes; }
    public void setThemes(List<ThemeItem> themes) { this.themes = themes; }
}
