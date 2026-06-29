package com.dreamstock.app.controller;

import com.dreamstock.app.service.EmotionScoreService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.EmotionScoreVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/emotion-score")
public class EmotionScoreController {

    private final EmotionScoreService emotionScoreService;

    public EmotionScoreController(EmotionScoreService emotionScoreService) {
        this.emotionScoreService = emotionScoreService;
    }

    @GetMapping
    public ApiResult<EmotionScoreVO> getEmotionScore(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tradeDate) {
        if (tradeDate == null) tradeDate = LocalDate.now();
        return ApiResult.ok(emotionScoreService.getEmotionScore(tradeDate));
    }
}
