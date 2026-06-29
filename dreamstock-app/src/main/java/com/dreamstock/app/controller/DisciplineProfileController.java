package com.dreamstock.app.controller;

import com.dreamstock.app.service.DisciplineProfileService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.DisciplineProfileVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discipline-profile")
public class DisciplineProfileController {

    private final DisciplineProfileService disciplineProfileService;

    public DisciplineProfileController(DisciplineProfileService disciplineProfileService) {
        this.disciplineProfileService = disciplineProfileService;
    }

    @GetMapping
    public ApiResult<DisciplineProfileVO> getProfile(
            @RequestParam(defaultValue = "1") Long userId) {
        return ApiResult.ok(disciplineProfileService.getProfile(userId));
    }
}
