package com.dreamstock.app.controller;

import com.dreamstock.app.service.TradePlanService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.TradePlanVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/trade-plan")
public class TradePlanController {

    private final TradePlanService tradePlanService;

    public TradePlanController(TradePlanService tradePlanService) {
        this.tradePlanService = tradePlanService;
    }

    @GetMapping
    public ApiResult<TradePlanVO> getTradePlans(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if (endDate == null) endDate = LocalDate.now();
        if (startDate == null) startDate = endDate.minusDays(7);
        return ApiResult.ok(tradePlanService.getTradePlans(startDate, endDate));
    }
}
