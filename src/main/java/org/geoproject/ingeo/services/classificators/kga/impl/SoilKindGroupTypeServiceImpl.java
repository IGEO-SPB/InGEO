package org.geoproject.ingeo.services.classificators.kga.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.dto.classificators.kga.SoilKindGroupTypeDto;
import org.geoproject.ingeo.mapper.classificators.kga.SoilKindGroupTypeMapper;
import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;
import org.geoproject.ingeo.repositories.classificators.kga.SoilKindGroupTypeRepository;
import org.geoproject.ingeo.services.classificators.kga.SoilKindGroupTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoilKindGroupTypeServiceImpl implements SoilKindGroupTypeService {

    private final SoilKindGroupTypeRepository soilKindGroupTypeRepository;
    private final SoilKindGroupTypeMapper soilKindGroupTypeMapper;

    @Override
    public List<SoilKindGroupTypeDto> getBySoilKindId(Long soilKindId) {
        var soilKindGroupTypes = soilKindGroupTypeRepository.findBySoilKindId(soilKindId);

        return soilKindGroupTypeMapper.soilKindGroupTypeToSoilKindGroupTypeDto(soilKindGroupTypes);
    }
}
