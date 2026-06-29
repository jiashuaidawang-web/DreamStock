package com.dreamstock.app.service;

import com.dreamstock.domain.vo.DisciplineProfileVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DisciplineProfileService {

    public DisciplineProfileVO getProfile(Long userId) {
        DisciplineProfileVO vo = new DisciplineProfileVO();

        List<DisciplineProfileVO.DimensionScore> dims = new ArrayList<>();
        dims.add(buildDim("DISCIPLINE", "纪律执行", "72.5", 45, "UP"));
        dims.add(buildDim("MENTALITY", "心态控制", "65.0", 38, "FLAT"));
        dims.add(buildDim("TIMING", "时机把握", "80.0", 42, "UP"));
        dims.add(buildDim("POSITION", "仓位管理", "68.5", 35, "DOWN"));
        dims.add(buildDim("STOP_LOSS", "止损执行", "58.0", 28, "DOWN"));
        dims.add(buildDim("REVIEW", "复盘质量", "85.0", 50, "UP"));
        vo.setDimensions(dims);

        vo.setWeekTopIssues(List.of(
            "追涨杀跌频率偏高，本周3次违反纪律",
            "退潮期仓位过重，未及时减仓",
            "止损执行不够果断，2次拖延止损"
        ));

        vo.setMonthTopMistakes(List.of(
            "高潮期未及时止盈，错过最佳卖点 (5次)",
            "情绪化交易，FOMO追入非主线股 (3次)",
            "退潮期抄底过早 (2次)"
        ));

        List<DisciplineProfileVO.StagePnlItem> stagePnl = new ArrayList<>();
        stagePnl.add(buildStagePnl("冰点", "-2850", 5, "20.0"));
        stagePnl.add(buildStagePnl("修复", "5680", 8, "62.5"));
        stagePnl.add(buildStagePnl("启动", "12500", 12, "75.0"));
        stagePnl.add(buildStagePnl("发酵", "18200", 15, "80.0"));
        stagePnl.add(buildStagePnl("高潮", "8500", 10, "60.0"));
        stagePnl.add(buildStagePnl("分歧", "-3200", 6, "33.3"));
        stagePnl.add(buildStagePnl("退潮", "-5800", 8, "25.0"));
        vo.setStagePnl(stagePnl);

        vo.setNextWeekReminders(List.of(
            "当前退潮阶段：仓位应控制在10%以内",
            "重点改善止损执行力，设置自动止损",
            "减少交易频率，等待情绪分数回升至35以上再考虑入场"
        ));

        return vo;
    }

    private DisciplineProfileVO.DimensionScore buildDim(String dim, String name, String score, int count, String trend) {
        DisciplineProfileVO.DimensionScore d = new DisciplineProfileVO.DimensionScore();
        d.setDimension(dim);
        d.setDimensionName(name);
        d.setScore(new BigDecimal(score));
        d.setCount(count);
        d.setTrend(trend);
        return d;
    }

    private DisciplineProfileVO.StagePnlItem buildStagePnl(String stage, String pnl, int count, String winRate) {
        DisciplineProfileVO.StagePnlItem item = new DisciplineProfileVO.StagePnlItem();
        item.setStageName(stage);
        item.setTotalPnl(new BigDecimal(pnl));
        item.setTradeCount(count);
        item.setWinRate(new BigDecimal(winRate));
        return item;
    }
}
