package org.geoproject.ingeo.dto.methodDtos;

import org.geoproject.ingeo.enums.dtoenums.ConstructionMeshResultDTOFieldsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstructionMeshResultDto {

    private String laborNumber;
    private String surveyPointNumber;
    private float depthMin;
    private float depthMax;

    private float X_more_70;
    private float X_70_40;
    private float X_40_20;
    private float X_20_10;
    private float X_10_5;
    private float X_5_25;
    private float X_25_2;
    private float X_2_125;
    private float X_125_1;
    private float X_1_063;
    private float X_063_050;
    private float X_050_0315;
    private float X_0315_025;
    private float X_025_016;
    private float X_016_01;
    private float X_01_005;
    private float X_less_005;
    private float correction;
    private String soilKind;
    private float uniformityCoefficient;
    private String uniformityDegree;
    private float finenessModulus;
    private String sandGroupByFinenessModulus;
    private String lensesAndSeams;

    public void setFieldValue(ConstructionMeshResultDTOFieldsEnum field, Object value) {
        switch (field) {
            case LABOR_NUMBER:
                laborNumber = (String) value;
                break;
            case SURVEY_POINT_NUMBER:
                surveyPointNumber = (String) value;
                break;
            case DEPTH_MIN:
                depthMin = (Float) value;
                break;
            case DEPTH_MAX:
                depthMax = (Float) value;
                break;
            case X_MORE_70:
                X_more_70 = (Float) value;
                break;
            case X_70_40:
                X_70_40 = (Float) value;
                break;
            case X_40_20:
                X_40_20 = (Float) value;
                break;
            case X_20_10:
                X_20_10 = (Float) value;
                break;
            case X_10_5:
                X_10_5 = (Float) value;
                break;
            case X_5_25:
                X_5_25 = (Float) value;
                break;
            case X_25_2:
                X_25_2 = (Float) value;
                break;
            case X_2_125:
                X_2_125 = (Float) value;
                break;
            case X_125_1:
                X_125_1 = (Float) value;
                break;
            case X_1_063:
                X_1_063 = (Float) value;
                break;
            case X_063_050:
                X_063_050 = (Float) value;
                break;
            case X_050_0315:
                X_050_0315 = (Float) value;
                break;
            case X_0315_025:
                X_0315_025 = (Float) value;
                break;
            case X_025_016:
                X_025_016 = (Float) value;
                break;
            case X_016_01:
                X_016_01 = (Float) value;
                break;
            case X_01_005:
                X_01_005 = (Float) value;
                break;
            case X_LESS_005:
                X_less_005 = (Float) value;
                break;
            case SOIL_KIND:
                soilKind = (String) value;
                break;
            case UNIFORMITY_COEFFICIENT:
                uniformityCoefficient = (Float) value;
                break;
            case UNIFORMITY_DEGREE:
                uniformityDegree = (String) value;
                break;
            case FINENESS_MODULUS:
                finenessModulus = (Float) value;
                break;
            case SAND_GROUP_BY_FINENESS_MODULUS:
                sandGroupByFinenessModulus = (String) value;
                break;
            case LENSES_AND_SEAMS:
                lensesAndSeams = (String) value;
                break;
            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }
    }
}
