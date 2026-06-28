package com.dreamstock.domain.entity.clickhouse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DwsMarketEmotionDaily {

    private LocalDate tradeDate;
    private Long ruleSetId;
    private String calcVersion;
    private LocalDateTime dataSnapshotTime;
    private BigDecimal emotionScore;
    private Integer emotionStage;
    private String emotionStageName;
    private BigDecimal trendScore;
    private BigDecimal indexScore;
    private BigDecimal moneyScore;
    private BigDecimal profitEffectScore;
    private BigDecimal lossEffectScore;
    private BigDecimal limitEffectScore;
    private BigDecimal themeScore;
    private BigDecimal leaderScore;
    private BigDecimal riskScore;
    private Long upCount;
    private Long downCount;
    private Long limitUpCount;
    private Long limitDownCount;
    private Long openBoardCount;
    private Long consecutiveLimitHigh;
    private Long above5pctCount;
    private Long belowMinus5pctCount;
    private BigDecimal totalAmount;
    private BigDecimal northMoneyNetInflow;
    private String conclusion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String features;

    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
    public Long getRuleSetId() { return ruleSetId; }
    public void setRuleSetId(Long ruleSetId) { this.ruleSetId = ruleSetId; }
    public String getCalcVersion() { return calcVersion; }
    public void setCalcVersion(String calcVersion) { this.calcVersion = calcVersion; }
    public LocalDateTime getDataSnapshotTime() { return dataSnapshotTime; }
    public void setDataSnapshotTime(LocalDateTime dataSnapshotTime) { this.dataSnapshotTime = dataSnapshotTime; }
    public BigDecimal getEmotionScore() { return emotionScore; }
    public void setEmotionScore(BigDecimal emotionScore) { this.emotionScore = emotionScore; }
    public Integer getEmotionStage() { return emotionStage; }
    public void setEmotionStage(Integer emotionStage) { this.emotionStage = emotionStage; }
    public String getEmotionStageName() { return emotionStageName; }
    public void setEmotionStageName(String emotionStageName) { this.emotionStageName = emotionStageName; }
    public BigDecimal getTrendScore() { return trendScore; }
    public void setTrendScore(BigDecimal trendScore) { this.trendScore = trendScore; }
    public BigDecimal getIndexScore() { return indexScore; }
    public void setIndexScore(BigDecimal indexScore) { this.indexScore = indexScore; }
    public BigDecimal getMoneyScore() { return moneyScore; }
    public void setMoneyScore(BigDecimal moneyScore) { this.moneyScore = moneyScore; }
    public BigDecimal getProfitEffectScore() { return profitEffectScore; }
    public void setProfitEffectScore(BigDecimal profitEffectScore) { this.profitEffectScore = profitEffectScore; }
    public BigDecimal getLossEffectScore() { return lossEffectScore; }
    public void setLossEffectScore(BigDecimal lossEffectScore) { this.lossEffectScore = lossEffectScore; }
    public BigDecimal getLimitEffectScore() { return limitEffectScore; }
    public void setLimitEffectScore(BigDecimal limitEffectScore) { this.limitEffectScore = limitEffectScore; }
    public BigDecimal getThemeScore() { return themeScore; }
    public void setThemeScore(BigDecimal themeScore) { this.themeScore = themeScore; }
    public BigDecimal getLeaderScore() { return leaderScore; }
    public void setLeaderScore(BigDecimal leaderScore) { this.leaderScore = leaderScore; }
    public BigDecimal getRiskScore() { return riskScore; }
    public void setRiskScore(BigDecimal riskScore) { this.riskScore = riskScore; }
    public Long getUpCount() { return upCount; }
    public void setUpCount(Long upCount) { this.upCount = upCount; }
    public Long getDownCount() { return downCount; }
    public void setDownCount(Long downCount) { this.downCount = downCount; }
    public Long getLimitUpCount() { return limitUpCount; }
    public void setLimitUpCount(Long limitUpCount) { this.limitUpCount = limitUpCount; }
    public Long getLimitDownCount() { return limitDownCount; }
    public void setLimitDownCount(Long limitDownCount) { this.limitDownCount = limitDownCount; }
    public Long getOpenBoardCount() { return openBoardCount; }
    public void setOpenBoardCount(Long openBoardCount) { this.openBoardCount = openBoardCount; }
    public Long getConsecutiveLimitHigh() { return consecutiveLimitHigh; }
    public void setConsecutiveLimitHigh(Long consecutiveLimitHigh) { this.consecutiveLimitHigh = consecutiveLimitHigh; }
    public Long getAbove5pctCount() { return above5pctCount; }
    public void setAbove5pctCount(Long above5pctCount) { this.above5pctCount = above5pctCount; }
    public Long getBelowMinus5pctCount() { return belowMinus5pctCount; }
    public void setBelowMinus5pctCount(Long belowMinus5pctCount) { this.belowMinus5pctCount = belowMinus5pctCount; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public BigDecimal getNorthMoneyNetInflow() { return northMoneyNetInflow; }
    public void setNorthMoneyNetInflow(BigDecimal northMoneyNetInflow) { this.northMoneyNetInflow = northMoneyNetInflow; }
    public String getConclusion() { return conclusion; }
    public void setConclusion(String conclusion) { this.conclusion = conclusion; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
}
