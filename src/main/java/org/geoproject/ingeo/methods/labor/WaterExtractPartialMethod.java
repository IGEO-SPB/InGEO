package org.geoproject.ingeo.labor;

import com.geoproject.igeo.dto.WaterExtractPartialDto;
import com.geoproject.igeo.dto.WaterExtractPartialResultDto;

import java.util.Objects;

public class WaterExtractPartialMethod {

    public static void calculateWaterExtractPartial(WaterExtractPartialDto sourceDto, WaterExtractPartialResultDto resultDto) {
        System.out.println("calculateWaterExtractPartial inited...");

        resultDto.setPH(sourceDto.getPH());

        resultDto.setCl(
                calculateCl(sourceDto.getCl_1(), sourceDto.getCl_2(), sourceDto.getClCoef())
        );
        
        resultDto.setNO3Txt(
                Objects.isNull(sourceDto.getNO3()) ? null : sourceDto.getNO3()
        );

        resultDto.setFeTxt(
                Objects.isNull(sourceDto.getFe()) ? null : sourceDto.getFe()
        );

        resultDto.setGum(
                calculateGum(sourceDto.getGum_1(), sourceDto.getGum_2())
        );

        resultDto.setSO4(
                calculateSO4(sourceDto.getSO4_1(), sourceDto.getSO4_2())
        );

        resultDto.setCl_v(
                calculateCl_v(sourceDto.getCl_1(), sourceDto.getCl_2(), sourceDto.getClCoef())
        );

        resultDto.setSO4_v(
                calculateSO4_v(sourceDto.getSO4_1(), sourceDto.getSO4_2())
        );

        resultDto.setClSO4(
                calculateClSO4(sourceDto.getCl_1(), sourceDto.getCl_2(), sourceDto.getClCoef(),
                        sourceDto.getSO4_1(), sourceDto.getSO4_2())
        );
    }

    private static Float calculateSO4_v(Float so4_1, Float so4_2) {
        if (Objects.isNull(so4_1) || Objects.isNull(so4_2)) {
            return 0F;
        }

        var result = (so4_1 - so4_2) * 4.11F * 10_000;

        return result;
    }

    private static Float calculateCl_v(Float cl_1, Float cl_2, float clCoef) {
        if (Objects.isNull(cl_1) || Objects.isNull(cl_2)) {
            return 0F;
        }

        var result = (cl_1 - cl_2) * clCoef * 0.0175F * 10_000;

        return result;
    }

    private static Float calculateSO4(Float so4_1, Float so4_2) {
        if (Objects.isNull(so4_1) || Objects.isNull(so4_2)) {
            return 0F;
        }

        var result = (so4_1 - so4_2) * 4.11F;

        return result;
    }

    private static Float calculateGum(Float gum_1, Float gum_2) {
        if (Objects.isNull(gum_1) || Objects.isNull(gum_2)) {
            return 0F;
        }

        var result = (gum_1 - gum_2) * 0.002585F;

        return result;
    }

    private static Float calculateCl(Float cl_1, Float cl_2, float clCoef) {
        if (Objects.isNull(cl_1) || Objects.isNull(cl_2)) {
            return 0F;
        }

        var result = (cl_1 - cl_2) * clCoef * 0.0175F;

        return result;
    }

    private static Float calculateClSO4(Float cl_1, Float cl_2, float clCoef, Float so4_1, Float so4_2) {
        if (Objects.isNull(cl_1) || Objects.isNull(cl_2) || Objects.isNull(so4_1) || Objects.isNull(so4_2)) {
            return 0F;
        }

        var result = ((cl_1 - cl_2) * clCoef * 0.0175F) * 10_000 +
                (so4_1 - so4_2) * 4.11F * 10_000 * 0.25F;

        return result;
    }
}
