package com.dreamstock.app.service;

import com.dreamstock.domain.vo.MarketTrendVO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class MarketTrendService {

    private final ExecutorService virtualThreadExecutor;

    public MarketTrendService(@Qualifier("virtualThreadExecutor") ExecutorService virtualThreadExecutor) {
        this.virtualThreadExecutor = virtualThreadExecutor;
    }

    public MarketTrendVO getMarketTrend(LocalDate tradeDate) {
        MarketTrendVO vo = new MarketTrendVO();

        Future<List<MarketTrendVO.IndexItem>> indexFuture = virtualThreadExecutor.submit(() -> buildIndices(tradeDate));
        Future<List<MarketTrendVO.KeyLevel>> levelFuture = virtualThreadExecutor.submit(() -> buildKeyLevels());

        try {
            vo.setIndices(indexFuture.get());
            vo.setKeyLevels(levelFuture.get());
        } catch (Exception e) {
            throw new RuntimeException("Failed to build market trend", e);
        }

        vo.setTrendStructure("震荡上行");
        vo.setTechnicalCycle("中期上升趋势");
        vo.setIndexEmotionRelation("指数与情绪正相关, 当前情绪温和偏热");
        return vo;
    }

    private List<MarketTrendVO.IndexItem> buildIndices(LocalDate tradeDate) {
        List<MarketTrendVO.IndexItem> indices = new ArrayList<>();
        String[][] meta = {
            {"000001", "上证指数", "3256.78", "1.24", "1125600000000", "多头排列", "多头排列", "站上", "站上", "震荡上行"},
            {"399001", "深证成指", "10856.32", "1.68", "985600000000", "多头排列", "多头排列", "站上", "站上", "强势上行"},
            {"399006", "创业板指", "2145.67", "2.12", "456800000000", "多头排列", "金叉", "站上", "靠近", "反弹确认"},
        };
        for (String[] row : meta) {
            MarketTrendVO.IndexItem item = new MarketTrendVO.IndexItem();
            item.setIndexCode(row[0]);
            item.setIndexName(row[1]);
            item.setClose(new BigDecimal(row[2]));
            item.setPctChg(new BigDecimal(row[3]));
            item.setTotalAmount(new BigDecimal(row[4]));
            item.setMa5Status(row[5]);
            item.setMa10Status(row[6]);
            item.setMa20Status(row[7]);
            item.setMa60Status(row[8]);
            item.setTrendLabel(row[9]);

            List<MarketTrendVO.KLinePoint> kLines = new ArrayList<>();
            double base = Double.parseDouble(row[2]);
            for (int i = 59; i >= 0; i--) {
                MarketTrendVO.KLinePoint k = new MarketTrendVO.KLinePoint();
                k.setTradeDate(tradeDate.minusDays(i));
                double change = (Math.random() - 0.48) * base * 0.02;
                double open = base - change;
                double close = base;
                k.setOpen(BigDecimal.valueOf(open).setScale(2, RoundingMode.HALF_UP));
                k.setClose(BigDecimal.valueOf(close).setScale(2, RoundingMode.HALF_UP));
                k.setHigh(BigDecimal.valueOf(Math.max(open, close) + Math.random() * base * 0.005).setScale(2, RoundingMode.HALF_UP));
                k.setLow(BigDecimal.valueOf(Math.min(open, close) - Math.random() * base * 0.005).setScale(2, RoundingMode.HALF_UP));
                k.setVolume(BigDecimal.valueOf(Math.random() * 500 + 200).setScale(0, RoundingMode.HALF_UP));
                kLines.add(k);
                base = close + (Math.random() - 0.48) * base * 0.01;
            }
            item.setKLines(kLines);
            indices.add(item);
        }
        return indices;
    }

    private List<MarketTrendVO.KeyLevel> buildKeyLevels() {
        List<MarketTrendVO.KeyLevel> levels = new ArrayList<>();
        String[][] data = {
            {"SUPPORT", "3200.00", "年线支撑位"},
            {"RESISTANCE", "3350.00", "前期高点压力"},
            {"SUPPORT", "3180.00", "60日均线支撑"},
        };
        for (String[] row : data) {
            MarketTrendVO.KeyLevel level = new MarketTrendVO.KeyLevel();
            level.setLevelType(row[0]);
            level.setPrice(new BigDecimal(row[1]));
            level.setDescription(row[2]);
            levels.add(level);
        }
        return levels;
    }
}
