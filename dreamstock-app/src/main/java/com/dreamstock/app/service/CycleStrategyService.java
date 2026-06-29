package com.dreamstock.app.service;

import com.dreamstock.domain.vo.CycleStrategyVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CycleStrategyService {

    public CycleStrategyVO getCycleStrategy() {
        CycleStrategyVO vo = new CycleStrategyVO();
        vo.setCurrentStage("EBB");
        vo.setCurrentStageName("退潮");

        List<CycleStrategyVO.StageStrategy> strategies = new ArrayList<>();

        strategies.add(buildStrategy("ICE_POINT", "冰点", false,
            "关注超跌反弹信号，寻找底部放量品种",
            List.of("连板高度≤3板", "涨停家数<30", "情绪分数<20"),
            "仓位不超过20%，以观望为主",
            "严格止损，单笔不超过总资金5%",
            List.of("601398 工商银行", "600036 招商银行")));

        strategies.add(buildStrategy("REPAIR", "修复", false,
            "关注先手资金介入方向，龙头股首板",
            List.of("连板高度3-5板", "涨停家数30-50", "情绪分数20-35"),
            "仓位控制在30%以内，试探性建仓",
            "首板试错，止损线-5%",
            List.of("002230 科大讯飞", "300124 汇川技术")));

        strategies.add(buildStrategy("LAUNCH", "启动", false,
            "主线确认，龙头加仓",
            List.of("连板高度5-7板", "涨停家数50-70", "情绪分数35-55"),
            "仓位可提升至50%，主攻龙头",
            "持有龙头不轻易卖出，非龙头设严格止损",
            List.of("688981 中芯国际", "300750 宁德时代")));

        strategies.add(buildStrategy("FERMENT", "发酵", false,
            "主线扩散，补涨品种出现",
            List.of("连板高度7-9板", "涨停家数70+", "情绪分数55-70"),
            "仓位可达60-70%，把握主升浪",
            "龙头持有不动，注意分仓",
            List.of("002594 比亚迪", "300059 东方财富")));

        strategies.add(buildStrategy("CLIMAX", "高潮", false,
            "注意见顶信号，准备分批止盈",
            List.of("连板高度≥10板", "涨停家数80+", "情绪分数>80"),
            "开始减仓，仓位降至40%以下",
            "逐步止盈，锁定利润",
            List.of()));

        strategies.add(buildStrategy("DIVERGE", "分歧", false,
            "市场分歧加大，选择性参与确认品种",
            List.of("龙头出现天地板", "涨停家数骤降", "炸板率>30%"),
            "仓位降至20%以内，以防守为主",
            "严格止损，不追高",
            List.of()));

        strategies.add(buildStrategy("EBB", "退潮", true,
            "控制仓位，等待新周期启动信号",
            List.of("连板高度下降", "涨停家数持续减少", "情绪分数持续下行"),
            "仓位控制在10%以内，空仓观望",
            "不抄底，不接飞刀",
            List.of()));

        vo.setStrategies(strategies);
        return vo;
    }

    private CycleStrategyVO.StageStrategy buildStrategy(String stage, String name, boolean current,
            String focus, List<String> filters, String position, String risk, List<String> candidates) {
        CycleStrategyVO.StageStrategy s = new CycleStrategyVO.StageStrategy();
        s.setStage(stage);
        s.setStageName(name);
        s.setCurrent(current);
        s.setFocusPoint(focus);
        s.setFilterRules(filters);
        s.setPositionAdvice(position);
        s.setRiskAdvice(risk);
        s.setCandidateStocks(candidates);
        return s;
    }
}
