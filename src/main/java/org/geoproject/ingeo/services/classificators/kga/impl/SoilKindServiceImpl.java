package org.geoproject.ingeo.services.classificators.kga.impl;

import lombok.RequiredArgsConstructor;
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

    @Override
    public List<SoilKind> getBySoilClass(SoilClass soilClass) {
        return soilKindRepository.findBySoilClass(soilClass);
    }
}
