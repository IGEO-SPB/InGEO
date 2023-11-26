package org.geoproject.ingeo.enums.laborenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WaterTransparencyEnum {

    TRANSPARENT("прозрачная"),
    SLIGHTLY_OPALESCENT("слабоопалесцирующая"),
    OPALESCENT("опалесцирующая"),
    MUDDY("мутная");

    private final String name;


}
