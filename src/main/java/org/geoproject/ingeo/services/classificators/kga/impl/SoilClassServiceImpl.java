package org.geoproject.ingeo.services.classificators.kga.impl;

import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.repositories.classificators.kga.SoilClassRepository;
import org.geoproject.ingeo.services.classificators.kga.SoilClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.geoproject.ingeo.constants.ServiceConstants.ID_FIELD;

@Service
@RequiredArgsConstructor
public class SoilClassServiceImpl implements SoilClassService {

    private final SoilClassRepository soilClassRepository;

    @Override
    public List<SoilClass> getAll() {
        var sort = Sort.by(ID_FIELD);

        return soilClassRepository.findAll(sort);
    }
}
