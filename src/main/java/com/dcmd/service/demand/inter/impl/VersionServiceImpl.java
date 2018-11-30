package com.dcmd.service.demand.inter.impl;

import com.dcmd.service.demand.entity.Version;
import com.dcmd.service.demand.inter.VersionService;
import com.dcmd.service.demand.mapper.VersionMapper;
import com.dcmd.service.demand.vo.VersionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionServiceImpl implements VersionService {

    @Autowired
    private VersionMapper versionMapper;


    @Override
    public int deleteByPrimaryKey(Long id)throws Exception {
        return versionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Version record)throws Exception {
        return versionMapper.insert(record);
    }

    @Override
    public Version selectByPrimaryKey(Long id)throws Exception {
        return versionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Version> selectAll() throws Exception{
        return versionMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Version record)throws Exception {
        return versionMapper.updateByPrimaryKey(record);
    }

    @Override
    public void update(Version version)throws Exception {
        versionMapper.update(version);
    }

    @Override
    public List<VersionVo> findDemandHistoryVersionDescription(Long demandId) {
        return versionMapper.findDemandHistoryVersionDescription(demandId);
    }


}
