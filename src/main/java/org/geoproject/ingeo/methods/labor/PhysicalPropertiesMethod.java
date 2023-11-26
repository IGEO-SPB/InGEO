package org.geoproject.ingeo.methods.labor;

import org.geoproject.ingeo.dto.methodDtos.PhysicalPropertiesDTO;
import org.geoproject.ingeo.utils.CommonOperations;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Log4j2
//todo аннотация
public final class PhysicalPropertiesMethod {

    private static Map<String, String> defineSoilSubTypes(PhysicalPropertiesDTO dto) {

        Map<String, String> soilSubTypesMap = new HashMap<>();

        //J = Число пластичности (доли ед.)
        //округлять до 3 знаков после запятой
        float roundPlasticityIndex = CommonOperations.round(dto.getPlasticityIndex(), 3);

//        Razn1 = liquiditySoilSubType
//        Razn2 = claySoilSubType
//        RaznJ = plasticitySoilSubType

        String liquiditySoilSubType = "";
        String claySoilSubType = "";
        String plasticitySoilSubType = "";

        float sandContent = calculateSandContent(dto);


        if (roundPlasticityIndex >= 0.01 && roundPlasticityIndex <= 0.07) {
            plasticitySoilSubType = "Супесь";
            if (sandContent >= 50) {
                claySoilSubType = "пес";
            } else {
                claySoilSubType = "пыл";
            }

        }

        if (roundPlasticityIndex > 0.07 && roundPlasticityIndex < 0.12) {
            plasticitySoilSubType = "Суглинок";
            if (sandContent >= 40) {
                claySoilSubType = "л.пес";
            } else {
                claySoilSubType = "л.пыл";
            }
        }

        if (roundPlasticityIndex >= 0.12 && roundPlasticityIndex <= 0.17) {
            plasticitySoilSubType = "Суглинок";
            if (sandContent >= 40) {
                claySoilSubType = "т.пес";
            } else {
                claySoilSubType = "т.пыл";
            }
        }

        if (roundPlasticityIndex > 0.17 && roundPlasticityIndex < 0.27) {

            plasticitySoilSubType = "Глина";
            if (sandContent >= 40) {
                claySoilSubType = "л.пес";
            } else {
                claySoilSubType = "л.пыл";
            }
        }

        if (roundPlasticityIndex >= 0.27) {
            plasticitySoilSubType = "Глина";
            claySoilSubType = "т.";
        }

        if (sandContent == -1) { //нет песка
            claySoilSubType = "";
        }

        soilSubTypesMap.put("plasticitySoilSubType", plasticitySoilSubType);
        soilSubTypesMap.put("claySoilSubType", claySoilSubType);

        liquiditySoilSubType = defineLiquiditySoilSubType(dto, plasticitySoilSubType);
        soilSubTypesMap.put("liquiditySoilSubType", liquiditySoilSubType);

        return soilSubTypesMap;
    }

    private static float calculateSandContent(PhysicalPropertiesDTO dto) { //содержание песка и пыли
        float dust;
        boolean sand;
        float sandContent;

        if (dto.getSum_2_005() == 0) {
            sandContent = -1;
        } else {
            sandContent = dto.getSum_2_005();
        }

        if (dto.getSum_005_0002_old_005_0005() == 0) {
            dust = -1;
        } else {
            dust = dto.getSum_005_0002_old_005_0005();
        }

        if (dto.getSum_2_005() == 0 && dto.getSum_005_0002_old_005_0005() == 0 && dto.getSum_less_0002_old_less_0005() == 0) {
            sandContent = -1;
            dust = -1;
        }

        sand = dto.isSand();

        return sandContent;
    }

    private static String defineLiquiditySoilSubType(PhysicalPropertiesDTO dto, String plasticitySoilSubType) {
        String liquiditySoilSubType = "";

        float liquidityIndex = dto.getLiquidityIndex(); //IL
        float roundLiquidityIndex = CommonOperations.round(dto.getLiquidityIndex(), 2); //IL

        //J = Число пластичности (доли ед.)
        //округлять до 3 знаков после запятой
//        float roundPlasticityIndex = CommonOperations.round(dto.getPlasticityIndex(), 3);
        float plasticityIndex = dto.getPlasticityIndex();

//        We = Природная влажность(доли, ед.) = dto.getNaturalAverageWaterContent();
        float naturalAverageWaterContent = dto.getNaturalAverageWaterContent();

//        Wb = Влажность по бюксу (доли ед.) = dto.get пока нет
        //JL = (WSr - Wp_sr) /J - формула из таблицы
        //IL (Показатель текучести (доли ед.)) = (We - Wp) / J - формула из программы ВА
        float wb = 0;

//        Wp = Влажность на границе пластичности (доли ед.) = dto.getPlasticAverageWaterContent();
        float averagePlasticLimit = dto.getPlasticLimit();

//        if (dto.getPlasticityIndex() != 0) {
//            if (naturalAverageWaterContent != 0 && wb != 0) {
//                liquidityIndex = (naturalAverageWaterContent - averagePlasticLimit) / plasticityIndex;
//            }
//
//            if (naturalAverageWaterContent == 0) {
//                liquidityIndex = (wb - averagePlasticLimit) / plasticityIndex;
//            }
//
//            if (wb == 0) {
//                liquidityIndex = (naturalAverageWaterContent - averagePlasticLimit) / plasticityIndex;
//            }
//
//            if (naturalAverageWaterContent == 0 && wb == 0) {
//                liquidityIndex = 0;
//            }
//        }
//
//        if (liquidityIndex == 0) {
//            return 0;
//        }


        if (Objects.equals(plasticitySoilSubType, "Супесь")) {
            if (roundLiquidityIndex < 0) {
                liquiditySoilSubType = "Т";
            }

            if (roundLiquidityIndex >= 0 && roundLiquidityIndex <= 1) {
                liquiditySoilSubType = "ПЛ";
            }

            if (roundLiquidityIndex > 1) {
                liquiditySoilSubType = "ТК";
            }
        }

        if ("Суглинок".equals(plasticitySoilSubType) || Objects.equals(plasticitySoilSubType, "Глина")) {
            if (roundLiquidityIndex < 0) {
                liquiditySoilSubType = "Т";
            }

            if (roundLiquidityIndex >= 0 && roundLiquidityIndex <= 0.25) {
                liquiditySoilSubType = "ПТ";
            }

            if (roundLiquidityIndex > 0.25 && roundLiquidityIndex <= 0.5) {
                liquiditySoilSubType = "ТП";
            }

            if (roundLiquidityIndex > 0.5 && roundLiquidityIndex <= 0.75) {
                liquiditySoilSubType = "МП";
            }

            if (roundLiquidityIndex > 0.75 && roundLiquidityIndex <= 1) {
                liquiditySoilSubType = "ТКП";
            }

            if (roundLiquidityIndex > 1) {
                liquiditySoilSubType = "ТК";
            }
        }

        return liquiditySoilSubType;
    }

    private static float calculateCbConsistencyDifference(PhysicalPropertiesDTO dto) {
        return dto.getBrokenStructureCbConsistency() - dto.getUndisturbedStructureCbConsistency();
    }

    private static String defineStructuralStrengthDegree(PhysicalPropertiesDTO dto) {
        float cbConsistencyDifference = dto.getCbConsistencyDifference();
        String structuralStrengthDegree = StringUtils.EMPTY;

        if (cbConsistencyDifference < 0.25) {
            structuralStrengthDegree = "Слабая";
        } else if (cbConsistencyDifference >= 0.25 && cbConsistencyDifference < 0.55) {
            structuralStrengthDegree = "Средняя";
        } else if (cbConsistencyDifference >= 0.55 && cbConsistencyDifference < 0.8) {
            structuralStrengthDegree = "Значит.";
        } else if (cbConsistencyDifference >= 0.8) {
            structuralStrengthDegree = "Резкая";
        }

        return structuralStrengthDegree;
    }

    private static String defineIgnitionLossAverageName(PhysicalPropertiesDTO dto) {
        boolean sand = dto.isSand();
        float ignitionLossAverageMass = dto.getIgnitionLossAverageMass();
        String ignitionLossAverageName = "";

        if ((sand && ignitionLossAverageMass >= 0.03 && ignitionLossAverageMass <= 0.1) ||
                (!sand && ignitionLossAverageMass > 0.05 && ignitionLossAverageMass <= 0.1)) {
            ignitionLossAverageName = "С прим.";
        }

        if (ignitionLossAverageMass > 0.1 && ignitionLossAverageMass <= 0.25) {
            ignitionLossAverageName = "Слабозаторфованный";
        }

        if (ignitionLossAverageMass > 0.25 && ignitionLossAverageMass <= 0.4) {
            ignitionLossAverageName = "Среднезаторфованный";
        }

        if (ignitionLossAverageMass > 0.4 && ignitionLossAverageMass < 0.5) {
            ignitionLossAverageName = "Сильнозаторфованный";
        }

        if (ignitionLossAverageMass >= 0.5) {
            ignitionLossAverageName = "Торфы";
        }

        return ignitionLossAverageName;
    }

    private static String defineDecompositionDegreeName(PhysicalPropertiesDTO dto) {
        String decompositionDegreeName = "";
        float decompositionDegree = dto.getDecompositionDegree();

        if (decompositionDegree == 0) {
            decompositionDegreeName = "";
        }

        if (decompositionDegree < 20 && decompositionDegree > 0) {
            decompositionDegreeName = "Слаборазложившийся";
        }

        if (decompositionDegree >= 20 && decompositionDegree <= 45) {
            decompositionDegreeName = "Среднеразложившийся";
        }

        if (decompositionDegree > 45) {
            decompositionDegreeName = "Сильноразложившийся";
        }

        return decompositionDegreeName;
    }

    public static void calculatePhysicalProperties(PhysicalPropertiesDTO dto) {
        Map<String, String> soilSubTypesMap = defineSoilSubTypes(dto);

        //Разновидность
        dto.setPlasticitySoilSubType(soilSubTypesMap.get("plasticitySoilSubType"));
        //Разновидность (консистенция)
        dto.setLiquiditySoilSubType(soilSubTypesMap.get("liquiditySoilSubType"));
        //Разновидность глинистых грунтов
        dto.setClaySoilSubType(soilSubTypesMap.get("claySoilSubType"));

//          Логика вычисления следующих четырех полей перенесена в BoychenkoConeLiquidityConsistencyService

//        //Консистенция по Cb при ненарушенной стр-ре (Cut | Paraffin)
//        dto.setUndisturbedStructureCbConsistency();
//
//        //Показатель текучести по Cb при ненарушенной стр-ре
//        dto.setUndisturbedStructureCbLiquidityIndexName();
//
//        //Консистенция по Cb при нарушенной стр-ре (Cut | Paraffin)
//        dto.setBrokenStructureCbConsistency();
//
//        //Показатель текучести по Cb при нарушенной стр-ре
//        dto.setBrokenStructureCbLiquidityIndexName();

        //Разница показателей текучести (используется, но не нашел в базе)
        dto.setCbConsistencyDifference(calculateCbConsistencyDifference(dto));

        //Степень выраженности структурных свойств (используется, но не нашел в базе)
        dto.setStructuralStrengthDegree(defineStructuralStrengthDegree(dto));

        if (Objects.nonNull(dto.getIgnitionLossAverageMass())) {
            //Наименование по отн. содержанию орг. в-ва
            dto.setIgnitionLossAverageName(defineIgnitionLossAverageName(dto));
        }

        if (Objects.nonNull(dto.getDecompositionDegree())) {
            //Наименование по степени разложения торфа
            dto.setDecompositionDegreeName(defineDecompositionDegreeName(dto));
        }

//        dto.setLiquidityIndex();

        //todo нейминг + коррозионный метод
//        dto.set
//        dto.set
//        dto.set
//        dto.set
        //Корроз. агр. грунта, Om*m
        //Корроз. агр. грунта, Om*m
        //Корроз. агр. грунта, A/m^2
        //Корроз. агр. грунта, A/m^2

    }

    public static float calculateLiquidityIndex(Float naturalAverageWaterContent, Float averagePlasticLimit, Float plasticityIndex) {

        log.info("naturalAverageWaterContent: " + naturalAverageWaterContent);
        log.info("averagePlasticLimit: " + averagePlasticLimit);
        log.info("plasticityIndex: " + plasticityIndex);

        System.out.println("plasticityIndex::::");
        System.out.println(plasticityIndex);


        if (plasticityIndex == 0) {
            plasticityIndex = 1F;
        }
        Float liquidityIndex = null;

        try {
            liquidityIndex = (naturalAverageWaterContent - averagePlasticLimit) / plasticityIndex;
            System.out.println("FINALLY CHECKING liquidityIndex");
            System.out.println(liquidityIndex);
        } catch (ArithmeticException | NullPointerException exception) {
//            exception.printStackTrace();
            throw new RuntimeException("Не рассчитано значение liquidityIndex");
        } finally {
            if (Objects.isNull(liquidityIndex)) {
                liquidityIndex = 1F;
            }
        }

        return liquidityIndex;
    }
}
