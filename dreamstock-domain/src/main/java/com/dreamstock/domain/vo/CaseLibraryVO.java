package com.dreamstock.domain.vo;

import java.time.LocalDate;
import java.util.List;

public class CaseLibraryVO {

    private List<CaseItem> cases;

    public static class CaseItem {
        private Long id;
        private String caseType;
        private String caseName;
        private LocalDate startDate;
        private LocalDate endDate;
        private String marketStage;
        private String mainTheme;
        private String coreStock;
        private String emotionChange;
        private String conclusion;
        private List<String> reusableRules;
        private List<String> tags;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getCaseType() { return caseType; }
        public void setCaseType(String caseType) { this.caseType = caseType; }
        public String getCaseName() { return caseName; }
        public void setCaseName(String caseName) { this.caseName = caseName; }
        public LocalDate getStartDate() { return startDate; }
        public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
        public LocalDate getEndDate() { return endDate; }
        public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
        public String getMarketStage() { return marketStage; }
        public void setMarketStage(String marketStage) { this.marketStage = marketStage; }
        public String getMainTheme() { return mainTheme; }
        public void setMainTheme(String mainTheme) { this.mainTheme = mainTheme; }
        public String getCoreStock() { return coreStock; }
        public void setCoreStock(String coreStock) { this.coreStock = coreStock; }
        public String getEmotionChange() { return emotionChange; }
        public void setEmotionChange(String emotionChange) { this.emotionChange = emotionChange; }
        public String getConclusion() { return conclusion; }
        public void setConclusion(String conclusion) { this.conclusion = conclusion; }
        public List<String> getReusableRules() { return reusableRules; }
        public void setReusableRules(List<String> reusableRules) { this.reusableRules = reusableRules; }
        public List<String> getTags() { return tags; }
        public void setTags(List<String> tags) { this.tags = tags; }
    }

    public List<CaseItem> getCases() { return cases; }
    public void setCases(List<CaseItem> cases) { this.cases = cases; }
}
