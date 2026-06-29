package com.dreamstock.app.service;

import com.dreamstock.domain.vo.StockCorrelationVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockCorrelationService {

    public StockCorrelationVO getCorrelation(String stockCode, LocalDate tradeDate, int windowSize) {
        StockCorrelationVO vo = new StockCorrelationVO();
        vo.setStockCode(stockCode);
        vo.setStockName(getStockName(stockCode));
        vo.setWindowSize(windowSize);
        vo.setIndexCorr(new BigDecimal("0.72"));
        vo.setEmotionCorr(new BigDecimal("0.85"));
        vo.setThemeCorr(new BigDecimal("0.68"));
        vo.setRsRank(15);
        vo.setPositionTag("强于大盘");
        vo.setDivergenceTag("与情绪正相关");
        vo.setExplanation("该股票与市场情绪高度正相关，在情绪启动阶段表现突出，RS排名前15%，属于强势领涨品种。");

        List<StockCorrelationVO.CorrTrendPoint> trend = new ArrayList<>();
        for (int i = windowSize - 1; i >= 0; i--) {
            StockCorrelationVO.CorrTrendPoint p = new StockCorrelationVO.CorrTrendPoint();
            p.setTradeDate(tradeDate.minusDays(i));
            p.setStockPctChg(BigDecimal.valueOf((Math.random() - 0.45) * 5).setScale(2, RoundingMode.HALF_UP));
            p.setIndexPctChg(BigDecimal.valueOf((Math.random() - 0.48) * 3).setScale(2, RoundingMode.HALF_UP));
            p.setEmotionScore(BigDecimal.valueOf(30 + Math.random() * 60).setScale(1, RoundingMode.HALF_UP));
            trend.add(p);
        }
        vo.setCorrTrend(trend);
        return vo;
    }

    private String getStockName(String code) {
        return switch (code) {
            case "688981" -> "中芯国际";
            case "002230" -> "科大讯飞";
            case "300750" -> "宁德时代";
            case "002594" -> "比亚迪";
            case "300308" -> "中际旭创";
            default -> "未知(" + code + ")";
        };
    }
}
