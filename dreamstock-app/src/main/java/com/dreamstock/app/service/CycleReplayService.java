package com.dreamstock.app.service;

import com.dreamstock.domain.enums.EmotionStage;
import com.dreamstock.domain.vo.CycleReplayVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CycleReplayService {

    public CycleReplayVO getReplay(LocalDate startDate, LocalDate endDate) {
        CycleReplayVO vo = new CycleReplayVO();
        vo.setStartDate(startDate);
        vo.setEndDate(endDate);

        List<CycleReplayVO.DaySnapshot> snapshots = new ArrayList<>();
        double[] scores = {35, 38, 42, 48, 55, 60, 65, 72, 78, 82, 85, 80, 75, 68, 62, 55, 48, 42, 38, 35};
        int[] stages = {2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 7, 7, 7, 7, 1, 1, 1};
        String[] themes = {"AI算力", "AI算力", "AI算力", "半导体", "半导体", "固态电池", "固态电池", "AI算力", "AI算力", "AI算力",
                          "半导体", "半导体", "混合主线", "退潮", "退潮", "退潮", "退潮", "高股息", "高股息", "高股息"};
        String[] dragons = {"科大讯飞", "科大讯飞", "中芯国际", "中芯国际", "中芯国际", "宁德时代", "宁德时代", "科大讯飞", "科大讯飞", "中芯国际",
                           "中芯国际", "东方财富", "比亚迪", "无", "无", "无", "无", "工商银行", "工商银行", "招商银行"};
        double baseIdx = 3180.0;

        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
        for (int i = 0; i < days && i < scores.length; i++) {
            CycleReplayVO.DaySnapshot snap = new CycleReplayVO.DaySnapshot();
            snap.setTradeDate(startDate.plusDays(i));
            snap.setEmotionScore(BigDecimal.valueOf(scores[i]));
            snap.setEmotionStageName(EmotionStage.fromCode(stages[i]).getLabel());
            double change = (scores[i] - 50) * 0.3;
            baseIdx += change;
            snap.setIndexClose(BigDecimal.valueOf(baseIdx).setScale(2, RoundingMode.HALF_UP));
            snap.setIndexPctChg(BigDecimal.valueOf(change / baseIdx * 100).setScale(2, RoundingMode.HALF_UP));
            snap.setTotalAmount(BigDecimal.valueOf((8000 + scores[i] * 50) * 1e8));
            snap.setMainTheme(themes[i]);
            snap.setTopDragon(dragons[i]);
            snap.setLimitUpCount((int)(scores[i] * 0.8 + 10));
            snapshots.add(snap);
        }
        vo.setSnapshots(snapshots);

        CycleReplayVO.ReportSummary summary = new CycleReplayVO.ReportSummary();
        summary.setPeriodConclusion("本周期经历了完整的修复→启动→发酵→高潮→分歧→退潮过程，主线从AI算力切换至半导体再到固态电池。");
        summary.setCycleChanges("情绪从35分升至85分再回落至35分，完整周期约20个交易日。");
        summary.setThemeRotation("AI算力(8天) → 半导体(5天) → 固态电池(3天) → 退潮(4天)");
        summary.setDragonLifecycle("科大讯飞(早期龙头) → 中芯国际(接力龙头) → 宁德时代(趋势龙头)");
        summary.setTotalPnl(new BigDecimal("33030"));
        summary.setTopBehaviorIssues(List.of("高潮期减仓不够果断", "退潮期仍有抄底行为", "止盈执行力需提升"));
        vo.setReportSummary(summary);
        return vo;
    }
}
