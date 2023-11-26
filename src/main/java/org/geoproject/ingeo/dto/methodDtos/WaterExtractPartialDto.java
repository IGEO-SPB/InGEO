package org.geoproject.ingeo.dto.methodDtos;

import org.geoproject.ingeo.enums.dtoenums.WaterExtractPartialDtoFieldsEnum;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WaterExtractPartialDto {

    private String laborNumber;
    private String surveyPointNumber;
    private Float depthFrom;
    private Float depthTo;
    private LocalDate samplingDate;
    private Boolean isArchive;
    private Boolean isBlocked;
    private Float pH;
    private Float Cl_1;
    private Float Cl_2;
    private Float NO3;
    private Float Fe;
    private Float gum_1;
    private Float gum_2;
    private Float SO4_1;
    private Float SO4_2;

    private FloatProperty clCoef = new SimpleFloatProperty();

    public void setFieldValue(WaterExtractPartialDtoFieldsEnum field, Object value) {
        switch (field) {
            case LABOR_NUMBER -> laborNumber = (String) value;
            case SURVEY_POINT_NUMBER -> surveyPointNumber = (String) value;
            case DEPTH_FROM -> depthFrom = (Float) value;
            case DEPTH_TO -> depthTo = (Float) value;
            case SAMPLING_DATE -> samplingDate = (LocalDate) value;
            case IS_ARCHIVE -> isArchive = (Boolean) value;
            case IS_BLOCKED -> isBlocked = (Boolean) value;
            case PH -> pH = (Float) value;
            case CL_1 -> Cl_1 = (Float) value;
            case CL_2 -> Cl_2 = (Float) value;
            case NO3 -> NO3 = (Float) value;
            case FE -> Fe = (Float) value;
            case GUM_1 -> gum_1 = (Float) value;
            case GUM_2 -> gum_2 = (Float) value;
            case SO4_1 -> SO4_1 = (Float) value;
            case SO4_2 -> SO4_2 = (Float) value;
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
