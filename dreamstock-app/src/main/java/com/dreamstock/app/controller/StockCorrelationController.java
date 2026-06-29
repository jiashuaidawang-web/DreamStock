package com.dreamstock.app.controller;

import com.dreamstock.app.service.StockCorrelationService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.StockCorrelationVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/stock-correlation")
public class StockCorrelationController {

    private final StockCorrelationService stockCorrelationService;

    public StockCorrelationController(StockCorrelationService stockCorrelationService) {
        this.stockCorrelationService = stockCorrelationService;
    }

    @GetMapping
    public ApiResult<StockCorrelationVO> getCorrelation(
            @RequestParam(defaultValue = "688981") String stockCode,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tradeDate,
            @RequestParam(defaultValue = "20") int windowSize) {
        if (tradeDate == null) tradeDate = LocalDate.now();
        return ApiResult.ok(stockCorrelationService.getCorrelation(stockCode, tradeDate, windowSize));
    }
}
