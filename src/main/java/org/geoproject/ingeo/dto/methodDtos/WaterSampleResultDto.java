package org.geoproject.ingeo.dto;

import org.geoproject.ingeo.enums.dtoenums.WaterSampleResultDtoFieldsEnum;
import org.geoproject.ingeo.models.classificators.WaterGroup;
import jakarta.persistence.PostLoad;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class WaterSampleResultDto {

    private Long id;

    private String laborNumber;

    private Boolean isBlocked;

    private BooleanProperty isBlockedTransient;

    private String surveyPointNumber;

    private WaterGroup waterGroup;
    private String aquifer;
    private Float depth;
    private LocalDate samplingDate;
    private LocalDate laboratoryAcceptanceDate;
    private String transparency;
    private String color;
    private String odor;
    private Float CO3_eq;
    private Float Cl_eq;
    private Float Cl_v;
    private Float SO4_v;
    private Float NO2_v;
    private Float NO3_v;
    private Float anSum;
    private Float Ca_eq;
    private Float pH;
    private Float Mg_izm;
    private Float Mg_eq;
    private Float Mg_v;
    private Float NH4_v;
    private Float Fe_v;
    private Float Na_eq;
    private Float Na_v;
    private Float dry;
    private Float hdnGen;
    private Float hdnTmp;
    private Float hdnCon;
    private Float SiO2;
    private Float O2;
    private Float CO2sv;
    private Float CO2ag_izm;
    private Float CO2ag;
    private Float H2S;
    private Float gum;
    private Float cemSlag;
    private Float HCO3_eq;
    private Float CL_SO4;
    private Float catSum;
    private Float HCO3_v;
    private Float CO3_v;
    private Float Ca_v;
    private Float OH_eq;
    private Float OH_v;


    private Float HCO3_eq_proc;
    private Float CO3_eq_proc;
    private Float Cl_eq_proc;
    private Float SO4_eq;
    private Float SO4_eq_proc;
    private Float NO2_eq;
    private Float NO2_eq_proc;
    private Float NO3_eq;
    private Float NO3_eq_proc;
    private Float Ca_eq_proc;
    private Float Mg_eq_proc;
    private Float Na_eq_proc;
    private Float NH4_eq;
    private Float NH4_eq_proc;
    private Float Fe_eq;
    private Float OH_eq_proc;

    private Float anSum_eq_proc;
    private Float catSum_eq_proc;


    public void setFieldValue(WaterSampleResultDtoFieldsEnum field, Object value) {
        switch (field) {
            case LABOR_NUMBER -> laborNumber = (String) value;
            case IS_BLOCKED -> isBlocked = (Boolean) value;
            case SURVEY_POINT_NUMBER -> surveyPointNumber = (String) value;
            case WATER_GROUP -> waterGroup = (WaterGroup) value;
            case AQUIFER -> aquifer = (String) value;
            case DEPTH -> depth = (Float) value;
            case SAMPLING_DATE -> samplingDate = (LocalDate) value;
            case LABORATORY_ACCEPTANCE_DATE -> laboratoryAcceptanceDate = (LocalDate) value;
            case TRANSPARENCY -> transparency = (String) value;
            case COLOR -> color = (String) value;
            case ODOR -> odor = (String) value;
            case CO3_EQ -> CO3_eq = (Float) value;
            case CL_EQ -> Cl_eq = (Float) value;
            case CL_V -> Cl_v = (Float) value;
            case SO4_V -> SO4_v = (Float) value;
            case NO2_V -> NO2_v = (Float) value;
            case NO3_V -> NO3_v = (Float) value;
            case AN_SUM_WITH_OH -> anSum = (Float) value;
            case CA_EQ -> Ca_eq = (Float) value;
            case PH -> pH = (Float) value;
            case MG_IZM -> Mg_izm = (Float) value;
            case MG_EQ -> Mg_eq = (Float) value;
            case MG_V -> Mg_v = (Float) value;
            case NH4_V -> NH4_v = (Float) value;
            case FE_V -> Fe_v = (Float) value;
            case NA_EQ -> Na_eq = (Float) value;
            case NA_V -> Na_v = (Float) value;
            case DRY -> dry = (Float) value;
            case HDN_GEN -> hdnGen = (Float) value;
            case HDN_TMP -> hdnTmp = (Float) value;
            case HDN_CON -> hdnCon = (Float) value;
            case SIO2 -> SiO2 = (Float) value;
            case O2 -> O2 = (Float) value;
            case CO2SV -> CO2sv = (Float) value;
            case CO2AG_IZM -> CO2ag_izm = (Float) value;
            case CO2AG -> CO2ag = (Float) value;
            case H2S -> H2S = (Float) value;
            case GUM -> gum = (Float) value;
            case CEM_SLAG -> cemSlag = (Float) value;
            case HCO3_EQ -> HCO3_eq = (Float) value;
            case CL_SO4 -> CL_SO4 = (Float) value;
            case CAT_SUM -> catSum = (Float) value;
            case HCO3_V -> HCO3_v = (Float) value;
            case CO3_V -> CO3_v = (Float) value;
            case CA_V -> Ca_v = (Float) value;
            case OH_EQ -> OH_eq = (Float) value;
            case OH_V -> OH_v = (Float) value;

            case HCO3_EQ_PROC -> HCO3_eq_proc = (Float) value;
            case CO3_EQ_PROC -> CO3_eq_proc = (Float) value;
            case CL_EQ_PROC -> Cl_eq_proc = (Float) value;
            case SO4_EQ -> SO4_eq = (Float) value;
            case SO4_EQ_PROC -> SO4_eq_proc = (Float) value;
            case NO2_EQ -> NO2_eq = (Float) value;
            case NO2_EQ_PROC -> NO2_eq_proc = (Float) value;
            case NO3_EQ -> NO3_eq = (Float) value;
            case NO3_EQ_PROC -> NO3_eq_proc = (Float) value;
            case CA_EQ_PROC -> Ca_eq_proc = (Float) value;
            case MG_EQ_PROC -> Mg_eq_proc = (Float) value;
            case NA_EQ_PROC -> Na_eq_proc = (Float) value;
            case NH4_EQ -> NH4_eq = (Float) value;
            case NH4_EQ_PROC -> NH4_eq_proc = (Float) value;
            case FE_EQ -> Fe_eq = (Float) value;
            case OH_EQ_PROC -> OH_eq_proc = (Float) value;
            case ANSUM_EQ_PROC -> anSum_eq_proc = (Float) value;
            case CATSUM_EQ_PROC -> catSum_eq_proc = (Float) value;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaterSampleResultDto that = (WaterSampleResultDto) o;
        return Objects.equals(laborNumber, that.laborNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(laborNumber);
    }

    @Override
    public String toString() {
        return "WaterSampleResultDto{" +
                "Ca_eq_proc=" + Ca_eq_proc +
                ", Mg_eq_proc=" + Mg_eq_proc +
                ", Na_eq_proc=" + Na_eq_proc +
                ", NH4_eq_proc=" + NH4_eq_proc +
                ", catSum_eq_proc=" + catSum_eq_proc +
                '}';
    }
}
