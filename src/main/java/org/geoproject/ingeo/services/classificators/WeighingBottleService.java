package org.geoproject.ingeo.services.classificators;

import org.geoproject.ingeo.dto.classificators.WeighingBottleDto;
import org.geoproject.ingeo.models.classificators.WeighingBottle;

import java.util.List;

public interface WeighingBottleService extends ClassificatorService<WeighingBottle, WeighingBottleDto> {
    WeighingBottle getByNumber(String number);

    List<WeighingBottle> getAll();

    @Override
    List<WeighingBottleDto> getDTOs(List<WeighingBottle> activeEntities);

    @Override
    void updateFromDtos(List<WeighingBottle> entities, List<WeighingBottleDto> dtos);

    @Override
    void create(List<WeighingBottleDto> dtos);

    @Override
    void delete(WeighingBottleDto dto, List<WeighingBottle> entities);

    @Override
    List<WeighingBottle> getActiveEntities(List<WeighingBottle> entities);
}
