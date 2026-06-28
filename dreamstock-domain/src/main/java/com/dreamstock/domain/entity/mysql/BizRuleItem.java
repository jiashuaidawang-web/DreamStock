package com.dreamstock.domain.entity.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("biz_rule_item")
public class BizRuleItem {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long ruleSetId;
    private String itemCode;
    private String itemName;
    private String factorCode;
    private String operator;
    private String thresholdValue;
    private BigDecimal weight;
    private Integer scoreDirection;
    private Integer requiredFlag;
    private Integer enabled;
    private String formulaText;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String features;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getRuleSetId() { return ruleSetId; }
    public void setRuleSetId(Long ruleSetId) { this.ruleSetId = ruleSetId; }
    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getFactorCode() { return factorCode; }
    public void setFactorCode(String factorCode) { this.factorCode = factorCode; }
    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }
    public String getThresholdValue() { return thresholdValue; }
    public void setThresholdValue(String thresholdValue) { this.thresholdValue = thresholdValue; }
    public BigDecimal getWeight() { return weight; }
    public void setWeight(BigDecimal weight) { this.weight = weight; }
    public Integer getScoreDirection() { return scoreDirection; }
    public void setScoreDirection(Integer scoreDirection) { this.scoreDirection = scoreDirection; }
    public Integer getRequiredFlag() { return requiredFlag; }
    public void setRequiredFlag(Integer requiredFlag) { this.requiredFlag = requiredFlag; }
    public Integer getEnabled() { return enabled; }
    public void setEnabled(Integer enabled) { this.enabled = enabled; }
    public String getFormulaText() { return formulaText; }
    public void setFormulaText(String formulaText) { this.formulaText = formulaText; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
}
