package org.geoproject.ingeo.services.classificators.kga.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.dto.classificators.kga.SoilSubkindAdjDto;
import org.geoproject.ingeo.mapper.classificators.kga.SoilSubkindAdjMapper;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkindAdj;
import org.geoproject.ingeo.repositories.classificators.kga.SoilSubkindAdjRepository;
import org.geoproject.ingeo.services.classificators.kga.SoilSubkindAdjService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoilSubkindAdjServiceImpl implements SoilSubkindAdjService {

    private final SoilSubkindAdjRepository soilSubkindAdjRepository;
    private final SoilSubkindAdjMapper soilSubkindAdjMapper;


    @Override
    public List<SoilSubkindAdjDto> getAll() {
        var soilSubkindAdjList = soilSubkindAdjRepository.findAll();

        return soilSubkindAdjMapper.soilSubkindAdjToSoilSubkindAdjDto(soilSubkindAdjList);
    }
}
