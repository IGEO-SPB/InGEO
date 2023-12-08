package org.geoproject.ingeo.services.classificators.kga.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.models.classificators.kga.SoilGroupType;
import org.geoproject.ingeo.repositories.classificators.kga.SoilGroupTypeRepository;
import org.geoproject.ingeo.services.classificators.kga.SoilGroupTypeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoilGroupTypeServiceImpl implements SoilGroupTypeService {

    private final SoilGroupTypeRepository soilGroupTypeRepository;


    @Override
    public SoilGroupType getById(Long id) {
        return soilGroupTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("SoilGroupType")));
    }
}
