package org.geoproject.ingeo.dto.methodDtos;

import org.geoproject.ingeo.enums.dtoenums.WaterSampleDtoFieldsEnum;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class WaterSampleDto {

    private String laborNumber;

    private Long surveyPointId;

    private Boolean blockedFromWaterSampleResult;

    private Float RHCO3;
    private Float RCO3;
    private Float RCL;
    private Float RNO2;
    private Float RNO3;
    private Float RCa;
    private Float RMg;
    private Float RNH4;
    private Float RFe;
    private Float RSiO2;
    private Float RO2;
    private Float RCO2sv;
    private Float RCO2ag;
    private Float RH2S;


    private Float HCO3_1;

    private Float HCO3_2;

    private Float OHa_1;

    private Float OHa_2;

    private Float OHb_1;

    private Float OHb_2;

    private Float CO3_1;

    private Float CO3_2;

    private Float Cl_1;

    private Float Cl_2;

    private Float SO4_1;

    private Float SO4_2;

    private Float NO2;

    private Float NO3;

    private Float Ca_1;

    private Float Ca_2;

    private Float pH;

    private Float Mg_izm_1;

    private Float Mg_izm_2;

    private Float NH4;

    private Float Fe;

    private Float dry_1;

    private Float dry_2;

    private Float O2_1;

    private Float O2_2;

    private Float CO2sv_1;

    private Float CO2sv_2;

    private Float CO2ag_1;

    private Float CO2ag_2;

    private Float SiO2_1;

    private Float SiO2_2;

    private Float H2S_1;

    private Float H2S_2;

    private Float cemSlag;

    private Float cemSulRes;

    private FloatProperty clCoef = new SimpleFloatProperty();

    public void setFieldValue(WaterSampleDtoFieldsEnum field, Object value) {
        switch (field) {
            case LABOR_NUMBER -> laborNumber = (String) value;
            case HCO3_1 -> HCO3_1 = (Float) value;
            case HCO3_2 -> HCO3_2 = (Float) value;
            case OHA_1 -> OHa_1 = (Float) value;
            case OHA_2 -> OHa_2 = (Float) value;
            case OHB_1 -> OHb_1 = (Float) value;
            case OHB_2 -> OHb_2 = (Float) value;
            case CO3_1 -> CO3_1 = (Float) value;
            case CO3_2 -> CO3_2 = (Float) value;
            case Cl_1 -> Cl_1 = (Float) value;
            case Cl_2 -> Cl_2 = (Float) value;
            case SO4_1 -> SO4_1 = (Float) value;
            case SO4_2 -> SO4_2 = (Float) value;
            case NO2 -> NO2 = (Float) value;
            case NO3 -> NO3 = (Float) value;
            case CA_1 -> Ca_1 = (Float) value;
            case CA_2 -> Ca_2 = (Float) value;
            case PH -> pH = (Float) value;
            case MG_IZM_1 -> Mg_izm_1 = (Float) value;
            case MG_IZM_2 -> Mg_izm_2 = (Float) value;
            case NH4 -> NH4 = (Float) value;
            case FE -> Fe = (Float) value;
            case DRY_1 -> dry_1 = (Float) value;
            case DRY_2 -> dry_2 = (Float) value;
            case O2_1 -> O2_1 = (Float) value;
            case O2_2 -> O2_2 = (Float) value;
            case CO2SV_1 -> CO2sv_1 = (Float) value;
            case CO2SV_2 -> CO2sv_2 = (Float) value;
            case CO2AG_1 -> CO2ag_1 = (Float) value;
            case CO2AG_2 -> CO2ag_2 = (Float) value;
            case SIO2_1 -> SiO2_1 = (Float) value;
            case SIO2_2 -> SiO2_2 = (Float) value;
            case H2S_1 -> H2S_1 = (Float) value;
            case H2S_2 -> H2S_2 = (Float) value;
            case CEM_SLAG -> cemSlag = (Float) value;
            case CEM_SUL_RES -> cemSulRes = (Float) value;

            case RHCO3 -> RHCO3 = (Float) value;
            case RCO3 -> RCO3 = (Float) value;
            case RCL -> RCL = (Float) value;
            case RNO2 -> RNO2 = (Float) value;
            case RNO3 -> RNO3 = (Float) value;
            case RCA -> RCa = (Float) value;
            case RMG -> RMg = (Float) value;
            case RNH4 -> RNH4 = (Float) value;
            case RFE -> RFe = (Float) value;
            case RSIO2 -> RSiO2 = (Float) value;
            case RO2 -> RO2 = (Float) value;
            case RCO2_SV -> RCO2sv = (Float) value;
            case RCO2_AG -> RCO2ag = (Float) value;
            case RH2S -> RH2S = (Float) value;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaterSampleDto that = (WaterSampleDto) o;
        return Objects.equals(laborNumber, that.laborNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(laborNumber);
    }
}
