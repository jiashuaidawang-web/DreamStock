package com.dreamstock.domain.entity.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("dim_stock_security")
public class DimStockSecurity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String stockCode;
    private String stockName;
    private String stockNamePinyin;
    private Integer isShanghai;
    private String exchangeCode;
    private Integer marketType;
    private Integer listingStatus;
    private LocalDate listDate;
    private LocalDate delistDate;
    private String industryCode;
    private String industryName;
    private Integer isSt;
    private Integer isRegistrationSystem;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String features;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStockCode() { return stockCode; }
    public void setStockCode(String stockCode) { this.stockCode = stockCode; }
    public String getStockName() { return stockName; }
    public void setStockName(String stockName) { this.stockName = stockName; }
    public String getStockNamePinyin() { return stockNamePinyin; }
    public void setStockNamePinyin(String stockNamePinyin) { this.stockNamePinyin = stockNamePinyin; }
    public Integer getIsShanghai() { return isShanghai; }
    public void setIsShanghai(Integer isShanghai) { this.isShanghai = isShanghai; }
    public String getExchangeCode() { return exchangeCode; }
    public void setExchangeCode(String exchangeCode) { this.exchangeCode = exchangeCode; }
    public Integer getMarketType() { return marketType; }
    public void setMarketType(Integer marketType) { this.marketType = marketType; }
    public Integer getListingStatus() { return listingStatus; }
    public void setListingStatus(Integer listingStatus) { this.listingStatus = listingStatus; }
    public LocalDate getListDate() { return listDate; }
    public void setListDate(LocalDate listDate) { this.listDate = listDate; }
    public LocalDate getDelistDate() { return delistDate; }
    public void setDelistDate(LocalDate delistDate) { this.delistDate = delistDate; }
    public String getIndustryCode() { return industryCode; }
    public void setIndustryCode(String industryCode) { this.industryCode = industryCode; }
    public String getIndustryName() { return industryName; }
    public void setIndustryName(String industryName) { this.industryName = industryName; }
    public Integer getIsSt() { return isSt; }
    public void setIsSt(Integer isSt) { this.isSt = isSt; }
    public Integer getIsRegistrationSystem() { return isRegistrationSystem; }
    public void setIsRegistrationSystem(Integer isRegistrationSystem) { this.isRegistrationSystem = isRegistrationSystem; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
}
