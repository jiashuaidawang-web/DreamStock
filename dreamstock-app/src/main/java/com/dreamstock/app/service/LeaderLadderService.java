package com.dreamstock.app.service;

import com.dreamstock.domain.vo.LeaderLadderVO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class LeaderLadderService {

    private final ExecutorService virtualThreadExecutor;

    public LeaderLadderService(@Qualifier("virtualThreadExecutor") ExecutorService virtualThreadExecutor) {
        this.virtualThreadExecutor = virtualThreadExecutor;
    }

    public LeaderLadderVO getLeaderLadder(LocalDate tradeDate) {
        List<Future<LeaderLadderVO.BoardTier>> tierFutures = new ArrayList<>();
        for (int board = 10; board >= 2; board--) {
            int currentBoard = board;
            tierFutures.add(virtualThreadExecutor.submit(() -> buildTier(tradeDate, currentBoard)));
        }

        LeaderLadderVO vo = new LeaderLadderVO();
        vo.setTradeDate(tradeDate);
        vo.setMaxConsecutiveBoard(10L);
        vo.setTotalBidAmount(new BigDecimal("2730000000"));
        vo.setHotStockCount(9L);

        List<LeaderLadderVO.BoardTier> tiers = new ArrayList<>();
        for (Future<LeaderLadderVO.BoardTier> f : tierFutures) {
            try {
                tiers.add(f.get());
            } catch (Exception e) {
                throw new RuntimeException("Failed to build leader tier via virtual threads", e);
            }
        }
        vo.setTiers(tiers);
        return vo;
    }

    private LeaderLadderVO.BoardTier buildTier(LocalDate tradeDate, int boardCount) {
        LeaderLadderVO.BoardTier tier = new LeaderLadderVO.BoardTier();
        tier.setBoardCount((long) boardCount);

        List<LeaderLadderVO.LeaderCard> cards = new ArrayList<>();
        switch (boardCount) {
            case 10 -> {
                tier.setStockCount(1L);
                tier.setTotalBidAmount(new BigDecimal("382000000"));
                cards.add(buildCard("688981", "中芯国际", "市场总龙", "加速", "382000000", "10.0", "半导体国产化", 2));
            }
            case 9 -> {
                tier.setStockCount(2L);
                tier.setTotalBidAmount(new BigDecimal("355000000"));
                cards.add(buildCard("002230", "科大讯飞", "板块龙头", "确认", "145000000", "9.8", "人工智能/大模型", 1));
                cards.add(buildCard("300750", "宁德时代", "趋势龙头", "确认", "210000000", "9.2", "新能源/固态电池", 1));
            }
            case 8 -> {
                tier.setStockCount(2L);
                tier.setTotalBidAmount(new BigDecimal("283000000"));
                cards.add(buildCard("300059", "东方财富", "板块龙头", "分歧", "188000000", "8.5", "券商/金融科技", 2));
                cards.add(buildCard("300760", "迈瑞医疗", "趋势龙头", "确认", "95000000", "8.1", "医疗器械/出海", 1));
            }
            case 7 -> {
                tier.setStockCount(2L);
                tier.setTotalBidAmount(new BigDecimal("785000000"));
                cards.add(buildCard("002594", "比亚迪", "趋势龙头", "确认", "425000000", "7.8", "新能源汽车", 1));
                cards.add(buildCard("601318", "中国平安", "板块龙头", "竞争", "360000000", "7.5", "金融/保险", 1));
            }
            case 6 -> {
                tier.setStockCount(2L);
                tier.setTotalBidAmount(new BigDecimal("190000000"));
                cards.add(buildCard("300308", "中际旭创", "板块龙头", "确认", "112000000", "6.9", "光模块/算力网络", 1));
                cards.add(buildCard("600438", "通威股份", "补涨龙", "候选", "78000000", "6.5", "光伏/硅料", 2));
            }
            case 5 -> {
                tier.setStockCount(2L);
                tier.setTotalBidAmount(new BigDecimal("107000000"));
                cards.add(buildCard("002050", "三花智控", "补涨龙", "候选", "62000000", "5.8", "热管理/新能源汽车零部件", 1));
                cards.add(buildCard("002049", "紫光展锐", "板块龙头", "候选", "45000000", "5.2", "半导体/芯片设计", 1));
            }
            case 4 -> {
                tier.setStockCount(2L);
                tier.setTotalBidAmount(new BigDecimal("193000000"));
                cards.add(buildCard("603259", "药明康德", "板块龙头", "竞争", "105000000", "4.8", "CXO/医药外包", 1));
                cards.add(buildCard("601127", "赛力斯", "补涨龙", "竞争", "88000000", "4.5", "鸿蒙智行/华为汽车", 1));
            }
            case 3 -> {
                tier.setStockCount(2L);
                tier.setTotalBidAmount(new BigDecimal("127000000"));
                cards.add(buildCard("002475", "立讯精密", "板块龙头", "候选", "72000000", "3.8", "消费电子/苹果产业链", 1));
                cards.add(buildCard("000157", "中联重科", "补涨龙", "候选", "55000000", "3.5", "工程机械/出海", 1));
            }
            case 2 -> {
                tier.setStockCount(2L);
                tier.setTotalBidAmount(new BigDecimal("307000000"));
                cards.add(buildCard("600276", "恒瑞医药", "趋势龙头", "候选", "92000000", "2.9", "创新药/ADC药物", 1));
                cards.add(buildCard("600036", "招商银行", "趋势龙头", "竞争", "215000000", "2.5", "银行/高股息", 1));
            }
            default -> {
                tier.setStockCount(0L);
                tier.setTotalBidAmount(BigDecimal.ZERO);
            }
        }
        tier.setLeaders(cards);
        return tier;
    }

    private LeaderLadderVO.LeaderCard buildCard(String code, String name, String role, String status,
                                                 String bidAmount, String pctChg, String sector, int risk) {
        LeaderLadderVO.LeaderCard card = new LeaderLadderVO.LeaderCard();
        card.setStockCode(code);
        card.setStockName(name);
        card.setLeaderRoleName(role);
        card.setStatusName(status);
        card.setBidAmount(new BigDecimal(bidAmount));
        card.setPctChg(new BigDecimal(pctChg));
        card.setIndustrySector(sector);
        card.setRiskLevel(risk);
        return card;
    }
}
