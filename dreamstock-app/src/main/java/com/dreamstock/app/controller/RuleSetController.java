package com.dreamstock.app.controller;

import com.dreamstock.app.service.RuleSetService;
import com.dreamstock.domain.entity.mysql.BizRuleItem;
import com.dreamstock.domain.entity.mysql.BizRuleSet;
import com.dreamstock.domain.vo.ApiResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rule-set")
public class RuleSetController {

    private final RuleSetService ruleSetService;

    public RuleSetController(RuleSetService ruleSetService) {
        this.ruleSetService = ruleSetService;
    }

    @GetMapping
    public ApiResult<List<BizRuleSet>> listRuleSets(
            @RequestParam(required = false) String domain,
            @RequestParam(required = false) Integer status) {
        return ApiResult.ok(ruleSetService.listRuleSets(domain, status));
    }

    @GetMapping("/{id}")
    public ApiResult<BizRuleSet> getRuleSet(@PathVariable Long id) {
        return ApiResult.ok(ruleSetService.getRuleSetById(id));
    }

    @GetMapping("/{id}/items")
    public ApiResult<List<BizRuleItem>> getRuleItems(@PathVariable Long id) {
        return ApiResult.ok(ruleSetService.getRuleItems(id));
    }

    @PostMapping
    public ApiResult<BizRuleSet> saveRuleSet(@RequestBody BizRuleSet ruleSet) {
        return ApiResult.ok(ruleSetService.saveRuleSet(ruleSet));
    }
}
