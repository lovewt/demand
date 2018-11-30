package com.dcmd.service.demand.mapper;

import com.dcmd.service.demand.entity.Version;
import com.dcmd.service.demand.vo.VersionVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionMapper{

    /**
     * 查询所有
     * @return
     */

    int deleteByPrimaryKey(Long id);

    int insert(Version record);

    Version selectByPrimaryKey(Long id);

    List<Version> selectAll();

    int updateByPrimaryKey(Version record);

    void update(Version version);


    /**
     * 查询历史版本需求
     * @param demandId
     * @return
     */
    List<VersionVo> findDemandHistoryVersionDescription(Long demandId);

    String queryMaxVersionNum(Long demandId);

    /**
     * 根据需求ID获取所有版本ID
     * @param id  需求ID
     * @return
     */
    List<Long> findAllByDemandId(Long id);

    /**
     * 批量删除
     * @param idList
     */
    void deleteAll( @Param("idList") List<Long> idList);

    void inserto(Version version);
}
