package com.geoproject.igeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum WaterSampleDtoFieldsEnum {
    //TODO библиотека для генерации enum-ов

    LABOR_NUMBER("laborNumber"),
    HCO3_1("HCO3_1"),
    HCO3_2("HCO3_2"),
    OHA_1("OHa_1"),
    OHA_2("OHa_2"),
    OHB_1("OHb_1"),
    OHB_2("OHb_2"),
    CO3_1("CO3_1"),
    CO3_2("CO3_2"),
    Cl_1("CL_1"),
    Cl_2("CL_2"),
    SO4_1("SO4_1"),
    SO4_2("SO4_2"),
    NO2("NO2"),
    NO3("NO3"),
    CA_1("Ca_1"),
    CA_2("Ca_2"),
    PH("pH"),
    MG_IZM_1("Mg_izm_1"),
    MG_IZM_2("Mg_izm_2"),
    NH4("NH4"),
    FE("Fe"),
    DRY_1("dry_1"),
    DRY_2("dry_2"),
    O2_1("O2_1"),
    O2_2("O2_2"),
    CO2SV_1("CO2sv_1"),
    CO2SV_2("CO2sv_2"),
    CO2AG_1("CO2ag_1"),
    CO2AG_2("CO2ag_2"),
    SIO2_1("SiO2_1"),
    SIO2_2("SiO2_2"),
    H2S_1("H2S_1"),
    H2S_2("H2S_2"),
    CEM_SLAG("cemSlag"),
    CEM_SUL_RES("cemSulRes"),
    CL_COEF("clCoef"),

    RHCO3("RHCO3"),
    RCO3("RCO3"),
    RCL("RCL"),
    RNO2("RNO2"),
    RNO3("RNO3"),
    RCA("RCa"),
    RMG("RMg"),
    RNH4("RNH4"),
    RFE("RFe"),
    RSIO2("RSiO2"),
    RO2("RO2"),
    RCO2_SV("RCO2sv"),
    RCO2_AG("RCO2ag"),
    RH2S("RH2S");

    private final String name;

    public static WaterSampleDtoFieldsEnum getEnumByName(String name) {
        return Arrays.stream(WaterSampleDtoFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}
