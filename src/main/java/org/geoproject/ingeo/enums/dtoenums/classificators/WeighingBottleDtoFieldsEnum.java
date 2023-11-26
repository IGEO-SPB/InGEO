package org.geoproject.ingeo.enums.dtoenums.classificators;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum WeighingBottleDtoFieldsEnum {
    NUMBER("number"),
    WEIGHT("weight"),
    IS_ACTIVE("isActive");

    private final String name;

    public static WeighingBottleDtoFieldsEnum getEnumByName(String name) {
        return Arrays.stream(WeighingBottleDtoFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}