package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CapitalFlowVO {

    private LocalDate tradeDate;
    private BigDecimal totalAmount;
    private BigDecimal amountPercentile;
    private BigDecimal amountChange5d;
    private BigDecimal amountChange20d;
    private BigDecimal northMoneyNetInflow;
    private BigDecimal marginBalance;
    private String supplyDemandLabel;
    private List<FlowTrendPoint> trend;
    private List<SectorFlowItem> sectorFlows;

    public static class FlowTrendPoint {
        private LocalDate tradeDate;
        private BigDecimal totalAmount;
        private BigDecimal northMoney;
        private BigDecimal marginChange;

        public LocalDate getTradeDate() { return tradeDate; }
        public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
        public BigDecimal getTotalAmount() { return totalAmount; }
        public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
        public BigDecimal getNorthMoney() { return northMoney; }
        public void setNorthMoney(BigDecimal northMoney) { this.northMoney = northMoney; }
        public BigDecimal getMarginChange() { return marginChange; }
        public void setMarginChange(BigDecimal marginChange) { this.marginChange = marginChange; }
    }

    public static class SectorFlowItem {
        private String sectorName;
        private BigDecimal netInflow;
        private BigDecimal amountRatio;

        public String getSectorName() { return sectorName; }
        public void setSectorName(String sectorName) { this.sectorName = sectorName; }
        public BigDecimal getNetInflow() { return netInflow; }
        public void setNetInflow(BigDecimal netInflow) { this.netInflow = netInflow; }
        public BigDecimal getAmountRatio() { return amountRatio; }
        public void setAmountRatio(BigDecimal amountRatio) { this.amountRatio = amountRatio; }
    }

    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public BigDecimal getAmountPercentile() { return amountPercentile; }
    public void setAmountPercentile(BigDecimal amountPercentile) { this.amountPercentile = amountPercentile; }
    public BigDecimal getAmountChange5d() { return amountChange5d; }
    public void setAmountChange5d(BigDecimal amountChange5d) { this.amountChange5d = amountChange5d; }
    public BigDecimal getAmountChange20d() { return amountChange20d; }
    public void setAmountChange20d(BigDecimal amountChange20d) { this.amountChange20d = amountChange20d; }
    public BigDecimal getNorthMoneyNetInflow() { return northMoneyNetInflow; }
    public void setNorthMoneyNetInflow(BigDecimal northMoneyNetInflow) { this.northMoneyNetInflow = northMoneyNetInflow; }
    public BigDecimal getMarginBalance() { return marginBalance; }
    public void setMarginBalance(BigDecimal marginBalance) { this.marginBalance = marginBalance; }
    public String getSupplyDemandLabel() { return supplyDemandLabel; }
    public void setSupplyDemandLabel(String supplyDemandLabel) { this.supplyDemandLabel = supplyDemandLabel; }
    public List<FlowTrendPoint> getTrend() { return trend; }
    public void setTrend(List<FlowTrendPoint> trend) { this.trend = trend; }
    public List<SectorFlowItem> getSectorFlows() { return sectorFlows; }
    public void setSectorFlows(List<SectorFlowItem> sectorFlows) { this.sectorFlows = sectorFlows; }
}
