package org.geoproject.ingeo.services.classificators.kga.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.dto.classificators.kga.SoilGroupTypeDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.mapper.classificators.kga.SoilGroupTypeMapper;
import org.geoproject.ingeo.repositories.classificators.kga.SoilGroupTypeRepository;
import org.geoproject.ingeo.services.classificators.kga.SoilGroupTypeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoilGroupTypeServiceImpl implements SoilGroupTypeService {

    private final SoilGroupTypeRepository soilGroupTypeRepository;
    private final SoilGroupTypeMapper soilGroupTypeMapper;

    @Override
    public SoilGroupTypeDto getById(Long id) {
        var soilGroupType = soilGroupTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("SoilGroupType")));

        return soilGroupTypeMapper.soilGroupTypeToSoilGroupTypeDto(soilGroupType);
    }
}
