package org.geoproject.ingeo.enums.laborenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StructureEnum {
    BROKEN("нарушенный"),
    UNDISTURBED("ненарушенный"),
    WATER("вода"),
    CORROSION("корр"),
    WATER_EXTRACT("водная вытяжка");

    private final String name;
}
