package com.dreamstock.app.controller;

import com.dreamstock.app.service.CycleStrategyService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.CycleStrategyVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cycle-strategy")
public class CycleStrategyController {

    private final CycleStrategyService cycleStrategyService;

    public CycleStrategyController(CycleStrategyService cycleStrategyService) {
        this.cycleStrategyService = cycleStrategyService;
    }

    @GetMapping
    public ApiResult<CycleStrategyVO> getCycleStrategy() {
        return ApiResult.ok(cycleStrategyService.getCycleStrategy());
    }
}
