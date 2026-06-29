package com.dreamstock.app.service;

import com.dreamstock.domain.vo.DataQualityVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataQualityService {

    public DataQualityVO getDataQuality() {
        DataQualityVO vo = new DataQualityVO();

        List<DataQualityVO.JobStatusItem> jobs = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        jobs.add(buildJob(1L, "日行情数据采集", "CRAWLER", "SUCCESS", now.minusHours(2), 5280, now.plusHours(22), null));
        jobs.add(buildJob(2L, "情绪因子计算", "COMPUTE", "SUCCESS", now.minusHours(1), 1, now.plusHours(23), null));
        jobs.add(buildJob(3L, "龙头识别计算", "COMPUTE", "SUCCESS", now.minusMinutes(45), 1, now.plusHours(23), null));
        jobs.add(buildJob(4L, "主题热度计算", "COMPUTE", "SUCCESS", now.minusMinutes(30), 8, now.plusHours(23), null));
        jobs.add(buildJob(5L, "相关性矩阵计算", "COMPUTE", "RUNNING", now.minusMinutes(10), 0, null, null));
        jobs.add(buildJob(6L, "分钟行情采集", "CRAWLER", "FAILED", now.minusHours(4), 0, now.plusHours(20), "Connection timeout to data source"));
        vo.setJobs(jobs);

        List<DataQualityVO.QualityCheckResult> checks = new ArrayList<>();
        checks.add(buildCheck(1L, "dwd_stock_daily_quote", "COMPLETENESS", "count > 4800", "PASS", "5280", "4800", now.minusHours(1)));
        checks.add(buildCheck(2L, "dws_market_emotion_daily", "FRESHNESS", "max(trade_date) = today", "PASS", "1", "1", now.minusMinutes(50)));
        checks.add(buildCheck(3L, "dws_theme_daily", "COMPLETENESS", "count >= 5", "PASS", "8", "5", now.minusMinutes(40)));
        checks.add(buildCheck(4L, "dwd_stock_minute_quote", "FRESHNESS", "latest within 30min", "FAIL", "240", "30", now.minusMinutes(20)));
        checks.add(buildCheck(5L, "dws_stock_leader_daily", "ACCURACY", "no duplicate entries", "PASS", "0", "0", now.minusMinutes(15)));
        vo.setQualityChecks(checks);

        boolean hasFailure = checks.stream().anyMatch(c -> "FAIL".equals(c.getResultStatus()));
        boolean hasRunning = jobs.stream().anyMatch(j -> "RUNNING".equals(j.getLastRunStatus()));
        vo.setOverallHealthStatus(hasFailure ? "WARNING" : hasRunning ? "RUNNING" : "HEALTHY");
        return vo;
    }

    private DataQualityVO.JobStatusItem buildJob(Long id, String name, String type, String status,
            LocalDateTime lastRun, int rows, LocalDateTime nextRun, String error) {
        DataQualityVO.JobStatusItem j = new DataQualityVO.JobStatusItem();
        j.setId(id); j.setJobName(name); j.setJobType(type); j.setLastRunStatus(status);
        j.setLastRunTime(lastRun); j.setLastRowsAffected(rows); j.setNextScheduledTime(nextRun);
        j.setErrorMessage(error);
        return j;
    }

    private DataQualityVO.QualityCheckResult buildCheck(Long id, String target, String type,
            String condition, String status, String actual, String threshold, LocalDateTime time) {
        DataQualityVO.QualityCheckResult c = new DataQualityVO.QualityCheckResult();
        c.setId(id); c.setCheckTarget(target); c.setCheckType(type); c.setCheckCondition(condition);
        c.setResultStatus(status); c.setActualValue(new BigDecimal(actual));
        c.setThresholdValue(new BigDecimal(threshold)); c.setCheckTime(time);
        return c;
    }
}
