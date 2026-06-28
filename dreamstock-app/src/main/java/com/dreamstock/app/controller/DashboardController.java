package com.dreamstock.app.controller;

import com.dreamstock.app.service.DashboardService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.DashboardVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ApiResult<DashboardVO> getDashboard(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tradeDate) {
        if (tradeDate == null) {
            tradeDate = LocalDate.now();
        }
        return ApiResult.ok(dashboardService.getDashboard(tradeDate));
    }
}
