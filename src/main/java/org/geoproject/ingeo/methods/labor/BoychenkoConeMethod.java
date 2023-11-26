package org.geoproject.ingeo.methods.labor;

import org.geoproject.ingeo.dto.methodDtos.BoychenkoConeDTO;
import org.geoproject.ingeo.utils.CommonOperations;

public class BoychenkoConeMethod {

    private static float calculateBoychenkoConeImmersionDepthAverage(float boychenkoConeImmersionDepthFirstMeasur,
                                                                     float boychenkoConeImmersionDepthSecondMeasur,
                                                                     float boychenkoConeImmersionDepthThirdMeasur) {

        float boychenkoConeImmersionDepthAverage = (boychenkoConeImmersionDepthFirstMeasur
                + boychenkoConeImmersionDepthSecondMeasur
                + boychenkoConeImmersionDepthThirdMeasur) / 3;

        if (boychenkoConeImmersionDepthAverage < 0) {
            System.out.println("Недопустимы отрицательные значения");
            return 0;
        }

        return CommonOperations.round(boychenkoConeImmersionDepthAverage, 3);
    }


    public static BoychenkoConeDTO calculateBoychenkoConeImmersionDepths(BoychenkoConeDTO boychenkoConeDTO) {

        float undisturbedStrBoychenkoConeImmersionDepthAverage = CommonOperations.round(calculateBoychenkoConeImmersionDepthAverage(
                boychenkoConeDTO.getUndisturbedStrImmersionDepthFirstMeasur(),
                boychenkoConeDTO.getUndisturbedStrImmersionDepthSecondMeasur(),
                boychenkoConeDTO.getUndisturbedStrImmersionDepthThirdMeasur()
        ), 3);
        float brokenStrBoychenkoConeImmersionDepthAverage = CommonOperations.round(calculateBoychenkoConeImmersionDepthAverage(
                boychenkoConeDTO.getBrokenStrImmersionDepthFirstMeasur(),
                boychenkoConeDTO.getBrokenStrImmersionDepthSecondMeasur(),
                boychenkoConeDTO.getBrokenStrImmersionDepthThirdMeasur()
        ), 3);

        boychenkoConeDTO.setUndisturbedStrImmersionDepthAverage(undisturbedStrBoychenkoConeImmersionDepthAverage);
        boychenkoConeDTO.setBrokenStrImmersionDepthAverage(brokenStrBoychenkoConeImmersionDepthAverage);

        return boychenkoConeDTO;
    }


}
