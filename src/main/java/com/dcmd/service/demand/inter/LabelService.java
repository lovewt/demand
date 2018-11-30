package com.dcmd.service.demand.inter;

import com.dcmd.service.demand.entity.Label;
import com.dcmd.service.demand.vo.ParameterVo;

import java.util.List;
import java.util.Map;

public interface LabelService {

    /**
     * 查询所有
     * @param
     * @return
     */
    List<Label> selectAll()throws Exception;

    String selectLabelesName(String[] ids)throws Exception;


    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(Label record)throws Exception;

    Label selectByPrimaryKey(Integer id)throws Exception;

    int updateByPrimaryKey(Label record)throws Exception;


    /**
     * markDown
     * @return
     */
    List<Label> getMarkDown()throws Exception;

    /**
     * 查询评审状态
     * @return
     */
    List<Label> queryReviewState()throws Exception;

    /**
     * 查询受理状态
     * @return
     */
    List<Label> queryAcceptType()throws Exception;

    /**
     * 根据需求类型拿到对应的Markdown模板
     * @param type
     * @return
     */
    List<Map<String,Object>> findMarkdownByDemandType(Long type) throws  Exception;

    /**
     * 获取需求状态
     * @param parameterType
     * @return
     */
    List<ParameterVo> getDemandStatusInfo(String parameterType);
}
