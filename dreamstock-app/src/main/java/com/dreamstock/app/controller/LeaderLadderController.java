package com.dreamstock.app.controller;

import com.dreamstock.app.service.LeaderLadderService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.LeaderLadderVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/leader-ladder")
public class LeaderLadderController {

    private final LeaderLadderService leaderLadderService;

    public LeaderLadderController(LeaderLadderService leaderLadderService) {
        this.leaderLadderService = leaderLadderService;
    }

    @GetMapping
    public ApiResult<LeaderLadderVO> getLeaderLadder(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tradeDate) {
        if (tradeDate == null) {
            tradeDate = LocalDate.now();
        }
        return ApiResult.ok(leaderLadderService.getLeaderLadder(tradeDate));
    }
}
