package org.geoproject.ingeo.dto.methodDtos;


import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class ShearWaterContentCutRingDto {
    private String laborNumber;
    private Integer shearPointNumber;






//По кнопке средние значения по нарушенной и ненарушенной структуре
// записываются в таблицу physical_properties, класс PhysicalProperties
    private FloatProperty undisturbedStrImmersionDepthFirstMeasur = new SimpleFloatProperty();
    private FloatProperty undisturbedStrImmersionDepthSecondMeasur = new SimpleFloatProperty();
    private FloatProperty undisturbedStrImmersionDepthThirdMeasur = new SimpleFloatProperty();
    private FloatProperty brokenStrImmersionDepthFirstMeasur = new SimpleFloatProperty();
    private FloatProperty brokenStrImmersionDepthSecondMeasur = new SimpleFloatProperty();
    private FloatProperty brokenStrImmersionDepthThirdMeasur = new SimpleFloatProperty();
    private FloatProperty undisturbedStrImmersionDepthAverage = new SimpleFloatProperty();
    private FloatProperty brokenStrImmersionDepthAverage = new SimpleFloatProperty();



}
