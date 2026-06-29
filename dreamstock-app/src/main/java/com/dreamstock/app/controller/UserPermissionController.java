package com.dreamstock.app.controller;

import com.dreamstock.app.service.UserPermissionService;
import com.dreamstock.domain.vo.ApiResult;
import com.dreamstock.domain.vo.UserPermissionVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-permission")
public class UserPermissionController {

    private final UserPermissionService userPermissionService;

    public UserPermissionController(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    @GetMapping
    public ApiResult<UserPermissionVO> getUserPermissions() {
        return ApiResult.ok(userPermissionService.getUserPermissions());
    }
}
