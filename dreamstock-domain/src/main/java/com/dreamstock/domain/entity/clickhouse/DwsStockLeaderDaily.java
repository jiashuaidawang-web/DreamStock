package com.dreamstock.domain.entity.clickhouse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DwsStockLeaderDaily {

    private LocalDate tradeDate;
    private String stockCode;
    private String stockName;
    private Long ruleSetId;
    private String calcVersion;
    private BigDecimal leaderScore;
    private Integer leaderRole;
    private String leaderRoleName;
    private Long marketHeight;
    private Long consecutiveLimitCount;
    private Long limitUpDays10d;
    private LocalDate firstStartDate;
    private String relatedThemeCode;
    private String relatedThemeName;
    private String relatedSectorCodes;
    private Integer status;
    private Integer riskLevel;
    private String reason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String features;

    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
    public String getStockCode() { return stockCode; }
    public void setStockCode(String stockCode) { this.stockCode = stockCode; }
    public String getStockName() { return stockName; }
    public void setStockName(String stockName) { this.stockName = stockName; }
    public Long getRuleSetId() { return ruleSetId; }
    public void setRuleSetId(Long ruleSetId) { this.ruleSetId = ruleSetId; }
    public String getCalcVersion() { return calcVersion; }
    public void setCalcVersion(String calcVersion) { this.calcVersion = calcVersion; }
    public BigDecimal getLeaderScore() { return leaderScore; }
    public void setLeaderScore(BigDecimal leaderScore) { this.leaderScore = leaderScore; }
    public Integer getLeaderRole() { return leaderRole; }
    public void setLeaderRole(Integer leaderRole) { this.leaderRole = leaderRole; }
    public String getLeaderRoleName() { return leaderRoleName; }
    public void setLeaderRoleName(String leaderRoleName) { this.leaderRoleName = leaderRoleName; }
    public Long getMarketHeight() { return marketHeight; }
    public void setMarketHeight(Long marketHeight) { this.marketHeight = marketHeight; }
    public Long getConsecutiveLimitCount() { return consecutiveLimitCount; }
    public void setConsecutiveLimitCount(Long consecutiveLimitCount) { this.consecutiveLimitCount = consecutiveLimitCount; }
    public Long getLimitUpDays10d() { return limitUpDays10d; }
    public void setLimitUpDays10d(Long limitUpDays10d) { this.limitUpDays10d = limitUpDays10d; }
    public LocalDate getFirstStartDate() { return firstStartDate; }
    public void setFirstStartDate(LocalDate firstStartDate) { this.firstStartDate = firstStartDate; }
    public String getRelatedThemeCode() { return relatedThemeCode; }
    public void setRelatedThemeCode(String relatedThemeCode) { this.relatedThemeCode = relatedThemeCode; }
    public String getRelatedThemeName() { return relatedThemeName; }
    public void setRelatedThemeName(String relatedThemeName) { this.relatedThemeName = relatedThemeName; }
    public String getRelatedSectorCodes() { return relatedSectorCodes; }
    public void setRelatedSectorCodes(String relatedSectorCodes) { this.relatedSectorCodes = relatedSectorCodes; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Integer getRiskLevel() { return riskLevel; }
    public void setRiskLevel(Integer riskLevel) { this.riskLevel = riskLevel; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
}
