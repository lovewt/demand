package com.dcmd.service.demand.inter;

import com.dcmd.service.demand.entity.Version;
import com.dcmd.service.demand.vo.VersionVo;

import java.util.List;

public interface VersionService {

    /**
     * 查询所有
     * @return
     */

    int deleteByPrimaryKey(Long id)throws Exception;

    int insert(Version record)throws Exception;

    Version selectByPrimaryKey(Long id)throws Exception;

    List<Version> selectAll()throws Exception;

    int updateByPrimaryKey(Version record)throws Exception;

    void update(Version version)throws Exception;


    /**
     * 查询历史版本需求
     * @param demandId
     * @return
     */
    List<VersionVo> findDemandHistoryVersionDescription(Long demandId);
}
