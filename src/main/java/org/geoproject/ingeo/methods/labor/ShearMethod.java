package org.geoproject.ingeo.methods.labor;

import org.geoproject.ingeo.dto.methodDtos.ShearDto;
import org.geoproject.ingeo.exceptions.ZeroDivisionException;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static org.geoproject.ingeo.exceptions.ExceptionTypeEnum.ZERO_DIVISION_ATTEMPT_EXCEPTION;

public class ShearMethod {

    public static void calculate(ShearDto shearDto, List<ShearDto> shearDtoList) {

        System.out.println("9999999999999999999999999999999999999999999999999");
        System.out.println(shearDto);

        //Влажность грунта до
        //We_a
        //Round(We([X1a], [X2a], [X3a]), 3)
        //Вес пуст. б. (авто) до
        //X1a
        //Вес с вл. грунтом до
        //X2a
        //Вес с сух. грунтом до
        //X3a
        shearDto.setShearNaturalWaterContentWeighingBottleFirstMeasurement(
                calculateNaturalWaterContent(
                        shearDto.getShearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement(),
                        shearDto.getShearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement(),
                        shearDto.getShearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement()
                )
        );

        shearDto.setWaterContentBefore(shearDto.getShearNaturalWaterContentWeighingBottleFirstMeasurement());

        shearDto.setWaterContentAfter(shearDto.getShearNaturalWaterContentWeighingBottleSecondMeasurement());

        //Влажность грунта после
        //We_b
        //Round(We([X1b], [X2b], [X3b]), 3)
        //Вес пуст. б. (авто) после
        //X1b
        //Вес с вл. грунтом после
        //X2b
        //Вес с сух. грунтом после
        //X3b
        shearDto.setShearNaturalWaterContentWeighingBottleSecondMeasurement(
                calculateNaturalWaterContent(
                        shearDto.getShearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement(),
                        shearDto.getShearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement(),
                        shearDto.getShearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement()
                )
        );

        //Влажность грунта среднее по точке
        //WSr
        //CalcAvgW([We_a], [We_b])
//        shearDto.setNaturalShearAverageWaterContentFirstMeasurement(
//                calculateAverageFromAllPointsInShear(
//                        shearDto.getShearNaturalWaterContentWeighingBottleFirstMeasurement(),
//                        shearDto.getShearNaturalWaterContentWeighingBottleSecondMeasurement()
//                )
//        );

        var waterContentValues = shearDtoList.stream()
                .map(ShearDto::getShearNaturalWaterContentWeighingBottleFirstMeasurement)
                .toList();

        var averageWaterContentFromAllPointsInShear = calculateAverageFromAllPointsInShear(waterContentValues);

        shearDto.setNaturalShearAverageWaterContentFirstMeasurement(averageWaterContentFromAllPointsInShear);

        shearDtoList.forEach(dto -> {
            dto.setNaturalShearAverageWaterContentFirstMeasurement(averageWaterContentFromAllPointsInShear);
        });

        System.out.println("0000000000000000000000000000000000000000000000000000000000000000");
        System.out.println(shearDto.getShearEmptyRingMassFirstMeasurement());
        System.out.println(shearDto.getShearRingWithWetSoilMassFirstMeasurement());
        System.out.println(shearDto.getShearRingVolumeFirstMeasurement());

        //Плотность грунта до
        //Roa
        //Round(Ro([Y1a], [Y2a], [Y3a]), 2)
        //Вес пуст. к.(авто) до
        //Y1a
        //Вес с вл. грунтом до
        //Y2a
        //Объем к. (авто) до
        //Y3a
        shearDto.setShearRingDensityFirstMeasurement(
                calculateRingDensity(
                        shearDto.getShearEmptyRingMassFirstMeasurement(),
                        shearDto.getShearRingWithWetSoilMassFirstMeasurement(),
                        shearDto.getShearRingVolumeFirstMeasurement()
                )
        );

        shearDto.setDensityBefore(shearDto.getShearRingDensityFirstMeasurement());

        //Плотность грунта после
        //Rob
        //Round(Ro([Y1b], [Y2b], [Y3b]), 2)
        //Вес пуст. к.(авто) после
        //Y1b
        //Вес с вл. грунтом после
        //Y2b
        //Объем к. (авто) после
        //Y3b
        shearDto.setShearRingDensitySecondMeasurement(
                calculateRingDensity(
                        shearDto.getShearEmptyRingMassSecondMeasurement(),
                        shearDto.getShearRingWithWetSoilMassSecondMeasurement(),
                        shearDto.getShearRingVolumeSecondMeasurement()
                )
        );

        //Плотн. ск. грунта до
        //Roda
        //Round([Roa] / (1 + [We_a]), 2)
        shearDto.setShearRingDrySoilDensityFirstMeasurement(
            calculateRingDrySoilDensity(
                    shearDto.getShearRingDensityFirstMeasurement(),
                    shearDto.getShearNaturalWaterContentWeighingBottleFirstMeasurement()
            )
        );
        //Плотн. ск. грунта после
        //Rodb
        //Round([Rob] / (1 + [We_b]), 2)
        shearDto.setShearRingDrySoilDensitySecondMeasurement(
                calculateRingDrySoilDensity(
                        shearDto.getShearRingDensitySecondMeasurement(),
                        shearDto.getShearNaturalWaterContentWeighingBottleSecondMeasurement()
                )
        );

        //Плотность грунта среднее
        //RoSr
        //CalcAvg([Roa], [Rob])
//        shearDto.setShearRingDensityAverageFirstMeasurement(
//                calculateAverage(
//                        shearDto.getShearRingDensityFirstMeasurement(),
//                        shearDto.getShearRingDensitySecondMeasurement()
//                )
//        );

        var ringDensityValues = shearDtoList.stream()
                .map(ShearDto::getShearRingDensityFirstMeasurement)
                .toList();

        System.out.println("ringDensityValues");
        System.out.println(ringDensityValues);

        var averageRingDensityFromAllPointsInShear = calculateAverageFromAllPointsInShear(ringDensityValues);

        System.out.println("averageRingDensityFromAllPointsInShear");
        System.out.println(averageRingDensityFromAllPointsInShear);

//        shearDto.setShearRingDensityAverageFirstMeasurement(averageRingDensityFromAllPointsInShear);

        shearDtoList.forEach(dto -> {
            dto.setShearRingDensityAverageFirstMeasurement(averageRingDensityFromAllPointsInShear);
        });

        //Плотн. ск. грунта среднее
//        shearDto.setShearRingDrySoilDensityAverageFirstMeasurement(
//                calculateAverage(
//                    shearDto.getShearRingDrySoilDensityFirstMeasurement(),
//                    shearDto.getShearRingDrySoilDensitySecondMeasurement()
//                )
//        );

        var ringDrySoilDensityValues = shearDtoList.stream()
                .map(ShearDto::getShearRingDrySoilDensityFirstMeasurement)
                .toList();

        var averageRingDrySoilDensityFromAllPointsInShear = calculateAverageFromAllPointsInShear(ringDrySoilDensityValues);

//        shearDto.setShearRingDensityAverageFirstMeasurement(averageRingDrySoilDensityFromAllPointsInShear);

        shearDtoList.forEach(dto -> {
            dto.setShearRingDrySoilDensityAverageFirstMeasurement(averageRingDrySoilDensityFromAllPointsInShear);
        });

    //shearNaturalWaterContentWeighingBottleFirstMeasurement
    //shearNaturalWaterContentWeighingBottleSecondMeasurement
    //naturalShearAverageWaterContentFirstMeasurement
    //shearRingDensityFirstMeasurement
    //shearRingDensitySecondMeasurement
    //shearRingDrySoilDensityFirstMeasurement
    //shearRingDrySoilDensitySecondMeasurement
    //shearRingDensityAverageFirstMeasurement
    //shearRingDrySoilDensityAverageFirstMeasurement
    }

    private static Float calculateAverageFromAllPointsInShear(List<Float> valuesList) {
        return valuesList.stream()
                .reduce(0F, Float::sum) / valuesList.size();
    }

    private static float calculateRingDrySoilDensity(Float x1,
                                                     Float x2) {
        //Round([Roa] / (1 + [We_a]), 2)

        if (1 + x2 == 0) {
            throw new ZeroDivisionException(ZERO_DIVISION_ATTEMPT_EXCEPTION.getMessage());
        }

        var result = x1 / (1 + x2);

        return result;
    }

    private static float calculateRingDensity(Float y1,
                                              Float y2,
                                              Float y3) {

//        Function Ro(Y1, Y2, Y3)
//        Ro = 0
//        If (Y1 <> 0 And Y2 <> 0 And Y3 <> 0) Then
//
//                Ro = Round((Y2 - Y1) / Y3, 2)
//        End If

        var result = 0F;

        if (y3 == 0) {
            throw new ZeroDivisionException(ZERO_DIVISION_ATTEMPT_EXCEPTION.getMessage());
        }

        if (y1 != 0 && y2 != 0) {
            result = (y2 - y1) / y3;
        }

        return result;
    }

    private static float calculateAverage(Float a, Float b) {
        var result = 0F;

        if (Objects.isNull(a) && Objects.isNull(b) ||
                a == 0 && b == 0) {
            return result;
        }

        if (Objects.isNull(a) || a == 0) {
            result = b;
        } else if (Objects.isNull(b) || b == 0) {
            result = a;
        } else {
            result = (a + b) / 2;
        }

        return result;
    }

    private static float calculateNaturalWaterContent(Float x1,
                                                      Float x2,
                                                      Float x3) {

//        Function We(x1, x2, x3)
//        We = 0
//        If (x1 <> 0 And x2 <> 0 And x3 <> 0) Then
//            If x3 - x1 <> 0 Then
//                We = Round((x2 - x3) / (x3 - x1), 3)
//            End If
//        End If

            Float result = 0F;

            if (x1 != 0 && x2 != 0 && x3 != 0) {
                if (x3 - x1 == 0) {
                    throw new ZeroDivisionException(ZERO_DIVISION_ATTEMPT_EXCEPTION.getMessage());
                }
                result = (x2 - x3) / (x3 - x1);
            }

            return result;
    }

    public static void calculateApproximation(List<ShearDto> shearDtoList) {
        AtomicReference<Float> xSigmaSum = new AtomicReference<>(0F);
        AtomicReference<Float> yTauSum = new AtomicReference<>(0F);

        AtomicReference<Float> xyProductionSum = new AtomicReference<>(0F);
        AtomicReference<Float> xSquaredSum = new AtomicReference<>(0F);

        shearDtoList.forEach(dto -> {
            if (Objects.nonNull(dto.getVerticalLoading())) {
                xSigmaSum.updateAndGet(v -> v + dto.getVerticalLoading());
            }

            if (Objects.nonNull(dto.getShearStrength())) {
                yTauSum.updateAndGet(v -> v + dto.getShearStrength());
            }

            if (Objects.nonNull(dto.getVerticalLoading()) && Objects.nonNull(dto.getShearStrength())) {
                xyProductionSum.updateAndGet(v -> v + (dto.getVerticalLoading() * dto.getShearStrength()));
                xSquaredSum.updateAndGet(v -> v + dto.getVerticalLoading() * dto.getVerticalLoading());
            }
        });

        //тангенс угла
        Float tgFi = (shearDtoList.size() * xyProductionSum.get() - xSigmaSum.get() * yTauSum.get())
                /
                (shearDtoList.size() * xSquaredSum.get() - xSigmaSum.get() * xSigmaSum.get());

        //угол
        Float Fi = (float) Math.atan(tgFi);

        Float degrees = (float) Math.toDegrees(Fi);

        Float c = (yTauSum.get() - tgFi * xSigmaSum.get()) / shearDtoList.size();

        System.out.println("xSigmaSum: " + xSigmaSum.get());
        System.out.println("yTauSum: " + yTauSum.get());
        System.out.println("xyProductionSum: " + xyProductionSum.get());
        System.out.println("xSquaredSum: " + xSquaredSum.get());
        System.out.println("tgFi: " + tgFi);
        System.out.println("Fi: " + Fi);
        System.out.println("degrees: " + degrees);
        System.out.println("c: " + c);

        shearDtoList.forEach(dto -> {
            dto.setXSigmaSum(xSigmaSum.get());
            dto.setYTauSum(yTauSum.get());
            dto.setXyProductionSum(xyProductionSum.get());
            dto.setXSquaredSum(xSquaredSum.get());
            dto.setTgFi(tgFi);
            dto.setFi(Fi);
            dto.setDegrees(degrees);
            dto.setC(c);
        });
    }
}
