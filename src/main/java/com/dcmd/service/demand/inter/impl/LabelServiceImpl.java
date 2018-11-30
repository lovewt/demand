package com.dcmd.service.demand.inter.impl;

import com.dcmd.common.core.utils.ErrorInfo;
import com.dcmd.service.demand.entity.Label;
import com.dcmd.service.demand.inter.LabelService;
import com.dcmd.service.demand.mapper.LabelMapper;
import com.dcmd.service.demand.mapper.SysParameterMapper;
import com.dcmd.service.demand.vo.ParameterVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private SysParameterMapper sysParameterMapper;
    /**
     * s所有
     * @return
     */
    @Override
    public List<Label> selectAll()throws Exception {
        return labelMapper.selectAll();
    }

    @Override
    public String selectLabelesName(String[] ids) throws Exception{
        return labelMapper.selectLabelesName(ids);
    }

    @Override
    public int deleteByPrimaryKey(Integer id)throws Exception {
        return labelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Label record)throws Exception {
        return labelMapper.insert(record);
    }

    @Override
    public Label selectByPrimaryKey(Integer id)throws Exception {
        return labelMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Label record)throws Exception {
        return labelMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Label> getMarkDown() throws Exception{
        return labelMapper.getMarkDown();
    }

    @Override
    public List<Label> queryReviewState()throws Exception {
        return labelMapper.queryReviewState();
    }

    @Override
    public List<Label> queryAcceptType()throws Exception {
        return labelMapper.queryAcceptType();
    }

    @Override
    public List<Map<String, Object>> findMarkdownByDemandType(Long type) throws Exception {
        List<Map<String, Object>> markdownList = sysParameterMapper.findMarkdownByDemandType(type);
        if(CollectionUtils.isEmpty(markdownList)){
            throw new ErrorInfo("没有对应的Markdown模板",500);
        }
        return markdownList;
    }

    @Override
    public List<ParameterVo> getDemandStatusInfo(String parameterType) {
        return sysParameterMapper.getDemandStatusInfo(parameterType);
    }

}
