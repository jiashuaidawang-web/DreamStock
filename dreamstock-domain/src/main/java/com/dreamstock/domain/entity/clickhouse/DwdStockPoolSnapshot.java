package com.dreamstock.domain.entity.clickhouse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DwdStockPoolSnapshot {

    private LocalDate tradeDate;
    private LocalDateTime requestTime;
    private Integer poolType;
    private Integer source;
    private String stockCode;
    private String stockName;
    private BigDecimal price;
    private BigDecimal pctChg;
    private BigDecimal amount;
    private BigDecimal turnoverRatePct;
    private BigDecimal totalMarketValue;
    private BigDecimal floatMarketValue;
    private BigDecimal sealAmount;
    private LocalDateTime firstSealTime;
    private LocalDateTime lastSealTime;
    private Long openBoardCount;
    private Long consecutiveLimitCount;
    private String industrySector;
    private Long totalDays;
    private Long limitUpDays;
    private Long poolRank;
    private String reason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String features;

    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
    public LocalDateTime getRequestTime() { return requestTime; }
    public void setRequestTime(LocalDateTime requestTime) { this.requestTime = requestTime; }
    public Integer getPoolType() { return poolType; }
    public void setPoolType(Integer poolType) { this.poolType = poolType; }
    public Integer getSource() { return source; }
    public void setSource(Integer source) { this.source = source; }
    public String getStockCode() { return stockCode; }
    public void setStockCode(String stockCode) { this.stockCode = stockCode; }
    public String getStockName() { return stockName; }
    public void setStockName(String stockName) { this.stockName = stockName; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public BigDecimal getPctChg() { return pctChg; }
    public void setPctChg(BigDecimal pctChg) { this.pctChg = pctChg; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getTurnoverRatePct() { return turnoverRatePct; }
    public void setTurnoverRatePct(BigDecimal turnoverRatePct) { this.turnoverRatePct = turnoverRatePct; }
    public BigDecimal getTotalMarketValue() { return totalMarketValue; }
    public void setTotalMarketValue(BigDecimal totalMarketValue) { this.totalMarketValue = totalMarketValue; }
    public BigDecimal getFloatMarketValue() { return floatMarketValue; }
    public void setFloatMarketValue(BigDecimal floatMarketValue) { this.floatMarketValue = floatMarketValue; }
    public BigDecimal getSealAmount() { return sealAmount; }
    public void setSealAmount(BigDecimal sealAmount) { this.sealAmount = sealAmount; }
    public LocalDateTime getFirstSealTime() { return firstSealTime; }
    public void setFirstSealTime(LocalDateTime firstSealTime) { this.firstSealTime = firstSealTime; }
    public LocalDateTime getLastSealTime() { return lastSealTime; }
    public void setLastSealTime(LocalDateTime lastSealTime) { this.lastSealTime = lastSealTime; }
    public Long getOpenBoardCount() { return openBoardCount; }
    public void setOpenBoardCount(Long openBoardCount) { this.openBoardCount = openBoardCount; }
    public Long getConsecutiveLimitCount() { return consecutiveLimitCount; }
    public void setConsecutiveLimitCount(Long consecutiveLimitCount) { this.consecutiveLimitCount = consecutiveLimitCount; }
    public String getIndustrySector() { return industrySector; }
    public void setIndustrySector(String industrySector) { this.industrySector = industrySector; }
    public Long getTotalDays() { return totalDays; }
    public void setTotalDays(Long totalDays) { this.totalDays = totalDays; }
    public Long getLimitUpDays() { return limitUpDays; }
    public void setLimitUpDays(Long limitUpDays) { this.limitUpDays = limitUpDays; }
    public Long getPoolRank() { return poolRank; }
    public void setPoolRank(Long poolRank) { this.poolRank = poolRank; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
}
