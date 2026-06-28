package com.dreamstock.domain.entity.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("biz_rule_set")
public class BizRuleSet {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String ruleSetCode;
    private String ruleSetName;
    private String ruleDomain;
    private String versionNo;
    private Integer status;
    private LocalDate effectiveStartDate;
    private LocalDate effectiveEndDate;
    private String configJson;
    private String description;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String features;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRuleSetCode() { return ruleSetCode; }
    public void setRuleSetCode(String ruleSetCode) { this.ruleSetCode = ruleSetCode; }
    public String getRuleSetName() { return ruleSetName; }
    public void setRuleSetName(String ruleSetName) { this.ruleSetName = ruleSetName; }
    public String getRuleDomain() { return ruleDomain; }
    public void setRuleDomain(String ruleDomain) { this.ruleDomain = ruleDomain; }
    public String getVersionNo() { return versionNo; }
    public void setVersionNo(String versionNo) { this.versionNo = versionNo; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public LocalDate getEffectiveStartDate() { return effectiveStartDate; }
    public void setEffectiveStartDate(LocalDate effectiveStartDate) { this.effectiveStartDate = effectiveStartDate; }
    public LocalDate getEffectiveEndDate() { return effectiveEndDate; }
    public void setEffectiveEndDate(LocalDate effectiveEndDate) { this.effectiveEndDate = effectiveEndDate; }
    public String getConfigJson() { return configJson; }
    public void setConfigJson(String configJson) { this.configJson = configJson; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
}
