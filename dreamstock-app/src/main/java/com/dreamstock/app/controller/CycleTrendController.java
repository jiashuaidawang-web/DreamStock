package com.dreamstock.app.controller;

import com.dreamstock.app.service.CycleTrendService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.CycleTrendVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/cycle-trend")
public class CycleTrendController {

    private final CycleTrendService cycleTrendService;

    public CycleTrendController(CycleTrendService cycleTrendService) {
        this.cycleTrendService = cycleTrendService;
    }

    @GetMapping
    public ApiResult<CycleTrendVO> getCycleTrend(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "60") int days) {
        if (endDate == null) endDate = LocalDate.now();
        if (startDate == null) startDate = endDate.minusDays(days);
        return ApiResult.ok(cycleTrendService.getCycleTrend(startDate, endDate, days));
    }
}
