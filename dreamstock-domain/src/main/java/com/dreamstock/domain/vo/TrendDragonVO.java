package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TrendDragonVO {

    private List<TrendStockItem> stocks;

    public static class TrendStockItem {
        private String stockCode;
        private String stockName;
        private BigDecimal close;
        private BigDecimal pctChg;
        private BigDecimal trendScore;
        private String maStatus;
        private String breakoutStatus;
        private Integer rsRank;
        private BigDecimal totalMarketValue;
        private String industrySector;
        private String weeklyTrendStatus;
        private BigDecimal volumeRatio;
        private String riskTag;

        public String getStockCode() { return stockCode; }
        public void setStockCode(String stockCode) { this.stockCode = stockCode; }
        public String getStockName() { return stockName; }
        public void setStockName(String stockName) { this.stockName = stockName; }
        public BigDecimal getClose() { return close; }
        public void setClose(BigDecimal close) { this.close = close; }
        public BigDecimal getPctChg() { return pctChg; }
        public void setPctChg(BigDecimal pctChg) { this.pctChg = pctChg; }
        public BigDecimal getTrendScore() { return trendScore; }
        public void setTrendScore(BigDecimal trendScore) { this.trendScore = trendScore; }
        public String getMaStatus() { return maStatus; }
        public void setMaStatus(String maStatus) { this.maStatus = maStatus; }
        public String getBreakoutStatus() { return breakoutStatus; }
        public void setBreakoutStatus(String breakoutStatus) { this.breakoutStatus = breakoutStatus; }
        public Integer getRsRank() { return rsRank; }
        public void setRsRank(Integer rsRank) { this.rsRank = rsRank; }
        public BigDecimal getTotalMarketValue() { return totalMarketValue; }
        public void setTotalMarketValue(BigDecimal totalMarketValue) { this.totalMarketValue = totalMarketValue; }
        public String getIndustrySector() { return industrySector; }
        public void setIndustrySector(String industrySector) { this.industrySector = industrySector; }
        public String getWeeklyTrendStatus() { return weeklyTrendStatus; }
        public void setWeeklyTrendStatus(String weeklyTrendStatus) { this.weeklyTrendStatus = weeklyTrendStatus; }
        public BigDecimal getVolumeRatio() { return volumeRatio; }
        public void setVolumeRatio(BigDecimal volumeRatio) { this.volumeRatio = volumeRatio; }
        public String getRiskTag() { return riskTag; }
        public void setRiskTag(String riskTag) { this.riskTag = riskTag; }
    }

    public List<TrendStockItem> getStocks() { return stocks; }
    public void setStocks(List<TrendStockItem> stocks) { this.stocks = stocks; }
}
