package org.geoproject.ingeo.methods.labor;

import org.geoproject.ingeo.dto.methodDtos.WaterContentDTO;
import org.geoproject.ingeo.utils.CommonOperations;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Log4j2
public class WaterContentMethod {

    private static float calculateWeighingBottleWaterContent(float bottleWithWetSoilMass, float bottleWithDrySoilMass,
                                                             float emptyBottleMass) {

        float wetMass = bottleWithWetSoilMass - bottleWithDrySoilMass;
        float drySoilMass = (bottleWithDrySoilMass - emptyBottleMass) == 0 ? 1 : bottleWithDrySoilMass - emptyBottleMass;
        float weighingBottleWaterContent = wetMass / drySoilMass;
        if (weighingBottleWaterContent < 0) {
            System.out.println("Недопустимы отрицательные значения");
            return 0;
        }

        return CommonOperations.round(weighingBottleWaterContent, 3);
    }


    public static float calculateAverageWaterContent(float weighingBottleWaterContentFirst, float weighingBottleWaterContentSecond) {

        float averageWaterContent = (weighingBottleWaterContentFirst + weighingBottleWaterContentSecond) / 2;

        return CommonOperations.round(averageWaterContent, 3);
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

        return CommonOperations.round(liquidityIndex, 3);
    }


    public static void calculatePlasticityIndex(WaterContentDTO waterContentDTO) {

        float naturalWaterContentWeighingBottleWaterContentFirst = calculateWeighingBottleWaterContent(
                waterContentDTO.getNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement(),
                waterContentDTO.getNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement(),
                waterContentDTO.getNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement());

        float naturalWaterContentWeighingBottleWaterContentSecond = calculateWeighingBottleWaterContent(
                waterContentDTO.getNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement(),
                waterContentDTO.getNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement(),
                waterContentDTO.getNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement());

        float liquidityWeighingBottleWaterContentFirst = calculateWeighingBottleWaterContent(
                waterContentDTO.getLiquidityWeighingBottleWithWetSoilMassFirstMeasurement(),
                waterContentDTO.getLiquidityWeighingBottleWithDrySoilMassFirstMeasurement(),
                waterContentDTO.getLiquidityEmptyWeighingBottleMassFirstMeasurement());

        float liquidityWeighingBottleWaterContentSecond = calculateWeighingBottleWaterContent(
                waterContentDTO.getLiquidityWeighingBottleWithWetSoilMassSecondMeasurement(),
                waterContentDTO.getLiquidityWeighingBottleWithDrySoilMassSecondMeasurement(),
                waterContentDTO.getLiquidityEmptyWeighingBottleMassSecondMeasurement());

        float plasticWeighingBottleWaterContentFirst = calculateWeighingBottleWaterContent(
                waterContentDTO.getPlasticWeighingBottleWithWetSoilMassFirstMeasurement(),
                waterContentDTO.getPlasticWeighingBottleWithDrySoilMassFirstMeasurement(),
                waterContentDTO.getPlasticEmptyWeighingBottleMassFirstMeasurement());

        float plasticWeighingBottleWaterContentSecond = calculateWeighingBottleWaterContent(
                waterContentDTO.getPlasticWeighingBottleWithWetSoilMassSecondMeasurement(),
                waterContentDTO.getPlasticWeighingBottleWithDrySoilMassSecondMeasurement(),
                waterContentDTO.getPlasticEmptyWeighingBottleMassSecondMeasurement());


        float naturalAverageWaterContent = calculateAverageWaterContent(naturalWaterContentWeighingBottleWaterContentFirst,
                naturalWaterContentWeighingBottleWaterContentSecond);
        float averageLiquidLimit = calculateAverageWaterContent(liquidityWeighingBottleWaterContentFirst,
                liquidityWeighingBottleWaterContentSecond);
        float averagePlasticLimit = calculateAverageWaterContent(plasticWeighingBottleWaterContentFirst,
                plasticWeighingBottleWaterContentSecond);

        float plasticityIndex = averageLiquidLimit - averagePlasticLimit;

        float liquidityIndex = calculateLiquidityIndex(naturalAverageWaterContent, averagePlasticLimit, plasticityIndex);

        waterContentDTO.setNaturalWaterContentWeighingBottleWaterContentFirstMeasurement(naturalWaterContentWeighingBottleWaterContentFirst);
        waterContentDTO.setNaturalWaterContentWeighingBottleWaterContentSecondMeasurement(naturalWaterContentWeighingBottleWaterContentSecond);
        waterContentDTO.setLiquidityWeighingBottleWaterContentFirstMeasurement(liquidityWeighingBottleWaterContentFirst);
        waterContentDTO.setLiquidityWeighingBottleWaterContentSecondMeasurement(liquidityWeighingBottleWaterContentSecond);
        waterContentDTO.setPlasticWeighingBottleWaterContentFirstMeasurement(plasticWeighingBottleWaterContentFirst);
        waterContentDTO.setPlasticWeighingBottleWaterContentSecondMeasurement(plasticWeighingBottleWaterContentSecond);
        waterContentDTO.setNaturalAverageWaterContent(naturalAverageWaterContent);
        waterContentDTO.setAverageLiquidLimit(averageLiquidLimit);
        waterContentDTO.setAveragePlasticLimit(averagePlasticLimit);
        waterContentDTO.setPlasticityIndex(plasticityIndex);

        waterContentDTO.setLiquidityIndex(liquidityIndex);

        //todo проверка на отрицательное значение
    }


}
