package com.dreamstock.app.controller;

import com.dreamstock.app.service.MarketTrendService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.MarketTrendVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/market-trend")
public class MarketTrendController {

    private final MarketTrendService marketTrendService;

    public MarketTrendController(MarketTrendService marketTrendService) {
        this.marketTrendService = marketTrendService;
    }

    @GetMapping
    public ApiResult<MarketTrendVO> getMarketTrend(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tradeDate) {
        if (tradeDate == null) tradeDate = LocalDate.now();
        return ApiResult.ok(marketTrendService.getMarketTrend(tradeDate));
    }
}
