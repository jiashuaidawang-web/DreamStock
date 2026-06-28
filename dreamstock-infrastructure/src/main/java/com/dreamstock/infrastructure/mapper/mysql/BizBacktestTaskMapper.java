package com.dreamstock.infrastructure.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamstock.domain.entity.mysql.BizBacktestTask;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BizBacktestTaskMapper extends BaseMapper<BizBacktestTask> {
}
