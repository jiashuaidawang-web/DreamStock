package com.dreamstock.app.service;

import com.dreamstock.domain.vo.DivergenceVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DivergenceService {

    public DivergenceVO getDivergence(LocalDate tradeDate) {
        DivergenceVO vo = new DivergenceVO();
        vo.setTradeDate(tradeDate);

        List<DivergenceVO.DivergenceItem> items = new ArrayList<>();
        String[][] data = {
            {"688981", "中芯国际", "10", "0", "95.2", "8.5", "382000000", "185000000", "一致看多", "3.25", "半导体国产化", "低风险"},
            {"002230", "科大讯飞", "9", "1", "82.5", "12.3", "145000000", "92000000", "轻微分歧", "1.85", "AI大模型", "低风险"},
            {"300059", "东方财富", "8", "2", "65.8", "15.6", "188000000", "76000000", "明显分歧", "-0.52", "券商金融科技", "中风险"},
            {"300750", "宁德时代", "9", "0", "88.6", "6.8", "210000000", "128000000", "一致看多", "2.68", "固态电池", "低风险"},
            {"002594", "比亚迪", "7", "0", "92.1", "5.8", "425000000", "215000000", "一致看多", "1.92", "新能源汽车", "低风险"},
            {"600438", "通威股份", "6", "4", "35.2", "25.8", "78000000", "0", "严重分歧", "-3.85", "光伏硅料", "高风险"},
            {"002475", "立讯精密", "3", "3", "28.5", "22.5", "72000000", "0", "严重分歧", "-2.15", "消费电子", "高风险"},
        };
        for (String[] row : data) {
            DivergenceVO.DivergenceItem item = new DivergenceVO.DivergenceItem();
            item.setStockCode(row[0]); item.setStockName(row[1]);
            item.setBoardCount(Integer.parseInt(row[2])); item.setOpenCount(Integer.parseInt(row[3]));
            item.setSealStrength(new BigDecimal(row[4])); item.setTurnoverRate(new BigDecimal(row[5]));
            item.setAmount(new BigDecimal(row[6])); item.setSealAmount(new BigDecimal(row[7]));
            item.setDivergenceStatus(row[8]); item.setNextDayPremium(new BigDecimal(row[9]));
            item.setThemeName(row[10]); item.setRiskTag(row[11]);
            items.add(item);
        }
        vo.setItems(items);
        return vo;
    }
}
