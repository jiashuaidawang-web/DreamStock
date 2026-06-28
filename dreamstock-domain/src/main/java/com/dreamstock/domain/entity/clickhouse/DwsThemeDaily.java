package com.dreamstock.domain.entity.clickhouse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DwsThemeDaily {

    private LocalDate tradeDate;
    private String themeCode;
    private String themeName;
    private Integer themeType;
    private Long ruleSetId;
    private String calcVersion;
    private BigDecimal themeStrengthScore;
    private Long themeRank;
    private Long continuityDays;
    private Integer heatStage;
    private String leaderStockCode;
    private String leaderStockName;
    private Long limitUpCount;
    private Long above5pctCount;
    private BigDecimal avgPctChg;
    private BigDecimal amount;
    private BigDecimal netInflow;
    private String sectorCodes;
    private String stockCodes;
    private String conclusion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String features;

    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
    public String getThemeCode() { return themeCode; }
    public void setThemeCode(String themeCode) { this.themeCode = themeCode; }
    public String getThemeName() { return themeName; }
    public void setThemeName(String themeName) { this.themeName = themeName; }
    public Integer getThemeType() { return themeType; }
    public void setThemeType(Integer themeType) { this.themeType = themeType; }
    public Long getRuleSetId() { return ruleSetId; }
    public void setRuleSetId(Long ruleSetId) { this.ruleSetId = ruleSetId; }
    public String getCalcVersion() { return calcVersion; }
    public void setCalcVersion(String calcVersion) { this.calcVersion = calcVersion; }
    public BigDecimal getThemeStrengthScore() { return themeStrengthScore; }
    public void setThemeStrengthScore(BigDecimal themeStrengthScore) { this.themeStrengthScore = themeStrengthScore; }
    public Long getThemeRank() { return themeRank; }
    public void setThemeRank(Long themeRank) { this.themeRank = themeRank; }
    public Long getContinuityDays() { return continuityDays; }
    public void setContinuityDays(Long continuityDays) { this.continuityDays = continuityDays; }
    public Integer getHeatStage() { return heatStage; }
    public void setHeatStage(Integer heatStage) { this.heatStage = heatStage; }
    public String getLeaderStockCode() { return leaderStockCode; }
    public void setLeaderStockCode(String leaderStockCode) { this.leaderStockCode = leaderStockCode; }
    public String getLeaderStockName() { return leaderStockName; }
    public void setLeaderStockName(String leaderStockName) { this.leaderStockName = leaderStockName; }
    public Long getLimitUpCount() { return limitUpCount; }
    public void setLimitUpCount(Long limitUpCount) { this.limitUpCount = limitUpCount; }
    public Long getAbove5pctCount() { return above5pctCount; }
    public void setAbove5pctCount(Long above5pctCount) { this.above5pctCount = above5pctCount; }
    public BigDecimal getAvgPctChg() { return avgPctChg; }
    public void setAvgPctChg(BigDecimal avgPctChg) { this.avgPctChg = avgPctChg; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getNetInflow() { return netInflow; }
    public void setNetInflow(BigDecimal netInflow) { this.netInflow = netInflow; }
    public String getSectorCodes() { return sectorCodes; }
    public void setSectorCodes(String sectorCodes) { this.sectorCodes = sectorCodes; }
    public String getStockCodes() { return stockCodes; }
    public void setStockCodes(String stockCodes) { this.stockCodes = stockCodes; }
    public String getConclusion() { return conclusion; }
    public void setConclusion(String conclusion) { this.conclusion = conclusion; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
}
