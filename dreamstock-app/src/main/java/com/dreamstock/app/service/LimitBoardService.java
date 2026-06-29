package com.dreamstock.app.service;

import com.dreamstock.domain.vo.LimitBoardVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LimitBoardService {

    public LimitBoardVO getLimitBoard(LocalDate tradeDate) {
        LimitBoardVO vo = new LimitBoardVO();
        vo.setTradeDate(tradeDate);
        vo.setFirstBoardCount(45);
        vo.setSecondBoardCount(18);
        vo.setThirdBoardCount(7);
        vo.setHighBoardCount(5);
        vo.setLimitDownCount(31);
        vo.setFailedLimitRate(new BigDecimal("22.3"));
        vo.setPromotionRate(new BigDecimal("48.5"));
        vo.setMaxBoardHeight(10);

        vo.setLimitUpPool(buildLimitUpPool());
        vo.setLimitDownPool(buildLimitDownPool());
        vo.setFailedBoardPool(buildFailedBoardPool());
        vo.setYesterdayLimitFeedback(buildYesterdayFeedback());
        return vo;
    }

    private List<LimitBoardVO.PoolItem> buildLimitUpPool() {
        List<LimitBoardVO.PoolItem> list = new ArrayList<>();
        String[][] data = {
            {"688981", "中芯国际", "10.00", "382000000", "10", "半导体国产化", "09:25:03", "185000000", "0", "8.5"},
            {"002230", "科大讯飞", "9.98", "145000000", "9", "AI大模型", "09:31:15", "92000000", "1", "12.3"},
            {"300750", "宁德时代", "10.01", "210000000", "9", "固态电池", "09:42:28", "128000000", "0", "6.8"},
            {"300059", "东方财富", "9.99", "188000000", "8", "券商金融科技", "10:05:42", "76000000", "2", "15.6"},
            {"300308", "中际旭创", "10.00", "112000000", "6", "光模块算力", "09:55:18", "68000000", "1", "9.2"},
            {"002594", "比亚迪", "10.02", "425000000", "7", "新能源汽车", "09:38:55", "215000000", "0", "5.8"},
        };
        for (String[] row : data) {
            LimitBoardVO.PoolItem item = new LimitBoardVO.PoolItem();
            item.setStockCode(row[0]); item.setStockName(row[1]); item.setPctChg(new BigDecimal(row[2]));
            item.setAmount(new BigDecimal(row[3])); item.setBoardCount(Integer.parseInt(row[4]));
            item.setReason(row[5]); item.setSealTime(row[6]); item.setSealAmount(new BigDecimal(row[7]));
            item.setOpenCount(Integer.parseInt(row[8])); item.setTurnoverRate(new BigDecimal(row[9]));
            list.add(item);
        }
        return list;
    }

    private List<LimitBoardVO.PoolItem> buildLimitDownPool() {
        List<LimitBoardVO.PoolItem> list = new ArrayList<>();
        String[][] data = {
            {"600123", "兰花科创", "-10.00", "52000000", "1", "煤炭退潮", "09:30:00", "35000000", "0", "18.5"},
            {"000725", "京东方A", "-9.98", "85000000", "1", "面板供过于求", "10:15:22", "42000000", "1", "12.8"},
        };
        for (String[] row : data) {
            LimitBoardVO.PoolItem item = new LimitBoardVO.PoolItem();
            item.setStockCode(row[0]); item.setStockName(row[1]); item.setPctChg(new BigDecimal(row[2]));
            item.setAmount(new BigDecimal(row[3])); item.setBoardCount(Integer.parseInt(row[4]));
            item.setReason(row[5]); item.setSealTime(row[6]); item.setSealAmount(new BigDecimal(row[7]));
            item.setOpenCount(Integer.parseInt(row[8])); item.setTurnoverRate(new BigDecimal(row[9]));
            list.add(item);
        }
        return list;
    }

    private List<LimitBoardVO.PoolItem> buildFailedBoardPool() {
        List<LimitBoardVO.PoolItem> list = new ArrayList<>();
        String[][] data = {
            {"002475", "立讯精密", "6.85", "72000000", "3", "消费电子", "10:22:35", "0", "3", "22.5"},
            {"600438", "通威股份", "5.32", "78000000", "6", "光伏硅料", "11:05:18", "0", "4", "25.8"},
        };
        for (String[] row : data) {
            LimitBoardVO.PoolItem item = new LimitBoardVO.PoolItem();
            item.setStockCode(row[0]); item.setStockName(row[1]); item.setPctChg(new BigDecimal(row[2]));
            item.setAmount(new BigDecimal(row[3])); item.setBoardCount(Integer.parseInt(row[4]));
            item.setReason(row[5]); item.setSealTime(row[6]); item.setSealAmount(new BigDecimal(row[7]));
            item.setOpenCount(Integer.parseInt(row[8])); item.setTurnoverRate(new BigDecimal(row[9]));
            list.add(item);
        }
        return list;
    }

    private List<LimitBoardVO.PoolItem> buildYesterdayFeedback() {
        List<LimitBoardVO.PoolItem> list = new ArrayList<>();
        String[][] data = {
            {"688981", "中芯国际", "10.00", "382000000", "10", "连板晋级成功", "09:25:03", "185000000", "0", "8.5"},
            {"002230", "科大讯飞", "9.98", "145000000", "9", "连板晋级成功", "09:31:15", "92000000", "1", "12.3"},
        };
        for (String[] row : data) {
            LimitBoardVO.PoolItem item = new LimitBoardVO.PoolItem();
            item.setStockCode(row[0]); item.setStockName(row[1]); item.setPctChg(new BigDecimal(row[2]));
            item.setAmount(new BigDecimal(row[3])); item.setBoardCount(Integer.parseInt(row[4]));
            item.setReason(row[5]); item.setSealTime(row[6]); item.setSealAmount(new BigDecimal(row[7]));
            item.setOpenCount(Integer.parseInt(row[8])); item.setTurnoverRate(new BigDecimal(row[9]));
            list.add(item);
        }
        return list;
    }
}
