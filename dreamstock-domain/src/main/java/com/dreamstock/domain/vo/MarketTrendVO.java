package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class MarketTrendVO {

    private List<IndexItem> indices;
    private String trendStructure;
    private String technicalCycle;
    private String indexEmotionRelation;
    private List<KeyLevel> keyLevels;

    public static class IndexItem {
        private String indexCode;
        private String indexName;
        private BigDecimal close;
        private BigDecimal pctChg;
        private BigDecimal totalAmount;
        private String ma5Status;
        private String ma10Status;
        private String ma20Status;
        private String ma60Status;
        private String trendLabel;
        private List<KLinePoint> kLines;

        public String getIndexCode() { return indexCode; }
        public void setIndexCode(String indexCode) { this.indexCode = indexCode; }
        public String getIndexName() { return indexName; }
        public void setIndexName(String indexName) { this.indexName = indexName; }
        public BigDecimal getClose() { return close; }
        public void setClose(BigDecimal close) { this.close = close; }
        public BigDecimal getPctChg() { return pctChg; }
        public void setPctChg(BigDecimal pctChg) { this.pctChg = pctChg; }
        public BigDecimal getTotalAmount() { return totalAmount; }
        public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
        public String getMa5Status() { return ma5Status; }
        public void setMa5Status(String ma5Status) { this.ma5Status = ma5Status; }
        public String getMa10Status() { return ma10Status; }
        public void setMa10Status(String ma10Status) { this.ma10Status = ma10Status; }
        public String getMa20Status() { return ma20Status; }
        public void setMa20Status(String ma20Status) { this.ma20Status = ma20Status; }
        public String getMa60Status() { return ma60Status; }
        public void setMa60Status(String ma60Status) { this.ma60Status = ma60Status; }
        public String getTrendLabel() { return trendLabel; }
        public void setTrendLabel(String trendLabel) { this.trendLabel = trendLabel; }
        public List<KLinePoint> getKLines() { return kLines; }
        public void setKLines(List<KLinePoint> kLines) { this.kLines = kLines; }
    }

    public static class KLinePoint {
        private LocalDate tradeDate;
        private BigDecimal open;
        private BigDecimal close;
        private BigDecimal high;
        private BigDecimal low;
        private BigDecimal volume;

        public LocalDate getTradeDate() { return tradeDate; }
        public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
        public BigDecimal getOpen() { return open; }
        public void setOpen(BigDecimal open) { this.open = open; }
        public BigDecimal getClose() { return close; }
        public void setClose(BigDecimal close) { this.close = close; }
        public BigDecimal getHigh() { return high; }
        public void setHigh(BigDecimal high) { this.high = high; }
        public BigDecimal getLow() { return low; }
        public void setLow(BigDecimal low) { this.low = low; }
        public BigDecimal getVolume() { return volume; }
        public void setVolume(BigDecimal volume) { this.volume = volume; }
    }

    public static class KeyLevel {
        private String levelType;
        private BigDecimal price;
        private String description;

        public String getLevelType() { return levelType; }
        public void setLevelType(String levelType) { this.levelType = levelType; }
        public BigDecimal getPrice() { return price; }
        public void setPrice(BigDecimal price) { this.price = price; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public List<IndexItem> getIndices() { return indices; }
    public void setIndices(List<IndexItem> indices) { this.indices = indices; }
    public String getTrendStructure() { return trendStructure; }
    public void setTrendStructure(String trendStructure) { this.trendStructure = trendStructure; }
    public String getTechnicalCycle() { return technicalCycle; }
    public void setTechnicalCycle(String technicalCycle) { this.technicalCycle = technicalCycle; }
    public String getIndexEmotionRelation() { return indexEmotionRelation; }
    public void setIndexEmotionRelation(String indexEmotionRelation) { this.indexEmotionRelation = indexEmotionRelation; }
    public List<KeyLevel> getKeyLevels() { return keyLevels; }
    public void setKeyLevels(List<KeyLevel> keyLevels) { this.keyLevels = keyLevels; }
}
