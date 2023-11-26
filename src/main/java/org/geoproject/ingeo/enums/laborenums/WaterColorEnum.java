package org.geoproject.ingeo.enums.laborenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WaterColorEnum {

    YELLOW("желтый"),
    PALE_YELLOW("бледно-желтый"),
    GRAY("серый"),
    BROWN("коричневый"),
    COLORLESS("бесцветная");

    private final String color;
}
