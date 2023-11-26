package com.geoproject.igeo.enums.dtoenums.classificators;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum PotDtoFieldsEnum {
    NUMBER("number"),
    WEIGHT("weight"),
    IS_ACTIVE("isActive");

    private final String name;

    public static PotDtoFieldsEnum getEnumByName(String name) {
        return Arrays.stream(PotDtoFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}