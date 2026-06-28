package com.dreamstock.domain.entity.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("biz_backtest_task")
public class BizBacktestTask {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String taskNo;
    private String taskName;
    private Long ruleSetId;
    private LocalDate dataStartDate;
    private LocalDate dataEndDate;
    private BigDecimal initialCapital;
    private String benchmarkCode;
    private String stockPoolConfig;
    private BigDecimal feeRate;
    private BigDecimal stampTaxRate;
    private BigDecimal slippageRate;
    private Integer maxPositionCount;
    private BigDecimal singlePositionRatio;
    private Integer status;
    private String errorMessage;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String features;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTaskNo() { return taskNo; }
    public void setTaskNo(String taskNo) { this.taskNo = taskNo; }
    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }
    public Long getRuleSetId() { return ruleSetId; }
    public void setRuleSetId(Long ruleSetId) { this.ruleSetId = ruleSetId; }
    public LocalDate getDataStartDate() { return dataStartDate; }
    public void setDataStartDate(LocalDate dataStartDate) { this.dataStartDate = dataStartDate; }
    public LocalDate getDataEndDate() { return dataEndDate; }
    public void setDataEndDate(LocalDate dataEndDate) { this.dataEndDate = dataEndDate; }
    public BigDecimal getInitialCapital() { return initialCapital; }
    public void setInitialCapital(BigDecimal initialCapital) { this.initialCapital = initialCapital; }
    public String getBenchmarkCode() { return benchmarkCode; }
    public void setBenchmarkCode(String benchmarkCode) { this.benchmarkCode = benchmarkCode; }
    public String getStockPoolConfig() { return stockPoolConfig; }
    public void setStockPoolConfig(String stockPoolConfig) { this.stockPoolConfig = stockPoolConfig; }
    public BigDecimal getFeeRate() { return feeRate; }
    public void setFeeRate(BigDecimal feeRate) { this.feeRate = feeRate; }
    public BigDecimal getStampTaxRate() { return stampTaxRate; }
    public void setStampTaxRate(BigDecimal stampTaxRate) { this.stampTaxRate = stampTaxRate; }
    public BigDecimal getSlippageRate() { return slippageRate; }
    public void setSlippageRate(BigDecimal slippageRate) { this.slippageRate = slippageRate; }
    public Integer getMaxPositionCount() { return maxPositionCount; }
    public void setMaxPositionCount(Integer maxPositionCount) { this.maxPositionCount = maxPositionCount; }
    public BigDecimal getSinglePositionRatio() { return singlePositionRatio; }
    public void setSinglePositionRatio(BigDecimal singlePositionRatio) { this.singlePositionRatio = singlePositionRatio; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }
    public LocalDateTime getFinishedAt() { return finishedAt; }
    public void setFinishedAt(LocalDateTime finishedAt) { this.finishedAt = finishedAt; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
}
