package com.dreamstock.app.service;

import com.dreamstock.domain.vo.CaseLibraryVO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CaseLibraryService {

    public CaseLibraryVO getCases(String caseType) {
        CaseLibraryVO vo = new CaseLibraryVO();
        List<CaseLibraryVO.CaseItem> cases = new ArrayList<>();

        cases.add(buildCase(1L, "CYCLE", "2024年AI算力主升浪",
            LocalDate.of(2024, 1, 15), LocalDate.of(2024, 3, 20),
            "冰点→启动→高潮→退潮", "人工智能算力", "中际旭创",
            "情绪从15分升至92分再回落至35分", "AI算力主线持续2个月，龙头中际旭创走出10连板，带动光模块板块整体翻倍。关键转折点在高潮期出现分歧天地板。",
            List.of("主线确认后跟随龙头", "高潮期注意分批止盈", "天地板出现即退出"), List.of("AI", "算力", "连板龙头")));

        cases.add(buildCase(2L, "DRAGON", "宁德时代趋势主升段",
            LocalDate.of(2024, 4, 10), LocalDate.of(2024, 6, 15),
            "修复→发酵→高潮", "新能源储能", "宁德时代",
            "情绪从28分升至85分", "宁德时代在固态电池催化下走出趋势主升段，期间涨幅超60%。资金持续流入，北向资金连续净买入。",
            List.of("趋势股回踩均线可加仓", "关注北向资金动向", "政策催化是核心驱动"), List.of("趋势龙头", "新能源", "北向资金")));

        cases.add(buildCase(3L, "MISTAKE", "退潮期抄底失败案例",
            LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 15),
            "退潮", "光伏设备", "隆基绿能",
            "情绪从45分跌至18分", "退潮期抄底光伏龙头，忽视了产能过剩和情绪持续下行。连续3天止损，合计亏损8.5%。教训：退潮期不抄底，等待冰点确认。",
            List.of("退潮期不抄底", "等待情绪冰点确认", "产能过剩行业回避"), List.of("失败案例", "抄底", "退潮期")));

        cases.add(buildCase(4L, "CYCLE", "半导体国产化替代行情",
            LocalDate.of(2024, 9, 1), LocalDate.of(2024, 11, 30),
            "冰点→修复→启动→发酵→高潮", "半导体国产化", "中芯国际",
            "情绪从12分升至88分", "受华为新品发布催化，半导体板块启动，中芯国际走出翻倍行情。主线持续近3个月，是年度最长主线之一。",
            List.of("政策催化叠加业绩确认是强主线标志", "龙头市值越大行情持续性越强"), List.of("半导体", "国产替代", "华为产业链")));

        if (caseType != null && !caseType.isEmpty()) {
            cases = cases.stream().filter(c -> c.getCaseType().equals(caseType)).collect(java.util.stream.Collectors.toList());
        }
        vo.setCases(cases);
        return vo;
    }

    private CaseLibraryVO.CaseItem buildCase(Long id, String type, String name,
            LocalDate start, LocalDate end, String stage, String theme, String stock,
            String emotionChange, String conclusion, List<String> rules, List<String> tags) {
        CaseLibraryVO.CaseItem c = new CaseLibraryVO.CaseItem();
        c.setId(id); c.setCaseType(type); c.setCaseName(name);
        c.setStartDate(start); c.setEndDate(end);
        c.setMarketStage(stage); c.setMainTheme(theme); c.setCoreStock(stock);
        c.setEmotionChange(emotionChange); c.setConclusion(conclusion);
        c.setReusableRules(rules); c.setTags(tags);
        return c;
    }
}
