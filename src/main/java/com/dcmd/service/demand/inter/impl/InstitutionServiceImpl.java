package com.dcmd.service.demand.inter.impl;

import com.dcmd.service.demand.entity.Institution;
import com.dcmd.service.demand.inter.InstitutionService;
import com.dcmd.service.demand.mapper.InstitutionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    @Autowired
    InstitutionMapper institutionMapper;
    @Override
    public List<Institution> queryInstitionNoAndName()throws Exception {
        return institutionMapper.queryInstitutionNoAndName();
    }
}
