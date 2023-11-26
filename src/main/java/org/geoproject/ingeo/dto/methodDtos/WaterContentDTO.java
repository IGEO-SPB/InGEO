package org.geoproject.ingeo.dto.methodDtos;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WaterContentDTO {

    private String laborNumber;

    private StringProperty naturalWaterContentWeighingBottleNumberFirstMeasurement = new SimpleStringProperty();
    private StringProperty naturalWaterContentWeighingBottleNumberSecondMeasurement = new SimpleStringProperty();
    private StringProperty liquidityWeighingBottleNumberFirstMeasurement = new SimpleStringProperty();
    private StringProperty liquidityWeighingBottleNumberSecondMeasurement = new SimpleStringProperty();
    private StringProperty plasticWeighingBottleNumberFirstMeasurement = new SimpleStringProperty();
    private StringProperty plasticWeighingBottleNumberSecondMeasurement = new SimpleStringProperty();

    private FloatProperty naturalWaterContentEmptyWeighingBottleMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty naturalWaterContentEmptyWeighingBottleMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty liquidityEmptyWeighingBottleMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty liquidityEmptyWeighingBottleMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty plasticEmptyWeighingBottleMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty plasticEmptyWeighingBottleMassSecondMeasurement = new SimpleFloatProperty();

    private FloatProperty naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty liquidityWeighingBottleWithWetSoilMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty liquidityWeighingBottleWithWetSoilMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty plasticWeighingBottleWithWetSoilMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty plasticWeighingBottleWithWetSoilMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty liquidityWeighingBottleWithDrySoilMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty liquidityWeighingBottleWithDrySoilMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty plasticWeighingBottleWithDrySoilMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty plasticWeighingBottleWithDrySoilMassSecondMeasurement = new SimpleFloatProperty();

    private FloatProperty naturalWaterContentWeighingBottleWaterContentFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty naturalWaterContentWeighingBottleWaterContentSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty liquidityWeighingBottleWaterContentFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty liquidityWeighingBottleWaterContentSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty plasticWeighingBottleWaterContentFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty plasticWeighingBottleWaterContentSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty naturalAverageWaterContent = new SimpleFloatProperty();

    private FloatProperty averageLiquidLimit = new SimpleFloatProperty();
    private FloatProperty averagePlasticLimit = new SimpleFloatProperty();
    private FloatProperty plasticityIndex = new SimpleFloatProperty();

    private FloatProperty liquidityIndex = new SimpleFloatProperty();

    public String getLaborNumber() {
        return laborNumber;
    }

    public void setLaborNumber(String laborNumber) {
        this.laborNumber = laborNumber;
    }

    public String getNaturalWaterContentWeighingBottleNumberFirstMeasurement() {
        return naturalWaterContentWeighingBottleNumberFirstMeasurement.get();
    }

    public StringProperty naturalWaterContentWeighingBottleNumberFirstMeasurementProperty() {
        return naturalWaterContentWeighingBottleNumberFirstMeasurement;
    }

    public void setNaturalWaterContentWeighingBottleNumberFirstMeasurement(String naturalWaterContentWeighingBottleNumberFirstMeasurement) {
        this.naturalWaterContentWeighingBottleNumberFirstMeasurement.set(naturalWaterContentWeighingBottleNumberFirstMeasurement);
    }

    public String getNaturalWaterContentWeighingBottleNumberSecondMeasurement() {
        return naturalWaterContentWeighingBottleNumberSecondMeasurement.get();
    }

    public StringProperty naturalWaterContentWeighingBottleNumberSecondMeasurementProperty() {
        return naturalWaterContentWeighingBottleNumberSecondMeasurement;
    }

    public void setNaturalWaterContentWeighingBottleNumberSecondMeasurement(String naturalWaterContentWeighingBottleNumberSecondMeasurement) {
        this.naturalWaterContentWeighingBottleNumberSecondMeasurement.set(naturalWaterContentWeighingBottleNumberSecondMeasurement);
    }

    public String getLiquidityWeighingBottleNumberFirstMeasurement() {
        return liquidityWeighingBottleNumberFirstMeasurement.get();
    }

    public StringProperty liquidityWeighingBottleNumberFirstMeasurementProperty() {
        return liquidityWeighingBottleNumberFirstMeasurement;
    }

    public void setLiquidityWeighingBottleNumberFirstMeasurement(String liquidityWeighingBottleNumberFirstMeasurement) {
        this.liquidityWeighingBottleNumberFirstMeasurement.set(liquidityWeighingBottleNumberFirstMeasurement);
    }

    public String getLiquidityWeighingBottleNumberSecondMeasurement() {
        return liquidityWeighingBottleNumberSecondMeasurement.get();
    }

    public StringProperty liquidityWeighingBottleNumberSecondMeasurementProperty() {
        return liquidityWeighingBottleNumberSecondMeasurement;
    }

    public void setLiquidityWeighingBottleNumberSecondMeasurement(String liquidityWeighingBottleNumberSecondMeasurement) {
        this.liquidityWeighingBottleNumberSecondMeasurement.set(liquidityWeighingBottleNumberSecondMeasurement);
    }

    public String getPlasticWeighingBottleNumberFirstMeasurement() {
        return plasticWeighingBottleNumberFirstMeasurement.get();
    }

    public StringProperty plasticWeighingBottleNumberFirstMeasurementProperty() {
        return plasticWeighingBottleNumberFirstMeasurement;
    }

    public void setPlasticWeighingBottleNumberFirstMeasurement(String plasticWeighingBottleNumberFirstMeasurement) {
        this.plasticWeighingBottleNumberFirstMeasurement.set(plasticWeighingBottleNumberFirstMeasurement);
    }

    public String getPlasticWeighingBottleNumberSecondMeasurement() {
        return plasticWeighingBottleNumberSecondMeasurement.get();
    }

    public StringProperty plasticWeighingBottleNumberSecondMeasurementProperty() {
        return plasticWeighingBottleNumberSecondMeasurement;
    }

    public void setPlasticWeighingBottleNumberSecondMeasurement(String plasticWeighingBottleNumberSecondMeasurement) {
        this.plasticWeighingBottleNumberSecondMeasurement.set(plasticWeighingBottleNumberSecondMeasurement);
    }

    public float getNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement() {
        return naturalWaterContentEmptyWeighingBottleMassFirstMeasurement.get();
    }

    public FloatProperty naturalWaterContentEmptyWeighingBottleMassFirstMeasurementProperty() {
        return naturalWaterContentEmptyWeighingBottleMassFirstMeasurement;
    }

    public void setNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement(float naturalWaterContentEmptyWeighingBottleMassFirstMeasurement) {
        this.naturalWaterContentEmptyWeighingBottleMassFirstMeasurement.set(naturalWaterContentEmptyWeighingBottleMassFirstMeasurement);
    }

    public float getNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement() {
        return naturalWaterContentEmptyWeighingBottleMassSecondMeasurement.get();
    }

    public FloatProperty naturalWaterContentEmptyWeighingBottleMassSecondMeasurementProperty() {
        return naturalWaterContentEmptyWeighingBottleMassSecondMeasurement;
    }

    public void setNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement(float naturalWaterContentEmptyWeighingBottleMassSecondMeasurement) {
        this.naturalWaterContentEmptyWeighingBottleMassSecondMeasurement.set(naturalWaterContentEmptyWeighingBottleMassSecondMeasurement);
    }

    public float getLiquidityEmptyWeighingBottleMassFirstMeasurement() {
        return liquidityEmptyWeighingBottleMassFirstMeasurement.get();
    }

    public FloatProperty liquidityEmptyWeighingBottleMassFirstMeasurementProperty() {
        return liquidityEmptyWeighingBottleMassFirstMeasurement;
    }

    public void setLiquidityEmptyWeighingBottleMassFirstMeasurement(float liquidityEmptyWeighingBottleMassFirstMeasurement) {
        this.liquidityEmptyWeighingBottleMassFirstMeasurement.set(liquidityEmptyWeighingBottleMassFirstMeasurement);
    }

    public float getLiquidityEmptyWeighingBottleMassSecondMeasurement() {
        return liquidityEmptyWeighingBottleMassSecondMeasurement.get();
    }

    public FloatProperty liquidityEmptyWeighingBottleMassSecondMeasurementProperty() {
        return liquidityEmptyWeighingBottleMassSecondMeasurement;
    }

    public void setLiquidityEmptyWeighingBottleMassSecondMeasurement(float liquidityEmptyWeighingBottleMassSecondMeasurement) {
        this.liquidityEmptyWeighingBottleMassSecondMeasurement.set(liquidityEmptyWeighingBottleMassSecondMeasurement);
    }

    public float getPlasticEmptyWeighingBottleMassFirstMeasurement() {
        return plasticEmptyWeighingBottleMassFirstMeasurement.get();
    }

    public FloatProperty plasticEmptyWeighingBottleMassFirstMeasurementProperty() {
        return plasticEmptyWeighingBottleMassFirstMeasurement;
    }

    public void setPlasticEmptyWeighingBottleMassFirstMeasurement(float plasticEmptyWeighingBottleMassFirstMeasurement) {
        this.plasticEmptyWeighingBottleMassFirstMeasurement.set(plasticEmptyWeighingBottleMassFirstMeasurement);
    }

    public float getPlasticEmptyWeighingBottleMassSecondMeasurement() {
        return plasticEmptyWeighingBottleMassSecondMeasurement.get();
    }

    public FloatProperty plasticEmptyWeighingBottleMassSecondMeasurementProperty() {
        return plasticEmptyWeighingBottleMassSecondMeasurement;
    }

    public void setPlasticEmptyWeighingBottleMassSecondMeasurement(float plasticEmptyWeighingBottleMassSecondMeasurement) {
        this.plasticEmptyWeighingBottleMassSecondMeasurement.set(plasticEmptyWeighingBottleMassSecondMeasurement);
    }

    public float getNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement() {
        return naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement.get();
    }

    public FloatProperty naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurementProperty() {
        return naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement;
    }

    public void setNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement(float naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement) {
        this.naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement.set(naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement);
    }

    public float getNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement() {
        return naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement.get();
    }

    public FloatProperty naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurementProperty() {
        return naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement;
    }

    public void setNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement(float naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement) {
        this.naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement.set(naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement);
    }

    public float getLiquidityWeighingBottleWithWetSoilMassFirstMeasurement() {
        return liquidityWeighingBottleWithWetSoilMassFirstMeasurement.get();
    }

    public FloatProperty liquidityWeighingBottleWithWetSoilMassFirstMeasurementProperty() {
        return liquidityWeighingBottleWithWetSoilMassFirstMeasurement;
    }

    public void setLiquidityWeighingBottleWithWetSoilMassFirstMeasurement(float liquidityWeighingBottleWithWetSoilMassFirstMeasurement) {
        this.liquidityWeighingBottleWithWetSoilMassFirstMeasurement.set(liquidityWeighingBottleWithWetSoilMassFirstMeasurement);
    }

    public float getLiquidityWeighingBottleWithWetSoilMassSecondMeasurement() {
        return liquidityWeighingBottleWithWetSoilMassSecondMeasurement.get();
    }

    public FloatProperty liquidityWeighingBottleWithWetSoilMassSecondMeasurementProperty() {
        return liquidityWeighingBottleWithWetSoilMassSecondMeasurement;
    }

    public void setLiquidityWeighingBottleWithWetSoilMassSecondMeasurement(float liquidityWeighingBottleWithWetSoilMassSecondMeasurement) {
        this.liquidityWeighingBottleWithWetSoilMassSecondMeasurement.set(liquidityWeighingBottleWithWetSoilMassSecondMeasurement);
    }

    public float getPlasticWeighingBottleWithWetSoilMassFirstMeasurement() {
        return plasticWeighingBottleWithWetSoilMassFirstMeasurement.get();
    }

    public FloatProperty plasticWeighingBottleWithWetSoilMassFirstMeasurementProperty() {
        return plasticWeighingBottleWithWetSoilMassFirstMeasurement;
    }

    public void setPlasticWeighingBottleWithWetSoilMassFirstMeasurement(float plasticWeighingBottleWithWetSoilMassFirstMeasurement) {
        this.plasticWeighingBottleWithWetSoilMassFirstMeasurement.set(plasticWeighingBottleWithWetSoilMassFirstMeasurement);
    }

    public float getPlasticWeighingBottleWithWetSoilMassSecondMeasurement() {
        return plasticWeighingBottleWithWetSoilMassSecondMeasurement.get();
    }

    public FloatProperty plasticWeighingBottleWithWetSoilMassSecondMeasurementProperty() {
        return plasticWeighingBottleWithWetSoilMassSecondMeasurement;
    }

    public void setPlasticWeighingBottleWithWetSoilMassSecondMeasurement(float plasticWeighingBottleWithWetSoilMassSecondMeasurement) {
        this.plasticWeighingBottleWithWetSoilMassSecondMeasurement.set(plasticWeighingBottleWithWetSoilMassSecondMeasurement);
    }

    public float getNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement() {
        return naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement.get();
    }

    public FloatProperty naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurementProperty() {
        return naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement;
    }

    public void setNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement(float naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement) {
        this.naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement.set(naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement);
    }

    public float getNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement() {
        return naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement.get();
    }

    public FloatProperty naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurementProperty() {
        return naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement;
    }

    public void setNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement(float naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement) {
        this.naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement.set(naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement);
    }

    public float getLiquidityWeighingBottleWithDrySoilMassFirstMeasurement() {
        return liquidityWeighingBottleWithDrySoilMassFirstMeasurement.get();
    }

    public FloatProperty liquidityWeighingBottleWithDrySoilMassFirstMeasurementProperty() {
        return liquidityWeighingBottleWithDrySoilMassFirstMeasurement;
    }

    public void setLiquidityWeighingBottleWithDrySoilMassFirstMeasurement(float liquidityWeighingBottleWithDrySoilMassFirstMeasurement) {
        this.liquidityWeighingBottleWithDrySoilMassFirstMeasurement.set(liquidityWeighingBottleWithDrySoilMassFirstMeasurement);
    }

    public float getLiquidityWeighingBottleWithDrySoilMassSecondMeasurement() {
        return liquidityWeighingBottleWithDrySoilMassSecondMeasurement.get();
    }

    public FloatProperty liquidityWeighingBottleWithDrySoilMassSecondMeasurementProperty() {
        return liquidityWeighingBottleWithDrySoilMassSecondMeasurement;
    }

    public void setLiquidityWeighingBottleWithDrySoilMassSecondMeasurement(float liquidityWeighingBottleWithDrySoilMassSecondMeasurement) {
        this.liquidityWeighingBottleWithDrySoilMassSecondMeasurement.set(liquidityWeighingBottleWithDrySoilMassSecondMeasurement);
    }

    public float getPlasticWeighingBottleWithDrySoilMassFirstMeasurement() {
        return plasticWeighingBottleWithDrySoilMassFirstMeasurement.get();
    }

    public FloatProperty plasticWeighingBottleWithDrySoilMassFirstMeasurementProperty() {
        return plasticWeighingBottleWithDrySoilMassFirstMeasurement;
    }

    public void setPlasticWeighingBottleWithDrySoilMassFirstMeasurement(float plasticWeighingBottleWithDrySoilMassFirstMeasurement) {
        this.plasticWeighingBottleWithDrySoilMassFirstMeasurement.set(plasticWeighingBottleWithDrySoilMassFirstMeasurement);
    }

    public float getPlasticWeighingBottleWithDrySoilMassSecondMeasurement() {
        return plasticWeighingBottleWithDrySoilMassSecondMeasurement.get();
    }

    public FloatProperty plasticWeighingBottleWithDrySoilMassSecondMeasurementProperty() {
        return plasticWeighingBottleWithDrySoilMassSecondMeasurement;
    }

    public void setPlasticWeighingBottleWithDrySoilMassSecondMeasurement(float plasticWeighingBottleWithDrySoilMassSecondMeasurement) {
        this.plasticWeighingBottleWithDrySoilMassSecondMeasurement.set(plasticWeighingBottleWithDrySoilMassSecondMeasurement);
    }

    public float getNaturalWaterContentWeighingBottleWaterContentFirstMeasurement() {
        return naturalWaterContentWeighingBottleWaterContentFirstMeasurement.get();
    }

    public FloatProperty naturalWaterContentWeighingBottleWaterContentFirstMeasurementProperty() {
        return naturalWaterContentWeighingBottleWaterContentFirstMeasurement;
    }

    public void setNaturalWaterContentWeighingBottleWaterContentFirstMeasurement(float naturalWaterContentWeighingBottleWaterContentFirstMeasurement) {
        this.naturalWaterContentWeighingBottleWaterContentFirstMeasurement.set(naturalWaterContentWeighingBottleWaterContentFirstMeasurement);
    }

    public float getNaturalWaterContentWeighingBottleWaterContentSecondMeasurement() {
        return naturalWaterContentWeighingBottleWaterContentSecondMeasurement.get();
    }

    public FloatProperty naturalWaterContentWeighingBottleWaterContentSecondMeasurementProperty() {
        return naturalWaterContentWeighingBottleWaterContentSecondMeasurement;
    }

    public void setNaturalWaterContentWeighingBottleWaterContentSecondMeasurement(float naturalWaterContentWeighingBottleWaterContentSecondMeasurement) {
        this.naturalWaterContentWeighingBottleWaterContentSecondMeasurement.set(naturalWaterContentWeighingBottleWaterContentSecondMeasurement);
    }

    public float getLiquidityWeighingBottleWaterContentFirstMeasurement() {
        return liquidityWeighingBottleWaterContentFirstMeasurement.get();
    }

    public FloatProperty liquidityWeighingBottleWaterContentFirstMeasurementProperty() {
        return liquidityWeighingBottleWaterContentFirstMeasurement;
    }

    public void setLiquidityWeighingBottleWaterContentFirstMeasurement(float liquidityWeighingBottleWaterContentFirstMeasurement) {
        this.liquidityWeighingBottleWaterContentFirstMeasurement.set(liquidityWeighingBottleWaterContentFirstMeasurement);
    }

    public float getLiquidityWeighingBottleWaterContentSecondMeasurement() {
        return liquidityWeighingBottleWaterContentSecondMeasurement.get();
    }

    public FloatProperty liquidityWeighingBottleWaterContentSecondMeasurementProperty() {
        return liquidityWeighingBottleWaterContentSecondMeasurement;
    }

    public void setLiquidityWeighingBottleWaterContentSecondMeasurement(float liquidityWeighingBottleWaterContentSecondMeasurement) {
        this.liquidityWeighingBottleWaterContentSecondMeasurement.set(liquidityWeighingBottleWaterContentSecondMeasurement);
    }

    public float getPlasticWeighingBottleWaterContentFirstMeasurement() {
        return plasticWeighingBottleWaterContentFirstMeasurement.get();
    }

    public FloatProperty plasticWeighingBottleWaterContentFirstMeasurementProperty() {
        return plasticWeighingBottleWaterContentFirstMeasurement;
    }

    public void setPlasticWeighingBottleWaterContentFirstMeasurement(float plasticWeighingBottleWaterContentFirstMeasurement) {
        this.plasticWeighingBottleWaterContentFirstMeasurement.set(plasticWeighingBottleWaterContentFirstMeasurement);
    }

    public float getPlasticWeighingBottleWaterContentSecondMeasurement() {
        return plasticWeighingBottleWaterContentSecondMeasurement.get();
    }

    public FloatProperty plasticWeighingBottleWaterContentSecondMeasurementProperty() {
        return plasticWeighingBottleWaterContentSecondMeasurement;
    }

    public void setPlasticWeighingBottleWaterContentSecondMeasurement(float plasticWeighingBottleWaterContentSecondMeasurement) {
        this.plasticWeighingBottleWaterContentSecondMeasurement.set(plasticWeighingBottleWaterContentSecondMeasurement);
    }

    public float getNaturalAverageWaterContent() {
        return naturalAverageWaterContent.get();
    }

    public FloatProperty naturalAverageWaterContentProperty() {
        return naturalAverageWaterContent;
    }

    public void setNaturalAverageWaterContent(float naturalAverageWaterContent) {
        this.naturalAverageWaterContent.set(naturalAverageWaterContent);
    }

    public float getAverageLiquidLimit() {
        return averageLiquidLimit.get();
    }

    public FloatProperty averageLiquidLimitProperty() {
        return averageLiquidLimit;
    }

    public void setAverageLiquidLimit(float averageLiquidLimit) {
        this.averageLiquidLimit.set(averageLiquidLimit);
    }

    public float getAveragePlasticLimit() {
        return averagePlasticLimit.get();
    }

    public FloatProperty averagePlasticLimitProperty() {
        return averagePlasticLimit;
    }

    public void setAveragePlasticLimit(float averagePlasticLimit) {
        this.averagePlasticLimit.set(averagePlasticLimit);
    }

    public float getPlasticityIndex() {
        return plasticityIndex.get();
    }

    public FloatProperty plasticityIndexProperty() {
        return plasticityIndex;
    }

    public void setPlasticityIndex(float plasticityIndex) {
        this.plasticityIndex.set(plasticityIndex);
    }

    public float getLiquidityIndex() {
        return liquidityIndex.get();
    }

    public FloatProperty liquidityIndexProperty() {
        return liquidityIndex;
    }

    public void setLiquidityIndex(float liquidityIndex) {
        this.liquidityIndex.set(liquidityIndex);
    }

    //    public void setFieldValue(WaterContentDTOFieldsEnum field, Object value) {
//        switch (field) {
//            case LABOR_NUMBER:
//                laborNumber = (String) value;
//                break;
//            case NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_NUMBER_FIRST_MEASUREMENT:
//                naturalWaterContentWeighingBottleNumberFirstMeasurement = (String) value;
//                break;
//            case NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_NUMBER_SECOND_MEASUREMENT:
//                naturalWaterContentWeighingBottleNumberSecondMeasurement = (String) value;
//                break;
//            case LIQUIDITY_WEIGHING_BOTTLE_NUMBER_FIRST_MEASUREMENT:
//                liquidityWeighingBottleNumberFirstMeasurement = (String) value;
//                break;
//            case LIQUIDITY_WEIGHING_BOTTLE_NUMBER_SECOND_MEASUREMENT:
//                liquidityWeighingBottleNumberSecondMeasurement = (String) value;
//                break;
//            case PLASTIC_WEIGHING_BOTTLE_NUMBER_FIRST_MEASUREMENT:
//                plasticWeighingBottleNumberFirstMeasurement = (String) value;
//                break;
//            case PLASTIC_WEIGHING_BOTTLE_NUMBER_SECOND_MEASUREMENT:
//                plasticWeighingBottleNumberSecondMeasurement = (String) value;
//                break;
//            case NATURAL_WATER_CONTENT_EMPTY_WEIGHING_BOTTLE_MASS_FIRST_MEASUREMENT:
//                naturalWaterContentEmptyWeighingBottleMassFirstMeasurement = (Float) value;
//                break;
//            case NATURAL_WATER_CONTENT_EMPTY_WEIGHING_BOTTLE_MASS_SECOND_MEASUREMENT:
//                naturalWaterContentEmptyWeighingBottleMassSecondMeasurement = (Float) value;
//                break;
//            case LIQUIDITY_EMPTY_WEIGHING_BOTTLE_MASS_FIRST_MEASUREMENT:
//                liquidityEmptyWeighingBottleMassFirstMeasurement = (Float) value;
//                break;
//            case LIQUIDITY_EMPTY_WEIGHING_BOTTLE_MASS_SECOND_MEASUREMENT:
//                liquidityEmptyWeighingBottleMassSecondMeasurement = (Float) value;
//                break;
//            case PLASTIC_EMPTY_WEIGHING_BOTTLE_MASS_FIRST_MEASUREMENT:
//                plasticEmptyWeighingBottleMassFirstMeasurement = (Float) value;
//                break;
//            case PLASTIC_EMPTY_WEIGHING_BOTTLE_MASS_SECOND_MEASUREMENT:
//                plasticEmptyWeighingBottleMassSecondMeasurement = (Float) value;
//                break;
//            case NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_FIRST_MEASUREMENT:
//                naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement = (Float) value;
//                break;
//            case NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_SECOND_MEASUREMENT:
//                naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement = (Float) value;
//                break;
//            case LIQUIDITY_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_FIRST_MEASUREMENT:
//                liquidityWeighingBottleWithWetSoilMassFirstMeasurement = (Float) value;
//                break;
//            case LIQUIDITY_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_SECOND_MEASUREMENT:
//                liquidityWeighingBottleWithWetSoilMassSecondMeasurement = (Float) value;
//                break;
//            case PLASTIC_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_FIRST_MEASUREMENT:
//                plasticWeighingBottleWithWetSoilMassFirstMeasurement = (Float) value;
//                break;
//            case PLASTIC_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_SECOND_MEASUREMENT:
//                plasticWeighingBottleWithWetSoilMassSecondMeasurement = (Float) value;
//                break;
//            case NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_FIRST_MEASUREMENT:
//                naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement = (Float) value;
//                break;
//            case NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_SECOND_MEASUREMENT:
//                naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement = (Float) value;
//                break;
//            case LIQUIDITY_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_FIRST_MEASUREMENT:
//                liquidityWeighingBottleWithDrySoilMassFirstMeasurement = (Float) value;
//                break;
//            case LIQUIDITY_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_SECOND_MEASUREMENT:
//                liquidityWeighingBottleWithDrySoilMassSecondMeasurement = (Float) value;
//                break;
//            case PLASTIC_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_FIRST_MEASUREMENT:
//                plasticWeighingBottleWithDrySoilMassFirstMeasurement = (Float) value;
//                break;
//            case PLASTIC_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_SECOND_MEASUREMENT:
//                plasticWeighingBottleWithDrySoilMassSecondMeasurement = (Float) value;
//                break;
//            case NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WATER_CONTENT_FIRST_MEASUREMENT:
//                naturalWaterContentWeighingBottleWaterContentFirstMeasurement = (Float) value;
//                break;
//            case NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WATER_CONTENT_SECOND_MEASUREMENT:
//                naturalWaterContentWeighingBottleWaterContentSecondMeasurement = (Float) value;
//                break;
//            case LIQUIDITY_WEIGHING_BOTTLE_WATER_CONTENT_FIRST_MEASUREMENT:
//                liquidityWeighingBottleWaterContentFirstMeasurement = (Float) value;
//                break;
//            case LIQUIDITY_WEIGHING_BOTTLE_WATER_CONTENT_SECOND_MEASUREMENT:
//                liquidityWeighingBottleWaterContentSecondMeasurement = (Float) value;
//                break;
//            case PLASTIC_WEIGHING_BOTTLE_WATER_CONTENT_FIRST_MEASUREMENT:
//                plasticWeighingBottleWaterContentFirstMeasurement = (Float) value;
//                break;
//            case PLASTIC_WEIGHING_BOTTLE_WATER_CONTENT_SECOND_MEASUREMENT:
//                plasticWeighingBottleWaterContentSecondMeasurement = (Float) value;
//                break;
//            case NATURAL_AVERAGE_WATER_CONTENT:
//                naturalAverageWaterContent = (Float) value;
//                break;
//            case AVERAGE_LIQUID_LIMIT:
//                averageLiquidLimit = (Float) value;
//                break;
//            case AVERAGE_PLASTIC_LIMIT:
//                averagePlasticLimit = (Float) value;
//                break;
//            case PLASTICITY_INDEX:
//                plasticityIndex = (Float) value;
//                break;
//            case LIQUIDITY_INDEX:
//                liquidityIndex = (Float) value;
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid field: " + field);
//        }
//    }
}

//    laborNumber
//    naturalWaterContentWeighingBottleNumberFirstMeasurement
//    naturalWaterContentWeighingBottleNumberSecondMeasurement
//    liquidityWeighingBottleNumberFirstMeasurement
//    liquidityWeighingBottleNumberSecondMeasurement
//    plasticWeighingBottleNumberFirstMeasurement
//    plasticWeighingBottleNumberSecondMeasurement
//    naturalWaterContentEmptyWeighingBottleMassFirstMeasurement
//    naturalWaterContentEmptyWeighingBottleMassSecondMeasurement
//    liquidityEmptyWeighingBottleMassFirstMeasurement
//    liquidityEmptyWeighingBottleMassSecondMeasurement
//    plasticEmptyWeighingBottleMassFirstMeasurement
//    plasticEmptyWeighingBottleMassSecondMeasurement
//    naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement
//    naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement
//    liquidityWeighingBottleWithWetSoilMassFirstMeasurement
//    liquidityWeighingBottleWithWetSoilMassSecondMeasurement
//    plasticWeighingBottleWithWetSoilMassFirstMeasurement
//    plasticWeighingBottleWithWetSoilMassSecondMeasurement
//    naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement
//    naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement
//    liquidityWeighingBottleWithDrySoilMassFirstMeasurement
//    liquidityWeighingBottleWithDrySoilMassSecondMeasurement
//    plasticWeighingBottleWithDrySoilMassFirstMeasurement
//    plasticWeighingBottleWithDrySoilMassSecondMeasurement
//    naturalWaterContentWeighingBottleWaterContentFirstMeasurement
//    naturalWaterContentWeighingBottleWaterContentSecondMeasurement
//    liquidityWeighingBottleWaterContentFirstMeasurement
//    liquidityWeighingBottleWaterContentSecondMeasurement
//    plasticWeighingBottleWaterContentFirstMeasurement
//    plasticWeighingBottleWaterContentSecondMeasurement
//    naturalAverageWaterContent
//    averageLiquidLimit
//    averagePlasticLimit
//    plasticityIndex
//    liquidityIndex
