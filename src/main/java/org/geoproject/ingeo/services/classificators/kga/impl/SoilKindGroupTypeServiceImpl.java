package org.geoproject.ingeo.services.classificators.kga.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.models.classificators.kga.SoilKind;
import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;
import org.geoproject.ingeo.repositories.classificators.kga.SoilKindGroupTypeRepository;
import org.geoproject.ingeo.services.classificators.kga.SoilKindGroupTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoilKindGroupTypeServiceImpl implements SoilKindGroupTypeService {

    private final SoilKindGroupTypeRepository soilKindGroupTypeRepository;

    @Override
    public List<SoilKindGroupType> getBySoilKind(SoilKind soilKind) {
        return soilKindGroupTypeRepository.findBySoilKind(soilKind);
    }
}
