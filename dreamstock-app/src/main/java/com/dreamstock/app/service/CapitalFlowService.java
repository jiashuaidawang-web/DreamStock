package com.dreamstock.app.service;

import com.dreamstock.domain.vo.CapitalFlowVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CapitalFlowService {

    public CapitalFlowVO getCapitalFlow(LocalDate tradeDate) {
        CapitalFlowVO vo = new CapitalFlowVO();
        vo.setTradeDate(tradeDate);
        vo.setTotalAmount(new BigDecimal("1125600000000"));
        vo.setAmountPercentile(new BigDecimal("72.5"));
        vo.setAmountChange5d(new BigDecimal("8.3"));
        vo.setAmountChange20d(new BigDecimal("15.6"));
        vo.setNorthMoneyNetInflow(new BigDecimal("5680000000"));
        vo.setMarginBalance(new BigDecimal("1856000000000"));
        vo.setSupplyDemandLabel("供需平衡偏多");

        List<CapitalFlowVO.FlowTrendPoint> trend = new ArrayList<>();
        for (int i = 29; i >= 0; i--) {
            CapitalFlowVO.FlowTrendPoint p = new CapitalFlowVO.FlowTrendPoint();
            p.setTradeDate(tradeDate.minusDays(i));
            p.setTotalAmount(BigDecimal.valueOf((9000 + Math.random() * 4000) * 1e8).setScale(0, RoundingMode.HALF_UP));
            p.setNorthMoney(BigDecimal.valueOf((Math.random() - 0.3) * 100 * 1e8).setScale(0, RoundingMode.HALF_UP));
            p.setMarginChange(BigDecimal.valueOf((Math.random() - 0.4) * 50 * 1e8).setScale(0, RoundingMode.HALF_UP));
            trend.add(p);
        }
        vo.setTrend(trend);

        List<CapitalFlowVO.SectorFlowItem> sectors = new ArrayList<>();
        String[][] data = {
            {"半导体", "2350000000", "12.5"},
            {"人工智能", "1890000000", "10.8"},
            {"新能源", "1560000000", "8.9"},
            {"医药生物", "-850000000", "5.2"},
            {"房地产", "-1230000000", "4.1"},
            {"消费电子", "780000000", "6.3"},
        };
        for (String[] row : data) {
            CapitalFlowVO.SectorFlowItem s = new CapitalFlowVO.SectorFlowItem();
            s.setSectorName(row[0]);
            s.setNetInflow(new BigDecimal(row[1]));
            s.setAmountRatio(new BigDecimal(row[2]));
            sectors.add(s);
        }
        vo.setSectorFlows(sectors);
        return vo;
    }
}
