package com.dreamstock.infrastructure.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamstock.domain.entity.mysql.DimStockSecurity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DimStockSecurityMapper extends BaseMapper<DimStockSecurity> {
}
