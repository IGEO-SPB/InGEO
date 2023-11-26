package com.geoproject.igeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum WaterSampleResultDtoFieldsEnum {
    LABOR_NUMBER("laborNumber"),
    IS_BLOCKED("isBlocked"),
    SURVEY_POINT_NUMBER("surveyPointNumber"),
    WATER_GROUP("waterGroup"),
    AQUIFER("aquifer"),
    DEPTH("depth"),
    SAMPLING_DATE("samplingDate"),
    LABORATORY_ACCEPTANCE_DATE("laboratoryAcceptanceDate"),
    TRANSPARENCY("transparency"),
    COLOR("color"),
    ODOR("odor"),
    CO3_EQ("CO3_eq"),
    CL_EQ("CL_eq"),
    CL_V("CL_v"),
    SO4_V("SO4_v"),
    NO2_V("NO2_v"),
    NO3_V("NO3_v"),
    AN_SUM_WITH_OH("anSumWithOh"),
    CA_EQ("Ca_eq"),
    PH("pH"),
    MG_IZM("Mg_izm"),
    MG_EQ("Mg_eq"),
    MG_V("Mg_v"),
    NH4_V("NH4_v"),
    FE_V("Fe_v"),
    NA_EQ("Na_eq"),
    NA_V("Na_v"),
    DRY("dry"),
    HDN_GEN("hdnGen"),
    HDN_TMP("hdnTmp"),
    HDN_CON("hdn_con"),
    SIO2("SiO2"),
    O2("O2"),
    CO2SV("CO2sv"),
    CO2AG_IZM("CO2ag_izm"),
    CO2AG("CO2ag"),
    H2S("H2S"),
    GUM("gum"),
    CEM_SLAG("cemSlag"),
    HCO3_EQ("HCO3_eq"),
    CL_SO4("CL_SO4"),
    CAT_SUM("catSum"),
    HCO3_V("HCO3_v"),
    CO3_V("CO3_v"),
    CA_V("Ca_v"),
    OH_EQ("OH_eq"),
    OH_V("OH_v"),

    HCO3_EQ_PROC("HCO3_eq_proc"),
    CO3_EQ_PROC("CO3_eq_proc"),
    CL_EQ_PROC("Cl_eq_proc"),
    SO4_EQ("SO4_eq"),
    SO4_EQ_PROC("SO4_eq_proc"),
    NO2_EQ("NO2_eq"),
    NO2_EQ_PROC("NO2_eq_proc"),
    NO3_EQ("NO3_eq"),
    NO3_EQ_PROC("NO3_eq_proc"),
    CA_EQ_PROC("Ca_eq_proc"),
    MG_EQ_PROC("Mg_eq_proc"),
    NA_EQ_PROC("Na_eq_proc"),
    NH4_EQ("NH4_eq"),
    NH4_EQ_PROC("NH4_eq_proc"),
    FE_EQ("Fe_eq"),
    OH_EQ_PROC("OH_eq_proc"),
    ANSUM_EQ_PROC("anSum_eq_proc"),
    CATSUM_EQ_PROC("catSum_eq_proc");

    private final String name;

    public static WaterSampleResultDtoFieldsEnum getEnumByName(String name) {
        return Arrays.stream(WaterSampleResultDtoFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}