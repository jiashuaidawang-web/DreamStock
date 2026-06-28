package com.dreamstock.domain.entity.clickhouse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DwdStockDailyQuote {

    private LocalDate tradeDate;
    private String stockCode;
    private String stockName;
    private Integer isShanghai;
    private Integer source;
    private BigDecimal price;
    private BigDecimal pctChg;
    private BigDecimal chgAmount;
    private Long volumeHand;
    private BigDecimal amount;
    private BigDecimal amplitudePct;
    private BigDecimal highPrice;
    private BigDecimal lowPrice;
    private BigDecimal openPrice;
    private BigDecimal preClosePrice;
    private BigDecimal volumeRatio;
    private BigDecimal turnoverRatePct;
    private BigDecimal peDynamic;
    private BigDecimal pb;
    private BigDecimal roePct;
    private BigDecimal totalMarketValue;
    private BigDecimal floatMarketValue;
    private Integer limitUpFlag;
    private Integer limitDownFlag;
    private Integer stFlag;
    private Integer suspendedFlag;
    private BigDecimal adjFactor;
    private Integer dataStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String features;

    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
    public String getStockCode() { return stockCode; }
    public void setStockCode(String stockCode) { this.stockCode = stockCode; }
    public String getStockName() { return stockName; }
    public void setStockName(String stockName) { this.stockName = stockName; }
    public Integer getIsShanghai() { return isShanghai; }
    public void setIsShanghai(Integer isShanghai) { this.isShanghai = isShanghai; }
    public Integer getSource() { return source; }
    public void setSource(Integer source) { this.source = source; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public BigDecimal getPctChg() { return pctChg; }
    public void setPctChg(BigDecimal pctChg) { this.pctChg = pctChg; }
    public BigDecimal getChgAmount() { return chgAmount; }
    public void setChgAmount(BigDecimal chgAmount) { this.chgAmount = chgAmount; }
    public Long getVolumeHand() { return volumeHand; }
    public void setVolumeHand(Long volumeHand) { this.volumeHand = volumeHand; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getAmplitudePct() { return amplitudePct; }
    public void setAmplitudePct(BigDecimal amplitudePct) { this.amplitudePct = amplitudePct; }
    public BigDecimal getHighPrice() { return highPrice; }
    public void setHighPrice(BigDecimal highPrice) { this.highPrice = highPrice; }
    public BigDecimal getLowPrice() { return lowPrice; }
    public void setLowPrice(BigDecimal lowPrice) { this.lowPrice = lowPrice; }
    public BigDecimal getOpenPrice() { return openPrice; }
    public void setOpenPrice(BigDecimal openPrice) { this.openPrice = openPrice; }
    public BigDecimal getPreClosePrice() { return preClosePrice; }
    public void setPreClosePrice(BigDecimal preClosePrice) { this.preClosePrice = preClosePrice; }
    public BigDecimal getVolumeRatio() { return volumeRatio; }
    public void setVolumeRatio(BigDecimal volumeRatio) { this.volumeRatio = volumeRatio; }
    public BigDecimal getTurnoverRatePct() { return turnoverRatePct; }
    public void setTurnoverRatePct(BigDecimal turnoverRatePct) { this.turnoverRatePct = turnoverRatePct; }
    public BigDecimal getPeDynamic() { return peDynamic; }
    public void setPeDynamic(BigDecimal peDynamic) { this.peDynamic = peDynamic; }
    public BigDecimal getPb() { return pb; }
    public void setPb(BigDecimal pb) { this.pb = pb; }
    public BigDecimal getRoePct() { return roePct; }
    public void setRoePct(BigDecimal roePct) { this.roePct = roePct; }
    public BigDecimal getTotalMarketValue() { return totalMarketValue; }
    public void setTotalMarketValue(BigDecimal totalMarketValue) { this.totalMarketValue = totalMarketValue; }
    public BigDecimal getFloatMarketValue() { return floatMarketValue; }
    public void setFloatMarketValue(BigDecimal floatMarketValue) { this.floatMarketValue = floatMarketValue; }
    public Integer getLimitUpFlag() { return limitUpFlag; }
    public void setLimitUpFlag(Integer limitUpFlag) { this.limitUpFlag = limitUpFlag; }
    public Integer getLimitDownFlag() { return limitDownFlag; }
    public void setLimitDownFlag(Integer limitDownFlag) { this.limitDownFlag = limitDownFlag; }
    public Integer getStFlag() { return stFlag; }
    public void setStFlag(Integer stFlag) { this.stFlag = stFlag; }
    public Integer getSuspendedFlag() { return suspendedFlag; }
    public void setSuspendedFlag(Integer suspendedFlag) { this.suspendedFlag = suspendedFlag; }
    public BigDecimal getAdjFactor() { return adjFactor; }
    public void setAdjFactor(BigDecimal adjFactor) { this.adjFactor = adjFactor; }
    public Integer getDataStatus() { return dataStatus; }
    public void setDataStatus(Integer dataStatus) { this.dataStatus = dataStatus; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
}
