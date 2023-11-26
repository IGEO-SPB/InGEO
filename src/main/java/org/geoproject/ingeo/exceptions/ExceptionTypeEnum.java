package com.geoproject.igeo.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionTypeEnum {

    METHOD_NOT_IMPLEMENTED_EXCEPTION("Метод %s не реализован"),
    ENTITY_NOT_FOUND_EXCEPTION("Сущность %s не найдена"),
    SAMPLE_NOT_FOUND_EXCEPTION("Образец не найден"),
    SURVEY_POINT_NOT_FOUND_EXCEPTION("Точка исследования не найдена. Сервис: %s"),
    WATER_SAMPLE_WITH_SUCH_LABOR_NUMBER_EXISTS_EXCEPTION("Образец воды с таким лабораторным номером уже существует"),
    PROJECT_NOT_CHOSEN_EXCEPTION("Проект не выбран"),
    NOT_ALL_FIELDS_FILLED_EXCEPTION("Заполнены не все поля"),
    LABOR_NUMBER_NOT_FILLED_EXCEPTION("Не заполнен лабораторный номер"),
    SURVEY_POINT_NUMBER_NOT_FILLED_EXCEPTION("Не заполнен номер выработки"),
    GRAN_COMPOSITION_AREOMETRY_NOT_FOUND_EXCEPTION("Гран.состав:ареометрия не найдена"),
    SHEAR_NOT_FOUND_EXCEPTION("Точка сдвиговых исследований не найдена"),
    ZERO_DIVISION_ATTEMPT_EXCEPTION("Попытка деления на 0, проверьте введенные данные"),
    ;

    private final String message;

    public String getExceptionMessage(String parameter) {
        return String.format(getMessage(), parameter);
    }
}
