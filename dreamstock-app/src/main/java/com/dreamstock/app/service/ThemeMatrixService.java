package com.dreamstock.app.service;

import com.dreamstock.domain.vo.ThemeMatrixVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ThemeMatrixService {

    public ThemeMatrixVO getThemeMatrix(LocalDate tradeDate) {
        ThemeMatrixVO vo = new ThemeMatrixVO();
        vo.setTradeDate(tradeDate);

        List<ThemeMatrixVO.ThemeItem> themes = new ArrayList<>();
        Object[][] data = {
            {1L, "人工智能算力", "92.5", 8, 5, "002230", "科大讯飞", 85, 12, "78.5", "高潮", "关注回调", "6.82", "2350000000", 1},
            {2L, "固态电池", "88.2", 6, 4, "300750", "宁德时代", 75, 8, "72.3", "发酵", "正常", "5.45", "1890000000", 2},
            {3L, "半导体国产化", "85.6", 7, 3, "688981", "中芯国际", 80, 15, "68.9", "发酵", "正常", "4.92", "2180000000", 3},
            {4L, "机器人", "78.3", 5, 3, "300124", "汇川技术", 70, 6, "65.2", "启动", "正常", "3.85", "1560000000", 4},
            {5L, "可控核聚变", "75.8", 4, 2, "601985", "中国核电", 65, 5, "58.6", "启动", "正常", "3.28", "980000000", 5},
            {6L, "低空经济", "72.1", 4, 2, "002097", "山河智能", 60, 4, "52.3", "修复", "正常", "2.95", "780000000", 6},
            {7L, "光伏设备", "45.6", 2, 1, "601012", "隆基绿能", 35, 3, "38.5", "退潮", "注意风险", "-1.85", "-850000000", 7},
            {8L, "医药生物", "42.3", 2, 1, "603259", "药明康德", 30, 2, "35.2", "冰点", "注意风险", "-2.15", "-620000000", 8},
        };

        for (Object[] row : data) {
            ThemeMatrixVO.ThemeItem item = new ThemeMatrixVO.ThemeItem();
            item.setThemeId((Long) row[0]);
            item.setThemeName((String) row[1]);
            item.setHeatScore(new BigDecimal((String) row[2]));
            item.setLimitUpCount((Integer) row[3]);
            item.setConsecutiveCount((Integer) row[4]);
            item.setLeaderStockCode((String) row[5]);
            item.setLeaderStockName((String) row[6]);
            item.setLadderCompleteness((Integer) row[7]);
            item.setContinuityDays((Integer) row[8]);
            item.setCapitalConcentration(new BigDecimal((String) row[9]));
            item.setEmotionStage((String) row[10]);
            item.setRiskTag((String) row[11]);
            item.setAvgPctChg(new BigDecimal((String) row[12]));
            item.setNetInflow(new BigDecimal((String) row[13]));
            item.setRank((Integer) row[14]);
            themes.add(item);
        }
        vo.setThemes(themes);
        return vo;
    }
}
