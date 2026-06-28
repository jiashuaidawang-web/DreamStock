package com.dreamstock.app.controller;

import com.dreamstock.app.service.WatchlistService;
import com.dreamstock.domain.entity.mysql.BizUserWatchlist;
import com.dreamstock.domain.vo.ApiResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    private final WatchlistService watchlistService;

    public WatchlistController(WatchlistService watchlistService) {
        this.watchlistService = watchlistService;
    }

    @GetMapping
    public ApiResult<List<BizUserWatchlist>> getUserWatchlist(
            @RequestParam Long userId,
            @RequestParam(required = false) Integer watchType) {
        return ApiResult.ok(watchlistService.getUserWatchlist(userId, watchType));
    }

    @PostMapping
    public ApiResult<BizUserWatchlist> addToWatchlist(@RequestBody BizUserWatchlist item) {
        return ApiResult.ok(watchlistService.addToWatchlist(item));
    }

    @DeleteMapping("/{id}")
    public ApiResult<Void> removeFromWatchlist(@PathVariable Long id) {
        watchlistService.removeFromWatchlist(id);
        return ApiResult.ok();
    }
}
