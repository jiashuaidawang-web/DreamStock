package com.dreamstock.app.controller;

import com.dreamstock.app.service.ThemeMatrixService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.ThemeMatrixVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/theme-matrix")
public class ThemeMatrixController {

    private final ThemeMatrixService themeMatrixService;

    public ThemeMatrixController(ThemeMatrixService themeMatrixService) {
        this.themeMatrixService = themeMatrixService;
    }

    @GetMapping
    public ApiResult<ThemeMatrixVO> getThemeMatrix(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tradeDate) {
        if (tradeDate == null) tradeDate = LocalDate.now();
        return ApiResult.ok(themeMatrixService.getThemeMatrix(tradeDate));
    }
}
