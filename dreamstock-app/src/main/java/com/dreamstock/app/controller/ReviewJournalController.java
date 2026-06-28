package com.dreamstock.app.controller;

import com.dreamstock.app.service.ReviewJournalService;
import com.dreamstock.domain.entity.mysql.BizReviewDailyJournal;
import com.dreamstock.domain.entity.mysql.BizReviewTradeRecord;
import com.dreamstock.domain.vo.ApiResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewJournalController {

    private final ReviewJournalService reviewJournalService;

    public ReviewJournalController(ReviewJournalService reviewJournalService) {
        this.reviewJournalService = reviewJournalService;
    }

    @GetMapping("/journal")
    public ApiResult<BizReviewDailyJournal> getJournal(
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tradeDate) {
        return ApiResult.ok(reviewJournalService.getJournalByDate(userId, tradeDate));
    }

    @GetMapping("/journal/recent")
    public ApiResult<List<BizReviewDailyJournal>> recentJournals(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "10") int limit) {
        return ApiResult.ok(reviewJournalService.listRecentJournals(userId, limit));
    }

    @PostMapping("/journal")
    public ApiResult<BizReviewDailyJournal> saveJournal(@RequestBody BizReviewDailyJournal journal) {
        return ApiResult.ok(reviewJournalService.saveJournal(journal));
    }

    @GetMapping("/trade-records")
    public ApiResult<List<BizReviewTradeRecord>> getTradeRecords(@RequestParam Long journalId) {
        return ApiResult.ok(reviewJournalService.getTradeRecords(journalId));
    }

    @PostMapping("/trade-record")
    public ApiResult<BizReviewTradeRecord> saveTradeRecord(@RequestBody BizReviewTradeRecord record) {
        return ApiResult.ok(reviewJournalService.saveTradeRecord(record));
    }

    @DeleteMapping("/trade-record/{id}")
    public ApiResult<Void> deleteTradeRecord(@PathVariable Long id) {
        reviewJournalService.deleteTradeRecord(id);
        return ApiResult.ok();
    }
}
