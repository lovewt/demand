package com.dcmd.service.demand.mapper;

import com.dcmd.service.demand.entity.Features;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeaturesMapper {

    /**
     * 批量插入功能点
     * @param features
     */
    void insert(Features features);
}
