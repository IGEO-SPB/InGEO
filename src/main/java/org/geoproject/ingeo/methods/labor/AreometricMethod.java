package org.geoproject.ingeo.labor;

import com.geoproject.igeo.dto.AreometryDTO;
import com.geoproject.igeo.dto.GranCompositionDTO;
import com.geoproject.igeo.enums.laborenums.AreometricParticleSizeEnum;
import com.geoproject.igeo.utils.CommonOperations;

import java.util.Arrays;

public class AreometricMethod {
//    AreometricDTO dto = new AreometricDTO();

    //служебные переменные
    private static float zzs1;
    private static float zzs2;
    private static float zzs3;
    private static float zzs4;

    private static float dm1;
    private static float dm2;
    private static float dm3;
    private static float dm4;

    private static float gg3;

    private static float waterContent;
    private static float k;
    private static float rop;
    private static float gs2;


    public static void setTempVariables(AreometryDTO dto, GranCompositionDTO granCompositionDTO) {
        zzs1 = dto.getTotalReadingForParticleSizeBetween005and001() + dto.getFirstAmendment();
        zzs2 = dto.getTotalReadingForParticleSizeBetween001and0002() + dto.getSecondAmendment();
        zzs3 = dto.getTotalReadingForParticleSizeNumberThree() + dto.getThirdAmendment();
        zzs4 = dto.getTotalReadingForParticleSizeBetweenLess0002() + dto.getFourthAmendment();

        if (dto.getTotalReadingForParticleSizeNumberThree() != 0 && dto.getThirdAmendment() != 0) { //'10-5-2- 0.001-0.002
            dm4 = zzs4;
            dm3 = zzs3 - zzs4;
            dm2 = zzs2 - zzs3;
            dm1 = zzs1 - zzs2;
        } else {
            dm4 = zzs4;
            dm3 = 0;
            dm2 = zzs2 - zzs4;
            dm1 = zzs1 - zzs2;
        }

        gg3 = dto.getTotalSubsample() / (1 + dto.getUndisturbedSampleWaterContent());

        if (dto.getWeighingBottleWithDrySoilMass() != dto.getEmptyWeighingBottleMass()) {
            waterContent = (dto.getWeighingBottleWithWetSoilMass() - dto.getWeighingBottleWithDrySoilMass())
                    / (dto.getWeighingBottleWithDrySoilMass() - dto.getEmptyWeighingBottleMass());
        } else {
            waterContent = 0F;
        }

        gs2 = dto.getSubsampleWetSoil() / (1 + waterContent);
    }

    public static void clearTempVariables() {
        zzs1 = 0F;
        zzs2 = 0F;
        zzs3 = 0F;
        zzs4 = 0F;

        dm1 = 0F;
        dm2 = 0F;
        dm4 = 0F;
        dm3 = 0F;

        gg3 = 0F;

        k = 0F;
        rop = 0F;
        gs2 = 0F;
    }

    private static Float calculateXMore2(Float totalSubsample, Float particleMass) {
        Float X;

        if (totalSubsample != 0) {
            X = particleMass / gg3 * 100;
        } else {
            X = 0F;
        }

        return CommonOperations.round(X, 2);
//        return X;
    }

    private static Float calculateXBetween2And01(float totalSubsample, float subsampleWetSoil, float particleMass, float percentageMore2) {
        float X;
        float a;

        if (totalSubsample != 0) {
            a = (100 - percentageMore2) / 100;
        } else {
            a = 1F;
        }

        k = (1 + waterContent) * a;

        if (subsampleWetSoil != 0) {
            X = particleMass / subsampleWetSoil * k * 100;
        } else {
            X = 0F;
        }

        return CommonOperations.round(X, 2);
//        return X;
    }

    private static Float calculateX_01_005(AreometryDTO dto, GranCompositionDTO granCompositionDTO) {
        Float X;
        //b1
        X = 100 - granCompositionDTO.getX_more_10() - granCompositionDTO.getX_10_5_old_10_2() - granCompositionDTO.getX_5_2() - granCompositionDTO.getX_2_1()
                - granCompositionDTO.getX_1_05() - granCompositionDTO.getX_05_025() - granCompositionDTO.getX_025_01() - granCompositionDTO.getX_001_0002_old_001_0005()
                - granCompositionDTO.getX_005_001() - granCompositionDTO.getX_0005_0002() - granCompositionDTO.getX_less_0002();


        if (X == 100) {
            X = 0F;
        }

        return CommonOperations.round(X, 2);
//        return X;
    }

    private static Float calculateXLess005(float particleDensity, float percentageMore2, float dm) {
        Float X;


        if (gs2 != 0) {
            rop = particleDensity * (100 - percentageMore2) / (particleDensity - 1) / gs2;
        }

        X = dm * rop;

        return CommonOperations.round(X, 2);
//        return X;
    }

    private static Float calculateSum_2_005(AreometryDTO dto, GranCompositionDTO granCompositionDTO) {
        Float X = granCompositionDTO.getX_2_1() + granCompositionDTO.getX_1_05() + granCompositionDTO.getX_05_025() + granCompositionDTO.getX_025_01() + granCompositionDTO.getX_01_005();

        return X;
    }

    private static Float calculateSum_005_0002_old_005_0005(AreometryDTO dto, GranCompositionDTO granCompositionDTO) {
        Float X = granCompositionDTO.getX_005_001() + granCompositionDTO.getX_001_0002_old_001_0005() + granCompositionDTO.getX_0005_0002();

        return X;
    }

    private static String defineSoilKind(AreometryDTO dto, GranCompositionDTO granCompositionDTO) {
        String soilKind = null;

        if (granCompositionDTO.getX_more_10() > 50) {
            soilKind = "галеч";
        } else if ((granCompositionDTO.getX_more_10() + granCompositionDTO.getX_10_5_old_10_2() + granCompositionDTO.getX_5_2()) > 50) {
            soilKind = "гравий";
        } else if ((granCompositionDTO.getX_more_10() + granCompositionDTO.getX_10_5_old_10_2() + granCompositionDTO.getX_5_2()) > 25) {
            soilKind = "гравел";
        } else if ((granCompositionDTO.getX_more_10() + granCompositionDTO.getX_10_5_old_10_2() + granCompositionDTO.getX_5_2() + granCompositionDTO.getX_2_1() + granCompositionDTO.getX_1_05()) > 50) {
            soilKind = "крупн";
        } else if ((granCompositionDTO.getX_more_10() + granCompositionDTO.getX_10_5_old_10_2() + granCompositionDTO.getX_5_2() + granCompositionDTO.getX_2_1() + granCompositionDTO.getX_1_05() + granCompositionDTO.getX_05_025()) > 50) {
            soilKind = "ср.крупн";
        } else if ((granCompositionDTO.getX_more_10() + granCompositionDTO.getX_10_5_old_10_2() + granCompositionDTO.getX_5_2() + granCompositionDTO.getX_2_1() + granCompositionDTO.getX_1_05() + granCompositionDTO.getX_05_025() + granCompositionDTO.getX_025_01()) >= 75) {
            soilKind = "мелк";
        } else if ((granCompositionDTO.getX_more_10() + granCompositionDTO.getX_10_5_old_10_2() + granCompositionDTO.getX_5_2() + granCompositionDTO.getX_2_1() + granCompositionDTO.getX_1_05() + granCompositionDTO.getX_05_025() + granCompositionDTO.getX_025_01()) < 75) {
            soilKind = "пылев";
        } else if (
                (granCompositionDTO.getX_more_10() + granCompositionDTO.getX_10_5_old_10_2() + granCompositionDTO.getX_5_2() + granCompositionDTO.getX_2_1() + granCompositionDTO.getX_1_05()
                        + granCompositionDTO.getX_05_025() + granCompositionDTO.getX_025_01() + granCompositionDTO.getX_01_005() + granCompositionDTO.getX_005_001()
                        + granCompositionDTO.getX_001_0002_old_001_0005() + granCompositionDTO.getX_0005_0002() + granCompositionDTO.getX_less_0002()) == 0
                        ||
                        granCompositionDTO.getX_01_005() == 100
        ) {
            soilKind = "";
            //fixme видимо здесь выставляется флаг flag = false;
        }

        return soilKind;
    }

    private static Float calculateUniformityCoefficient(AreometryDTO dto, GranCompositionDTO granCompositionDTO, boolean flag) {
//fixme в коде ВА в метод передается флаг. Зачем? Если флаг = 0, то значение uniformity проставляется как пустое


        Float uniformityCoefficient = 0F;

        Float d10 = null;
        Float d60 = null;


        if (!flag) {
            uniformityCoefficient = 0F;
            return uniformityCoefficient;
        }

        float[] calculatedValuesArray = {
                granCompositionDTO.getX_more_10(), granCompositionDTO.getX_10_5_old_10_2(), granCompositionDTO.getX_5_2(),
                granCompositionDTO.getX_2_1(), granCompositionDTO.getX_1_05(), granCompositionDTO.getX_05_025(),
                granCompositionDTO.getX_025_01(), granCompositionDTO.getX_01_005(), granCompositionDTO.getX_005_001(),
                granCompositionDTO.getX_001_0002_old_001_0005(), granCompositionDTO.getX_0005_0002(), granCompositionDTO.getX_less_0002()};
        //X_10_5_old_10_2 - для 10-5-2
        //X_5_2 - X_2_1 для 10-2    X_5_2 - это содержание 5-2

        for (Float value : calculatedValuesArray) {
            if (value == null) {
                value = 0F;
            }
        }

        float p1;
        float p2 = 0;


        for (int i = 12; i > 0; i--) {
            p2 = p2 + calculatedValuesArray[i - 1];
            float d1;
            float d2;

            if (p2 >= 10) {
                p1 = p2 - calculatedValuesArray[i - 1];
                d1 = AreometricParticleSizeEnum.getById(i + 1).getSize();
                d2 = AreometricParticleSizeEnum.getById(i).getSize();

                if (p2 == p1) {
                    return null;
                }

                d10 = d1 + (10 - p1) * (d2 - d1) / (p2 - p1);
                break;
            }
        }

        p2 = 0;


        for (int i = 12; i > 0; i--) { // i = 11 для 10-2
            p2 = p2 + calculatedValuesArray[i - 1];
            float d1;
            float d2;

            if (p2 >= 60) {
                p1 = p2 - calculatedValuesArray[i - 1];
                d1 = AreometricParticleSizeEnum.getById(i + 1).getSize();
                d2 = AreometricParticleSizeEnum.getById(i).getSize();

                d60 = d1 + (60 - p1) * (d2 - d1) / (p2 - p1);
                if (d10 == 0) {
                    return null;
                }
                break;
            }

        }


        uniformityCoefficient = d60 / d10;


        return uniformityCoefficient;
    }

    private static void defineUniformityDegree(AreometryDTO dto, GranCompositionDTO granCompositionDTO, boolean flag) {
        Float uniformityCoefficient = 0F;
        String uniformityDegree = "";

        if (flag) {
            uniformityCoefficient = calculateUniformityCoefficient(dto, granCompositionDTO, flag);

            if (uniformityCoefficient > 3) {
                uniformityDegree = "Неодн";
            } else if (uniformityCoefficient > 0 && uniformityCoefficient <= 3) {
                uniformityDegree = "Одн";
            }
        }

        granCompositionDTO.setUniformityCoefficient(uniformityCoefficient);
        granCompositionDTO.setUniformityDegree(uniformityDegree);
    }

    private static Float calculateDispersityIndex(AreometryDTO dto, GranCompositionDTO granCompositionDTO, boolean flag) {
        //todo что за флаг?
        //флаг - к песку (если песок - true)
        //если не песок, т.е. влаг - false, то присваиваем пустые значения и выходим из метода
        Float dispersityIndex = 0F;


        Float[] calculatedValuesArray = {
                null, null,
                granCompositionDTO.getX_2_1(), granCompositionDTO.getX_1_05(), granCompositionDTO.getX_05_025(),
                granCompositionDTO.getX_025_01(), granCompositionDTO.getX_01_005(), granCompositionDTO.getX_005_001(),
                granCompositionDTO.getX_001_0002_old_001_0005() + granCompositionDTO.getX_0005_0002(),
                granCompositionDTO.getX_less_0002()};

        System.out.println(Arrays.toString(calculatedValuesArray));
        System.out.println("Arrays.toString(calculatedValuesArray):");

//        'z(1) = ![z1]
//        'z(2) = ![z2]
//        'z(3) = ![z10_5] 'для 10-5-2
//        z(3) = Nz(![z3]) '4  2-1
//        z(4) = Nz(![z4]) '5   1-0.5
//        z(5) = Nz(![z5]) '6   0.5-0.25
//        z(6) = Nz(![z6]) '    0.25-0.1
//        z(7) = Nz(![b1]) '2     0.1-0.05
//        z(8) = Nz(![b2]) '3     0.05-0.01
//        z(9) = Nz(![b3]) + Nz(![b4]) '4   0.01-0.002
//        z(10) = Nz(![b5]) '5  <0.002


        Float voidRatio = null;

        System.out.println("!!!!!!!!!!!!!!!!!dto.getVoidRatio():");
        System.out.println(dto.getVoidRatio());

        System.out.println("dto.getSoilKind()");
        System.out.println(granCompositionDTO.getSoilKind());

        if (dto.getVoidRatio() != 0) {
            voidRatio = dto.getVoidRatio();
        } else {
            //TODO: сравнить строки
            switch (granCompositionDTO.getSoilKind()) {
                case ("гравий"):
                case ("галеч"):
                    break;
                case ("гравел"):
                case ("крупн"):
                case ("ср.крупн"):
                    voidRatio = 0.65F;
                    break;
                case ("мелк"):
                case ("пылев"):
                    voidRatio = 0.7F;
                    break;
            }
        }

        System.out.println("voidRatio:");
        System.out.println(voidRatio);


// Оператор Exit Sub приводит к тому, что VBA немедленно прекращает выполнение кода процедуры.
// После выполнения этого оператора VBA прекращает выполнение текущей процедуры и возвращается к выполнению той
// процедуры или функции, которая вызвала процедуру, содержащую оператор Exit Sub.


        Float[] dd = {10F, 5F, 2F, 1F, 0.5F, 0.25F, 0.1F, 0.05F, 0.01F, 0.002F, CommonOperations.round((0.002F / 1.4F) / 1.4F, 3)}; // для 10-5-2
        // выражение в скобках округлить до 5 знаков после запятой
        Float spd = 0F;

        Float diam;
        for (int i = 3; i < dd.length; i++) {
            System.out.println("dd[i]");
            System.out.println(dd[i]);
            diam = ((dd[i] * 1.4F) / 10);  // см
            System.out.println("diam:");
            System.out.println(diam);
            System.out.println("calculatedValuesArray[i - 1]:");
            System.out.println(calculatedValuesArray[i - 1]);
            System.out.println("spd before summing:");
            System.out.println((calculatedValuesArray[i - 1] / 100) / diam);
            spd = spd + (calculatedValuesArray[i - 1] / 100) / diam;
            System.out.println("spd:");
            System.out.println(spd);
        }


        if (spd == 0) {
            return 0F;
        }

        Float avd = (1 / spd); //0.0029618060876008645
//        Double avd = 0.0028;
//        Double avd = 0.002989997328;

        System.out.println("avd:");
        System.out.println(avd);

        if (voidRatio != null) {

            Float temp = (float) Math.pow(10F, (-4));

            dispersityIndex = 1.85F * temp / (avd * avd * voidRatio);

            System.out.println("!!!dispersityIndex!!!");
            System.out.println(dispersityIndex);
        }

        return dispersityIndex;
    }


    public static String defineHeavingDegree(AreometryDTO dto, GranCompositionDTO granCompositionDTO, boolean flag) {

        Float dispersityIndex = calculateDispersityIndex(dto, granCompositionDTO, flag);
        String heavingDegree = "";

        if (!flag) {
            dispersityIndex = 0F;
            heavingDegree = "";
        } else {
            if (dispersityIndex < 1) {
                heavingDegree = "непуч";
            } else if (dispersityIndex >= 5) {
                heavingDegree = "пучин";
            } else if (dispersityIndex >= 1 && dispersityIndex < 5) {
                heavingDegree = "сл.пуч";
            }
        }

        granCompositionDTO.setDispersityIndex(dispersityIndex);

        return heavingDegree;
    }

    public static void calculateGranCompositionAreometry(AreometryDTO areometryDTO,
                                                         GranCompositionDTO granCompositionDTO) {
        clearTempVariables();
        setTempVariables(areometryDTO, granCompositionDTO);


        granCompositionDTO.setX_more_10(calculateXMore2(areometryDTO.getTotalSubsample(), areometryDTO.getParticleMassOver10mm()));
        granCompositionDTO.setX_10_5_old_10_2(calculateXMore2(areometryDTO.getTotalSubsample(), areometryDTO.getParticleMassBetween10and5mm()));
        granCompositionDTO.setX_5_2(calculateXMore2(areometryDTO.getTotalSubsample(), areometryDTO.getParticleMassBetween5and2mm()));

        float percentageMore2 = granCompositionDTO.getX_more_10() + granCompositionDTO.getX_10_5_old_10_2() + granCompositionDTO.getX_5_2();

        granCompositionDTO.setX_2_1(calculateXBetween2And01(areometryDTO.getSubsampleWetSoil(), areometryDTO.getParticleMassBetween2and1mm(),
                percentageMore2, areometryDTO.getTotalSubsample()));
        granCompositionDTO.setX_1_05(calculateXBetween2And01(areometryDTO.getSubsampleWetSoil(), areometryDTO.getParticleMassBetween1and05mm(),
                percentageMore2, areometryDTO.getTotalSubsample()));
        granCompositionDTO.setX_05_025(calculateXBetween2And01(areometryDTO.getSubsampleWetSoil(), areometryDTO.getParticleMassBetween05and025mm(),
                percentageMore2, areometryDTO.getTotalSubsample()));
        granCompositionDTO.setX_025_01(calculateXBetween2And01(areometryDTO.getSubsampleWetSoil(), areometryDTO.getParticleMassBetween025and01mm(),
                percentageMore2, areometryDTO.getTotalSubsample()));

        granCompositionDTO.setX_005_001(calculateXLess005(areometryDTO.getParticleDensity(), percentageMore2,
                dm1));
        granCompositionDTO.setX_001_0002_old_001_0005(calculateXLess005(areometryDTO.getParticleDensity(), percentageMore2,
                dm2));
        granCompositionDTO.setX_0005_0002(calculateXLess005(areometryDTO.getParticleDensity(), percentageMore2,
                dm3));
        granCompositionDTO.setX_less_0002(calculateXLess005(areometryDTO.getParticleDensity(), percentageMore2,
                dm4));

        granCompositionDTO.setX_01_005(calculateX_01_005(areometryDTO, granCompositionDTO));


        granCompositionDTO.setSum_2_005(calculateSum_2_005(areometryDTO, granCompositionDTO));
        granCompositionDTO.setSum_005_0002_old_005_0005(calculateSum_005_0002_old_005_0005(areometryDTO, granCompositionDTO));
        granCompositionDTO.setSum_less_0002_old_less_0005(granCompositionDTO.getX_less_0002());

        granCompositionDTO.setSoilKind(defineSoilKind(areometryDTO, granCompositionDTO)); //выставлен флаг
        //temp
        boolean flag = true;

        defineUniformityDegree(areometryDTO, granCompositionDTO, flag);
//        granCompositionDTO.setUniformityCoefficient();
//        granCompositionDTO.setUniformityDegree();

//        granCompositionDTO.setHCl();

//        granCompositionDTO.setDispersityIndex();
        granCompositionDTO.setHeavingDegree(defineHeavingDegree(areometryDTO, granCompositionDTO, flag));
    }

}
