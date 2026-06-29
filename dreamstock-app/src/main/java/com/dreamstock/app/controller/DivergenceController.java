package com.dreamstock.app.controller;

import com.dreamstock.app.service.DivergenceService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.DivergenceVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/divergence")
public class DivergenceController {

    private final DivergenceService divergenceService;

    public DivergenceController(DivergenceService divergenceService) {
        this.divergenceService = divergenceService;
    }

    @GetMapping
    public ApiResult<DivergenceVO> getDivergence(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tradeDate) {
        if (tradeDate == null) tradeDate = LocalDate.now();
        return ApiResult.ok(divergenceService.getDivergence(tradeDate));
    }
}
