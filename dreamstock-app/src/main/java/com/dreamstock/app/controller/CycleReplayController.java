package com.dreamstock.app.controller;

import com.dreamstock.app.service.CycleReplayService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.CycleReplayVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/cycle-replay")
public class CycleReplayController {

    private final CycleReplayService cycleReplayService;

    public CycleReplayController(CycleReplayService cycleReplayService) {
        this.cycleReplayService = cycleReplayService;
    }

    @GetMapping
    public ApiResult<CycleReplayVO> getReplay(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if (endDate == null) endDate = LocalDate.now();
        if (startDate == null) startDate = endDate.minusDays(20);
        return ApiResult.ok(cycleReplayService.getReplay(startDate, endDate));
    }
}
