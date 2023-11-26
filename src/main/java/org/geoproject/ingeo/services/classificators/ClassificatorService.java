package com.geoproject.igeo.services.classificators;

import com.geoproject.igeo.models.classificators.WeighingBottle;

import java.util.List;

public interface ClassificatorService<T, Y> {
    T getByNumber(String newValue);

    List<Y> getDTOs(List<T> activeEntities);

    void updateFromDtos(List<T> entities, List<Y> dtos);

    void create(List<Y> dtos);

    void delete(Y dto, List<T> entities);

    List<T> getActiveEntities(List<T> entities);
}