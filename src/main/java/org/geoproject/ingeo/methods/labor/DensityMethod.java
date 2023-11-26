package org.geoproject.ingeo.labor;

import com.geoproject.igeo.dto.DensityDTO;
import com.geoproject.igeo.utils.CommonOperations;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class DensityMethod {
    public static float calculateDrySoilWeight(float weightWithDrySoil, float emptyPycnometerWeight) {
        if ((weightWithDrySoil - emptyPycnometerWeight) < 0) {
            System.out.println("Недопустимы отрицательные значения");
            return 0;
        }
        float drySoilWeight = weightWithDrySoil - emptyPycnometerWeight;

        return CommonOperations.round(drySoilWeight, 3);
    }

    public static float calculateSoilVolume(float weightWithDrySoil, float emptyPycnometerWeight,
                                            float weightWithWater, float weightWithSoilAndWater) {
        float drySoilWeight = calculateDrySoilWeight(weightWithDrySoil, emptyPycnometerWeight);
        float soilVolume = weightWithWater + drySoilWeight - weightWithSoilAndWater;

        if (soilVolume < 0) {
            System.out.println("Недопустимы отрицательные значения");
            return 0;
        }

        return CommonOperations.round(soilVolume, 3);
    }

    public static float[] calculateSpecificSoilWeight(float weightWithDrySoil, float emptyPycnometerWeight,
                                                      float weightWithWater, float weightWithSoilAndWater) {
        float[] resultArray = new float[3];
        float specificSoilWeight = 0;

        float drySoilWeight = calculateDrySoilWeight(weightWithDrySoil, emptyPycnometerWeight);
        float soilVolume = calculateSoilVolume(weightWithDrySoil, emptyPycnometerWeight, weightWithWater, weightWithSoilAndWater);

        if (soilVolume == 0) {
            soilVolume = 1;
        }

        System.out.println("drySoilWeight:");
        System.out.println(drySoilWeight);
        System.out.println("soilVolume:");
        System.out.println(soilVolume);
        try {
            specificSoilWeight = CommonOperations.round(drySoilWeight / soilVolume, 3);
        } catch (ArithmeticException e) {
            e.printStackTrace();
            System.out.println("Вероятно, деление на 0");
        }

        resultArray[0] = drySoilWeight;
        resultArray[1] = soilVolume;
        resultArray[2] = specificSoilWeight;

        return resultArray;
    }

    public static void calculateAverageDensity(DensityDTO densityDTO) {
        float[] firstMeasurementsArray = calculateSpecificSoilWeight(densityDTO.getPycnometerWeightWithDrySoilFirstMeasurement(),
                densityDTO.getEmptyPycnometerWeightFirstMeasurement(), densityDTO.getPycnometerWeightWithWaterFirstMeasurement(),
                densityDTO.getPycnometerWeightWithSoilAndWaterFirstMeasurement());
        float drySoilWeightFirstMeasure = firstMeasurementsArray[0];
        float soilVolumeFirstMeasure = firstMeasurementsArray[1];
        float specificSoilWeightFirstMeasure = firstMeasurementsArray[2];

        float[] secondMeasurementsArray = calculateSpecificSoilWeight(densityDTO.getPycnometerWeightWithDrySoilSecondMeasurement(),
                densityDTO.getEmptyPycnometerWeightSecondMeasurement(), densityDTO.getPycnometerWeightWithWaterSecondMeasurement(),
                densityDTO.getPycnometerWeightWithSoilAndWaterSecondMeasurement());

        float drySoilWeightSecondMeasure = secondMeasurementsArray[0];
        float soilVolumeSecondMeasure = secondMeasurementsArray[1];
        float specificSoilWeightSecondMeasure = secondMeasurementsArray[2];

        float averageDensity = CommonOperations.round((specificSoilWeightFirstMeasure + specificSoilWeightSecondMeasure) / 2, 3);

        densityDTO.setDrySoilWeightFirstMeasurement(drySoilWeightFirstMeasure);
        densityDTO.setDrySoilWeightSecondMeasurement(drySoilWeightSecondMeasure);

        densityDTO.setSoilVolumeFirstMeasurement(soilVolumeFirstMeasure);
        densityDTO.setSoilVolumeSecondMeasurement(soilVolumeSecondMeasure);

        densityDTO.setSoilDensityFirstMeasurement(specificSoilWeightFirstMeasure);
        densityDTO.setSoilDensitySecondMeasurement(specificSoilWeightSecondMeasure);
        densityDTO.setAverageDensity(averageDensity);
    }

}
