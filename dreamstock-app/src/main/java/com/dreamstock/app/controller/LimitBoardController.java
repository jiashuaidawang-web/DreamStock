package com.dreamstock.app.controller;

import com.dreamstock.app.service.LimitBoardService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.LimitBoardVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/limit-board")
public class LimitBoardController {

    private final LimitBoardService limitBoardService;

    public LimitBoardController(LimitBoardService limitBoardService) {
        this.limitBoardService = limitBoardService;
    }

    @GetMapping
    public ApiResult<LimitBoardVO> getLimitBoard(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tradeDate) {
        if (tradeDate == null) tradeDate = LocalDate.now();
        return ApiResult.ok(limitBoardService.getLimitBoard(tradeDate));
    }
}
