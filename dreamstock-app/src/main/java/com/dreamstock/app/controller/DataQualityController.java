package com.dreamstock.app.controller;

import com.dreamstock.app.service.DataQualityService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.DataQualityVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data-quality")
public class DataQualityController {

    private final DataQualityService dataQualityService;

    public DataQualityController(DataQualityService dataQualityService) {
        this.dataQualityService = dataQualityService;
    }

    @GetMapping
    public ApiResult<DataQualityVO> getDataQuality() {
        return ApiResult.ok(dataQualityService.getDataQuality());
    }
}
