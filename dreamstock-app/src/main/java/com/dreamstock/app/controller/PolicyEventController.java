package com.dreamstock.app.controller;

import com.dreamstock.app.service.PolicyEventService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.PolicyEventVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/policy-event")
public class PolicyEventController {

    private final PolicyEventService policyEventService;

    public PolicyEventController(PolicyEventService policyEventService) {
        this.policyEventService = policyEventService;
    }

    @GetMapping
    public ApiResult<PolicyEventVO> getPolicyEvents(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if (endDate == null) endDate = LocalDate.now();
        if (startDate == null) startDate = endDate.minusDays(30);
        return ApiResult.ok(policyEventService.getPolicyEvents(startDate, endDate));
    }
}
