package org.geoproject.ingeo.services.classificators.kga.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
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

    @Override
    public List<SoilClassKindGroup> getBySoilClass(SoilClass soilClass) {
        var sort = Sort.by(ID_FIELD);

        return soilClassKindGroupRepository.findBySoilClass(soilClass, sort);
    }
}
