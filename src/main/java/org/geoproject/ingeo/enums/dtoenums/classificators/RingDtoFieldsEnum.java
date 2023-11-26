package com.geoproject.igeo.enums.dtoenums.classificators;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum RingDtoFieldsEnum {
    NUMBER("number"),
    D1("d1"),
    D2("d2"),
    HEIGHT("height"),
    VOLUME("volume"),
    WEIGHT("weight"),
    IS_ACTIVE("isActive");

    private final String name;

    public static RingDtoFieldsEnum getEnumByName(String name) {
        return Arrays.stream(RingDtoFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}