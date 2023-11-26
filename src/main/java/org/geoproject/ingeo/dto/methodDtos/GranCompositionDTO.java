package org.geoproject.ingeo.dto.methodDtos;

import org.geoproject.ingeo.enums.dtoenums.GranCompositionDTOFieldsEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GranCompositionDTO {
    private String laborNumber;

    private String surveyPointNumber;
    private Float depthMin;
    private Float depthMax;

    private Float included_more_2;
    private Float X_more_10;
    private Float X_10_5_old_10_2;
    private Float X_5_2;
    private Float X_2_1;
    private Float X_1_05;
    private Float X_05_025;
    private Float X_025_01;
    private Float X_01_005;
    private Float X_005_001;
    private Float X_001_0002_old_001_0005;
    private Float X_0005_0002;
    private Float X_less_0002;
    private Float sum_2_005;
    private Float sum_005_0002_old_005_0005;
    private Float sum_less_0002_old_less_0005;
    private String soilKind;
    private Float uniformityCoefficient;
    private String uniformityDegree;
    private Float HCl;
    private Float dispersityIndex;
    private String heavingDegree;

//    private FloatProperty X_more_10 = new SimpleFloatProperty();
//    private FloatProperty X_10_5_old_10_2 = new SimpleFloatProperty();
//    private FloatProperty X_5_2 = new SimpleFloatProperty();
//    private FloatProperty X_2_1 = new SimpleFloatProperty();
//    private FloatProperty X_1_05 = new SimpleFloatProperty();
//    private FloatProperty X_05_025 = new SimpleFloatProperty();
//    private FloatProperty X_025_01 = new SimpleFloatProperty();
//    private FloatProperty X_01_005 = new SimpleFloatProperty();
//    private FloatProperty X_005_001 = new SimpleFloatProperty();
//    private FloatProperty X_001_0002_old_001_0005 = new SimpleFloatProperty();
//    private FloatProperty X_0005_0002 = new SimpleFloatProperty();
//    private FloatProperty X_less_0002 = new SimpleFloatProperty();
//    private FloatProperty sum_2_005 = new SimpleFloatProperty();
//    private FloatProperty sum_005_0002_old_005_0005 = new SimpleFloatProperty();
//    private FloatProperty sum_less_0002_old_less_0005 = new SimpleFloatProperty();
//    private StringProperty soilKind = new SimpleStringProperty();
//    private FloatProperty uniformityCoefficient = new SimpleFloatProperty();
//    private StringProperty uniformityDegree = new SimpleStringProperty();
//    private FloatProperty HCl = new SimpleFloatProperty();
//    private FloatProperty dispersityIndex = new SimpleFloatProperty();
//    private StringProperty heavingDegree = new SimpleStringProperty();

    private Boolean isSand;
//    private BooleanProperty isSand = new SimpleBooleanProperty();

    public void setFieldValue(GranCompositionDTOFieldsEnum field, Object value) {
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
            case INCLUDED_MORE_2:
                included_more_2 = (Float) value;
                break;
            case X_MORE_10:
                X_more_10 = (Float) value;
                break;
            case X_10_5_OLD_10_2:
                X_10_5_old_10_2 = (Float) value;
                break;
            case X_5_2:
                X_5_2 = (Float) value;
                break;
            case X_2_1:
                X_2_1 = (Float) value;
                break;
            case X_1_05:
                X_1_05 = (Float) value;
                break;
            case X_05_025:
                X_05_025 = (Float) value;
                break;
            case X_025_01:
                X_025_01 = (Float) value;
                break;
            case X_01_005:
                X_01_005 = (Float) value;
                break;
            case X_005_001:
                X_005_001 = (Float) value;
                break;
            case X_001_0002_OLD_001_0005:
                X_001_0002_old_001_0005 = (Float) value;
                break;
            case X_0005_0002:
                X_0005_0002 = (Float) value;
                break;
            case X_LESS_0002:
                X_less_0002 = (Float) value;
                break;
            case SUM_2_005:
                sum_2_005 = (Float) value;
                break;
            case SUM_005_0002_OLD_005_0005:
                sum_005_0002_old_005_0005 = (Float) value;
                break;
            case SUM_LESS_0002_OLD_LESS_0005:
                sum_less_0002_old_less_0005 = (Float) value;
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
            case HCL:
                HCl = (Float) value;
                break;
            case DISPERSITY_INDEX:
                dispersityIndex = (Float) value;
                break;
            case HEAVING_DEGREE:
                heavingDegree = (String) value;
                break;
            case IS_SAND:
                isSand = (Boolean) value;
                break;
        }
    }
}
