package com.geoproject.igeo.services.classificators;

import com.geoproject.igeo.dto.classificators.RingDto;
import com.geoproject.igeo.models.classificators.Ring;

import java.util.List;

public interface RingService extends ClassificatorService<Ring, RingDto> {
    Ring getByNumber(String number);

    List<Ring> findAll();

    void save(Ring ring);
    void saveAll(List<Ring> rings);

    @Override
    List<RingDto> getDTOs(List<Ring> activeEntities);

    @Override
    void updateFromDtos(List<Ring> entities, List<RingDto> dtos);

    @Override
    void create(List<RingDto> dtos);

    @Override
    void delete(RingDto ringDto, List<Ring> rings);

    @Override
    List<Ring> getActiveEntities(List<Ring> entities);
}
