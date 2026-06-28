package com.dreamstock.domain.entity.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

@TableName("dim_sector")
public class DimSector {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer sectorType;
    private String sectorCode;
    private String sectorName;
    private Integer source;
    private String parentSectorCode;
    private Integer enabled;
    private LocalDateTime firstSeenAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String features;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getSectorType() { return sectorType; }
    public void setSectorType(Integer sectorType) { this.sectorType = sectorType; }
    public String getSectorCode() { return sectorCode; }
    public void setSectorCode(String sectorCode) { this.sectorCode = sectorCode; }
    public String getSectorName() { return sectorName; }
    public void setSectorName(String sectorName) { this.sectorName = sectorName; }
    public Integer getSource() { return source; }
    public void setSource(Integer source) { this.source = source; }
    public String getParentSectorCode() { return parentSectorCode; }
    public void setParentSectorCode(String parentSectorCode) { this.parentSectorCode = parentSectorCode; }
    public Integer getEnabled() { return enabled; }
    public void setEnabled(Integer enabled) { this.enabled = enabled; }
    public LocalDateTime getFirstSeenAt() { return firstSeenAt; }
    public void setFirstSeenAt(LocalDateTime firstSeenAt) { this.firstSeenAt = firstSeenAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
}
