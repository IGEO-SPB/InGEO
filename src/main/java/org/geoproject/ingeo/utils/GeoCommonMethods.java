package org.geoproject.ingeo.utils;

import org.geoproject.ingeo.dto.SurveyPointDto;
import org.geoproject.ingeo.dto.mainViewsDtos.BoreholeLayerDto;
import org.geoproject.ingeo.models.BoreholeLayer;
import javafx.collections.ObservableList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.geoproject.ingeo.models.SurveyPoint;

import java.util.Objects;

import static org.geoproject.ingeo.constants.LaborMethodConstants.FIRST_BOREHOLELAYER_NUMBER;
import static org.geoproject.ingeo.constants.LaborMethodConstants.ZERO_LAYERTOP_DEPTH;
import static org.geoproject.ingeo.constants.ServiceConstants.SINGLE_INDEX_POINT;
import static org.geoproject.ingeo.constants.ServiceConstants.ZERO_INDEX;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GeoCommonMethods {

    /**
     * Автоматический расчет глубин слоя
     *
     * @param boreholeLayerDto - DTO слоя в скважине
     * @param currentLayerBottomDepth - глубина подошвы текущего слоя
     */
    public static void setLayerDepths(BoreholeLayerDto boreholeLayerDto, Float currentLayerBottomDepth,
                                      ObservableList<BoreholeLayerDto> observableList, SurveyPointDto currentSurveyPointDto) {

        boreholeLayerDto.setLayerBottomDepth(currentLayerBottomDepth);

        var layerIndex = observableList.indexOf(boreholeLayerDto);

        boreholeLayerDto.setLayerTopDepth(layerIndex <= ZERO_INDEX ? ZERO_LAYERTOP_DEPTH :
                        observableList.get(layerIndex - SINGLE_INDEX_POINT).getLayerBottomDepth());

        var absoluteTopMark = Objects.isNull(currentSurveyPointDto.getAbsoluteMark()) ||
                Objects.isNull(boreholeLayerDto.getLayerTopDepth()) ?
                null :
                currentSurveyPointDto.getAbsoluteMark() - boreholeLayerDto.getLayerTopDepth();

        boreholeLayerDto.setAbsoluteTopMark(absoluteTopMark);

        var layerPower = Objects.isNull(currentLayerBottomDepth) ||
                Objects.isNull(boreholeLayerDto.getLayerTopDepth()) ?
                null :
                currentLayerBottomDepth - boreholeLayerDto.getLayerTopDepth();

        boreholeLayerDto.setLayerPower(layerPower);

        var firstLayerTop = Objects.isNull(boreholeLayerDto.getLayerTopDepth()) ?
                null :
                boreholeLayerDto.getLayerTopDepth();

        boreholeLayerDto.setFirstLayerTop(firstLayerTop);

        var secondLayerBottom = Objects.isNull(currentLayerBottomDepth) ?
                null :
                currentLayerBottomDepth;

        boreholeLayerDto.setSecondLayerBottom(secondLayerBottom);

        System.out.println("currentLayerBottomDepth: " + boreholeLayerDto.getLayerBottomDepth());
        System.out.println("AbsoluteTopMark: " + boreholeLayerDto.getAbsoluteTopMark());
        System.out.println("LayerPower: " + boreholeLayerDto.getLayerPower());
        System.out.println("LayerTopDepth: " + boreholeLayerDto.getLayerTopDepth());
        System.out.println("FirstLayerTop: " + boreholeLayerDto.getFirstLayerTop());
        System.out.println("FirstLayerBottom: " + boreholeLayerDto.getFirstLayerBottom());
        System.out.println("SecondLayerBottom: " + boreholeLayerDto.getSecondLayerBottom());
    }
}
