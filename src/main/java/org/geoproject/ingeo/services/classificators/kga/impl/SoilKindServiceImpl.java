package org.geoproject.ingeo.services.classificators.kga.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.dto.classificators.kga.SoilKindDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.mapper.classificators.kga.SoilKindMapper;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilKind;
import org.geoproject.ingeo.repositories.classificators.kga.SoilKindRepository;
import org.geoproject.ingeo.services.classificators.kga.SoilKindService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoilKindServiceImpl implements SoilKindService {

    private final SoilKindRepository soilKindRepository;
    private final SoilKindMapper soilKindMapper;

    @Override
    public List<SoilKind> getBySoilClass(SoilClass soilClass) {
        return soilKindRepository.findBySoilClass(soilClass);
    }

    @Override
    public List<SoilKindDto> getDtos(Long soilKindGroupId) {
        var soilKinds = soilKindRepository.findBySoilClassKindGroupId(soilKindGroupId);

        return soilKindMapper.soilKindToSoilKindDto(soilKinds);
    }

    @Override
    public SoilKind getById(Long id) {
        return soilKindRepository.findById(id).
                orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("SoilKind")));
    }
}
