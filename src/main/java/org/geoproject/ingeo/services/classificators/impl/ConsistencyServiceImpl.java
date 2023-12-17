package org.geoproject.ingeo.services.classificators.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.dto.classificators.ConsistencyDto;
import org.geoproject.ingeo.mapper.classificators.ConsistencyMapper;
import org.geoproject.ingeo.models.classificators.Consistency;
import org.geoproject.ingeo.models.classificators.Genesis;
import org.geoproject.ingeo.repositories.classificators.ConsistencyRepository;
import org.geoproject.ingeo.services.classificators.ConsistencyService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.geoproject.ingeo.constants.ServiceConstants.ID_FIELD;

@Service
@RequiredArgsConstructor
public class ConsistencyServiceImpl implements ConsistencyService {

    private final ConsistencyRepository consistencyRepository;
    private final ConsistencyMapper consistencyMapper;

    @Override
    public List<Consistency> getAll() {
        var sort = Sort.by(ID_FIELD);

        return consistencyRepository.findAll(sort);
    }

    @Override
    public List<ConsistencyDto> getConsistencyDtos() {
        var consistencyList = getAll();

        return consistencyMapper.consistencyToConsistencyDto(consistencyList);
    }
}
