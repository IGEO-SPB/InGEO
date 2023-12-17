package org.geoproject.ingeo.controllers.functionalInterfaces;

/**
 * Функциональный интерфейс для передачи метода-сеттера конкретного поля класса,
 * экземпляры которого наполняют таблицу TableView
 *
 * @param <E> объект класса, сеттер которого передается
 * @param <Y> тип данных, значение которых сеттится в конкретное поле класса
 */
@FunctionalInterface
public interface Settable<E, Y> {

    void setValue(E tableDto, Y object);
}
