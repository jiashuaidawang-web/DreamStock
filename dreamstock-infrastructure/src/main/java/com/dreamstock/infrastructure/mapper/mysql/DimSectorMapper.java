package com.dreamstock.infrastructure.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dreamstock.domain.entity.mysql.DimSector;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DimSectorMapper extends BaseMapper<DimSector> {
}
