package org.geoproject.ingeo.services;

public interface ModalWindowService<T, Y> {

    /**
     * Добавление новой сущности
     *
     * @param dto DTO соответствующего сервиса (дженерик)
     */
    void create(Y dto);

    /**
     * Обновление после сохранения изменений
     *
     * @param dto DTO соответствующего сервиса (дженерик)
     */
    void update(Y dto);

    /**
     * Получение DTO соответствующей сущности (дженерик) по экземпляру сущности
     *
     * @param entity экзмепляр сущности
     * @return DTO соответствующей сущности (дженерик)
     */
    Y getDto(T entity);
}
