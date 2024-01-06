package org.geoproject.ingeo.services.classificators.kga.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.dto.classificators.kga.SoilClassKindGroupDto;
import org.geoproject.ingeo.mapper.classificators.kga.SoilClassKindGroupMapper;
import org.geoproject.ingeo.models.classificators.kga.SoilClassKindGroup;
import org.geoproject.ingeo.repositories.classificators.kga.SoilClassKindGroupRepository;
import org.geoproject.ingeo.services.classificators.kga.SoilClassKindGroupService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.geoproject.ingeo.constants.ServiceConstants.ID_FIELD;

@RequiredArgsConstructor
@Service
public class SoilClassKindGroupServiceImpl implements SoilClassKindGroupService {

    private final SoilClassKindGroupRepository soilClassKindGroupRepository;
    private final SoilClassKindGroupMapper soilClassKindGroupMapper;

    @Override
    public List<SoilClassKindGroupDto> getBySoilClassId(Long soilClassId) {
        var sort = Sort.by(ID_FIELD);

        var soilClassKindGroups = soilClassKindGroupRepository.findBySoilClassId(soilClassId, sort);

        return soilClassKindGroupMapper.soilClassKindGroupToSoilClassKindGroupDto(soilClassKindGroups);
    }
}
