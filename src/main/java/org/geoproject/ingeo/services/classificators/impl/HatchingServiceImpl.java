package org.geoproject.ingeo.services.classificators.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.dto.classificators.ConsistencyDto;
import org.geoproject.ingeo.dto.classificators.HatchingDto;
import org.geoproject.ingeo.mapper.classificators.HatchingMapper;
import org.geoproject.ingeo.models.classificators.Consistency;
import org.geoproject.ingeo.models.classificators.Hatching;
import org.geoproject.ingeo.repositories.classificators.HatchingRepository;
import org.geoproject.ingeo.services.classificators.HatchingService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.geoproject.ingeo.constants.ServiceConstants.ID_FIELD;

@Service
@RequiredArgsConstructor
public class HatchingServiceImpl implements HatchingService {

    private final HatchingRepository hatchingRepository;
    private final HatchingMapper hatchingMapper;

    @Override
    public List<Hatching> getAll() {
        var sort = Sort.by(ID_FIELD);

        return hatchingRepository.findAll(sort);
    }

    @Override
    public List<HatchingDto> getHatchingDtos() {
        var hatchingList = getAll();

        return hatchingMapper.hatchingToHatchingDto(hatchingList);
    }
}
