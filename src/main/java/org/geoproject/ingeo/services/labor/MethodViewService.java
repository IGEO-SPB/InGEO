package org.geoproject.ingeo.services.labor;

import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;

import java.util.List;

public interface MethodViewService<T, Y> {

    /**
     * Получение сущности по образцу
     *
     * @param sample {@link Sample} образец
     * @return сущность в соответствующем сервисе
     */
    T getBySample(Sample sample);

    List<T> getAllBySample(Sample sample);

    void update(T object);

    /**
     * Обновление сущности из полей DTO
     *
     * @param dto    DTO соответствующего сервиса (дженерик)
     * @param sample образец
     */
    void updateFromDTO(Y dto, Sample sample);

    Y getDto(T entity);

    void calculate(Sample sample, Y dto);

    T getBySampleAndNumber(Sample sample, Integer number);

    List<T> getByProject(Project currentProject);
}
