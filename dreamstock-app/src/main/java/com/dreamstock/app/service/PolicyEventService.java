package com.dreamstock.app.service;

import com.dreamstock.domain.vo.PolicyEventVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyEventService {

    public PolicyEventVO getPolicyEvents(LocalDate startDate, LocalDate endDate) {
        PolicyEventVO vo = new PolicyEventVO();
        List<PolicyEventVO.EventItem> events = new ArrayList<>();

        Object[][] data = {
            {1L, endDate, "POLICY", "央行宣布降准50基点", "中国人民银行决定下调金融机构存款准备金率0.5个百分点，释放长期资金约1万亿元。", "全市场", "UP", List.of("银行", "券商"), List.of("601398", "601318"), "2.15", "3.42", "1.85", "已验证"},
            {2L, endDate.minusDays(2), "REGULATORY", "证监会规范量化交易", "证监会发布量化交易监管新规，要求量化机构加强报告义务。", "量化相关", "DOWN", List.of("量化", "券商"), List.of("300059", "600030"), "-1.20", "-0.85", "0.32", "已验证"},
            {3L, endDate.minusDays(5), "INDUSTRY", "工信部发布AI产业规划", "工信部发布人工智能产业发展三年行动计划，重点支持大模型和算力基础设施。", "AI板块", "UP", List.of("人工智能", "算力"), List.of("002230", "688981"), "3.85", "5.62", "4.28", "已验证"},
            {4L, endDate.minusDays(8), "EXTERNAL", "美联储暂停加息", "美联储宣布维持利率不变，暗示年内可能降息。", "全市场", "UP", List.of("外资偏好", "高股息"), List.of("600519", "000858"), "1.56", "2.18", "1.92", "已验证"},
            {5L, endDate.minusDays(12), "POLICY", "新能源车购置税延续", "国务院决定延续新能源汽车车辆购置税减免政策至2027年底。", "新能源车", "UP", List.of("新能源车", "锂电池"), List.of("002594", "300750"), "2.68", "4.15", "3.52", "已验证"},
            {6L, endDate.minusDays(15), "REGULATORY", "IPO节奏阶段性收紧", "证监会表示将阶段性收紧IPO节奏，统筹一二级市场平衡。", "次新股", "UP", List.of("次新股"), List.of(), "0.85", "1.32", "0.68", "待验证"},
        };

        for (Object[] row : data) {
            PolicyEventVO.EventItem item = new PolicyEventVO.EventItem();
            item.setId((Long) row[0]);
            item.setEventDate((LocalDate) row[1]);
            item.setEventType((String) row[2]);
            item.setTitle((String) row[3]);
            item.setContent((String) row[4]);
            item.setImpactScope((String) row[5]);
            item.setImpactDirection((String) row[6]);
            @SuppressWarnings("unchecked") List<String> themes = (List<String>) row[7];
            item.setRelatedThemes(themes);
            @SuppressWarnings("unchecked") List<String> stocks = (List<String>) row[8];
            item.setRelatedStocks(stocks);
            item.setReturn1d(new BigDecimal((String) row[9]));
            item.setReturn3d(new BigDecimal((String) row[10]));
            item.setReturn5d(new BigDecimal((String) row[11]));
            item.setVerificationStatus((String) row[12]);
            events.add(item);
        }
        vo.setEvents(events);
        return vo;
    }
}
