package org.geoproject.ingeo.dto;

import org.geoproject.ingeo.enums.dtoenums.WaterExtractFullResultDtoFieldsEnum;
import jakarta.persistence.PostLoad;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WaterExtractFullResultDto {

    private String laborNumber;
    private String surveyPointNumber;
    private Float depthFrom;
    private Float depthTo;
    private LocalDate samplingDate;
    private Boolean isArchive;
    private Boolean isBlocked;
    private BooleanProperty isBlockedTransient;

    private Float HCO3_v;
    private Float HCO3_eq;
    private Float HCO3_proc;
    private Float CO3_v;
    private Float CO3_eq;
    private Float CO3_proc;
    private Float Cl_v;
    private Float Cl_eq;
    private Float Cl_proc;
    private Float SO4_v;
    private Float SO4_eq;
    private Float SO4_proc;
    private Float anSumEq;
    private Float anSumProc;

    private Float Ca_v;
    private Float Ca_eq;
    private Float Ca_proc;
    private Float Mg_v;
    private Float Mg_eq;
    private Float Mg_proc;
    private Float Na_v;
    private Float Na_eq;
    private Float Na_proc;
    private Float katSumEq;
    private Float katSumProc;

    private Float ionSum;
    private Float Dry;
    private Float O2;
    private Float pH;
    private Float gum;

    public void setFieldValue(WaterExtractFullResultDtoFieldsEnum field, Object value) {
        switch (field) {
            case LABOR_NUMBER -> laborNumber = (String) value;
            case SURVEY_POINT_NUMBER -> surveyPointNumber = (String) value;
            case DEPTH_FROM -> depthFrom = (Float) value;
            case DEPTH_TO -> depthTo = (Float) value;
            case SAMPLING_DATE -> samplingDate = (LocalDate) value;
            case IS_ARCHIVE -> isArchive = (Boolean) value;
            case IS_BLOCKED -> isBlocked = (Boolean) value;
            case HCO3_V -> HCO3_v = (Float) value;
            case HCO3_EQ -> HCO3_eq = (Float) value;
            case HCO3_PROC -> HCO3_proc = (Float) value;
            case CO3_V -> CO3_v = (Float) value;
            case CO3_EQ -> CO3_eq = (Float) value;
            case CO3_PROC -> CO3_proc = (Float) value;
            case CL_V -> Cl_v = (Float) value;
            case CL_EQ -> Cl_eq = (Float) value;
            case CL_PROC -> Cl_proc = (Float) value;
            case SO4_V -> SO4_v = (Float) value;
            case SO4_EQ -> SO4_eq = (Float) value;
            case SO4_PROC -> SO4_proc = (Float) value;
            case AN_SUM_EQ -> anSumEq = (Float) value;
            case AN_SUM_PROC -> anSumProc = (Float) value;
            case CA_V -> Ca_v = (Float) value;
            case CA_EQ -> Ca_eq = (Float) value;
            case CA_PROC -> Ca_proc = (Float) value;
            case MG_V -> Mg_v = (Float) value;
            case MG_EQ -> Mg_eq = (Float) value;
            case MG_PROC -> Mg_proc = (Float) value;
            case NA_V -> Na_v = (Float) value;
            case NA_EQ -> Na_eq = (Float) value;
            case NA_PROC -> Na_proc = (Float) value;
            case KAT_SUM_EQ -> katSumEq = (Float) value;
            case KAT_SUM_PROC -> katSumProc = (Float) value;
            case ION_SUM -> ionSum = (Float) value;
            case DRY -> Dry = (Float) value;
            case O2 -> O2 = (Float) value;
            case PH -> pH = (Float) value;
            case GUM -> gum = (Float) value;
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
