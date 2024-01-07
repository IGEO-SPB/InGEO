package org.geoproject.ingeo.services.classificators.kga.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.dto.classificators.kga.SoilClassDto;
import org.geoproject.ingeo.mapper.classificators.kga.SoilClassMapper;
import org.geoproject.ingeo.repositories.classificators.kga.SoilClassRepository;
import org.geoproject.ingeo.services.classificators.kga.SoilClassService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.geoproject.ingeo.constants.ServiceConstants.ID_FIELD;

@Service
@RequiredArgsConstructor
public class SoilClassServiceImpl implements SoilClassService {

    private final SoilClassRepository soilClassRepository;
    private final SoilClassMapper soilClassMapper;

    @Override
    public List<SoilClassDto> getAll() {
        var sort = Sort.by(ID_FIELD);

        var soilClasses = soilClassRepository.findAll(sort);

        return soilClassMapper.soilClassToSoilClassDto(soilClasses);
    }
}
