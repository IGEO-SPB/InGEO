package org.geoproject.ingeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum EgeDtoFieldsEnum {
        EGE_NUMBER("egeNumber"),
        CODE_NUMBER("codeNumber"),
        SHORT_NAME("shortName"),
        GENESIS("genesis"),
//        GENESIS_CODE("genesisCode"),
//        GENESIS_DESCRIPTION("genesisDescription"),
//        SOIL_KIND_ENUM("soilKindEnum"),
        DESCRIPTION_CREDO_FORMULAR("descriptionCredoFormular"),
        DESCRIPTION_KGA("descriptionKga"),
        DESCRIPTION_FOR_ORGANISATION("descriptionForOrganisation"),
        HATCHING("hatching"),
        CONSISTENCY("consistency"),
//        COLOR("color")
        ;

        private final String name;

        public static EgeDtoFieldsEnum getEnumByName(String name) {
            return Arrays.stream(EgeDtoFieldsEnum.values())
                    .filter(value -> value.name.equalsIgnoreCase(name))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
        }
    }