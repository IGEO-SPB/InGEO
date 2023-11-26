package org.geoproject.ingeo.labor;

import com.geoproject.igeo.dto.OrganicMatterDTO;
import com.geoproject.igeo.utils.CommonOperations;

public class OrganicMatterContentDeterminationMethod {

    private static float calculateIgnitionLossMass(float emptyPotMass, float absolutelyDrySoilPotMass, float calcinedSoilPotMass) {

        float result_calcinedMass = calcinedSoilPotMass - emptyPotMass;

        if (result_calcinedMass == 0) {
            System.out.println("Происходит деление на 0, проверьте введенные данные");
            result_calcinedMass = 1;
        }

        float ignitionLossMass = (absolutelyDrySoilPotMass - calcinedSoilPotMass) / result_calcinedMass;

        if (ignitionLossMass < 0) {
//            todo модальное окно, log
            System.out.println("Отрицательные значения недопустимы");
            return 1;
        }

        return CommonOperations.round(ignitionLossMass, 3);
    }

    public static float calculateP250(OrganicMatterDTO organicMatterDTO) {

        float dryMatterContentBefore = organicMatterDTO.getDryMatterContentBefore();
        float dryMatterContentAfter = organicMatterDTO.getDryMatterContentAfter();

        if (dryMatterContentBefore == 0) {
            System.out.println("Происходит деление на 0, проверьте введенные данные");
            dryMatterContentBefore = 1;
        }

        float p250 = ((dryMatterContentBefore - dryMatterContentAfter) / dryMatterContentBefore) * 100;

        if (p250 < 0) {
//            todo модальное окно, log
            System.out.println("Отрицательные значения недопустимы");
            return 1;
        }

        if (p250 < 10 || p250 > 90) {
            System.out.println("Введены некорректные данные по доле сухого вещества");
//            todo throw new P250OutOfBoundException();
            return 0;
        }
        return CommonOperations.round(p250, 2);
    }

//    private static float calculateDecompositionDegree(OrganicMatterDTO organicMatterDTO) {
//        float p250 = calculateP250(organicMatterDTO);
//
//
////todo разобрать метод
////        If Me!Torf_z < 0 Or IsNull(Me!Torf_z) Or Me!Torf_z > 9 Then GoTo e1
////        If Me!P250 < 10 Or IsNull(Me!P250) Or Me!P250 > 90 Then GoTo e1
//        return 0;
//    }

    public static void calculateOrganicMatter(OrganicMatterDTO organicMatterDTO) {

        float ignitionLossMassFirstMeasurement = calculateIgnitionLossMass(
                organicMatterDTO.getEmptyPotMassFirstMeasurement(),
                organicMatterDTO.getAbsolutelyDrySoilPotMassFirstMeasurement(),
                organicMatterDTO.getCalcinedSoilPotMassFirstMeasurement());
        float ignitionLossMassSecondMeasurement = calculateIgnitionLossMass(
                organicMatterDTO.getEmptyPotMassSecondMeasurement(),
                organicMatterDTO.getAbsolutelyDrySoilPotMassSecondMeasurement(),
                organicMatterDTO.getCalcinedSoilPotMassSecondMeasurement());
        float ignitionLossAverageMass = CommonOperations.round((ignitionLossMassFirstMeasurement + ignitionLossMassSecondMeasurement) / 2, 3);
        float p250 = calculateP250(organicMatterDTO);
//        float decompositionDegree = calculateDecompositionDegree(organicMatterDTO);

        organicMatterDTO.setIgnitionLossMassFirstMeasurement(ignitionLossMassFirstMeasurement);
        organicMatterDTO.setIgnitionLossMassSecondMeasurement(ignitionLossMassSecondMeasurement);
        organicMatterDTO.setIgnitionLossAverageMass(ignitionLossAverageMass);
        organicMatterDTO.setP250(p250);
//        organicMatterDTO.setDecompositionDegree(decompositionDegree);

    }
}
