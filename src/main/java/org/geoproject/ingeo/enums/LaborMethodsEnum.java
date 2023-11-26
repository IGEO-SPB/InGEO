package org.geoproject.ingeo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LaborMethodsEnum {
    DENSITY_AND_WATER_CONTENT_METHOD("Плотность и влажность"),
    AREOMETRIC_METHOD("Ареометрия"),
    CONSTRUCTION_MESH_METHOD("Строительная сетка"),
    WATER_EXTRACT("Водная вытяжка"),
    SHEAR("Сдвиговые испытания"),
    CONSTRUCTION_MESH_AREOMETRY_METHOD("Строительная сетка (Ареометрия)"),
    SOIL_CORROSION_METHOD("Коррозионная агрессивность грунта"),
    COMPRESSION_METHOD("Компрессия"),
    WATER_CHEMISTRY_METHOD("Химия воды");

    private String name;
}
