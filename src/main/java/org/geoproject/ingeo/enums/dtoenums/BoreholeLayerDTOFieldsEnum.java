package org.geoproject.ingeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum BoreholeLayerDTOFieldsEnum {
    NUMBER("number"),
    EGE_NUMBER("egeNumber"),
    SOIL_TYPE("soilType"),
    LAYER_BOTTOM_DEPTH("layerBottomDepth"),
    ABSOLUTE_TOP_MARK("absoluteTopMark"),
    LAYER_POWER("layerPower"),
    LAYER_TOP_DEPTH("layerTopDepth"),
    FIRST_LAYER_TOP("firstLayerTop"),
    FIRST_LAYER_BOTTOM("firstLayerBottom"),
    FIRST_LAYER_TYPE("firstLayerType"),
    SECOND_LAYER_BOTTOM("secondLayerBottom"),
    SECOND_LAYER_TYPE("secondLayerType"),
    HATCHING_NAME_CREDO_AUTOCAD("hatchingNameCredoAutocad"),
    COLOR("color"),
    WATER("water"),
    IS_EDITABLE_FROM_EGE_LIST("isEditableFromEgeList"),
    DESCRIPTION_CREDO_FORMULAR("descriptionCredoFormular"),
    DESCRIPTION_KGA("descriptionKga");

    private final String name;

    public static BoreholeLayerDTOFieldsEnum getEnumByName(String name) {
        return Arrays.stream(BoreholeLayerDTOFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}
