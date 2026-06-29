package com.dreamstock.app.controller;

import com.dreamstock.app.service.CaseLibraryService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.CaseLibraryVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/case-library")
public class CaseLibraryController {

    private final CaseLibraryService caseLibraryService;

    public CaseLibraryController(CaseLibraryService caseLibraryService) {
        this.caseLibraryService = caseLibraryService;
    }

    @GetMapping
    public ApiResult<CaseLibraryVO> getCases(
            @RequestParam(required = false) String caseType) {
        return ApiResult.ok(caseLibraryService.getCases(caseType));
    }
}
