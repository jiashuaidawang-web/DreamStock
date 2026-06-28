package com.dreamstock.app.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dreamstock.domain.entity.mysql.BizRuleItem;
import com.dreamstock.domain.entity.mysql.BizRuleSet;
import com.dreamstock.infrastructure.mapper.mysql.BizRuleItemMapper;
import com.dreamstock.infrastructure.mapper.mysql.BizRuleSetMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleSetService {

    private final BizRuleSetMapper ruleSetMapper;
    private final BizRuleItemMapper ruleItemMapper;

    public RuleSetService(BizRuleSetMapper ruleSetMapper, BizRuleItemMapper ruleItemMapper) {
        this.ruleSetMapper = ruleSetMapper;
        this.ruleItemMapper = ruleItemMapper;
    }

    public List<BizRuleSet> listRuleSets(String domain, Integer status) {
        LambdaQueryWrapper<BizRuleSet> wrapper = new LambdaQueryWrapper<>();
        if (domain != null) wrapper.eq(BizRuleSet::getRuleDomain, domain);
        if (status != null) wrapper.eq(BizRuleSet::getStatus, status);
        wrapper.orderByDesc(BizRuleSet::getUpdatedAt);
        return ruleSetMapper.selectList(wrapper);
    }

    public BizRuleSet getRuleSetById(Long id) {
        return ruleSetMapper.selectById(id);
    }

    public List<BizRuleItem> getRuleItems(Long ruleSetId) {
        LambdaQueryWrapper<BizRuleItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BizRuleItem::getRuleSetId, ruleSetId)
               .eq(BizRuleItem::getEnabled, 1)
               .orderByAsc(BizRuleItem::getId);
        return ruleItemMapper.selectList(wrapper);
    }

    public BizRuleSet saveRuleSet(BizRuleSet ruleSet) {
        if (ruleSet.getId() != null) {
            ruleSetMapper.updateById(ruleSet);
        } else {
            ruleSetMapper.insert(ruleSet);
        }
        return ruleSet;
    }
}
