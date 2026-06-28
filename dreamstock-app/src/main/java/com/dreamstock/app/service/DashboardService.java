package com.dreamstock.app.service;

import com.dreamstock.domain.enums.EmotionStage;
import com.dreamstock.domain.vo.DashboardVO;
import com.dreamstock.infrastructure.mapper.mysql.BizReviewDailyJournalMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class DashboardService {

    private final ExecutorService virtualThreadExecutor;
    private final BizReviewDailyJournalMapper journalMapper;

    public DashboardService(
            @Qualifier("virtualThreadExecutor") ExecutorService virtualThreadExecutor,
            BizReviewDailyJournalMapper journalMapper) {
        this.virtualThreadExecutor = virtualThreadExecutor;
        this.journalMapper = journalMapper;
    }

    public DashboardVO getDashboard(LocalDate tradeDate) {
        DashboardVO vo = new DashboardVO();
        vo.setTradeDate(tradeDate);

        Future<DashboardVO.EmotionSummary> emotionFuture = virtualThreadExecutor.submit(
                () -> buildEmotionSummary(tradeDate));
        Future<DashboardVO.MarketOverview> marketFuture = virtualThreadExecutor.submit(
                () -> buildMarketOverview(tradeDate));
        Future<List<DashboardVO.ThemeRankItem>> themeFuture = virtualThreadExecutor.submit(
                () -> buildTopThemes(tradeDate));
        Future<List<DashboardVO.EmotionTrendPoint>> trendFuture = virtualThreadExecutor.submit(
                () -> buildEmotionTrend(tradeDate, 30));

        try {
            vo.setEmotionSummary(emotionFuture.get());
            vo.setMarketOverview(marketFuture.get());
            vo.setTopThemes(themeFuture.get());
            vo.setEmotionTrend(trendFuture.get());
        } catch (Exception e) {
            throw new RuntimeException("Failed to aggregate dashboard data via virtual threads", e);
        }

        vo.setPersonalPerformance(buildPersonalPerformance());
        return vo;
    }

    private DashboardVO.EmotionSummary buildEmotionSummary(LocalDate tradeDate) {
        DashboardVO.EmotionSummary summary = new DashboardVO.EmotionSummary();
        summary.setEmotionScore(new BigDecimal("64.0000"));
        summary.setEmotionStageName(EmotionStage.EBB.getLabel());
        summary.setLimitUpCount(72L);
        summary.setLimitDownCount(31L);
        summary.setIndexPctChg(new BigDecimal("1.24"));
        summary.setIndexName("上证综合指数");
        return summary;
    }

    private DashboardVO.MarketOverview buildMarketOverview(LocalDate tradeDate) {
        DashboardVO.MarketOverview overview = new DashboardVO.MarketOverview();
        overview.setUpCount(2856L);
        overview.setDownCount(1890L);
        overview.setAbove5pctCount(215L);
        overview.setBelowMinus5pctCount(87L);
        overview.setTotalAmount(new BigDecimal("1125600000000"));
        overview.setConsecutiveLimitHigh(10L);
        return overview;
    }

    private List<DashboardVO.ThemeRankItem> buildTopThemes(LocalDate tradeDate) {
        List<DashboardVO.ThemeRankItem> themes = new ArrayList<>();
        String[][] data = {
            {"1", "人工智能算力", "中际旭创", "9.98"},
            {"2", "固态电池", "三祥新材", "10.01"},
            {"3", "可控核聚变", "国光电气", "7.34"},
            {"4", "机器人", "鸣志电器", "5.62"},
            {"5", "低空经济", "万丰奥威", "-2.18"},
            {"6", "光伏设备", "阳光电源", "-3.45"}
        };
        for (String[] row : data) {
            DashboardVO.ThemeRankItem item = new DashboardVO.ThemeRankItem();
            item.setRank(Long.parseLong(row[0]));
            item.setThemeName(row[1]);
            item.setLeaderStockName(row[2]);
            item.setLeaderPctChg(new BigDecimal(row[3]));
            themes.add(item);
        }
        return themes;
    }

    private List<DashboardVO.EmotionTrendPoint> buildEmotionTrend(LocalDate endDate, int days) {
        List<DashboardVO.EmotionTrendPoint> trend = new ArrayList<>();
        double[] scores = {15, 22, 30, 38, 45, 55, 62, 70, 78, 85, 80, 72, 65, 58, 50, 45, 40, 35, 30, 25, 20, 28, 38, 50, 60, 70, 80, 75, 65, 55};
        int[] stages = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 6, 6, 7, 7, 7, 1, 1, 1, 1, 1, 2, 3, 3, 4, 4, 5, 6, 7, 7};
        for (int i = 0; i < days; i++) {
            DashboardVO.EmotionTrendPoint point = new DashboardVO.EmotionTrendPoint();
            point.setTradeDate(endDate.minusDays(days - 1 - i));
            point.setEmotionScore(BigDecimal.valueOf(scores[i]));
            point.setEmotionStage(stages[i]);
            trend.add(point);
        }
        return trend;
    }

    private DashboardVO.PersonalPerformance buildPersonalPerformance() {
        DashboardVO.PersonalPerformance perf = new DashboardVO.PersonalPerformance();
        perf.setTodayPnl(new BigDecimal("12480"));
        perf.setCumulativeReturnPct(new BigDecimal("38.6"));
        perf.setRecentWinRatePct(new BigDecimal("62.5"));
        return perf;
    }
}
