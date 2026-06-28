package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DashboardVO {

    private LocalDate tradeDate;
    private EmotionSummary emotionSummary;
    private MarketOverview marketOverview;
    private PersonalPerformance personalPerformance;
    private List<ThemeRankItem> topThemes;
    private List<EmotionTrendPoint> emotionTrend;

    public static class EmotionSummary {
        private BigDecimal emotionScore;
        private String emotionStageName;
        private Long limitUpCount;
        private Long limitDownCount;
        private BigDecimal indexPctChg;
        private String indexName;

        public BigDecimal getEmotionScore() { return emotionScore; }
        public void setEmotionScore(BigDecimal emotionScore) { this.emotionScore = emotionScore; }
        public String getEmotionStageName() { return emotionStageName; }
        public void setEmotionStageName(String emotionStageName) { this.emotionStageName = emotionStageName; }
        public Long getLimitUpCount() { return limitUpCount; }
        public void setLimitUpCount(Long limitUpCount) { this.limitUpCount = limitUpCount; }
        public Long getLimitDownCount() { return limitDownCount; }
        public void setLimitDownCount(Long limitDownCount) { this.limitDownCount = limitDownCount; }
        public BigDecimal getIndexPctChg() { return indexPctChg; }
        public void setIndexPctChg(BigDecimal indexPctChg) { this.indexPctChg = indexPctChg; }
        public String getIndexName() { return indexName; }
        public void setIndexName(String indexName) { this.indexName = indexName; }
    }

    public static class MarketOverview {
        private Long upCount;
        private Long downCount;
        private Long above5pctCount;
        private Long belowMinus5pctCount;
        private BigDecimal totalAmount;
        private Long consecutiveLimitHigh;

        public Long getUpCount() { return upCount; }
        public void setUpCount(Long upCount) { this.upCount = upCount; }
        public Long getDownCount() { return downCount; }
        public void setDownCount(Long downCount) { this.downCount = downCount; }
        public Long getAbove5pctCount() { return above5pctCount; }
        public void setAbove5pctCount(Long above5pctCount) { this.above5pctCount = above5pctCount; }
        public Long getBelowMinus5pctCount() { return belowMinus5pctCount; }
        public void setBelowMinus5pctCount(Long belowMinus5pctCount) { this.belowMinus5pctCount = belowMinus5pctCount; }
        public BigDecimal getTotalAmount() { return totalAmount; }
        public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
        public Long getConsecutiveLimitHigh() { return consecutiveLimitHigh; }
        public void setConsecutiveLimitHigh(Long consecutiveLimitHigh) { this.consecutiveLimitHigh = consecutiveLimitHigh; }
    }

    public static class PersonalPerformance {
        private BigDecimal todayPnl;
        private BigDecimal cumulativeReturnPct;
        private BigDecimal recentWinRatePct;

        public BigDecimal getTodayPnl() { return todayPnl; }
        public void setTodayPnl(BigDecimal todayPnl) { this.todayPnl = todayPnl; }
        public BigDecimal getCumulativeReturnPct() { return cumulativeReturnPct; }
        public void setCumulativeReturnPct(BigDecimal cumulativeReturnPct) { this.cumulativeReturnPct = cumulativeReturnPct; }
        public BigDecimal getRecentWinRatePct() { return recentWinRatePct; }
        public void setRecentWinRatePct(BigDecimal recentWinRatePct) { this.recentWinRatePct = recentWinRatePct; }
    }

    public static class ThemeRankItem {
        private Long rank;
        private String themeName;
        private String leaderStockName;
        private BigDecimal leaderPctChg;

        public Long getRank() { return rank; }
        public void setRank(Long rank) { this.rank = rank; }
        public String getThemeName() { return themeName; }
        public void setThemeName(String themeName) { this.themeName = themeName; }
        public String getLeaderStockName() { return leaderStockName; }
        public void setLeaderStockName(String leaderStockName) { this.leaderStockName = leaderStockName; }
        public BigDecimal getLeaderPctChg() { return leaderPctChg; }
        public void setLeaderPctChg(BigDecimal leaderPctChg) { this.leaderPctChg = leaderPctChg; }
    }

    public static class EmotionTrendPoint {
        private LocalDate tradeDate;
        private BigDecimal emotionScore;
        private Integer emotionStage;

        public LocalDate getTradeDate() { return tradeDate; }
        public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
        public BigDecimal getEmotionScore() { return emotionScore; }
        public void setEmotionScore(BigDecimal emotionScore) { this.emotionScore = emotionScore; }
        public Integer getEmotionStage() { return emotionStage; }
        public void setEmotionStage(Integer emotionStage) { this.emotionStage = emotionStage; }
    }

    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
    public EmotionSummary getEmotionSummary() { return emotionSummary; }
    public void setEmotionSummary(EmotionSummary emotionSummary) { this.emotionSummary = emotionSummary; }
    public MarketOverview getMarketOverview() { return marketOverview; }
    public void setMarketOverview(MarketOverview marketOverview) { this.marketOverview = marketOverview; }
    public PersonalPerformance getPersonalPerformance() { return personalPerformance; }
    public void setPersonalPerformance(PersonalPerformance personalPerformance) { this.personalPerformance = personalPerformance; }
    public List<ThemeRankItem> getTopThemes() { return topThemes; }
    public void setTopThemes(List<ThemeRankItem> topThemes) { this.topThemes = topThemes; }
    public List<EmotionTrendPoint> getEmotionTrend() { return emotionTrend; }
    public void setEmotionTrend(List<EmotionTrendPoint> emotionTrend) { this.emotionTrend = emotionTrend; }
}
