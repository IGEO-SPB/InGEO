package org.geoproject.ingeo.dto.methodDtos;

import org.geoproject.ingeo.enums.dtoenums.WaterExtractPartialResultDtoFieldsEnum;
import jakarta.persistence.PostLoad;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WaterExtractPartialResultDto {

    private String laborNumber;
    private String surveyPointNumber;
    private Float depthFrom;
    private Float depthTo;
    private LocalDate samplingDate;
    private Boolean isArchive;
    private Boolean isBlocked;
    private BooleanProperty isBlockedTransient;

    private Float pH;
    private Float Cl;
    private Float NO3Txt;
    private Float FeTxt;
    private Float gum;
    private Float SO4;
    private Float Cl_v;
    private Float SO4_v;

    private Float ClSO4;

    public void setFieldValue(WaterExtractPartialResultDtoFieldsEnum field, Object value) {
        switch (field) {
            case LABOR_NUMBER -> laborNumber = (String) value;
            case SURVEY_POINT_NUMBER -> surveyPointNumber = (String) value;
            case DEPTH_FROM -> depthFrom = (Float) value;
            case DEPTH_TO -> depthTo = (Float) value;
            case SAMPLING_DATE -> samplingDate = (LocalDate) value;
            case IS_ARCHIVE -> isArchive = (Boolean) value;
            case IS_BLOCKED -> isBlocked = (Boolean) value;
            case PH -> pH = (Float) value;
            case CL -> Cl = (Float) value;
            case NO3_TXT -> NO3Txt = (Float) value;
            case FE_TXT -> FeTxt = (Float) value;
            case GUM -> gum = (Float) value;
            case SO4 -> SO4 = (Float) value;
            case CL_V -> Cl_v = (Float) value;
            case SO4_V -> SO4_v = (Float) value;
            case ClSO4 -> ClSO4 = (Float) value;
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        }
    }

    @PostLoad
    void fillTransients() {
        if (isBlocked != null) {
            this.isBlockedTransient = new SimpleBooleanProperty(isBlocked);
        } else {
            this.isBlockedTransient = new SimpleBooleanProperty();
        }
    }
}
