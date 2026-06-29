package com.dreamstock.domain.vo;

import java.util.List;

public class CycleStrategyVO {

    private String currentStage;
    private String currentStageName;
    private List<StageStrategy> strategies;

    public static class StageStrategy {
        private String stage;
        private String stageName;
        private boolean isCurrent;
        private String focusPoint;
        private List<String> filterRules;
        private String positionAdvice;
        private String riskAdvice;
        private List<String> candidateStocks;

        public String getStage() { return stage; }
        public void setStage(String stage) { this.stage = stage; }
        public String getStageName() { return stageName; }
        public void setStageName(String stageName) { this.stageName = stageName; }
        public boolean isCurrent() { return isCurrent; }
        public void setCurrent(boolean current) { isCurrent = current; }
        public String getFocusPoint() { return focusPoint; }
        public void setFocusPoint(String focusPoint) { this.focusPoint = focusPoint; }
        public List<String> getFilterRules() { return filterRules; }
        public void setFilterRules(List<String> filterRules) { this.filterRules = filterRules; }
        public String getPositionAdvice() { return positionAdvice; }
        public void setPositionAdvice(String positionAdvice) { this.positionAdvice = positionAdvice; }
        public String getRiskAdvice() { return riskAdvice; }
        public void setRiskAdvice(String riskAdvice) { this.riskAdvice = riskAdvice; }
        public List<String> getCandidateStocks() { return candidateStocks; }
        public void setCandidateStocks(List<String> candidateStocks) { this.candidateStocks = candidateStocks; }
    }

    public String getCurrentStage() { return currentStage; }
    public void setCurrentStage(String currentStage) { this.currentStage = currentStage; }
    public String getCurrentStageName() { return currentStageName; }
    public void setCurrentStageName(String currentStageName) { this.currentStageName = currentStageName; }
    public List<StageStrategy> getStrategies() { return strategies; }
    public void setStrategies(List<StageStrategy> strategies) { this.strategies = strategies; }
}
