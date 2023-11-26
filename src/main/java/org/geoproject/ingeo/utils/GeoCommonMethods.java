package org.geoproject.ingeo.utils;

import org.geoproject.ingeo.models.BoreholeLayer;
import javafx.collections.ObservableList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.geoproject.ingeo.constants.LaborMethodConstants.FIRST_BOREHOLELAYER_NUMBER;
import static org.geoproject.ingeo.constants.LaborMethodConstants.ZERO_LAYERTOP_DEPTH;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GeoCommonMethods {

    /**
     * автоматический расчет глубин слоя
     *
     * @param boreholeLayer - слой в скважине
     * @param currentLayerBottomDepth - глубина подошвы текущего слоя
     */
    public static void setLayerDepths(BoreholeLayer boreholeLayer, Float currentLayerBottomDepth, ObservableList<BoreholeLayer> objectListForView) {
        boreholeLayer.setLayerBottomDepth(currentLayerBottomDepth);

        int layerNumber = boreholeLayer.getNumber();

        boreholeLayer.setLayerTopDepth(boreholeLayer.getNumber() == FIRST_BOREHOLELAYER_NUMBER ? ZERO_LAYERTOP_DEPTH :
                objectListForView.stream()
                        .filter(layer -> layer.getNumber() == layerNumber - 1)
                        .findFirst().get()
                        .getLayerBottomDepth());

        boreholeLayer.setAbsoluteTopMark(boreholeLayer.getSurveyPoint().getAbsoluteMark() - boreholeLayer.getLayerTopDepth());
        boreholeLayer.setLayerPower(currentLayerBottomDepth - boreholeLayer.getLayerTopDepth());

        boreholeLayer.setFirstLayerTop(boreholeLayer.getLayerTopDepth());
        boreholeLayer.setSecondLayerBottom(currentLayerBottomDepth);
    }
}
