package org.geoproject.ingeo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StageTitleEnum {
    PROJECTS("Проекты"),
    FIELD_MODULE("Полевой модуль"),
    LABOR_MODULE("Лабораторный модуль"),
    CAMERAL_MODULE("Камеральный модуль"),
//    PROJECTS("Проекты"),
    CONSTRUCTION_MESH("Строительная сетка"),

    GRAN_COMPOSITION_AREOMETRY("Гранулометрический состав (ареометрия)"),
    PHYSICAL_PROPERTIES("Физические свойства"),
    WATER_CHEMISTRY_MAIN("Химия воды. Добавление образцов"),
    WATER_CHEMISTRY_DATA("Ввод данных для расчета хим.состава"),
    WATER_CHEMISTRY_RESULT("Ввод готовых данных хим.состава"),
    WATER_CHEMISTRY_FINAL_RESULT("Химический состав воды"),

    WATER_EXTRACT_CHOOSE_METHOD_VOLUME("Выбор водной вытяжки"),
    WATER_EXTRACT_PARTIAL_DATA("Водная вытяжка. Ввод данных"),
    WATER_EXTRACT_FULL_DATA("Полная водная вытяжка. Ввод данных"),
    WATER_EXTRACT_PARTIAL_RESULT("Химический состав водной вытяжки из грунтов"),
    WATER_EXTRACT_FULL_RESULT("Химический состав полной водной вытяжки из грунтов"),

    SHEAR_WATER_CONTENT_CUT_RING("Сдвиг - влажность и режущее кольцо"),
    SHEAR_DATA("Сдвиговые испытания. Ввод данных"),


    WEIGHING_BOTTLES("Бюксы"),
    RINGS("Кольца"),
    POTS("Тигли"),
    DILUTION_FACTORS("Коэффициенты разбавления"),

    NEW_PROJECT("Новый проект"),
    EDIT_PROJECT("Редактирование проекта"),

    ;

    private final String title;
}
