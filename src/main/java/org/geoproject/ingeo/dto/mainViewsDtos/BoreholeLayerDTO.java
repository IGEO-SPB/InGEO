package org.geoproject.ingeo.dto.mainViewsDto;

import org.geoproject.ingeo.enums.dtoenums.BoreholeLayerDTOFieldsEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * Описание слоев в колонке
 */

@Getter
@Setter
public class BoreholeLayerDTO {

    //№ по порядку в рамках данной выработки
    private int number;
    private String egeNumber;
    private String soilType;
    private float layerBottomDepth;
    private float absoluteTopMark;
    private float layerPower;
    private float layerTopDepth;
    private float firstLayerTop;
    private float firstLayerBottom;
    private String firstLayerType;
    private float secondLayerBottom;
    private String secondLayerType;
    private String hatchingNameCredoAutocad;
    private String color;
    private float water;
    private boolean isEditableFromEgeList;
    private String descriptionCredoFormular;
    private String descriptionKga;

//    number
//    egeNumber
//    soilType
//    layerBottomDepth
//    absoluteTopMark
//    layerPower
//    layerTopDepth
//    firstLayerTop
//    firstLayerBottom
//    firstLayerType
//    secondLayerBottom
//    secondLayerType
//    hatchingNameCredoAutocad
//    color
//    water
//    isEditableFromEgeList
//    descriptionCredoFormular
//    descriptionKga

    public void setFieldValue(BoreholeLayerDTOFieldsEnum field, Object value) {
        switch (field) {
            case NUMBER:
                number = (Integer) value;
                break;
            case EGE_NUMBER:
                egeNumber = (String) value;
                break;
            case SOIL_TYPE:
                soilType = (String) value;
                break;
            case LAYER_BOTTOM_DEPTH:
                layerBottomDepth = (Float) value;
                break;
            case ABSOLUTE_TOP_MARK:
                absoluteTopMark = (Float) value;
                break;
            case LAYER_POWER:
                layerPower = (Float) value;
                break;
            case LAYER_TOP_DEPTH:
                layerTopDepth = (Float) value;
                break;
            case FIRST_LAYER_TOP:
                firstLayerTop = (Float) value;
                break;
            case FIRST_LAYER_BOTTOM:
                firstLayerBottom = (Float) value;
                break;
            case FIRST_LAYER_TYPE:
                firstLayerType = (String) value;
                break;
            case SECOND_LAYER_BOTTOM:
                secondLayerBottom = (Float) value;
                break;
            case SECOND_LAYER_TYPE:
                secondLayerType = (String) value;
                break;
            case HATCHING_NAME_CREDO_AUTOCAD:
                hatchingNameCredoAutocad = (String) value;
                break;
            case COLOR:
                color = (String) value;
                break;
            case WATER:
                water = (Float) value;
                break;
            case IS_EDITABLE_FROM_EGE_LIST:
                isEditableFromEgeList = (Boolean) value;
                break;
            case DESCRIPTION_CREDO_FORMULAR:
                descriptionCredoFormular = (String) value;
                break;
            case DESCRIPTION_KGA:
                descriptionKga = (String) value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }
    }
}