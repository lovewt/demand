package com.dcmd.service.demand.mapper;

import com.dcmd.service.demand.vo.ParameterVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 2018/9/5.
 */
@Repository
public interface SysParameterMapper {
    /**
     * 根据需求类型拿到对应的Markdown模板
     * @param type
     * @return
     */
    List<Map<String,Object>> findMarkdownByDemandType(Long type) throws  Exception;

    /**
     * 获取需求条目层级信息
     * @return
     */
    List<ParameterVo> getEntryLevelInfo() throws  Exception;

    List<ParameterVo> getDemandStatusInfo(@Param("parameterType") String parameterType);
}
