package com.dreamstock.app.controller;

import com.dreamstock.app.service.CapitalFlowService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.CapitalFlowVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/capital-flow")
public class CapitalFlowController {

    private final CapitalFlowService capitalFlowService;

    public CapitalFlowController(CapitalFlowService capitalFlowService) {
        this.capitalFlowService = capitalFlowService;
    }

    @GetMapping
    public ApiResult<CapitalFlowVO> getCapitalFlow(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tradeDate) {
        if (tradeDate == null) tradeDate = LocalDate.now();
        return ApiResult.ok(capitalFlowService.getCapitalFlow(tradeDate));
    }
}
