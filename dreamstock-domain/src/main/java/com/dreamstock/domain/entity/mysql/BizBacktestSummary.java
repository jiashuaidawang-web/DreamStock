package com.dreamstock.domain.entity.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("biz_backtest_summary")
public class BizBacktestSummary {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long taskId;
    private String taskNo;
    private BigDecimal totalReturnPct;
    private BigDecimal annualReturnPct;
    private BigDecimal maxDrawdownPct;
    private BigDecimal sharpeRatio;
    private BigDecimal winRatePct;
    private BigDecimal profitLossRatio;
    private Integer tradeCount;
    private Integer winTradeCount;
    private Integer lossTradeCount;
    private BigDecimal avgHoldingDays;
    private BigDecimal bestTradeReturnPct;
    private BigDecimal worstTradeReturnPct;
    private BigDecimal finalCapital;
    private BigDecimal benchmarkReturnPct;
    private BigDecimal excessReturnPct;
    private String summaryJson;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String features;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }
    public String getTaskNo() { return taskNo; }
    public void setTaskNo(String taskNo) { this.taskNo = taskNo; }
    public BigDecimal getTotalReturnPct() { return totalReturnPct; }
    public void setTotalReturnPct(BigDecimal totalReturnPct) { this.totalReturnPct = totalReturnPct; }
    public BigDecimal getAnnualReturnPct() { return annualReturnPct; }
    public void setAnnualReturnPct(BigDecimal annualReturnPct) { this.annualReturnPct = annualReturnPct; }
    public BigDecimal getMaxDrawdownPct() { return maxDrawdownPct; }
    public void setMaxDrawdownPct(BigDecimal maxDrawdownPct) { this.maxDrawdownPct = maxDrawdownPct; }
    public BigDecimal getSharpeRatio() { return sharpeRatio; }
    public void setSharpeRatio(BigDecimal sharpeRatio) { this.sharpeRatio = sharpeRatio; }
    public BigDecimal getWinRatePct() { return winRatePct; }
    public void setWinRatePct(BigDecimal winRatePct) { this.winRatePct = winRatePct; }
    public BigDecimal getProfitLossRatio() { return profitLossRatio; }
    public void setProfitLossRatio(BigDecimal profitLossRatio) { this.profitLossRatio = profitLossRatio; }
    public Integer getTradeCount() { return tradeCount; }
    public void setTradeCount(Integer tradeCount) { this.tradeCount = tradeCount; }
    public Integer getWinTradeCount() { return winTradeCount; }
    public void setWinTradeCount(Integer winTradeCount) { this.winTradeCount = winTradeCount; }
    public Integer getLossTradeCount() { return lossTradeCount; }
    public void setLossTradeCount(Integer lossTradeCount) { this.lossTradeCount = lossTradeCount; }
    public BigDecimal getAvgHoldingDays() { return avgHoldingDays; }
    public void setAvgHoldingDays(BigDecimal avgHoldingDays) { this.avgHoldingDays = avgHoldingDays; }
    public BigDecimal getBestTradeReturnPct() { return bestTradeReturnPct; }
    public void setBestTradeReturnPct(BigDecimal bestTradeReturnPct) { this.bestTradeReturnPct = bestTradeReturnPct; }
    public BigDecimal getWorstTradeReturnPct() { return worstTradeReturnPct; }
    public void setWorstTradeReturnPct(BigDecimal worstTradeReturnPct) { this.worstTradeReturnPct = worstTradeReturnPct; }
    public BigDecimal getFinalCapital() { return finalCapital; }
    public void setFinalCapital(BigDecimal finalCapital) { this.finalCapital = finalCapital; }
    public BigDecimal getBenchmarkReturnPct() { return benchmarkReturnPct; }
    public void setBenchmarkReturnPct(BigDecimal benchmarkReturnPct) { this.benchmarkReturnPct = benchmarkReturnPct; }
    public BigDecimal getExcessReturnPct() { return excessReturnPct; }
    public void setExcessReturnPct(BigDecimal excessReturnPct) { this.excessReturnPct = excessReturnPct; }
    public String getSummaryJson() { return summaryJson; }
    public void setSummaryJson(String summaryJson) { this.summaryJson = summaryJson; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
}
