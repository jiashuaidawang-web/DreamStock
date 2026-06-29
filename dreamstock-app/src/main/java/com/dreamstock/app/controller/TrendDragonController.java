package com.dreamstock.app.controller;

import com.dreamstock.app.service.TrendDragonService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.TrendDragonVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trend-dragon")
public class TrendDragonController {

    private final TrendDragonService trendDragonService;

    public TrendDragonController(TrendDragonService trendDragonService) {
        this.trendDragonService = trendDragonService;
    }

    @GetMapping
    public ApiResult<TrendDragonVO> getTrendDragons() {
        return ApiResult.ok(trendDragonService.getTrendDragons());
    }
}
