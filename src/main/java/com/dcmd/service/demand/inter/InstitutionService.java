package com.dcmd.service.demand.inter;

import com.dcmd.service.demand.entity.Institution;

import java.util.List;

public interface InstitutionService {

     List<Institution> queryInstitionNoAndName()throws Exception;
}
