package com.dcmd.service.demand.mapper;

import com.dcmd.service.demand.entity.Institution;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionMapper {
    List<Institution> queryInstitutionNoAndName();
}
