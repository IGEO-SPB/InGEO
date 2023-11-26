package org.geoproject.ingeo.services.classificators.kga.impl;

import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.repositories.classificators.kga.SoilClassRepository;
import org.geoproject.ingeo.services.classificators.kga.SoilClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoilClassServiceImpl implements SoilClassService {

    private final SoilClassRepository soilClassRepository;

    @Override
    public List<SoilClass> getAll() {
        return soilClassRepository.findAll();
    }
}
