package com.dreamstock.app.service;

import com.dreamstock.domain.vo.TrendDragonVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrendDragonService {

    public TrendDragonVO getTrendDragons() {
        TrendDragonVO vo = new TrendDragonVO();
        List<TrendDragonVO.TrendStockItem> stocks = new ArrayList<>();
        String[][] data = {
            {"002230", "科大讯飞", "82.50", "3.85", "92.5", "多头排列", "突破新高", "5", "125000000000", "AI大模型", "周线多头", "2.35", "低风险"},
            {"300750", "宁德时代", "258.30", "2.68", "88.2", "多头排列", "平台突破", "8", "635000000000", "新能源储能", "周线多头", "1.85", "低风险"},
            {"688981", "中芯国际", "45.80", "4.52", "85.6", "多头排列", "突破前高", "3", "356000000000", "半导体", "周线金叉", "3.12", "低风险"},
            {"002594", "比亚迪", "305.80", "1.92", "82.3", "多头排列", "高位震荡", "12", "889000000000", "新能源车", "周线多头", "1.52", "中风险"},
            {"300124", "汇川技术", "68.50", "3.15", "78.5", "金叉确认", "回踩确认", "10", "288000000000", "机器人", "周线金叉", "1.98", "低风险"},
            {"601012", "隆基绿能", "22.80", "-1.85", "35.2", "空头排列", "破位下行", "85", "172000000000", "光伏", "周线空头", "0.85", "高风险"},
        };
        for (String[] row : data) {
            TrendDragonVO.TrendStockItem item = new TrendDragonVO.TrendStockItem();
            item.setStockCode(row[0]); item.setStockName(row[1]);
            item.setClose(new BigDecimal(row[2])); item.setPctChg(new BigDecimal(row[3]));
            item.setTrendScore(new BigDecimal(row[4])); item.setMaStatus(row[5]);
            item.setBreakoutStatus(row[6]); item.setRsRank(Integer.parseInt(row[7]));
            item.setTotalMarketValue(new BigDecimal(row[8])); item.setIndustrySector(row[9]);
            item.setWeeklyTrendStatus(row[10]); item.setVolumeRatio(new BigDecimal(row[11]));
            item.setRiskTag(row[12]);
            stocks.add(item);
        }
        vo.setStocks(stocks);
        return vo;
    }
}
