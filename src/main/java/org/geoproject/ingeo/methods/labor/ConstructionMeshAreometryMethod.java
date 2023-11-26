package org.geoproject.ingeo.labor;

import com.geoproject.igeo.dto.ConstructionMeshAreometryDto;
import com.geoproject.igeo.dto.ConstructionMeshAreometryResultDto;
import com.geoproject.igeo.utils.CommonOperations;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.geoproject.igeo.constants.LaborMethodConstants.ZERO_FLOAT_VALUE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstructionMeshAreometryMethod {

    private static Float calculateFrom70To2(Float particleWeight, Float totalSubsample) {
        if (totalSubsample == 0) return 0F;

        var number = particleWeight / totalSubsample * 100;
        return CommonOperations.round(number, 1);
    }

    private static Float calculateFrom2To01(Float particleWeight, Float sieveDropperSubsample, Float lessThan2Percentage) {
        if (sieveDropperSubsample == 0) return 0F;

        var number = particleWeight / sieveDropperSubsample * lessThan2Percentage;
        return CommonOperations.round(number, 1);
    }

    private static float calculateCorrection(ConstructionMeshAreometryDto dto, Float lessThan2Percentage) {

        var gam5 = dto.getCorrection() + dto.getReading();
        var rop = 0F;
        if (dto.getDrySoilSubsample() != 0) // What about particle density == 1&
            rop = dto.getParticleDensity() * lessThan2Percentage / (dto.getParticleDensity() - 1) / dto.getDrySoilSubsample();

        var number = gam5 * rop;

        return CommonOperations.round(number, 1);
    }


    private static float calculateFrom01To005(ConstructionMeshAreometryDto dto,
                                              ConstructionMeshAreometryResultDto resultDto,
                                              Float lessThan2Percentage) {

        Float moreThan2Percentage =
                resultDto.getX_25_125() +
                        resultDto.getX_125_063() +
                        resultDto.getX_063_0315() +
                        resultDto.getX_0315_016() +
                        resultDto.getX_016_01();


        var number = lessThan2Percentage - moreThan2Percentage - resultDto.getX_less_005();

        return CommonOperations.round(number, 1);
    }


    // TODO refactor this calculation method
    private static String defineSoilKind(ConstructionMeshAreometryDto dto,
                                         ConstructionMeshAreometryResultDto resultDto) {

        // s у ВА
        Float percentageSum = ZERO_FLOAT_VALUE;
        String soilKind = StringUtils.EMPTY;

        // Dim flag_empty As Integer
        // Integer flag_empty = 1;


        percentageSum =
                resultDto.getX_more_70() +
                        resultDto.getX_70_40() +
                        resultDto.getX_40_20() +
                        resultDto.getX_20_10();

        if (percentageSum > 50) {
            soilKind = "галеч";
            return soilKind;
        }

        percentageSum = percentageSum +
                resultDto.getX_10_5() +
                resultDto.getX_5_25();

        if (percentageSum > 50) {
            soilKind = "гравий";
            return soilKind;
        }

        if (percentageSum > 25) {
            soilKind = "гравел";
            return soilKind;
        }

        percentageSum = percentageSum +
                resultDto.getX_25_125() +
                resultDto.getX_125_063();

        if (percentageSum > 50) {
            soilKind = "крупн";
            return soilKind;
        }

        percentageSum = percentageSum +
                resultDto.getX_063_0315();

        if (percentageSum > 50) {
            soilKind = "ср. крупн";
            return soilKind;
        }

        percentageSum = percentageSum +
                resultDto.getX_0315_016() +
                resultDto.getX_016_01();

        if (percentageSum >= 75) {
            soilKind = "мелк";
            return soilKind;
        }

        if (percentageSum < 75) {
            soilKind = "пылеват";
            return soilKind;
        }

        if (percentageSum == 0) {
            soilKind = " ";
//            flag_empty = 0;
            return soilKind;
        }

        return soilKind;
    }

    private static Float calculateUniformityCoefficient(ConstructionMeshAreometryDto dto,
                                                        ConstructionMeshAreometryResultDto resultDto) {
        Float uniformityCoefficient = ZERO_FLOAT_VALUE; //Cn
//        String uniformityDegree = StringUtils.EMPTY; //Stn

//        z(1) = Nz(.[Y70])
//        z(2) = Nz(.[Y70_40])
//        z(3) = Nz(.[Y40_20])
//        z(4) = Nz(.[Y20_10])
//        z(5) = Nz(.[Y10_5])
//        z(6) = Nz(.[Y5_25])
//        z(7) = Nz(.[Y25_2])
//        z(8) = Nz(.[Y2_125])
//        z(9) = Nz(.[Y125_1])
//        z(10) = Nz(.[Y1_063])
//        z(11) = Nz(.[Y063_050])
//        z(12) = Nz(.[Y050_0315])
//        z(13) = Nz(.[Y0315_025])
//        z(14) = Nz(.[Y025_016])
//        z(15) = Nz(.[Y016_01])
//        z(16) = Nz(.[Y01_005])
//        z(17) = Nz(.[Y005])

        List<Float> percentageList = new ArrayList<>(
                List.of(resultDto.getX_more_70(),
                        resultDto.getX_70_40(),
                        resultDto.getX_40_20(),
                        resultDto.getX_20_10(),
                        resultDto.getX_10_5(),
                        resultDto.getX_5_25(),
                        resultDto.getX_25_125(),
                        resultDto.getX_125_063(),
                        resultDto.getX_063_0315(),
                        resultDto.getX_0315_016(),
                        resultDto.getX_016_01(),
                        resultDto.getX_01_005(),
                        resultDto.getX_less_005(),
                        0F));

        Integer FL = 0;
        for (int i = 0; i < percentageList.size() - 2; i++) {
            if ((percentageList.get(i) != null || percentageList.get(i) != 0) && percentageList.get(percentageList.size()-1) < 100) {
                FL = 1;
            }
        }

        if (FL == 0) {
            uniformityCoefficient = null; //.[Cn] = Empty
//            uniformityDegree = " ";       //.[Stn] = " "

            return uniformityCoefficient;
        }

//        N(1) = 140
//        N(2) = 70: N(3) = 40: N(4) = 20: N(5) = 10: N(6) = 5: N(7) = 2.5
//        N(7) = 2: N(9) = 1.25: N(10) = 1: N(11) = 0.63: N(12) = 0.5
//        N(13) = 0.315: N(14) = 0.25: N(15) = 0.16: N(16) = 0.1: N(17) = 0.05: N(18) = 0.025

        List<Float> someList = new ArrayList<>(List.of(
                140F,
                70F,
                40F,
                20F,
                10F,
                5F,
                2.5F,
                1.25F,
                0.63F,
                0.315F,
                0.16F,
                0.1F,
                0.05F,
                0.025F
        ));


        Float p2 = 0F;
        Float p1 = 0F;

        Float d1 = 0F;
        Float d2 = 0F;

        Float d10 = 0F;
        Float d60 = 0F;

//        z(18) = 0

        for (int i = someList.size()-1; i != 0; i--) {
            p2 = p2 + percentageList.get(i);

            if (p2 >= 10) {
                p1 = p2 - percentageList.get(i);
                d1 = someList.get(i + 1);
                d2 = someList.get(i);

                if (p2 == p1) {
                    uniformityCoefficient = null; //.[Cn] = Empty
//                    uniformityDegree = " ";       //.[Stn] = " "

                    return uniformityCoefficient;
                }

                d10 = d1 + (10 - p1) * (d2 - d1) / (p2 - p1);

                break;
            }
        }

//        For J = 17 To 1 Step -1
//        p2 = p2 + z(J)
//        If p2 >= 10 Then
//                p1 = p2 - z(J)
//        p2 = p2
//        d1 = N(J + 1)
//        d2 = N(J)

//            If p2 = p1 Then
//            GoTo e1
//            End If

//        d10 = d1 + (10 - p1) * (d2 - d1) / (p2 - p1)
//        GoTo break1
//        End If
//
//        Next J

        break1:
        p2 = 0F;

        for (int i = percentageList.size()-1; i != 0; i--) {
            p2 = p2 + percentageList.get(i);

            if (p2 >= 60) {
                p1 = p2 - percentageList.get(i);
                d1 = someList.get(i + 1);
                d2 = someList.get(i);

                d60 = d1 + (60 - p1) * (d2 - d1) / (p2 - p1);
                break;
            }
        }

//        p2 = p2 + z(J)
//        If p2 >= 60 Then
//                    p1 = p2 - z(J)
//            p2 = p2
//            d1 = N(J + 1)
//            d2 = N(J)
//            d60 = d1 + (60 - p1) * (d2 - d1) / (p2 - p1)
//            GoTo break2
//        End If
//        Next J

        if (d10 == 0) {
            uniformityCoefficient = null; //.[Cn] = Empty
//            uniformityDegree = " ";       //.[Stn] = " "
            return uniformityCoefficient;
        }
//        break2:
//        If d10 = 0 Then
//
//        GoTo e1
//        End If

        uniformityCoefficient = CommonOperations.round(d60 / d10, 0);
//
//        Cn = d60 / d10
//        Cn = Round(Cn, 0)


//        If Cn > 3 Then
//                Stn = "Неодн"
//        End If
//        If Cn > 0 And Cn <= 3 Then
//                Stn = "Одн"
//        End If

        return uniformityCoefficient;
    }

    private static String calculateUniformityDegree(Float uniformityCoefficient) {
        String uniformityDegree = StringUtils.EMPTY; //Stn

        if (uniformityCoefficient == null) {
            uniformityDegree = " ";       //.[Stn] = " "

            return uniformityDegree;
        }

        if (uniformityCoefficient > 3) {
            uniformityDegree = "Неодн";
        } else if (uniformityCoefficient > 0 && uniformityCoefficient <= 3) {
            uniformityDegree = "Одн";
        }

        return uniformityDegree;
    }

    private static Float calculateFinenessModulus(ConstructionMeshAreometryDto dto,
                                                  ConstructionMeshAreometryResultDto resultDto) {
        Float sum =
                resultDto.getX_5_25() +
                        resultDto.getX_25_125() +
                        resultDto.getX_125_063() +
                        resultDto.getX_063_0315() +
                        resultDto.getX_0315_016() +
                        resultDto.getX_016_01() +
                        resultDto.getX_01_005() +
                        resultDto.getX_less_005();

        Float z6 = resultDto.getX_5_25() * 100 / sum;
        Float k1 = resultDto.getX_25_125() * 100 / sum;
        Float k2 = resultDto.getX_125_063() * 100 / sum;
        Float k3 = resultDto.getX_063_0315() * 100 / sum;
        Float k4 = resultDto.getX_0315_016() * 100 / sum;
        Float k5 = resultDto.getX_016_01() * 100 / sum;

        return (5 * z6 + 3 * k1 + 3 * k2 + 3 * k3 + 2 * k4 + 2 * k5) / 100;
    }

    private static String defineSandGroupByFinenessModulus(Float finenessModulus) {

        String sandGroup = StringUtils.EMPTY;

        if (finenessModulus >= 3.5) {
            sandGroup = "Оч.крупн.";
        } else if (finenessModulus >= 3 && finenessModulus < 3.5) {
            sandGroup = "Пов.крупн.";
        } else if (finenessModulus >= 2.5 && finenessModulus < 3) {
            sandGroup = "Крупн.";
        } else if (finenessModulus >= 2 && finenessModulus < 2.5) {
            sandGroup = "Средний";
        } else if (finenessModulus >= 1.5 && finenessModulus < 2) {
            sandGroup = "Мелкий";
        } else if (finenessModulus >= 1 && finenessModulus < 1.5) {
            sandGroup = "Оч.мелкий";
        } else if (finenessModulus >= 0.7 && finenessModulus < 1) {
            sandGroup = "Тонкий";
        } else if (finenessModulus < 0.7) {
            sandGroup = "Оч.тонкий";
        } else if (finenessModulus == 0) {
            sandGroup = " ";
        }

        return sandGroup;
    }

    public static void calculate(ConstructionMeshAreometryDto dto,
                                                 ConstructionMeshAreometryResultDto resultDto) {

        //TODO: модалка, логи
        if (dto.getTotalSubsample() == 0) {
//            throw new ...
            System.out.println("Не введена общая навеска");
        }

        //TODO: модалка, логи
        if (dto.getSieveDropperSubsample() == 0) {
//            throw new ...
            System.out.println("Не введена навеска влажного грунта");
        }

        //    g1 = .[g1] //Общая навеска
        //    If g1 <> 0 Then
        //    z1 = Round(.[Y70] / g1 * 100, 1)
        //    z2 = Round(.[Y70_40] / g1 * 100, 1)
        //    z3 = Round(.[Y40_20] / g1 * 100, 1)
        //    z4 = Round(.[Y20_10] / g1 * 100, 1)
        //    z5 = Round(.[Y10_5] / g1 * 100, 1)
        //    z6 = Round(.[Y5_25] / g1 * 100, 1)
        //    z7 = Round(.[Y25_2] / g1 * 100, 1)

        //    .[Y70] = z1
        //    .[Y70_40] = z2
        //    .[Y40_20] = z3
        //    .[Y20_10] = z4
        //    .[Y10_5] = z5
        //    .[Y5_25] = z6
        //    .[Y25_2] = z7

        resultDto.setX_more_70(calculateFrom70To2(dto.getMass_X_more_70(), dto.getTotalSubsample()));
        resultDto.setX_70_40(calculateFrom70To2(dto.getMass_X_70_40(), dto.getTotalSubsample()));
        resultDto.setX_40_20(calculateFrom70To2(dto.getMass_X_40_20(), dto.getTotalSubsample()));
        resultDto.setX_20_10(calculateFrom70To2(dto.getMass_X_20_10(), dto.getTotalSubsample()));
        resultDto.setX_10_5(calculateFrom70To2(dto.getMass_X_10_5(), dto.getTotalSubsample()));
        resultDto.setX_5_25(calculateFrom70To2(dto.getMass_X_5_25(), dto.getTotalSubsample()));

//        A = 100 - z1 - z2 - z3 - z4 - z5 - z6 - z7
//        g2 = .[g2] //Навеска влажного грунта
//        k1 = 0: k2 = 0: k3 = 0: k4 = 0: k5 = 0: k6 = 0: k7 = 0: k8 = 0
//        If g2 <> 0 Then
//        k1 = Round(.[Y2_125] / g2 * A, 1)
//        k2 = Round(.[Y125_1] / g2 * A, 1)
//        k3 = Round(.[Y1_063] / g2 * A, 1)
//        k4 = Round(.[Y063_050] / g2 * A, 1)
//        k5 = Round(.[Y050_0315] / g2 * A, 1)
//        k6 = Round(.[Y0315_025] / g2 * A, 1)
//        k7 = Round(.[Y025_016] / g2 * A, 1)
//        k8 = Round(.[Y016_01] / g2 * A, 1)

//        .[Y2_125] = k1
//        .[Y125_1] = k2
//        .[Y1_063] = k3
//        .[Y063_050] = k4
//        .[Y050_0315] = k5
//        .[Y0315_025] = k6
//        .[Y025_016] = k7
//        .[Y016_01] = k8

        Float lessThan2Percentage = 100 - (
                resultDto.getX_more_70() +
                        resultDto.getX_70_40() +
                        resultDto.getX_40_20() +
                        resultDto.getX_20_10() +
                        resultDto.getX_10_5() +
                        resultDto.getX_5_25()
        );

        resultDto.setX_25_125(calculateFrom2To01(dto.getMass_X_25_125(),
                dto.getSieveDropperSubsample(),
                lessThan2Percentage));

        resultDto.setX_125_063(calculateFrom2To01(dto.getMass_X_125_063(),
                dto.getSieveDropperSubsample(),
                lessThan2Percentage));

        resultDto.setX_063_0315(calculateFrom2To01(dto.getMass_X_063_0315(),
                dto.getSieveDropperSubsample(),
                lessThan2Percentage));

        resultDto.setX_0315_016(calculateFrom2To01(dto.getMass_X_0315_016(),
                dto.getSieveDropperSubsample(),
                lessThan2Percentage));

        resultDto.setX_016_01(calculateFrom2To01(dto.getMass_X_016_01(),
                dto.getSieveDropperSubsample(),
                lessThan2Percentage));


//        .[Y01_005] = k9
//        .[Y005] = Delta

//        gam1 = .[gam1] //вес тигля с осадком
//        gam2 = .[gam2] //вес тигля
//        gam3 = gam1 - gam2 // не используется в методе
//        Delta = Round(gam3 * 1000 * A / g2 / 20, 1) //correction
        var gam5 = dto.getReading() * dto.getCorrection();
        resultDto.setCorrection(calculateCorrection(dto, lessThan2Percentage));


        //почему-то Delta и < 005 это одно и то же. В таблице два разных поля
        resultDto.setX_less_005(calculateCorrection(dto, lessThan2Percentage));
        resultDto.setX_01_005(calculateFrom01To005(dto, resultDto, lessThan2Percentage));

        Float uniformityCoefficient = calculateUniformityCoefficient(dto, resultDto);

        resultDto.setUniformityCoefficient(uniformityCoefficient == null ? 0 : uniformityCoefficient);
        resultDto.setUniformityDegree(calculateUniformityDegree(uniformityCoefficient));

        Float finenessModulus = calculateFinenessModulus(dto, resultDto);

        resultDto.setFinenessModulus(finenessModulus);

        resultDto.setSandGroupByFinenessModulus(defineSandGroupByFinenessModulus(finenessModulus));

        // оставить пустым
        // String lensesAndSeams;
    }
}
