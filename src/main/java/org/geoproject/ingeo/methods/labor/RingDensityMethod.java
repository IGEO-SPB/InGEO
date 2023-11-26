package org.geoproject.ingeo.labor;

import com.geoproject.igeo.dto.RingDensityDTO;
import com.geoproject.igeo.utils.CommonOperations;

public class RingDensityMethod {
    private static float calculateRingDensity(float ringWithWetSoilMass, float ringEmptyMass, float ringVolume) {
        if (ringVolume == 0) {
            ringVolume = 1;
        }

        float ringDensity;

        try {
            ringDensity = (ringWithWetSoilMass - ringEmptyMass) / ringVolume;
        } catch (ArithmeticException | NullPointerException exception) {
            exception.printStackTrace();
            return 0;
        }

        if (ringDensity < 0) {
//            throw new LessThanZeroException();
            System.out.println("Отрицательное значение!!!");
            return 0;
        }

        return CommonOperations.round(ringDensity, 3);
    }


    private static float calculateRingDensityAverage(float ringDensityFirstMeasurement, float ringDensitySecondMeasurement) {
        float ringDensityAverage;

        try {
            ringDensityAverage = (ringDensityFirstMeasurement + ringDensitySecondMeasurement) / 2;
        } catch (ArithmeticException | NullPointerException exception) {
            exception.printStackTrace();
            return 0;
        }

        if (ringDensityAverage < 0) {
//            throw new LessThanZeroException();
            System.out.println("Отрицательное значение!!!");
            return 0;
        }

        return CommonOperations.round(ringDensityAverage, 3);
    }


    private static float calculateRingDrySoilDensity(float ringParticleDensity, float naturalAverageWaterContent) {
        float ringDrySoilDensity;

        System.out.println("RING!!!");
        System.out.println(ringParticleDensity);
        System.out.println(naturalAverageWaterContent);

        try {
            ringDrySoilDensity = ringParticleDensity / (1 + naturalAverageWaterContent);
        } catch (ArithmeticException | NullPointerException exception) {
            exception.printStackTrace();
            return 0;
        }

        if (ringDrySoilDensity < 0) {
//            throw new LessThanZeroException();
            System.out.println("Отрицательное значение!!!");
            return 0;
        }

        return CommonOperations.round(ringDrySoilDensity, 3);
    }

    private static float calculateVoidRatio(float ringParticleDensity, float ringDrySoilDensity) {
        float voidRatio;
        System.out.println("Void ratio init");
        System.out.println(ringParticleDensity);
        System.out.println(ringDrySoilDensity);


        if (ringDrySoilDensity == 0) {
            ringDrySoilDensity = 1;
        }

        try {
            System.out.println("CHECK");
            System.out.println(ringParticleDensity);
            System.out.println(ringDrySoilDensity);
            voidRatio = (ringParticleDensity - ringDrySoilDensity) / ringDrySoilDensity;
        } catch (ArithmeticException | NullPointerException exception) {
            exception.printStackTrace();
            return 1;
        }

        if (voidRatio < 0) {
//            throw new LessThanZeroException();
            System.out.println("Отрицательное значение!!!");
            return 1;
        }

        System.out.println("voidRatio:");
        System.out.println(voidRatio);
        return CommonOperations.round(voidRatio, 3);
    }


    private static float calculateFullWaterContent(float voidRatio, float ringParticleDensity) {
        float fullWaterContent;
        System.out.println("CHECK2");
        System.out.println(voidRatio);
        System.out.println(ringParticleDensity);

        if (ringParticleDensity == 0) {
            ringParticleDensity = 1;
        }

        try {
            fullWaterContent = voidRatio / ringParticleDensity;
        } catch (ArithmeticException | NullPointerException exception) {
            exception.printStackTrace();
            return 1;
        }

        return CommonOperations.round(fullWaterContent, 3);
    }


    private static float calculateSaturationRatio(float naturalAverageWaterContent, float fullWaterContent) {
        float saturationRatio;

        if (fullWaterContent == 0) {
            fullWaterContent = 1;
        }

        try {
            saturationRatio = naturalAverageWaterContent / fullWaterContent;
        } catch (ArithmeticException | NullPointerException | NumberFormatException exception) {
            exception.printStackTrace();
            return 0;
        }

        return CommonOperations.round(saturationRatio, 3);
    }


    public static void calculateRingDensityAndIndexes(RingDensityDTO ringDensityDTO, float naturalAverageWaterContent,
                                                      float averageDensity) {

        float ringDensityFirstMeasurement = calculateRingDensity(ringDensityDTO.getRingWithWetSoilMassFirstMeasurement(),
                ringDensityDTO.getEmptyRingMassFirstMeasurement(), ringDensityDTO.getRingVolumeFirstMeasurement());

        float ringDensitySecondMeasurement = calculateRingDensity(ringDensityDTO.getRingWithWetSoilMassSecondMeasurement(),
                ringDensityDTO.getEmptyRingMassSecondMeasurement(), ringDensityDTO.getRingVolumeSecondMeasurement());

        float ringDensityAverage = calculateRingDensityAverage(ringDensityFirstMeasurement, ringDensitySecondMeasurement);
        float ringDrySoilDensity = calculateRingDrySoilDensity(ringDensityDTO.getRingDensityAverage(), naturalAverageWaterContent);
        float voidRatio = calculateVoidRatio(averageDensity, ringDrySoilDensity);
        float fullWaterContent = calculateFullWaterContent(voidRatio, averageDensity);
        float saturationRatio = calculateSaturationRatio(naturalAverageWaterContent, fullWaterContent);

        ringDensityDTO.setRingDensityFirstMeasurement(ringDensityFirstMeasurement);
        ringDensityDTO.setRingDensitySecondMeasurement(ringDensitySecondMeasurement);
        ringDensityDTO.setRingDensityAverage(ringDensityAverage);
        ringDensityDTO.setRingDrySoilDensity(ringDrySoilDensity);
        ringDensityDTO.setVoidRatio(voidRatio);
        ringDensityDTO.setFullWaterContent(fullWaterContent);
        ringDensityDTO.setSaturationRatio(saturationRatio);
    }

}

