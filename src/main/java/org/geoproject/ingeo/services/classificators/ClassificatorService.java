package org.geoproject.ingeo.services.classificators;

import java.util.List;

public interface ClassificatorService<T, Y> {
    T getByNumber(String newValue);

    List<Y> getDTOs(List<T> activeEntities);

    void updateFromDtos(List<T> entities, List<Y> dtos);

    void create(List<Y> dtos);

    void delete(Y dto, List<T> entities);

    List<T> getActiveEntities(List<T> entities);
}