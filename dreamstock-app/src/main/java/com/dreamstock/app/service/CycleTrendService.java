package com.dreamstock.app.service;

import com.dreamstock.domain.enums.EmotionStage;
import com.dreamstock.domain.vo.CycleTrendVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CycleTrendService {

    public CycleTrendVO getCycleTrend(LocalDate startDate, LocalDate endDate, int days) {
        CycleTrendVO vo = new CycleTrendVO();
        List<CycleTrendVO.TrendPoint> points = new ArrayList<>();

        double[] scores = {15, 22, 30, 38, 45, 55, 62, 70, 78, 85, 80, 72, 65, 58, 50, 45, 40, 35, 30, 25, 20, 28, 38, 50, 60, 70, 80, 75, 65, 55,
                           48, 42, 38, 32, 28, 22, 18, 25, 35, 45, 55, 60, 68, 75, 82, 88, 92, 85, 78, 70, 62, 55, 48, 42, 38, 35, 30, 28, 35, 45};
        int[] stages = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 6, 6, 7, 7, 7, 1, 1, 1, 1, 1, 2, 3, 3, 4, 4, 5, 6, 7, 7,
                        7, 7, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 7, 7, 7, 7, 1, 1, 1, 1, 2, 2, 3};
        double baseIndex = 3100.0;

        for (int i = 0; i < days && i < scores.length; i++) {
            CycleTrendVO.TrendPoint point = new CycleTrendVO.TrendPoint();
            point.setTradeDate(endDate.minusDays(days - 1 - i));
            point.setEmotionScore(BigDecimal.valueOf(scores[i]));
            point.setEmotionStage(stages[i]);
            point.setEmotionStageName(EmotionStage.fromCode(stages[i]).getLabel());
            double idxChange = (scores[i] - 50) * 0.5 + (Math.random() - 0.5) * 20;
            point.setIndexClose(BigDecimal.valueOf(baseIndex + idxChange));
            point.setIndexPctChg(BigDecimal.valueOf((scores[i] - 50) * 0.03 + (Math.random() - 0.5) * 2).setScale(2, java.math.RoundingMode.HALF_UP));
            point.setTotalAmount(BigDecimal.valueOf((8000 + scores[i] * 50 + Math.random() * 2000) * 1e8));
            points.add(point);
        }
        vo.setPoints(points);

        List<CycleTrendVO.EventMarker> events = new ArrayList<>();
        CycleTrendVO.EventMarker e1 = new CycleTrendVO.EventMarker();
        e1.setTradeDate(endDate.minusDays(25));
        e1.setEventType("POLICY");
        e1.setEventTitle("央行降准50bp");
        e1.setImpactDirection("UP");
        events.add(e1);
        CycleTrendVO.EventMarker e2 = new CycleTrendVO.EventMarker();
        e2.setTradeDate(endDate.minusDays(10));
        e2.setEventType("REGULATORY");
        e2.setEventTitle("证监会规范量化交易");
        e2.setImpactDirection("DOWN");
        events.add(e2);
        vo.setEvents(events);
        return vo;
    }
}
