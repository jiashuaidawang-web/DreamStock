package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class DataQualityVO {

    private List<JobStatusItem> jobs;
    private List<QualityCheckResult> qualityChecks;
    private String overallHealthStatus;

    public static class JobStatusItem {
        private Long id;
        private String jobName;
        private String jobType;
        private String lastRunStatus;
        private LocalDateTime lastRunTime;
        private Integer lastRowsAffected;
        private LocalDateTime nextScheduledTime;
        private String errorMessage;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getJobName() { return jobName; }
        public void setJobName(String jobName) { this.jobName = jobName; }
        public String getJobType() { return jobType; }
        public void setJobType(String jobType) { this.jobType = jobType; }
        public String getLastRunStatus() { return lastRunStatus; }
        public void setLastRunStatus(String lastRunStatus) { this.lastRunStatus = lastRunStatus; }
        public LocalDateTime getLastRunTime() { return lastRunTime; }
        public void setLastRunTime(LocalDateTime lastRunTime) { this.lastRunTime = lastRunTime; }
        public Integer getLastRowsAffected() { return lastRowsAffected; }
        public void setLastRowsAffected(Integer lastRowsAffected) { this.lastRowsAffected = lastRowsAffected; }
        public LocalDateTime getNextScheduledTime() { return nextScheduledTime; }
        public void setNextScheduledTime(LocalDateTime nextScheduledTime) { this.nextScheduledTime = nextScheduledTime; }
        public String getErrorMessage() { return errorMessage; }
        public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    }

    public static class QualityCheckResult {
        private Long id;
        private String checkTarget;
        private String checkType;
        private String checkCondition;
        private String resultStatus;
        private BigDecimal actualValue;
        private BigDecimal thresholdValue;
        private LocalDateTime checkTime;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getCheckTarget() { return checkTarget; }
        public void setCheckTarget(String checkTarget) { this.checkTarget = checkTarget; }
        public String getCheckType() { return checkType; }
        public void setCheckType(String checkType) { this.checkType = checkType; }
        public String getCheckCondition() { return checkCondition; }
        public void setCheckCondition(String checkCondition) { this.checkCondition = checkCondition; }
        public String getResultStatus() { return resultStatus; }
        public void setResultStatus(String resultStatus) { this.resultStatus = resultStatus; }
        public BigDecimal getActualValue() { return actualValue; }
        public void setActualValue(BigDecimal actualValue) { this.actualValue = actualValue; }
        public BigDecimal getThresholdValue() { return thresholdValue; }
        public void setThresholdValue(BigDecimal thresholdValue) { this.thresholdValue = thresholdValue; }
        public LocalDateTime getCheckTime() { return checkTime; }
        public void setCheckTime(LocalDateTime checkTime) { this.checkTime = checkTime; }
    }

    public List<JobStatusItem> getJobs() { return jobs; }
    public void setJobs(List<JobStatusItem> jobs) { this.jobs = jobs; }
    public List<QualityCheckResult> getQualityChecks() { return qualityChecks; }
    public void setQualityChecks(List<QualityCheckResult> qualityChecks) { this.qualityChecks = qualityChecks; }
    public String getOverallHealthStatus() { return overallHealthStatus; }
    public void setOverallHealthStatus(String overallHealthStatus) { this.overallHealthStatus = overallHealthStatus; }
}
