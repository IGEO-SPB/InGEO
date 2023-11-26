package org.geoproject.ingeo.methods.labor;

import org.geoproject.ingeo.dto.methodDtos.WaterExtractFullDto;
import org.geoproject.ingeo.dto.methodDtos.WaterExtractFullResultDto;

import java.util.Objects;

public class WaterExtractFullMethod {

    public static void calculateWaterExtractFull(WaterExtractFullDto sourceDto, WaterExtractFullResultDto resultDto) {
        System.out.println("calculateWaterExtractFull inited...");

        resultDto.setHCO3_v(
                calculateHCO3_v(sourceDto.getHCO3_1(), sourceDto.getHCO3_2())
        );

        resultDto.setHCO3_eq(
                calculateHCO3_eq(sourceDto.getHCO3_1(), sourceDto.getHCO3_2())
        );

//        resultDto.setHCO3_proc(
//
//        );

        resultDto.setCO3_v(
                calculateCO3_v(sourceDto.getCO3_1(), sourceDto.getCO3_2())
        );

        resultDto.setCO3_eq(
                calculateCO3_eq(sourceDto.getCO3_1(), sourceDto.getCO3_2())
        );

//        resultDto.setCO3_proc(
//
//        );

        resultDto.setCl_v(
                calculateCl_v(sourceDto.getCl_1(), sourceDto.getCl_2(), sourceDto.getClCoef())
        );

        resultDto.setCl_eq(
                calculateCl_eq(sourceDto.getCl_1(), sourceDto.getCl_2(), sourceDto.getClCoef())
        );

//        resultDto.setCl_proc(
//
//        );

        resultDto.setSO4_v(
                calculateSO4_v(sourceDto.getSO4_1(), sourceDto.getSO4_2())
        );

        resultDto.setSO4_eq(
                calculateSO4_eq(sourceDto.getSO4_1(), sourceDto.getSO4_2())
        );

//        resultDto.setSO4_proc(
//
//        );

        resultDto.setAnSumEq(
                calculateAnSumEq(resultDto.getCl_eq(), resultDto.getSO4_eq(),
                        resultDto.getHCO3_eq(), resultDto.getCO3_eq())
        );

//        resultDto.setAnSumProc(
//
//        );

        resultDto.setCa_v(
                calculateCa_v(sourceDto.getCa_1(), sourceDto.getCa_2())
        );

        resultDto.setCa_eq(
                calculateCa_eq(sourceDto.getCa_1(), sourceDto.getCa_2())
        );

//        resultDto.setCa_proc(
//
//        );

        resultDto.setMg_v(
                calculateMg_v(sourceDto.getMg_1(), sourceDto.getMg_2(),
                        sourceDto.getCa_1(), sourceDto.getCa_2())
        );

        resultDto.setMg_eq(
                calculateMg_eq(sourceDto.getMg_1(), sourceDto.getMg_2(),
                        sourceDto.getCa_1(), sourceDto.getCa_2())
        );

//        resultDto.setMg_proc(
//
//        );


//        resultDto.setKatSumProc(
//
//        );

        resultDto.setNa_eq(
                calculateNa_eq(resultDto.getAnSumEq(),
                        resultDto.getMg_eq(),
                        resultDto.getCa_eq())
        );

        resultDto.setKatSumEq(
                calculateKatSumEq(resultDto.getMg_eq(), resultDto.getCa_eq(), resultDto.getNa_eq())
        );

        resultDto.setNa_v(
                calculateNa_v(resultDto.getNa_eq())
        );

//        resultDto.setNa_proc(
//
//        );


        resultDto.setIonSum(
                calculateIonSum(
                        resultDto.getCl_v(),
                        resultDto.getSO4_v(),
                        resultDto.getHCO3_v(),
                        resultDto.getCO3_v(),
                        resultDto.getMg_v(),
                        resultDto.getCa_v(),
                        resultDto.getNa_v()
                )
        );

        resultDto.setDry(
                calculateDry(sourceDto.getDry_1(), sourceDto.getDry_2())
        );

        resultDto.setO2(
                calculateO2(sourceDto.getO2_1(), sourceDto.getO2_2())
        );

        resultDto.setPH(
                sourceDto.getPH()
        );

        resultDto.setGum(
                calculateGum(sourceDto.getO2_1(), sourceDto.getO2_2())
        );

        calculateAllProc(resultDto);
    }

    private static Float calculateHCO3_v(Float hco3_1, Float hco3_2) {
        if (Objects.isNull(hco3_1) || Objects.isNull(hco3_2)) {
            return 0F;
        }

        var result = (hco3_1 - hco3_2) * 0.0305F;

        return result;
    }

    private static Float calculateHCO3_eq(Float hco3_1, Float hco3_2) {
        if (Objects.isNull(hco3_1) || Objects.isNull(hco3_2)) {
            return 0F;
        }

        var result = ((hco3_1 - hco3_2) * 0.0305F) * 1000 / 61.019F;

        return result;
    }

    private static Float calculateCO3_v(Float co3_1, Float co3_2) {
        if (Objects.isNull(co3_1) || Objects.isNull(co3_2)) {
            return 0F;
        }

        var result = (co3_1 - co3_2) * 0.03F;

        return result;
    }

    private static Float calculateCO3_eq(Float co3_1, Float co3_2) {
        if (Objects.isNull(co3_1) || Objects.isNull(co3_2)) {
            return 0F;
        }

        var result = ((co3_1 - co3_2) * 0.03F) * 1000 / 30.0055F;

        return result;
    }

    private static Float calculateCl_v(Float cl_1, Float cl_2, float clCoef) {
        if (Objects.isNull(cl_1) || Objects.isNull(cl_2)) {
            return 0F;
        }

        var result = (cl_1 - cl_2) * clCoef * 0.0175F;

        return result;
    }

    private static Float calculateCl_eq(Float cl_1, Float cl_2, float clCoef) {
        if (Objects.isNull(cl_1) || Objects.isNull(cl_2)) {
            return 0F;
        }

        var result = ((cl_1 - cl_2) * clCoef * 0.0175F) * 1000 / 35.5457F;

        return result;
    }

    private static Float calculateSO4_v(Float so4_1, Float so4_2) {
        if (Objects.isNull(so4_1) || Objects.isNull(so4_2)) {
            return 0F;
        }

        var result = (so4_1 - so4_2) * 4.11F;

        return result;
    }

    private static Float calculateSO4_eq(Float so4_1, Float so4_2) {
        if (Objects.isNull(so4_1) || Objects.isNull(so4_2)) {
            return 0F;
        }

        var result = (so4_1 - so4_2) * 4.11F * 1000 / 48.033F;

        return result;
    }

    private static Float calculateAnSumEq(Float cl_eq, Float so4_eq, Float hco3_eq, Float co3_eq) {
        var result = cl_eq + so4_eq + hco3_eq + co3_eq;

        return result;
    }

    private static Float calculateCa_v(Float ca_1, Float ca_2) {
        if (Objects.isNull(ca_1) || Objects.isNull(ca_2)) {
            return 0F;
        }

        var result = (ca_1 - ca_2) * 0.01F;

        return result;
    }

    private static Float calculateCa_eq(Float ca_1, Float ca_2) {
        if (Objects.isNull(ca_1) || Objects.isNull(ca_2)) {
            return 0F;
        }

        var result = ((ca_1 - ca_2) * 0.01F) * 1000 / 20.04F;

        return result;
    }

    private static Float calculateMg_v(Float mg_1, Float mg_2, Float ca_1, Float ca_2) {
        if (Objects.isNull(mg_1) || Objects.isNull(mg_2) || Objects.isNull(ca_1) || Objects.isNull(ca_2)) {
            return 0F;
        }

        var result = ((mg_1 - mg_2) - (ca_1 - ca_2)) * 0.006F;

        return result;
    }

    private static Float calculateMg_eq(Float mg_1, Float mg_2, Float ca_1, Float ca_2) {
        if (Objects.isNull(mg_1) || Objects.isNull(mg_2) || Objects.isNull(ca_1) || Objects.isNull(ca_2)) {
            return 0F;
        }

        var result = (((mg_1 - mg_2) - (ca_1 - ca_2)) * 0.006F) * 1000 / 12.16F;

        return result;
    }

    private static Float calculateKatSumEqWithoutNa(Float mg_eq, Float ca_eq) {
        if (Objects.isNull(mg_eq) || Objects.isNull(ca_eq)) {
            return 0F;
        }

        var result = mg_eq + ca_eq;

        return result;
    }

    private static Float calculateNa_eq(Float anSumEq, Float mg_eq, Float ca_eq) {
        Float katSumEqWithoutNa = calculateKatSumEqWithoutNa(mg_eq, ca_eq);

        if (Objects.isNull(anSumEq) || Objects.isNull(katSumEqWithoutNa)) {
            return 0F;
        }

        var result = anSumEq - katSumEqWithoutNa;

        return result;
    }


    private static Float calculateKatSumEq(Float mg_eq, Float ca_eq, Float na_eq) {
        if (Objects.isNull(mg_eq) || Objects.isNull(ca_eq) || Objects.isNull(na_eq)) {
            return 0F;
        }

        var result = mg_eq + ca_eq + na_eq;

        return result;
    }

    private static Float calculateNa_v(Float na_eq) {
        if (Objects.isNull(na_eq)) {
            return 0F;
        }

        var result = na_eq * 0.022991F;

        return result;
    }

    private static Float calculateDry(Float dry_1, Float dry_2) {
        if (Objects.isNull(dry_1) || Objects.isNull(dry_2)) {
            return 0F;
        }

        var result = (dry_1 - dry_2) * 10F;

        return result;
    }

    private static Float calculateO2(Float o2_1, Float o2_2) {
        if (Objects.isNull(o2_1) || Objects.isNull(o2_2)) {
            return 0F;
        }

        var result = (o2_1 - o2_2) * 0.004F;

        return result;
    }

    private static Float calculateGum(Float o2_1, Float o2_2) {
        if (Objects.isNull(o2_1) || Objects.isNull(o2_2)) {
            return 0F;
        }

        var result = (o2_1 - o2_2) * 0.002585F;

        return result;
    }

    //    private static Float calculateIonSum(Float cl_v, Float so4_v, Float hco3_v, Float co3_v, Float mg_v, Float ca_v, Float na_v) {
    private static Float calculateIonSum(Float... args) {
        Float result = 0F;

        for (Float arg : args) {
            if (Objects.isNull(arg)) {
                return 0F;
            } else {
                result += arg;
            }
        }

        return result;
    }

    private static void calculateAllProc(WaterExtractFullResultDto resultDto) {
        var HCO3_proc = calculateProc(resultDto.getHCO3_eq(), resultDto.getAnSumEq());
        var CO3_proc = calculateProc(resultDto.getCO3_eq(), resultDto.getAnSumEq());
        var Cl_proc = calculateProc(resultDto.getCl_eq(), resultDto.getAnSumEq());
        var SO4_proc = calculateProc(resultDto.getSO4_eq(), resultDto.getAnSumEq());
        var Ca_proc = calculateProc(resultDto.getCa_eq(), resultDto.getAnSumEq());
        var Mg_proc = calculateProc(resultDto.getMg_eq(), resultDto.getAnSumEq());
        var Na_proc = calculateProc(resultDto.getNa_eq(), resultDto.getAnSumEq());

        var katSumProc = Ca_proc + Mg_proc + Na_proc;
        var anSumProc = HCO3_proc + CO3_proc + Cl_proc + SO4_proc;

        resultDto.setHCO3_proc(
                HCO3_proc
        );

        resultDto.setCO3_proc(
                CO3_proc
        );

        resultDto.setCl_proc(
                Cl_proc
        );

        resultDto.setSO4_proc(
                SO4_proc
        );

        resultDto.setCa_proc(
                Ca_proc
        );

        resultDto.setMg_proc(
                Mg_proc
        );

        resultDto.setNa_proc(
                Na_proc
        );

        resultDto.setKatSumProc(
                katSumProc
        );

        resultDto.setAnSumProc(
                anSumProc
        );
    }

    private static Float calculateProc(Float element, Float anSumEq) {
        if (Objects.isNull(anSumEq) || anSumEq == 0) {
            return 0F;
        }

        var result = (element / anSumEq) * 100;

        return result;
    }
}
