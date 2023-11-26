package org.geoproject.ingeo.dto;

import org.geoproject.ingeo.enums.dtoenums.WaterExtractFullDtoFieldsEnum;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WaterExtractFullDto {

    private String laborNumber;
    private String surveyPointNumber;
    private Float depthFrom;
    private Float depthTo;
    private LocalDate samplingDate;
    private Boolean isArchive;
    private Boolean isBlocked;

    private Float HCO3_1;
    private Float HCO3_2;
    private Float CO3_1;
    private Float CO3_2;
    private Float Cl_1;
    private Float Cl_2;
    private Float SO4_1;
    private Float SO4_2;
    private Float Ca_1;
    private Float Ca_2;
    private Float Mg_1;
    private Float Mg_2;
    private Float Dry_1;
    private Float Dry_2;
    private Float O2_1;
    private Float O2_2;
    private Float pH;

    private FloatProperty clCoef = new SimpleFloatProperty();

    public void setFieldValue(WaterExtractFullDtoFieldsEnum field, Object value) {
        switch (field) {
            case LABOR_NUMBER -> laborNumber = (String) value;
            case SURVEY_POINT_NUMBER -> surveyPointNumber = (String) value;
            case DEPTH_FROM -> depthFrom = (Float) value;
            case DEPTH_TO -> depthTo = (Float) value;
            case SAMPLING_DATE -> samplingDate = (LocalDate) value;
            case IS_ARCHIVE -> isArchive = (Boolean) value;
            case IS_BLOCKED -> isBlocked = (Boolean) value;
            case HCO3_1 -> HCO3_1 = (Float) value;
            case HCO3_2 -> HCO3_2 = (Float) value;
            case CO3_1 -> CO3_1 = (Float) value;
            case CO3_2 -> CO3_2 = (Float) value;
            case CL_1 -> Cl_1 = (Float) value;
            case CL_2 -> Cl_2 = (Float) value;
            case SO4_1 -> SO4_1 = (Float) value;
            case SO4_2 -> SO4_2 = (Float) value;
            case CA_1 -> Ca_1 = (Float) value;
            case CA_2 -> Ca_2 = (Float) value;
            case MG_1 -> Mg_1 = (Float) value;
            case MG_2 -> Mg_2 = (Float) value;
            case DRY_1 -> Dry_1 = (Float) value;
            case DRY_2 -> Dry_2 = (Float) value;
            case O2_1 -> O2_1 = (Float) value;
            case O2_2 -> O2_2 = (Float) value;
            case PH -> pH = (Float) value;
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        }
    }

    public float getClCoef() {
        return clCoef.get();
    }

    public FloatProperty clCoefProperty() {
        return clCoef;
    }

    public void setClCoef(float clCoef) {
        this.clCoef.set(clCoef);
    }
}
