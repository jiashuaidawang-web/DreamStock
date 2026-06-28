package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LeaderLadderVO {

    private LocalDate tradeDate;
    private Long maxConsecutiveBoard;
    private BigDecimal totalBidAmount;
    private Long hotStockCount;
    private List<BoardTier> tiers;

    public static class BoardTier {
        private Long boardCount;
        private Long stockCount;
        private BigDecimal totalBidAmount;
        private List<LeaderCard> leaders;

        public Long getBoardCount() { return boardCount; }
        public void setBoardCount(Long boardCount) { this.boardCount = boardCount; }
        public Long getStockCount() { return stockCount; }
        public void setStockCount(Long stockCount) { this.stockCount = stockCount; }
        public BigDecimal getTotalBidAmount() { return totalBidAmount; }
        public void setTotalBidAmount(BigDecimal totalBidAmount) { this.totalBidAmount = totalBidAmount; }
        public List<LeaderCard> getLeaders() { return leaders; }
        public void setLeaders(List<LeaderCard> leaders) { this.leaders = leaders; }
    }

    public static class LeaderCard {
        private String stockCode;
        private String stockName;
        private String leaderRoleName;
        private String statusName;
        private BigDecimal bidAmount;
        private BigDecimal pctChg;
        private String industrySector;
        private Integer riskLevel;

        public String getStockCode() { return stockCode; }
        public void setStockCode(String stockCode) { this.stockCode = stockCode; }
        public String getStockName() { return stockName; }
        public void setStockName(String stockName) { this.stockName = stockName; }
        public String getLeaderRoleName() { return leaderRoleName; }
        public void setLeaderRoleName(String leaderRoleName) { this.leaderRoleName = leaderRoleName; }
        public String getStatusName() { return statusName; }
        public void setStatusName(String statusName) { this.statusName = statusName; }
        public BigDecimal getBidAmount() { return bidAmount; }
        public void setBidAmount(BigDecimal bidAmount) { this.bidAmount = bidAmount; }
        public BigDecimal getPctChg() { return pctChg; }
        public void setPctChg(BigDecimal pctChg) { this.pctChg = pctChg; }
        public String getIndustrySector() { return industrySector; }
        public void setIndustrySector(String industrySector) { this.industrySector = industrySector; }
        public Integer getRiskLevel() { return riskLevel; }
        public void setRiskLevel(Integer riskLevel) { this.riskLevel = riskLevel; }
    }

    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
    public Long getMaxConsecutiveBoard() { return maxConsecutiveBoard; }
    public void setMaxConsecutiveBoard(Long maxConsecutiveBoard) { this.maxConsecutiveBoard = maxConsecutiveBoard; }
    public BigDecimal getTotalBidAmount() { return totalBidAmount; }
    public void setTotalBidAmount(BigDecimal totalBidAmount) { this.totalBidAmount = totalBidAmount; }
    public Long getHotStockCount() { return hotStockCount; }
    public void setHotStockCount(Long hotStockCount) { this.hotStockCount = hotStockCount; }
    public List<BoardTier> getTiers() { return tiers; }
    public void setTiers(List<BoardTier> tiers) { this.tiers = tiers; }
}
