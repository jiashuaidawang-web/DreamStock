package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class StockCorrelationVO {

    private String stockCode;
    private String stockName;
    private Integer windowSize;
    private BigDecimal indexCorr;
    private BigDecimal emotionCorr;
    private BigDecimal themeCorr;
    private Integer rsRank;
    private String positionTag;
    private String divergenceTag;
    private String explanation;
    private List<CorrTrendPoint> corrTrend;

    public static class CorrTrendPoint {
        private LocalDate tradeDate;
        private BigDecimal stockPctChg;
        private BigDecimal indexPctChg;
        private BigDecimal emotionScore;

        public LocalDate getTradeDate() { return tradeDate; }
        public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
        public BigDecimal getStockPctChg() { return stockPctChg; }
        public void setStockPctChg(BigDecimal stockPctChg) { this.stockPctChg = stockPctChg; }
        public BigDecimal getIndexPctChg() { return indexPctChg; }
        public void setIndexPctChg(BigDecimal indexPctChg) { this.indexPctChg = indexPctChg; }
        public BigDecimal getEmotionScore() { return emotionScore; }
        public void setEmotionScore(BigDecimal emotionScore) { this.emotionScore = emotionScore; }
    }

    public String getStockCode() { return stockCode; }
    public void setStockCode(String stockCode) { this.stockCode = stockCode; }
    public String getStockName() { return stockName; }
    public void setStockName(String stockName) { this.stockName = stockName; }
    public Integer getWindowSize() { return windowSize; }
    public void setWindowSize(Integer windowSize) { this.windowSize = windowSize; }
    public BigDecimal getIndexCorr() { return indexCorr; }
    public void setIndexCorr(BigDecimal indexCorr) { this.indexCorr = indexCorr; }
    public BigDecimal getEmotionCorr() { return emotionCorr; }
    public void setEmotionCorr(BigDecimal emotionCorr) { this.emotionCorr = emotionCorr; }
    public BigDecimal getThemeCorr() { return themeCorr; }
    public void setThemeCorr(BigDecimal themeCorr) { this.themeCorr = themeCorr; }
    public Integer getRsRank() { return rsRank; }
    public void setRsRank(Integer rsRank) { this.rsRank = rsRank; }
    public String getPositionTag() { return positionTag; }
    public void setPositionTag(String positionTag) { this.positionTag = positionTag; }
    public String getDivergenceTag() { return divergenceTag; }
    public void setDivergenceTag(String divergenceTag) { this.divergenceTag = divergenceTag; }
    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
    public List<CorrTrendPoint> getCorrTrend() { return corrTrend; }
    public void setCorrTrend(List<CorrTrendPoint> corrTrend) { this.corrTrend = corrTrend; }
}
