package org.geoproject.ingeo.dto.methodDtos;

import org.geoproject.ingeo.enums.dtoenums.ShearDtoFieldsEnum;
import jakarta.persistence.PostLoad;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShearDto {

    private Long id;

    private String laborNumber;
    private Boolean isArchive;
    private Float depth;

    private Boolean kd;
    private BooleanProperty kdTransient;

    private Integer shearPointNumber;
    private Float verticalLoading;
    private Float shearStrength;

    private Boolean isExcluded;
    private BooleanProperty isExcludedTransient;

    private Float densityBefore;
    private Float waterContentBefore;
    private Float waterContentAfter;
    private String soilDescription;

    //TODO: если ничего не сломается, удалить эти поля из базы:
//    private FloatProperty averageDensityBefore = new SimpleFloatProperty();
//    private FloatProperty averageWaterContentBefore = new SimpleFloatProperty();
    //вместо них:
    //naturalShearAverageWaterContentFirstMeasurement
    //shearRingDensityAverageFirstMeasurement

    private FloatProperty physicalPropertiesDensityBefore = new SimpleFloatProperty();
    private FloatProperty physicalPropertiesWaterContentBefore = new SimpleFloatProperty();

    private StringProperty shearNaturalWaterContentWeighingBottleNumberFirstMeasurement = new SimpleStringProperty();
    private StringProperty shearNaturalWaterContentWeighingBottleNumberSecondMeasurement = new SimpleStringProperty();
    private FloatProperty shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty shearNaturalWaterContentWeighingBottleFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty shearNaturalWaterContentWeighingBottleSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty naturalShearAverageWaterContentFirstMeasurement = new SimpleFloatProperty();

    private StringProperty shearRingNumberFirstMeasurement = new SimpleStringProperty();
    private StringProperty shearRingNumberSecondMeasurement = new SimpleStringProperty();
    private FloatProperty shearEmptyRingMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty shearEmptyRingMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty shearRingWithWetSoilMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty shearRingWithWetSoilMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty shearRingVolumeFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty shearRingVolumeSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty shearRingDensityFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty shearRingDensitySecondMeasurement = new SimpleFloatProperty();
    private FloatProperty shearRingDrySoilDensityFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty shearRingDrySoilDensitySecondMeasurement = new SimpleFloatProperty();
    private FloatProperty shearRingDensityAverageFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty shearRingDrySoilDensityAverageFirstMeasurement = new SimpleFloatProperty();


    //diagram block

    private Float xSigmaSum;
    private Float yTauSum;
    private Float xyProductionSum;
    private Float xSquaredSum;
    private Float tgFi;
    private Float Fi;
    private Float degrees;
    private Float c;

//    public void setFieldValue(ShearDtoFieldsEnum field, Object value) {
//        switch (field) {
//            case LABOR_NUMBER -> laborNumber = (String) value;
//            case IS_ARCHIVE -> isArchive = (Boolean) value;
//            case DEPTH -> depth = (Float) value;
//            case KD -> kd = (Boolean) value;
//            case SHEAR_POINT_NUMBER -> shearPointNumber = (Integer) value;
//            case VERTICAL_LOADING -> verticalLoading = (Float) value;
//            case SHEAR_STRENGTH -> shearStrength = (Float) value;
//            case IS_EXCLUDED -> isExcluded = (Boolean) value;
//            case DENSITY_BEFORE -> densityBefore = (Float) value;
//            case WATER_CONTENT_BEFORE -> waterContentBefore = (Float) value;
//            case WATER_CONTENT_AFTER -> waterContentAfter = (Float) value;
//            case SOIL_DESCRIPTION -> soilDescription = (String) value;
//            default -> throw new IllegalArgumentException("Invalid field: " + field);
//        }
//    }

    public void setFieldValue(ShearDtoFieldsEnum field, Object value) {
        switch (field) {
            case LABOR_NUMBER -> laborNumber = (String) value;
            case IS_ARCHIVE -> isArchive = (Boolean) value;
            case DEPTH -> depth = (Float) value;
            case KD -> kd = (Boolean) value;
            case SHEAR_POINT_NUMBER -> shearPointNumber = (Integer) value;
            case VERTICAL_LOADING -> verticalLoading = (Float) value;
            case SHEAR_STRENGTH -> shearStrength = (Float) value;
            case IS_EXCLUDED -> isExcluded = (Boolean) value;
            case DENSITY_BEFORE -> densityBefore = (Float) value;
            case WATER_CONTENT_BEFORE -> waterContentBefore = (Float) value;
            case WATER_CONTENT_AFTER -> waterContentAfter = (Float) value;
            case SOIL_DESCRIPTION -> soilDescription = (String) value;

//            case SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_NUMBER_FIRST_MEASUREMENT ->
//                    shearNaturalWaterContentWeighingBottleNumberFirstMeasurement = (String) value;
//            case SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_NUMBER_SECOND_MEASUREMENT ->
//                    shearNaturalWaterContentWeighingBottleNumberSecondMeasurement = (String) value;
//            case SHEAR_NATURAL_WATER_CONTENT_EMPTY_WEIGHING_BOTTLE_MASS_FIRST_MEASUREMENT ->
//                    shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement = (Float) value;
//            case SHEAR_NATURAL_WATER_CONTENT_EMPTY_WEIGHING_BOTTLE_MASS_SECOND_MEASUREMENT ->
//                    shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement = (Float) value;
//            case SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_FIRST_MEASUREMENT ->
//                    shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement = (Float) value;
//            case SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_SECOND_MEASUREMENT ->
//                    shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement = (Float) value;
//            case SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_FIRST_MEASUREMENT ->
//                    shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement = (Float) value;
//            case SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_SECOND_MEASUREMENT ->
//                    shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement = (Float) value;

//            case SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_FIRST_MEASUREMENT ->
//                    shearNaturalWaterContentWeighingBottleFirstMeasurement = (Float) value;
//            case SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_SECOND_MEASUREMENT ->
//                    shearNaturalWaterContentWeighingBottleSecondMeasurement = (Float) value;

//            case NATURAL_SHEAR_AVERAGE_WATER_CONTENT_FIRST_MEASUREMENT ->
//                    naturalShearAverageWaterContentFirstMeasurement = (Float) value;
//            case SHEAR_RING_NUMBER_FIRST_MEASUREMENT -> shearRingNumberFirstMeasurement = (String) value;
//            case SHEAR_RING_NUMBER_SECOND_MEASUREMENT -> shearRingNumberSecondMeasurement = (String) value;
//            case SHEAR_EMPTY_RING_MASS_FIRST_MEASUREMENT -> shearEmptyRingMassFirstMeasurement = (Float) value;
//            case SHEAR_EMPTY_RING_MASS_SECOND_MEASUREMENT -> shearEmptyRingMassSecondMeasurement = (Float) value;
//            case SHEAR_RING_WITH_WET_SOIL_MASS_FIRST_MEASUREMENT ->
//                    shearRingWithWetSoilMassFirstMeasurement = (Float) value;
//            case SHEAR_RING_WITH_WET_SOIL_MASS_SECOND_MEASUREMENT ->
//                    shearRingWithWetSoilMassSecondMeasurement = (Float) value;
//            case SHEAR_RING_VOLUME_FIRST_MEASUREMENT -> shearRingVolumeFirstMeasurement = (Float) value;
//            case SHEAR_RING_VOLUME_SECOND_MEASUREMENT -> shearRingVolumeSecondMeasurement = (Float) value;

//            case SHEAR_RING_DENSITY_FIRST_MEASUREMENT -> shearRingDensityFirstMeasurement = (Float) value;

//            case SHEAR_RING_DENSITY_SECOND_MEASUREMENT -> shearRingDensitySecondMeasurement = (Float) value;
//            case SHEAR_RING_DRY_SOIL_DENSITY_FIRST_MEASUREMENT ->
//                    shearRingDrySoilDensityFirstMeasurement = (Float) value;
//            case SHEAR_RING_DRY_SOIL_DENSITY_SECOND_MEASUREMENT ->
//                    shearRingDrySoilDensitySecondMeasurement = (Float) value;
//            case SHEAR_RING_DENSITY_AVERAGE_FIRST_MEASUREMENT ->
//                    shearRingDensityAverageFirstMeasurement = (Float) value;
//            case SHEAR_RING_DRY_SOIL_DENSITY_AVERAGE_FIRST_MEASUREMENT ->
//                    shearRingDrySoilDensityAverageFirstMeasurement = (Float) value;
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        }
    }

//    public float getAverageDensityBefore() {
//        return averageDensityBefore.get();
//    }
//
//    public FloatProperty averageDensityBeforeProperty() {
//        return averageDensityBefore;
//    }
//
//    public void setAverageDensityBefore(float averageDensityBefore) {
//        this.averageDensityBefore.set(averageDensityBefore);
//    }
//
//    public float getAverageWaterContentBefore() {
//        return averageWaterContentBefore.get();
//    }
//
//    public FloatProperty averageWaterContentBeforeProperty() {
//        return averageWaterContentBefore;
//    }
//
//    public void setAverageWaterContentBefore(float averageWaterContentBefore) {
//        this.averageWaterContentBefore.set(averageWaterContentBefore);
//    }

    public float getPhysicalPropertiesDensityBefore() {
        return physicalPropertiesDensityBefore.get();
    }

    public FloatProperty physicalPropertiesDensityBeforeProperty() {
        return physicalPropertiesDensityBefore;
    }

    public void setPhysicalPropertiesDensityBefore(float physicalPropertiesDensityBefore) {
        this.physicalPropertiesDensityBefore.set(physicalPropertiesDensityBefore);
    }

    public float getPhysicalPropertiesWaterContentBefore() {
        return physicalPropertiesWaterContentBefore.get();
    }

    public FloatProperty physicalPropertiesWaterContentBeforeProperty() {
        return physicalPropertiesWaterContentBefore;
    }

    public void setPhysicalPropertiesWaterContentBefore(float physicalPropertiesWaterContentBefore) {
        this.physicalPropertiesWaterContentBefore.set(physicalPropertiesWaterContentBefore);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaborNumber() {
        return laborNumber;
    }

    public void setLaborNumber(String laborNumber) {
        this.laborNumber = laborNumber;
    }

    public Boolean getArchive() {
        return isArchive;
    }

    public void setArchive(Boolean archive) {
        isArchive = archive;
    }

    public Float getDepth() {
        return depth;
    }

    public void setDepth(Float depth) {
        this.depth = depth;
    }

    public Boolean getKd() {
        return kd;
    }

    public void setKd(Boolean kd) {
        this.kd = kd;
    }

    public Integer getShearPointNumber() {
        return shearPointNumber;
    }

    public void setShearPointNumber(Integer shearPointNumber) {
        this.shearPointNumber = shearPointNumber;
    }

    public Float getVerticalLoading() {
        return verticalLoading;
    }

    public void setVerticalLoading(Float verticalLoading) {
        this.verticalLoading = verticalLoading;
    }

    public Float getShearStrength() {
        return shearStrength;
    }

    public void setShearStrength(Float shearStrength) {
        this.shearStrength = shearStrength;
    }

    public Boolean getExcluded() {
        return isExcluded;
    }

    public void setExcluded(Boolean excluded) {
        isExcluded = excluded;
    }

    public Float getDensityBefore() {
        return densityBefore;
    }

    public void setDensityBefore(Float densityBefore) {
        this.densityBefore = densityBefore;
    }

    public Float getWaterContentBefore() {
        return waterContentBefore;
    }

    public void setWaterContentBefore(Float waterContentBefore) {
        this.waterContentBefore = waterContentBefore;
    }

    public Float getWaterContentAfter() {
        return waterContentAfter;
    }

    public void setWaterContentAfter(Float waterContentAfter) {
        this.waterContentAfter = waterContentAfter;
    }

    public String getSoilDescription() {
        return soilDescription;
    }

    public void setSoilDescription(String soilDescription) {
        this.soilDescription = soilDescription;
    }

    public String getShearNaturalWaterContentWeighingBottleNumberFirstMeasurement() {
        return shearNaturalWaterContentWeighingBottleNumberFirstMeasurement.get();
    }

    public StringProperty shearNaturalWaterContentWeighingBottleNumberFirstMeasurementProperty() {
        return shearNaturalWaterContentWeighingBottleNumberFirstMeasurement;
    }

    public void setShearNaturalWaterContentWeighingBottleNumberFirstMeasurement(String shearNaturalWaterContentWeighingBottleNumberFirstMeasurement) {
        this.shearNaturalWaterContentWeighingBottleNumberFirstMeasurement.set(shearNaturalWaterContentWeighingBottleNumberFirstMeasurement);
    }

    public String getShearNaturalWaterContentWeighingBottleNumberSecondMeasurement() {
        return shearNaturalWaterContentWeighingBottleNumberSecondMeasurement.get();
    }

    public StringProperty shearNaturalWaterContentWeighingBottleNumberSecondMeasurementProperty() {
        return shearNaturalWaterContentWeighingBottleNumberSecondMeasurement;
    }

    public void setShearNaturalWaterContentWeighingBottleNumberSecondMeasurement(String shearNaturalWaterContentWeighingBottleNumberSecondMeasurement) {
        this.shearNaturalWaterContentWeighingBottleNumberSecondMeasurement.set(shearNaturalWaterContentWeighingBottleNumberSecondMeasurement);
    }

    public float getShearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement() {
        return shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement.get();
    }

    public FloatProperty shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurementProperty() {
        return shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement;
    }

    public void setShearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement(float shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement) {
        this.shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement.set(shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement);
    }

    public float getShearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement() {
        return shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement.get();
    }

    public FloatProperty shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurementProperty() {
        return shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement;
    }

    public void setShearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement(float shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement) {
        this.shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement.set(shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement);
    }

    public float getShearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement() {
        return shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement.get();
    }

    public FloatProperty shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurementProperty() {
        return shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement;
    }

    public void setShearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement(float shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement) {
        this.shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement.set(shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement);
    }

    public float getShearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement() {
        return shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement.get();
    }

    public FloatProperty shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurementProperty() {
        return shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement;
    }

    public void setShearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement(float shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement) {
        this.shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement.set(shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement);
    }

    public float getShearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement() {
        return shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement.get();
    }

    public FloatProperty shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurementProperty() {
        return shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement;
    }

    public void setShearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement(float shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement) {
        this.shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement.set(shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement);
    }

    public float getShearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement() {
        return shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement.get();
    }

    public FloatProperty shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurementProperty() {
        return shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement;
    }

    public void setShearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement(float shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement) {
        this.shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement.set(shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement);
    }

    public float getShearNaturalWaterContentWeighingBottleFirstMeasurement() {
        return shearNaturalWaterContentWeighingBottleFirstMeasurement.get();
    }

    public FloatProperty shearNaturalWaterContentWeighingBottleFirstMeasurementProperty() {
        return shearNaturalWaterContentWeighingBottleFirstMeasurement;
    }

    public void setShearNaturalWaterContentWeighingBottleFirstMeasurement(float shearNaturalWaterContentWeighingBottleFirstMeasurement) {
        this.shearNaturalWaterContentWeighingBottleFirstMeasurement.set(shearNaturalWaterContentWeighingBottleFirstMeasurement);
    }

    public float getShearNaturalWaterContentWeighingBottleSecondMeasurement() {
        return shearNaturalWaterContentWeighingBottleSecondMeasurement.get();
    }

    public FloatProperty shearNaturalWaterContentWeighingBottleSecondMeasurementProperty() {
        return shearNaturalWaterContentWeighingBottleSecondMeasurement;
    }

    public void setShearNaturalWaterContentWeighingBottleSecondMeasurement(float shearNaturalWaterContentWeighingBottleSecondMeasurement) {
        this.shearNaturalWaterContentWeighingBottleSecondMeasurement.set(shearNaturalWaterContentWeighingBottleSecondMeasurement);
    }

    public float getNaturalShearAverageWaterContentFirstMeasurement() {
        return naturalShearAverageWaterContentFirstMeasurement.get();
    }

    public FloatProperty naturalShearAverageWaterContentFirstMeasurementProperty() {
        return naturalShearAverageWaterContentFirstMeasurement;
    }

    public void setNaturalShearAverageWaterContentFirstMeasurement(float naturalShearAverageWaterContentFirstMeasurement) {
        this.naturalShearAverageWaterContentFirstMeasurement.set(naturalShearAverageWaterContentFirstMeasurement);
    }

    public String getShearRingNumberFirstMeasurement() {
        return shearRingNumberFirstMeasurement.get();
    }

    public StringProperty shearRingNumberFirstMeasurementProperty() {
        return shearRingNumberFirstMeasurement;
    }

    public void setShearRingNumberFirstMeasurement(String shearRingNumberFirstMeasurement) {
        this.shearRingNumberFirstMeasurement.set(shearRingNumberFirstMeasurement);
    }

    public String getShearRingNumberSecondMeasurement() {
        return shearRingNumberSecondMeasurement.get();
    }

    public StringProperty shearRingNumberSecondMeasurementProperty() {
        return shearRingNumberSecondMeasurement;
    }

    public void setShearRingNumberSecondMeasurement(String shearRingNumberSecondMeasurement) {
        this.shearRingNumberSecondMeasurement.set(shearRingNumberSecondMeasurement);
    }

    public float getShearEmptyRingMassFirstMeasurement() {
        return shearEmptyRingMassFirstMeasurement.get();
    }

    public FloatProperty shearEmptyRingMassFirstMeasurementProperty() {
        return shearEmptyRingMassFirstMeasurement;
    }

    public void setShearEmptyRingMassFirstMeasurement(float shearEmptyRingMassFirstMeasurement) {
        this.shearEmptyRingMassFirstMeasurement.set(shearEmptyRingMassFirstMeasurement);
    }

    public float getShearEmptyRingMassSecondMeasurement() {
        return shearEmptyRingMassSecondMeasurement.get();
    }

    public FloatProperty shearEmptyRingMassSecondMeasurementProperty() {
        return shearEmptyRingMassSecondMeasurement;
    }

    public void setShearEmptyRingMassSecondMeasurement(float shearEmptyRingMassSecondMeasurement) {
        this.shearEmptyRingMassSecondMeasurement.set(shearEmptyRingMassSecondMeasurement);
    }

    public float getShearRingWithWetSoilMassFirstMeasurement() {
        return shearRingWithWetSoilMassFirstMeasurement.get();
    }

    public FloatProperty shearRingWithWetSoilMassFirstMeasurementProperty() {
        return shearRingWithWetSoilMassFirstMeasurement;
    }

    public void setShearRingWithWetSoilMassFirstMeasurement(float shearRingWithWetSoilMassFirstMeasurement) {
        this.shearRingWithWetSoilMassFirstMeasurement.set(shearRingWithWetSoilMassFirstMeasurement);
    }

    public float getShearRingWithWetSoilMassSecondMeasurement() {
        return shearRingWithWetSoilMassSecondMeasurement.get();
    }

    public FloatProperty shearRingWithWetSoilMassSecondMeasurementProperty() {
        return shearRingWithWetSoilMassSecondMeasurement;
    }

    public void setShearRingWithWetSoilMassSecondMeasurement(float shearRingWithWetSoilMassSecondMeasurement) {
        this.shearRingWithWetSoilMassSecondMeasurement.set(shearRingWithWetSoilMassSecondMeasurement);
    }

    public float getShearRingVolumeFirstMeasurement() {
        return shearRingVolumeFirstMeasurement.get();
    }

    public FloatProperty shearRingVolumeFirstMeasurementProperty() {
        return shearRingVolumeFirstMeasurement;
    }

    public void setShearRingVolumeFirstMeasurement(float shearRingVolumeFirstMeasurement) {
        this.shearRingVolumeFirstMeasurement.set(shearRingVolumeFirstMeasurement);
    }

    public float getShearRingVolumeSecondMeasurement() {
        return shearRingVolumeSecondMeasurement.get();
    }

    public FloatProperty shearRingVolumeSecondMeasurementProperty() {
        return shearRingVolumeSecondMeasurement;
    }

    public void setShearRingVolumeSecondMeasurement(float shearRingVolumeSecondMeasurement) {
        this.shearRingVolumeSecondMeasurement.set(shearRingVolumeSecondMeasurement);
    }

    public float getShearRingDensityFirstMeasurement() {
        return shearRingDensityFirstMeasurement.get();
    }

    public FloatProperty shearRingDensityFirstMeasurementProperty() {
        return shearRingDensityFirstMeasurement;
    }

    public void setShearRingDensityFirstMeasurement(float shearRingDensityFirstMeasurement) {
        this.shearRingDensityFirstMeasurement.set(shearRingDensityFirstMeasurement);
    }

    public float getShearRingDensitySecondMeasurement() {
        return shearRingDensitySecondMeasurement.get();
    }

    public FloatProperty shearRingDensitySecondMeasurementProperty() {
        return shearRingDensitySecondMeasurement;
    }

    public void setShearRingDensitySecondMeasurement(float shearRingDensitySecondMeasurement) {
        this.shearRingDensitySecondMeasurement.set(shearRingDensitySecondMeasurement);
    }

    public float getShearRingDrySoilDensityFirstMeasurement() {
        return shearRingDrySoilDensityFirstMeasurement.get();
    }

    public FloatProperty shearRingDrySoilDensityFirstMeasurementProperty() {
        return shearRingDrySoilDensityFirstMeasurement;
    }

    public void setShearRingDrySoilDensityFirstMeasurement(float shearRingDrySoilDensityFirstMeasurement) {
        this.shearRingDrySoilDensityFirstMeasurement.set(shearRingDrySoilDensityFirstMeasurement);
    }

    public float getShearRingDrySoilDensitySecondMeasurement() {
        return shearRingDrySoilDensitySecondMeasurement.get();
    }

    public FloatProperty shearRingDrySoilDensitySecondMeasurementProperty() {
        return shearRingDrySoilDensitySecondMeasurement;
    }

    public void setShearRingDrySoilDensitySecondMeasurement(float shearRingDrySoilDensitySecondMeasurement) {
        this.shearRingDrySoilDensitySecondMeasurement.set(shearRingDrySoilDensitySecondMeasurement);
    }

    public float getShearRingDensityAverageFirstMeasurement() {
        return shearRingDensityAverageFirstMeasurement.get();
    }

    public FloatProperty shearRingDensityAverageFirstMeasurementProperty() {
        return shearRingDensityAverageFirstMeasurement;
    }

    public void setShearRingDensityAverageFirstMeasurement(float shearRingDensityAverageFirstMeasurement) {
        this.shearRingDensityAverageFirstMeasurement.set(shearRingDensityAverageFirstMeasurement);
    }

    public float getShearRingDrySoilDensityAverageFirstMeasurement() {
        return shearRingDrySoilDensityAverageFirstMeasurement.get();
    }

    public FloatProperty shearRingDrySoilDensityAverageFirstMeasurementProperty() {
        return shearRingDrySoilDensityAverageFirstMeasurement;
    }

    public void setShearRingDrySoilDensityAverageFirstMeasurement(float shearRingDrySoilDensityAverageFirstMeasurement) {
        this.shearRingDrySoilDensityAverageFirstMeasurement.set(shearRingDrySoilDensityAverageFirstMeasurement);
    }

    @PostLoad
    void fillTransients() {
        if (kd != null) {
            this.kdTransient = new SimpleBooleanProperty(kd);
        } else {
            this.kdTransient = new SimpleBooleanProperty();
        }

        if (isExcluded != null) {
            this.isExcludedTransient = new SimpleBooleanProperty(isExcluded);
        } else {
            this.isExcludedTransient = new SimpleBooleanProperty();
        }
    }

    @Override
    public String toString() {
        return "ShearDto{" +
                "shearNaturalWaterContentWeighingBottleNumberFirstMeasurement=" + shearNaturalWaterContentWeighingBottleNumberFirstMeasurement +
                ", shearNaturalWaterContentWeighingBottleNumberSecondMeasurement=" + shearNaturalWaterContentWeighingBottleNumberSecondMeasurement +
                ", shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement=" + shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement +
                ", shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement=" + shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement +
                ", shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement=" + shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement +
                ", shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement=" + shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement +
                ", shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement=" + shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement +
                ", shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement=" + shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement +
                ", shearNaturalWaterContentWeighingBottleFirstMeasurement=" + shearNaturalWaterContentWeighingBottleFirstMeasurement +
                ", shearNaturalWaterContentWeighingBottleSecondMeasurement=" + shearNaturalWaterContentWeighingBottleSecondMeasurement +
                ", naturalShearAverageWaterContentFirstMeasurement=" + naturalShearAverageWaterContentFirstMeasurement +
                ", shearRingNumberFirstMeasurement=" + shearRingNumberFirstMeasurement +
                ", shearRingNumberSecondMeasurement=" + shearRingNumberSecondMeasurement +
                ", shearEmptyRingMassFirstMeasurement=" + shearEmptyRingMassFirstMeasurement +
                ", shearEmptyRingMassSecondMeasurement=" + shearEmptyRingMassSecondMeasurement +
                ", shearRingWithWetSoilMassFirstMeasurement=" + shearRingWithWetSoilMassFirstMeasurement +
                ", shearRingWithWetSoilMassSecondMeasurement=" + shearRingWithWetSoilMassSecondMeasurement +
                ", shearRingVolumeFirstMeasurement=" + shearRingVolumeFirstMeasurement +
                ", shearRingVolumeSecondMeasurement=" + shearRingVolumeSecondMeasurement +
                ", shearRingDensityFirstMeasurement=" + shearRingDensityFirstMeasurement +
                ", shearRingDensitySecondMeasurement=" + shearRingDensitySecondMeasurement +
                ", shearRingDrySoilDensityFirstMeasurement=" + shearRingDrySoilDensityFirstMeasurement +
                ", shearRingDrySoilDensitySecondMeasurement=" + shearRingDrySoilDensitySecondMeasurement +
                ", shearRingDensityAverageFirstMeasurement=" + shearRingDensityAverageFirstMeasurement +
                ", shearRingDrySoilDensityAverageFirstMeasurement=" + shearRingDrySoilDensityAverageFirstMeasurement +
                '}';
    }
}
