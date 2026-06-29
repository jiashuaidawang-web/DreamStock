package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TradePlanVO {

    private List<PlanItem> plans;

    public static class PlanItem {
        private Long id;
        private LocalDate planDate;
        private String marketStageJudgement;
        private String systemStage;
        private List<String> focusThemes;
        private List<StockCandidate> focusStocks;
        private String entryCondition;
        private BigDecimal positionLimit;
        private String stopLossCondition;
        private String takeProfitCondition;
        private String riskPlan;
        private String status;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public LocalDate getPlanDate() { return planDate; }
        public void setPlanDate(LocalDate planDate) { this.planDate = planDate; }
        public String getMarketStageJudgement() { return marketStageJudgement; }
        public void setMarketStageJudgement(String marketStageJudgement) { this.marketStageJudgement = marketStageJudgement; }
        public String getSystemStage() { return systemStage; }
        public void setSystemStage(String systemStage) { this.systemStage = systemStage; }
        public List<String> getFocusThemes() { return focusThemes; }
        public void setFocusThemes(List<String> focusThemes) { this.focusThemes = focusThemes; }
        public List<StockCandidate> getFocusStocks() { return focusStocks; }
        public void setFocusStocks(List<StockCandidate> focusStocks) { this.focusStocks = focusStocks; }
        public String getEntryCondition() { return entryCondition; }
        public void setEntryCondition(String entryCondition) { this.entryCondition = entryCondition; }
        public BigDecimal getPositionLimit() { return positionLimit; }
        public void setPositionLimit(BigDecimal positionLimit) { this.positionLimit = positionLimit; }
        public String getStopLossCondition() { return stopLossCondition; }
        public void setStopLossCondition(String stopLossCondition) { this.stopLossCondition = stopLossCondition; }
        public String getTakeProfitCondition() { return takeProfitCondition; }
        public void setTakeProfitCondition(String takeProfitCondition) { this.takeProfitCondition = takeProfitCondition; }
        public String getRiskPlan() { return riskPlan; }
        public void setRiskPlan(String riskPlan) { this.riskPlan = riskPlan; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    public static class StockCandidate {
        private String stockCode;
        private String stockName;
        private String reason;

        public String getStockCode() { return stockCode; }
        public void setStockCode(String stockCode) { this.stockCode = stockCode; }
        public String getStockName() { return stockName; }
        public void setStockName(String stockName) { this.stockName = stockName; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
    }

    public List<PlanItem> getPlans() { return plans; }
    public void setPlans(List<PlanItem> plans) { this.plans = plans; }
}
