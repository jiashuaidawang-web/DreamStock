package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class EmotionScoreVO {

    private LocalDate tradeDate;
    private BigDecimal emotionScore;
    private String emotionStageName;
    private String emotionGrade;
    private String conclusion;
    private List<FactorItem> factors;
    private CompareInfo compareInfo;
    private List<LimitDetail> limitUpDetails;

    public static class FactorItem {
        private String factorCode;
        private String factorName;
        private BigDecimal rawValue;
        private BigDecimal score;
        private BigDecimal weight;
        private String direction;

        public String getFactorCode() { return factorCode; }
        public void setFactorCode(String factorCode) { this.factorCode = factorCode; }
        public String getFactorName() { return factorName; }
        public void setFactorName(String factorName) { this.factorName = factorName; }
        public BigDecimal getRawValue() { return rawValue; }
        public void setRawValue(BigDecimal rawValue) { this.rawValue = rawValue; }
        public BigDecimal getScore() { return score; }
        public void setScore(BigDecimal score) { this.score = score; }
        public BigDecimal getWeight() { return weight; }
        public void setWeight(BigDecimal weight) { this.weight = weight; }
        public String getDirection() { return direction; }
        public void setDirection(String direction) { this.direction = direction; }
    }

    public static class CompareInfo {
        private BigDecimal yesterdayScore;
        private BigDecimal avg5dScore;
        private BigDecimal avg20dScore;
        private BigDecimal changeFromYesterday;

        public BigDecimal getYesterdayScore() { return yesterdayScore; }
        public void setYesterdayScore(BigDecimal yesterdayScore) { this.yesterdayScore = yesterdayScore; }
        public BigDecimal getAvg5dScore() { return avg5dScore; }
        public void setAvg5dScore(BigDecimal avg5dScore) { this.avg5dScore = avg5dScore; }
        public BigDecimal getAvg20dScore() { return avg20dScore; }
        public void setAvg20dScore(BigDecimal avg20dScore) { this.avg20dScore = avg20dScore; }
        public BigDecimal getChangeFromYesterday() { return changeFromYesterday; }
        public void setChangeFromYesterday(BigDecimal changeFromYesterday) { this.changeFromYesterday = changeFromYesterday; }
    }

    public static class LimitDetail {
        private String stockCode;
        private String stockName;
        private BigDecimal pctChg;
        private Integer boardCount;
        private String reason;

        public String getStockCode() { return stockCode; }
        public void setStockCode(String stockCode) { this.stockCode = stockCode; }
        public String getStockName() { return stockName; }
        public void setStockName(String stockName) { this.stockName = stockName; }
        public BigDecimal getPctChg() { return pctChg; }
        public void setPctChg(BigDecimal pctChg) { this.pctChg = pctChg; }
        public Integer getBoardCount() { return boardCount; }
        public void setBoardCount(Integer boardCount) { this.boardCount = boardCount; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
    }

    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
    public BigDecimal getEmotionScore() { return emotionScore; }
    public void setEmotionScore(BigDecimal emotionScore) { this.emotionScore = emotionScore; }
    public String getEmotionStageName() { return emotionStageName; }
    public void setEmotionStageName(String emotionStageName) { this.emotionStageName = emotionStageName; }
    public String getEmotionGrade() { return emotionGrade; }
    public void setEmotionGrade(String emotionGrade) { this.emotionGrade = emotionGrade; }
    public String getConclusion() { return conclusion; }
    public void setConclusion(String conclusion) { this.conclusion = conclusion; }
    public List<FactorItem> getFactors() { return factors; }
    public void setFactors(List<FactorItem> factors) { this.factors = factors; }
    public CompareInfo getCompareInfo() { return compareInfo; }
    public void setCompareInfo(CompareInfo compareInfo) { this.compareInfo = compareInfo; }
    public List<LimitDetail> getLimitUpDetails() { return limitUpDetails; }
    public void setLimitUpDetails(List<LimitDetail> limitUpDetails) { this.limitUpDetails = limitUpDetails; }
}
