package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DivergenceVO {

    private LocalDate tradeDate;
    private List<DivergenceItem> items;

    public static class DivergenceItem {
        private String stockCode;
        private String stockName;
        private Integer boardCount;
        private Integer openCount;
        private BigDecimal sealStrength;
        private BigDecimal turnoverRate;
        private BigDecimal amount;
        private BigDecimal sealAmount;
        private String divergenceStatus;
        private BigDecimal nextDayPremium;
        private String themeName;
        private String riskTag;

        public String getStockCode() { return stockCode; }
        public void setStockCode(String stockCode) { this.stockCode = stockCode; }
        public String getStockName() { return stockName; }
        public void setStockName(String stockName) { this.stockName = stockName; }
        public Integer getBoardCount() { return boardCount; }
        public void setBoardCount(Integer boardCount) { this.boardCount = boardCount; }
        public Integer getOpenCount() { return openCount; }
        public void setOpenCount(Integer openCount) { this.openCount = openCount; }
        public BigDecimal getSealStrength() { return sealStrength; }
        public void setSealStrength(BigDecimal sealStrength) { this.sealStrength = sealStrength; }
        public BigDecimal getTurnoverRate() { return turnoverRate; }
        public void setTurnoverRate(BigDecimal turnoverRate) { this.turnoverRate = turnoverRate; }
        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }
        public BigDecimal getSealAmount() { return sealAmount; }
        public void setSealAmount(BigDecimal sealAmount) { this.sealAmount = sealAmount; }
        public String getDivergenceStatus() { return divergenceStatus; }
        public void setDivergenceStatus(String divergenceStatus) { this.divergenceStatus = divergenceStatus; }
        public BigDecimal getNextDayPremium() { return nextDayPremium; }
        public void setNextDayPremium(BigDecimal nextDayPremium) { this.nextDayPremium = nextDayPremium; }
        public String getThemeName() { return themeName; }
        public void setThemeName(String themeName) { this.themeName = themeName; }
        public String getRiskTag() { return riskTag; }
        public void setRiskTag(String riskTag) { this.riskTag = riskTag; }
    }

    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
    public List<DivergenceItem> getItems() { return items; }
    public void setItems(List<DivergenceItem> items) { this.items = items; }
}
