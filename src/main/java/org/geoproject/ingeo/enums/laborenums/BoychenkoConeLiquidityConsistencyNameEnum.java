package com.geoproject.igeo.enums.laborenums;

import lombok.Getter;

@Getter
public enum BoychenkoConeLiquidityConsistencyNameEnum {
    VERY_STIFF("твердая"),
    STIFF("полутвердая"),
    FIRM_STIFF("тугопластичная"),
    FIRM("мягкопластичная"),
    VERY_FIRM("очень мягкопластичная"),
    SOFT_FIRM("текучепластичная"),
    VERY_SOFT("текучая");

    private String value;

    BoychenkoConeLiquidityConsistencyNameEnum(String value) {
        this.value = value;
    }
}




