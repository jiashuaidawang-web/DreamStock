package com.dreamstock.app.service;

import com.dreamstock.domain.vo.TradePlanVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TradePlanService {

    public TradePlanVO getTradePlans(LocalDate startDate, LocalDate endDate) {
        TradePlanVO vo = new TradePlanVO();
        List<TradePlanVO.PlanItem> plans = new ArrayList<>();

        TradePlanVO.PlanItem plan1 = new TradePlanVO.PlanItem();
        plan1.setId(1L);
        plan1.setPlanDate(endDate);
        plan1.setMarketStageJudgement("退潮阶段，市场情绪逐步降温，但主线AI算力仍有延续性");
        plan1.setSystemStage("退潮");
        plan1.setFocusThemes(List.of("AI算力", "半导体"));
        plan1.setFocusStocks(List.of(
            buildCandidate("002230", "科大讯飞", "AI大模型龙头，关注回踩MA10支撑"),
            buildCandidate("688981", "中芯国际", "半导体龙头，注意成交额变化")
        ));
        plan1.setEntryCondition("情绪分数回升至40以上，龙头股放量突破前高");
        plan1.setPositionLimit(new BigDecimal("0.30"));
        plan1.setStopLossCondition("跌破5日均线或亏损达-5%");
        plan1.setTakeProfitCondition("盈利达15%或出现高位放量滞涨");
        plan1.setRiskPlan("单笔风险不超过总资金2%，最大持仓3只");
        plan1.setStatus("ACTIVE");
        plans.add(plan1);

        TradePlanVO.PlanItem plan2 = new TradePlanVO.PlanItem();
        plan2.setId(2L);
        plan2.setPlanDate(endDate.minusDays(1));
        plan2.setMarketStageJudgement("退潮初期，连板股开始补跌");
        plan2.setSystemStage("退潮");
        plan2.setFocusThemes(List.of("新能源", "高股息"));
        plan2.setFocusStocks(List.of(
            buildCandidate("300750", "宁德时代", "固态电池催化，关注政策面"),
            buildCandidate("601318", "中国平安", "高股息防守品种")
        ));
        plan2.setEntryCondition("指数企稳不再创新低，龙头股止跌放量");
        plan2.setPositionLimit(new BigDecimal("0.20"));
        plan2.setStopLossCondition("跌破前低或亏损达-3%");
        plan2.setTakeProfitCondition("盈利达10%分批止盈");
        plan2.setRiskPlan("防守为主，高股息底仓+主线试探仓");
        plan2.setStatus("COMPLETED");
        plans.add(plan2);

        vo.setPlans(plans);
        return vo;
    }

    private TradePlanVO.StockCandidate buildCandidate(String code, String name, String reason) {
        TradePlanVO.StockCandidate c = new TradePlanVO.StockCandidate();
        c.setStockCode(code);
        c.setStockName(name);
        c.setReason(reason);
        return c;
    }
}
