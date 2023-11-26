package org.geoproject.ingeo.services.classificators;

import org.geoproject.ingeo.models.classificators.ConstructionType;

import java.util.List;

/**
 * Сервис для работы с сущностью ConstructionType - Типы строительства
 */
public interface ConstructionTypeService {

    /**
     * Получение типа строительства по названию
     *
     * @param type Наименование типа строительства
     * @return {@link ConstructionType} Экземпляр типа строительства
     */
    ConstructionType getByType(String type);
    void create(List<ConstructionType> types);

    /**
     * Получение всех типов строительства, сохраненных в классификаторе
     *
     * @return {@link List<ConstructionType>} Список типов строительства
     */
    List<ConstructionType> getAll();
}
