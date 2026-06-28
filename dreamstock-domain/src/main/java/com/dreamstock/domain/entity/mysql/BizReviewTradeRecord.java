package com.dreamstock.domain.entity.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("biz_review_trade_record")
public class BizReviewTradeRecord {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long journalId;
    private Long userId;
    private LocalDate tradeDate;
    private String stockCode;
    private String stockName;
    private Integer operationType;
    private LocalDateTime operationTime;
    private BigDecimal operationPrice;
    private BigDecimal operationAmount;
    private BigDecimal positionRatio;
    private String buyReason;
    private String sellReason;
    private String matchedRuleCode;
    private String expectedResult;
    private String actualResult;
    private Integer mistakeType;
    private BigDecimal pnlAmount;
    private BigDecimal pnlPct;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String features;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getJournalId() { return journalId; }
    public void setJournalId(Long journalId) { this.journalId = journalId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
    public String getStockCode() { return stockCode; }
    public void setStockCode(String stockCode) { this.stockCode = stockCode; }
    public String getStockName() { return stockName; }
    public void setStockName(String stockName) { this.stockName = stockName; }
    public Integer getOperationType() { return operationType; }
    public void setOperationType(Integer operationType) { this.operationType = operationType; }
    public LocalDateTime getOperationTime() { return operationTime; }
    public void setOperationTime(LocalDateTime operationTime) { this.operationTime = operationTime; }
    public BigDecimal getOperationPrice() { return operationPrice; }
    public void setOperationPrice(BigDecimal operationPrice) { this.operationPrice = operationPrice; }
    public BigDecimal getOperationAmount() { return operationAmount; }
    public void setOperationAmount(BigDecimal operationAmount) { this.operationAmount = operationAmount; }
    public BigDecimal getPositionRatio() { return positionRatio; }
    public void setPositionRatio(BigDecimal positionRatio) { this.positionRatio = positionRatio; }
    public String getBuyReason() { return buyReason; }
    public void setBuyReason(String buyReason) { this.buyReason = buyReason; }
    public String getSellReason() { return sellReason; }
    public void setSellReason(String sellReason) { this.sellReason = sellReason; }
    public String getMatchedRuleCode() { return matchedRuleCode; }
    public void setMatchedRuleCode(String matchedRuleCode) { this.matchedRuleCode = matchedRuleCode; }
    public String getExpectedResult() { return expectedResult; }
    public void setExpectedResult(String expectedResult) { this.expectedResult = expectedResult; }
    public String getActualResult() { return actualResult; }
    public void setActualResult(String actualResult) { this.actualResult = actualResult; }
    public Integer getMistakeType() { return mistakeType; }
    public void setMistakeType(Integer mistakeType) { this.mistakeType = mistakeType; }
    public BigDecimal getPnlAmount() { return pnlAmount; }
    public void setPnlAmount(BigDecimal pnlAmount) { this.pnlAmount = pnlAmount; }
    public BigDecimal getPnlPct() { return pnlPct; }
    public void setPnlPct(BigDecimal pnlPct) { this.pnlPct = pnlPct; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
}
