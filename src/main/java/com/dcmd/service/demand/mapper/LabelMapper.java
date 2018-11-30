package com.dcmd.service.demand.mapper;

import com.dcmd.service.demand.entity.Label;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelMapper{
    /**
     * 查询所有
     * @param
     * @return
     */
    List<Label> selectAll();

    String selectLabelesName(String[] ids);


    int deleteByPrimaryKey(Integer id);

    int insert(Label record);

    Label selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Label record);


    /**
     * markDown
     * @return
     */
    List<Label> getMarkDown();

    /**
     * 查询评审状态
     * @return
     */
    List<Label> queryReviewState();

    /**
     * 查询受理状态
     * @return
     */
    List<Label> queryAcceptType();
}
