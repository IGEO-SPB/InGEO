package org.geoproject.ingeo.methods.labor;

import org.geoproject.ingeo.dto.methodDtos.WaterSampleDto;
import org.geoproject.ingeo.dto.methodDtos.WaterSampleResultDto;

import java.util.Objects;

public class WaterChemicalAnalyzeMethod {

    private static Float calculateCO3_eq(Float oh_eq, Float oHb_1, Float oHb_2, Float co3_1, Float co3_2, Float dilutionRatio) {
        Float result;

        if (Objects.nonNull(oh_eq)) {
            result = 2 * (oHb_1 - oHb_2);
        } else {
            result = calculateElementWithDilutionRatio(
                    calculatePreliminaryResult(co3_1, co3_2),
                    dilutionRatio);
        }

        return result;
    }

    private static Float calculateCl_eq(Float cl_1, Float cl_2, Float clCoef) {
        Float result = (cl_1 - cl_2) * clCoef;

        return result;
    }

    private static Float calculateSO4_v(Float so4_1, Float so4_2) {
        Float result = (so4_1 - so4_2) * 8228;

        return result;
    }

    private static Float calculateElementWithDilutionRatio(Float elementValue, Float dilutionRatio) {
        Float result = elementValue * dilutionRatio;

        return result;
    }

    private static Float calculateElementWithDilutionRatioAndAdditionalCoef(Float elementValue,
                                                                            Float dilutionRatio,
                                                                            Float additionalCoef) {

        Float result = elementValue * dilutionRatio * additionalCoef;

        return result;
    }

    private static Float calculatePreliminaryResult(Float firstElementValue, Float secondElementValue) {
        Float result = firstElementValue - secondElementValue;

        return result;
    }

    private static Float calculateDry(Float dry_1, Float dry_2) {
        Float result = (dry_1 - dry_2) * 20_000;

        return result;
    }

    private static Float calculateGum(Float o2) {
        Float result = o2 * 5.17F / 8;

        return result;
    }

    private static Float calculateCemSlag(Float cemSlag, Float cemSulRes) {
        return (cemSlag - cemSulRes) * 2000;
    }

    private static Float calculateOH_eq(Float oHa_1, Float oHa_2, Float oHb_1, Float oHb_2) {
        Float result = oHa_1 - oHa_2 - oHb_1 - oHb_2;

        return result;
    }

    private static Float calculateCl_v(Float cl_eq) {
        Float result = cl_eq * 35.457F;

        return result;
    }

    private static Float calculateOH_v(Float oh_eq) {
        return oh_eq * 17;
    }

    private static Float calculateMg_eq(Float mg_izm, Float ca_eq) {
        return mg_izm - ca_eq;
    }

    private static Float calculateMg_v(Float mg_eq) {
        return mg_eq * 12.16F;
    }

    private static Float calculateAnSum(Float hco3_eq, Float co3_eq, Float cl_eq, Float so4_eq,
                                        Float no2_eq, Float no3_eq, Float oh_eq) {

        Float result = hco3_eq +
                co3_eq +
                cl_eq +
                so4_eq +
                no2_eq +
                no3_eq +
                oh_eq;

        return result;
    }

    private static Float calculateNa_eq(Float anSumWithOh, Float ca_eq, Float mg_eq, Float nh4) {
        Float result = anSumWithOh -
                ca_eq -
                mg_eq -
                nh4 / 18.04F;

        return result;
    }

    private static Float calculateNa_v(Float na_eq) {
        Float result = na_eq * 22.991F;

        return result;
    }

    private static Float calculateCatSum(Float ca_eq, Float mg_eq, Float na_eq, Float nh4_eq) {
        Float result = ca_eq + mg_eq + na_eq + nh4_eq;

        return result;
    }

    private static Float calculateHdnTmp(Float hco3_eq, Float co3_eq, Float hdnGen) {
        Float hdnTmp = hco3_eq + co3_eq;

        if (hdnGen < hdnTmp) {
            hdnTmp = hdnGen;
        }

        return hdnTmp;
    }

    private static Float calculateHdnCon(Float hdnGen, Float hdnTmp) {
        Float result = hdnGen - hdnTmp;

        return result;
    }

    private static Float calculateCO2ag(Float co2ag_izm, Float hco3_eq) {
        Float result = (co2ag_izm - hco3_eq) * 22;

        return result;
    }

    private static Float calculateCl_SO4(Float so4_v, Float cl_v) {
        Float result = cl_v + so4_v * 0.25F + 0.012F;

        return Objects.isNull(so4_v) || so4_v == 0 ?
                cl_v :
                result;
    }

    private static Float calculateHCO3_v(Float hco3_eq) {
        Float result = hco3_eq * 61.019F;

        return result;
    }

    private static Float calculateCO3_v(Float co3_eq) {
        Float result = co3_eq * 30.0055F;

        return result;
    }

    private static Float calculateCa_v(Float ca_eq) {
        Float result = ca_eq * 20.04F;

        return result;
    }

    private static Float calculateNO3_eq(Float no3_v) {
        Float result = no3_v / 62.008F;

        return result;
    }

    private static Float calculateNH4_eq(Float nh4_v) {
        Float result = nh4_v / 18.04F;

        return result;
    }

    private static Float calculateFe_eq(Float fe_v) {
        Float result = fe_v / 18.6167F;

        return result;
    }

    private static Float calculateSO4_eq(Float so4_v) {
        Float result = so4_v / 48.033F;

        return result;
    }

    private static Float calculateNO2_eq(Float no2_v) {
        Float result = no2_v / 46.008F;

        return result;
    }

    private static Float calculateCO2ag_izm(Float HCO3_eq, Float CO2ag) {
        Float result = (HCO3_eq + CO2ag) / 22;

        return result;
    }

    private static Float calculateMg_izm(Float Mg_eq, Float Ca_eq) {
        Float result = Mg_eq + Ca_eq;

        return result;
    }

    private static Float calculate_anion_proc(Float eq, Float anSum) {
        return (eq * 100) / anSum;
    }

    private static Float calculate_cation_proc(Float eq, Float catSum) {
        return (eq * 100) / catSum;
    }

    private static Float calculate_cation_sum_proc(Float ca_eq_proc, Float mg_eq_proc, Float na_eq_proc, Float nh4_eq_proc) {
        Float result = ca_eq_proc + mg_eq_proc + na_eq_proc + nh4_eq_proc;

        return result;
    }

    public static void calculateWaterChemistry(WaterSampleResultDto waterSampleResultDto, WaterSampleDto waterSampleDto) {

        waterSampleResultDto.setCl_eq(
                calculateCl_eq(waterSampleDto.getCl_1(),
                        waterSampleDto.getCl_2(),
                        waterSampleDto.getClCoef())
        );

        waterSampleResultDto.setCl_v(
                calculateCl_v(waterSampleResultDto.getCl_eq())
        );

        waterSampleResultDto.setSO4_v(calculateSO4_v(waterSampleDto.getSO4_1(), waterSampleDto.getSO4_2()));
        waterSampleResultDto.setNO2_v(calculateElementWithDilutionRatio(waterSampleDto.getNO2(), waterSampleDto.getRNO2()));
        waterSampleResultDto.setNO3_v(calculateElementWithDilutionRatio(waterSampleDto.getNO3(), waterSampleDto.getRNO3()));

        waterSampleResultDto.setCa_eq(calculateElementWithDilutionRatio(
                calculatePreliminaryResult(waterSampleDto.getCa_1(), waterSampleDto.getCa_2()),
                waterSampleDto.getRCa()));

        waterSampleResultDto.setPH(waterSampleDto.getPH());

        waterSampleResultDto.setMg_izm(
                calculateElementWithDilutionRatio(
                        calculatePreliminaryResult(waterSampleDto.getMg_izm_1(), waterSampleDto.getMg_izm_2()),
                        waterSampleDto.getRMg())
        );

        waterSampleResultDto.setMg_eq(
                calculateMg_eq(waterSampleResultDto.getMg_izm(), waterSampleResultDto.getCa_eq())
        );

        waterSampleResultDto.setMg_v(
                calculateMg_v(waterSampleResultDto.getMg_eq())
        );

        waterSampleResultDto.setNH4_v(calculateElementWithDilutionRatio(waterSampleDto.getNH4(), waterSampleDto.getRNH4()));

        waterSampleResultDto.setFe_v(calculateElementWithDilutionRatio(waterSampleDto.getFe(), waterSampleDto.getRFe()));

        waterSampleResultDto.setDry(calculateDry(waterSampleDto.getDry_1(), waterSampleDto.getDry_2()));

        waterSampleResultDto.setSiO2(
                calculateElementWithDilutionRatioAndAdditionalCoef(
                        calculatePreliminaryResult(waterSampleDto.getSiO2_1(), waterSampleDto.getSiO2_2()),
                        waterSampleDto.getRSiO2(),
                        0.8F)
        );

        waterSampleResultDto.setO2(calculateElementWithDilutionRatioAndAdditionalCoef(
                calculatePreliminaryResult(waterSampleDto.getO2_1(), waterSampleDto.getO2_2()),
                waterSampleDto.getRO2(),
                8F
        ));

        waterSampleResultDto.setCO2sv(calculateElementWithDilutionRatioAndAdditionalCoef(
                calculatePreliminaryResult(waterSampleDto.getCO2sv_1(), waterSampleDto.getCO2sv_2()),
                waterSampleDto.getRCO2sv(),
                22F
        ));

        waterSampleResultDto.setCO2ag_izm(
                calculateElementWithDilutionRatio(
                        calculatePreliminaryResult(waterSampleDto.getCO2ag_1(), waterSampleDto.getCO2ag_2()),
                        waterSampleDto.getRCO2ag())
        );

        waterSampleResultDto.setH2S(
                calculateElementWithDilutionRatioAndAdditionalCoef(
                        calculatePreliminaryResult(waterSampleDto.getH2S_1(), waterSampleDto.getH2S_2()),
                        waterSampleDto.getRH2S(),
                        0.8F)
        );

        waterSampleResultDto.setGum(
                calculateGum(waterSampleResultDto.getO2())
        );

        waterSampleResultDto.setCemSlag(calculateCemSlag(waterSampleDto.getCemSlag(), waterSampleDto.getCemSulRes()));

        waterSampleResultDto.setHCO3_eq(
                calculateElementWithDilutionRatio(
                        calculatePreliminaryResult(waterSampleDto.getHCO3_1(), waterSampleDto.getHCO3_2()),
                        waterSampleDto.getRHCO3())
        );

        waterSampleResultDto.setCO2ag(
                calculateCO2ag(waterSampleResultDto.getCO2ag_izm(),
                        waterSampleResultDto.getHCO3_eq())
        );

        waterSampleResultDto.setCL_SO4(
                calculateCl_SO4(waterSampleResultDto.getSO4_v(),
                        waterSampleResultDto.getCl_v())
        );

        waterSampleResultDto.setHCO3_v(
                calculateHCO3_v(waterSampleResultDto.getHCO3_eq())
        );

        waterSampleResultDto.setCa_v(
                calculateCa_v(
                        waterSampleResultDto.getCa_eq()
                )
        );

        waterSampleResultDto.setOH_eq(
                calculateOH_eq(waterSampleDto.getOHa_1(),
                        waterSampleDto.getOHa_2(),
                        waterSampleDto.getOHb_1(),
                        waterSampleDto.getOHb_2()
                ));

        waterSampleResultDto.setOH_v(
                calculateOH_v(waterSampleResultDto.getOH_eq())
        );

        waterSampleResultDto.setCO3_eq(
                calculateCO3_eq(waterSampleResultDto.getOH_eq(),
                        waterSampleDto.getOHb_1(),
                        waterSampleDto.getOHb_2(),
                        waterSampleDto.getCO3_1(),
                        waterSampleDto.getCO3_2(),
                        waterSampleDto.getRCO3()
                )
        );

        waterSampleResultDto.setCO3_v(
                calculateCO3_v(
                        waterSampleResultDto.getCO3_eq()
                )
        );

        waterSampleResultDto.setHdnGen(waterSampleResultDto.getMg_izm());

        waterSampleResultDto.setHdnTmp(
                calculateHdnTmp(
                        waterSampleResultDto.getHCO3_eq(),
                        waterSampleResultDto.getCO3_eq(),
                        waterSampleResultDto.getHdnGen())
        );

        waterSampleResultDto.setHdnCon(
                calculateHdnCon(waterSampleResultDto.getHdnGen(), waterSampleResultDto.getHdnTmp())
        );

        waterSampleResultDto.setAnSum(
                calculateAnSum(
                        waterSampleResultDto.getHCO3_eq(),
                        waterSampleResultDto.getCO3_eq(),
                        waterSampleResultDto.getCl_eq(),
                        waterSampleResultDto.getSO4_eq(),
                        waterSampleResultDto.getNO2_eq(),
                        waterSampleResultDto.getNO3_eq(),
                        waterSampleResultDto.getOH_eq()
                )
        );

        waterSampleResultDto.setNa_v(
                calculateNa_v(waterSampleResultDto.getNa_eq())
        );

        waterSampleResultDto.setCatSum(
                calculateCatSum(
                        waterSampleResultDto.getCa_eq(),
                        waterSampleResultDto.getMg_eq(),
                        waterSampleResultDto.getNa_eq(),
                        waterSampleResultDto.getNH4_eq()
                )
        );

        waterSampleResultDto.setNa_eq(
                calculateNa_eq(
                        waterSampleResultDto.getAnSum(),
                        waterSampleResultDto.getCa_eq(),
                        waterSampleResultDto.getMg_eq(),
                        waterSampleDto.getNH4()
                )
        );
    }

    public static void calculateForFinalResultTable(WaterSampleResultDto waterSampleResultDto) {

        waterSampleResultDto.setSO4_eq(
                calculateSO4_eq(
                        waterSampleResultDto.getSO4_v()
                ));

        waterSampleResultDto.setNO2_eq(
                calculateNO2_eq(
                        waterSampleResultDto.getNO2_v()
                ));

        waterSampleResultDto.setNO3_eq(
                calculateNO3_eq(
                        waterSampleResultDto.getNO3_v()
                ));

        waterSampleResultDto.setNH4_eq(
                calculateNH4_eq(
                        waterSampleResultDto.getNH4_v()
                ));

        waterSampleResultDto.setFe_eq(
                calculateFe_eq(
                        waterSampleResultDto.getFe_v()
                ));

        waterSampleResultDto.setOH_v(
                calculateOH_v(
                        waterSampleResultDto.getOH_eq()
                ));

        waterSampleResultDto.setAnSum(
                calculateAnSum(
                        waterSampleResultDto.getHCO3_eq(),
                        waterSampleResultDto.getCO3_eq(),
                        waterSampleResultDto.getCl_eq(),
                        waterSampleResultDto.getSO4_eq(),
                        waterSampleResultDto.getNO2_eq(),
                        waterSampleResultDto.getNO3_eq(),
                        waterSampleResultDto.getOH_eq()
                )
        );

        waterSampleResultDto.setCatSum(
                calculateCatSum(
                        waterSampleResultDto.getCa_eq(),
                        waterSampleResultDto.getMg_eq(),
                        waterSampleResultDto.getNa_eq(),
                        waterSampleResultDto.getNH4_eq()
                )
        );

        calculateProc(waterSampleResultDto);
    }

    private static void calculateProc(WaterSampleResultDto waterSampleResultDto) {
        waterSampleResultDto.setHCO3_eq_proc(
                calculate_anion_proc(waterSampleResultDto.getHCO3_eq(), waterSampleResultDto.getAnSum())
        );

        waterSampleResultDto.setCO3_eq_proc(
                calculate_anion_proc(waterSampleResultDto.getCO3_eq(), waterSampleResultDto.getAnSum())
        );

        waterSampleResultDto.setCl_eq_proc(
                calculate_anion_proc(waterSampleResultDto.getCl_eq(), waterSampleResultDto.getAnSum())
        );

        waterSampleResultDto.setSO4_eq_proc(
                calculate_anion_proc(waterSampleResultDto.getSO4_eq(), waterSampleResultDto.getAnSum())
        );

        waterSampleResultDto.setNO2_eq_proc(
                calculate_anion_proc(waterSampleResultDto.getNO2_eq(), waterSampleResultDto.getAnSum())
        );

        waterSampleResultDto.setNO3_eq_proc(
                calculate_anion_proc(waterSampleResultDto.getNO3_eq(), waterSampleResultDto.getAnSum())
        );

        waterSampleResultDto.setOH_eq_proc(
                calculate_anion_proc(waterSampleResultDto.getOH_eq(), waterSampleResultDto.getAnSum())
        );

        waterSampleResultDto.setCa_eq_proc(
                calculate_cation_proc(waterSampleResultDto.getCa_eq(), waterSampleResultDto.getCatSum())
        );

        waterSampleResultDto.setMg_eq_proc(
                calculate_cation_proc(waterSampleResultDto.getMg_eq(), waterSampleResultDto.getCatSum())
        );

        waterSampleResultDto.setNa_eq_proc(
                calculate_cation_proc(waterSampleResultDto.getNa_eq(), waterSampleResultDto.getCatSum())
        );

        waterSampleResultDto.setNH4_eq_proc(
                calculate_cation_proc(waterSampleResultDto.getNH4_eq(), waterSampleResultDto.getCatSum())
        );

        waterSampleResultDto.setAnSum_eq_proc(
                waterSampleResultDto.getHCO3_eq_proc() +
                        waterSampleResultDto.getCO3_eq_proc() +
                        waterSampleResultDto.getCl_eq_proc() +
                        waterSampleResultDto.getSO4_eq_proc() +
                        waterSampleResultDto.getNO2_eq_proc() +
                        waterSampleResultDto.getNO3_eq_proc() +
                        waterSampleResultDto.getOH_eq_proc()
        );

        waterSampleResultDto.setCatSum_eq_proc(
                calculate_cation_sum_proc(
                waterSampleResultDto.getCa_eq_proc(),
                        waterSampleResultDto.getMg_eq_proc(),
                        waterSampleResultDto.getNa_eq_proc(),
                        waterSampleResultDto.getNH4_eq_proc()
                )
        );
    }

    public static void calculateAdditionalWaterChemistry(WaterSampleResultDto waterSampleResultDto) {
        waterSampleResultDto.setHCO3_v(
                calculateHCO3_v(waterSampleResultDto.getHCO3_eq())
        );

        waterSampleResultDto.setCO3_v(
                calculateCO3_v(
                        waterSampleResultDto.getCO3_eq()
                )
        );

        waterSampleResultDto.setCl_v(
                calculateCl_v(waterSampleResultDto.getCl_eq())
        );

        waterSampleResultDto.setCa_v(
                calculateCa_v(
                        waterSampleResultDto.getCa_eq()
                )
        );

        waterSampleResultDto.setMg_v(
                calculateMg_v(waterSampleResultDto.getMg_eq())
        );

        waterSampleResultDto.setNa_v(
                calculateNa_v(waterSampleResultDto.getNa_eq())
        );

        waterSampleResultDto.setHdnGen(waterSampleResultDto.getMg_eq() + waterSampleResultDto.getCa_eq());

        waterSampleResultDto.setHdnTmp(
                calculateHdnTmp(
                        waterSampleResultDto.getHCO3_eq(),
                        waterSampleResultDto.getCO3_eq(),
                        waterSampleResultDto.getHdnGen())
        );

        waterSampleResultDto.setHdnCon(
                calculateHdnCon(waterSampleResultDto.getHdnGen(), waterSampleResultDto.getHdnTmp())
        );

        waterSampleResultDto.setCL_SO4(
                calculateCl_SO4(waterSampleResultDto.getSO4_v(),
                        waterSampleResultDto.getCl_v())
        );

        waterSampleResultDto.setMg_izm(
                calculateMg_izm(
                        waterSampleResultDto.getMg_eq(), waterSampleResultDto.getCa_eq()
                ));

        waterSampleResultDto.setCO2ag_izm(
                calculateCO2ag_izm(
                        waterSampleResultDto.getHCO3_eq(), waterSampleResultDto.getCO2ag()
                ));

        waterSampleResultDto.setGum(
                calculateGum(waterSampleResultDto.getO2())
        );

        calculateForFinalResultTable(waterSampleResultDto);
    }
}
