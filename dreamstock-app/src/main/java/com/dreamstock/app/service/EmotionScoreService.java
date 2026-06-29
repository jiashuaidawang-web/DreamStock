package com.dreamstock.app.service;

import com.dreamstock.domain.enums.EmotionStage;
import com.dreamstock.domain.vo.EmotionScoreVO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class EmotionScoreService {

    private final ExecutorService virtualThreadExecutor;

    public EmotionScoreService(@Qualifier("virtualThreadExecutor") ExecutorService virtualThreadExecutor) {
        this.virtualThreadExecutor = virtualThreadExecutor;
    }

    public EmotionScoreVO getEmotionScore(LocalDate tradeDate) {
        EmotionScoreVO vo = new EmotionScoreVO();
        vo.setTradeDate(tradeDate);

        Future<List<EmotionScoreVO.FactorItem>> factorsFuture = virtualThreadExecutor.submit(() -> buildFactors(tradeDate));
        Future<EmotionScoreVO.CompareInfo> compareFuture = virtualThreadExecutor.submit(() -> buildCompareInfo(tradeDate));
        Future<List<EmotionScoreVO.LimitDetail>> limitFuture = virtualThreadExecutor.submit(() -> buildLimitDetails(tradeDate));

        try {
            vo.setFactors(factorsFuture.get());
            vo.setCompareInfo(compareFuture.get());
            vo.setLimitUpDetails(limitFuture.get());
        } catch (Exception e) {
            throw new RuntimeException("Failed to build emotion score", e);
        }

        BigDecimal totalScore = BigDecimal.ZERO;
        for (EmotionScoreVO.FactorItem f : vo.getFactors()) {
            totalScore = totalScore.add(f.getScore().multiply(f.getWeight()));
        }
        vo.setEmotionScore(totalScore);
        int stageCode = totalScore.intValue() <= 20 ? 1 : totalScore.intValue() <= 35 ? 2 : totalScore.intValue() <= 50 ? 3 : totalScore.intValue() <= 65 ? 4 : totalScore.intValue() <= 80 ? 5 : totalScore.intValue() <= 90 ? 6 : 7;
        vo.setEmotionStageName(EmotionStage.fromCode(stageCode).getLabel());
        vo.setEmotionGrade(totalScore.intValue() >= 70 ? "A" : totalScore.intValue() >= 50 ? "B" : totalScore.intValue() >= 30 ? "C" : "D");
        vo.setConclusion("市场情绪处于" + vo.getEmotionStageName() + "阶段，综合评分" + totalScore.intValue() + "分，建议关注主线板块龙头表现。");
        return vo;
    }

    private List<EmotionScoreVO.FactorItem> buildFactors(LocalDate tradeDate) {
        List<EmotionScoreVO.FactorItem> factors = new ArrayList<>();
        String[][] data = {
            {"LIMIT_UP_COUNT", "涨停家数", "72", "75", "0.20", "UP"},
            {"LIMIT_DOWN_COUNT", "跌停家数", "31", "40", "0.10", "DOWN"},
            {"HIGH_BOARD_HEIGHT", "最高连板高度", "10", "85", "0.15", "UP"},
            {"UP_DOWN_RATIO", "涨跌比", "1.51", "65", "0.15", "UP"},
            {"TOTAL_AMOUNT", "成交额", "11256", "60", "0.10", "UP"},
            {"PROMOTION_RATE", "晋级率", "48.5", "55", "0.10", "FLAT"},
            {"FAILED_RATE", "炸板率", "22.3", "45", "0.10", "DOWN"},
            {"THEME_HEAT", "主线热度", "78", "70", "0.10", "UP"},
        };
        for (String[] row : data) {
            EmotionScoreVO.FactorItem item = new EmotionScoreVO.FactorItem();
            item.setFactorCode(row[0]);
            item.setFactorName(row[1]);
            item.setRawValue(new BigDecimal(row[2]));
            item.setScore(new BigDecimal(row[3]));
            item.setWeight(new BigDecimal(row[4]));
            item.setDirection(row[5]);
            factors.add(item);
        }
        return factors;
    }

    private EmotionScoreVO.CompareInfo buildCompareInfo(LocalDate tradeDate) {
        EmotionScoreVO.CompareInfo info = new EmotionScoreVO.CompareInfo();
        info.setYesterdayScore(new BigDecimal("58.5"));
        info.setAvg5dScore(new BigDecimal("55.2"));
        info.setAvg20dScore(new BigDecimal("52.8"));
        info.setChangeFromYesterday(new BigDecimal("5.5"));
        return info;
    }

    private List<EmotionScoreVO.LimitDetail> buildLimitDetails(LocalDate tradeDate) {
        List<EmotionScoreVO.LimitDetail> details = new ArrayList<>();
        String[][] data = {
            {"688981", "中芯国际", "10.00", "10", "半导体国产化主线"},
            {"002230", "科大讯飞", "9.98", "9", "AI大模型龙头"},
            {"300750", "宁德时代", "10.01", "9", "新能源储能龙头"},
            {"300059", "东方财富", "9.99", "8", "券商金融科技"},
            {"300308", "中际旭创", "10.00", "6", "光模块算力网络"},
        };
        for (String[] row : data) {
            EmotionScoreVO.LimitDetail d = new EmotionScoreVO.LimitDetail();
            d.setStockCode(row[0]);
            d.setStockName(row[1]);
            d.setPctChg(new BigDecimal(row[2]));
            d.setBoardCount(Integer.parseInt(row[3]));
            d.setReason(row[4]);
            details.add(d);
        }
        return details;
    }
}
