package org.geoproject.ingeo.services.classificators.kga.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkind;
import org.geoproject.ingeo.repositories.classificators.kga.SoilSubkindRepository;
import org.geoproject.ingeo.services.classificators.kga.SoilSubkindService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoilSubkindServiceImpl implements SoilSubkindService {

    private final SoilSubkindRepository soilSubkindRepository;

    @Override
    public List<SoilSubkind> getBySoilKindGroupType(SoilKindGroupType soilKindGroupType) {
        return soilSubkindRepository.findAllBySoilKindGroupType(soilKindGroupType);
    }
}
