package com.geoproject.igeo.services.classificators.kga.impl;

import com.geoproject.igeo.models.classificators.kga.SoilClass;
import com.geoproject.igeo.repositories.classificators.kga.SoilClassRepository;
import com.geoproject.igeo.services.classificators.kga.SoilClassService;
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
