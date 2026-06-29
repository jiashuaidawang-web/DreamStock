package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LimitBoardVO {

    private LocalDate tradeDate;
    private Integer firstBoardCount;
    private Integer secondBoardCount;
    private Integer thirdBoardCount;
    private Integer highBoardCount;
    private Integer limitDownCount;
    private BigDecimal failedLimitRate;
    private BigDecimal promotionRate;
    private Integer maxBoardHeight;
    private List<PoolItem> limitUpPool;
    private List<PoolItem> limitDownPool;
    private List<PoolItem> failedBoardPool;
    private List<PoolItem> yesterdayLimitFeedback;

    public static class PoolItem {
        private String stockCode;
        private String stockName;
        private BigDecimal pctChg;
        private BigDecimal amount;
        private Integer boardCount;
        private String reason;
        private String sealTime;
        private BigDecimal sealAmount;
        private Integer openCount;
        private BigDecimal turnoverRate;

        public String getStockCode() { return stockCode; }
        public void setStockCode(String stockCode) { this.stockCode = stockCode; }
        public String getStockName() { return stockName; }
        public void setStockName(String stockName) { this.stockName = stockName; }
        public BigDecimal getPctChg() { return pctChg; }
        public void setPctChg(BigDecimal pctChg) { this.pctChg = pctChg; }
        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }
        public Integer getBoardCount() { return boardCount; }
        public void setBoardCount(Integer boardCount) { this.boardCount = boardCount; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
        public String getSealTime() { return sealTime; }
        public void setSealTime(String sealTime) { this.sealTime = sealTime; }
        public BigDecimal getSealAmount() { return sealAmount; }
        public void setSealAmount(BigDecimal sealAmount) { this.sealAmount = sealAmount; }
        public Integer getOpenCount() { return openCount; }
        public void setOpenCount(Integer openCount) { this.openCount = openCount; }
        public BigDecimal getTurnoverRate() { return turnoverRate; }
        public void setTurnoverRate(BigDecimal turnoverRate) { this.turnoverRate = turnoverRate; }
    }

    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
    public Integer getFirstBoardCount() { return firstBoardCount; }
    public void setFirstBoardCount(Integer firstBoardCount) { this.firstBoardCount = firstBoardCount; }
    public Integer getSecondBoardCount() { return secondBoardCount; }
    public void setSecondBoardCount(Integer secondBoardCount) { this.secondBoardCount = secondBoardCount; }
    public Integer getThirdBoardCount() { return thirdBoardCount; }
    public void setThirdBoardCount(Integer thirdBoardCount) { this.thirdBoardCount = thirdBoardCount; }
    public Integer getHighBoardCount() { return highBoardCount; }
    public void setHighBoardCount(Integer highBoardCount) { this.highBoardCount = highBoardCount; }
    public Integer getLimitDownCount() { return limitDownCount; }
    public void setLimitDownCount(Integer limitDownCount) { this.limitDownCount = limitDownCount; }
    public BigDecimal getFailedLimitRate() { return failedLimitRate; }
    public void setFailedLimitRate(BigDecimal failedLimitRate) { this.failedLimitRate = failedLimitRate; }
    public BigDecimal getPromotionRate() { return promotionRate; }
    public void setPromotionRate(BigDecimal promotionRate) { this.promotionRate = promotionRate; }
    public Integer getMaxBoardHeight() { return maxBoardHeight; }
    public void setMaxBoardHeight(Integer maxBoardHeight) { this.maxBoardHeight = maxBoardHeight; }
    public List<PoolItem> getLimitUpPool() { return limitUpPool; }
    public void setLimitUpPool(List<PoolItem> limitUpPool) { this.limitUpPool = limitUpPool; }
    public List<PoolItem> getLimitDownPool() { return limitDownPool; }
    public void setLimitDownPool(List<PoolItem> limitDownPool) { this.limitDownPool = limitDownPool; }
    public List<PoolItem> getFailedBoardPool() { return failedBoardPool; }
    public void setFailedBoardPool(List<PoolItem> failedBoardPool) { this.failedBoardPool = failedBoardPool; }
    public List<PoolItem> getYesterdayLimitFeedback() { return yesterdayLimitFeedback; }
    public void setYesterdayLimitFeedback(List<PoolItem> yesterdayLimitFeedback) { this.yesterdayLimitFeedback = yesterdayLimitFeedback; }
}
