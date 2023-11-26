package org.geoproject.ingeo.dto.methodDtos;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RingDensityDTO {

    private String laborNumber;

    private StringProperty ringNumberFirstMeasurement = new SimpleStringProperty();
    private StringProperty ringNumberSecondMeasurement = new SimpleStringProperty();
    private FloatProperty emptyRingMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty emptyRingMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty ringWithWetSoilMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty ringWithWetSoilMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty ringVolumeFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty ringVolumeSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty ringDensityFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty ringDensitySecondMeasurement = new SimpleFloatProperty();
    private FloatProperty ringDensityAverage = new SimpleFloatProperty();
    private FloatProperty ringDrySoilDensity = new SimpleFloatProperty();
    private FloatProperty voidRatio = new SimpleFloatProperty();
    private FloatProperty fullWaterContent = new SimpleFloatProperty();
    private FloatProperty saturationRatio = new SimpleFloatProperty();
    private FloatProperty liquidityIndex = new SimpleFloatProperty();

    public String getLaborNumber() {
        return laborNumber;
    }

    public void setLaborNumber(String laborNumber) {
        this.laborNumber = laborNumber;
    }

    public String getRingNumberFirstMeasurement() {
        return ringNumberFirstMeasurement.get();
    }

    public StringProperty ringNumberFirstMeasurementProperty() {
        return ringNumberFirstMeasurement;
    }

    public void setRingNumberFirstMeasurement(String ringNumberFirstMeasurement) {
        this.ringNumberFirstMeasurement.set(ringNumberFirstMeasurement);
    }

    public String getRingNumberSecondMeasurement() {
        return ringNumberSecondMeasurement.get();
    }

    public StringProperty ringNumberSecondMeasurementProperty() {
        return ringNumberSecondMeasurement;
    }

    public void setRingNumberSecondMeasurement(String ringNumberSecondMeasurement) {
        this.ringNumberSecondMeasurement.set(ringNumberSecondMeasurement);
    }

    public float getEmptyRingMassFirstMeasurement() {
        return emptyRingMassFirstMeasurement.get();
    }

    public FloatProperty emptyRingMassFirstMeasurementProperty() {
        return emptyRingMassFirstMeasurement;
    }

    public void setEmptyRingMassFirstMeasurement(float emptyRingMassFirstMeasurement) {
        this.emptyRingMassFirstMeasurement.set(emptyRingMassFirstMeasurement);
    }

    public float getEmptyRingMassSecondMeasurement() {
        return emptyRingMassSecondMeasurement.get();
    }

    public FloatProperty emptyRingMassSecondMeasurementProperty() {
        return emptyRingMassSecondMeasurement;
    }

    public void setEmptyRingMassSecondMeasurement(float emptyRingMassSecondMeasurement) {
        this.emptyRingMassSecondMeasurement.set(emptyRingMassSecondMeasurement);
    }

    public float getRingWithWetSoilMassFirstMeasurement() {
        return ringWithWetSoilMassFirstMeasurement.get();
    }

    public FloatProperty ringWithWetSoilMassFirstMeasurementProperty() {
        return ringWithWetSoilMassFirstMeasurement;
    }

    public void setRingWithWetSoilMassFirstMeasurement(float ringWithWetSoilMassFirstMeasurement) {
        this.ringWithWetSoilMassFirstMeasurement.set(ringWithWetSoilMassFirstMeasurement);
    }

    public float getRingWithWetSoilMassSecondMeasurement() {
        return ringWithWetSoilMassSecondMeasurement.get();
    }

    public FloatProperty ringWithWetSoilMassSecondMeasurementProperty() {
        return ringWithWetSoilMassSecondMeasurement;
    }

    public void setRingWithWetSoilMassSecondMeasurement(float ringWithWetSoilMassSecondMeasurement) {
        this.ringWithWetSoilMassSecondMeasurement.set(ringWithWetSoilMassSecondMeasurement);
    }

    public float getRingVolumeFirstMeasurement() {
        return ringVolumeFirstMeasurement.get();
    }

    public FloatProperty ringVolumeFirstMeasurementProperty() {
        return ringVolumeFirstMeasurement;
    }

    public void setRingVolumeFirstMeasurement(float ringVolumeFirstMeasurement) {
        this.ringVolumeFirstMeasurement.set(ringVolumeFirstMeasurement);
    }

    public float getRingVolumeSecondMeasurement() {
        return ringVolumeSecondMeasurement.get();
    }

    public FloatProperty ringVolumeSecondMeasurementProperty() {
        return ringVolumeSecondMeasurement;
    }

    public void setRingVolumeSecondMeasurement(float ringVolumeSecondMeasurement) {
        this.ringVolumeSecondMeasurement.set(ringVolumeSecondMeasurement);
    }

    public float getRingDensityFirstMeasurement() {
        return ringDensityFirstMeasurement.get();
    }

    public FloatProperty ringDensityFirstMeasurementProperty() {
        return ringDensityFirstMeasurement;
    }

    public void setRingDensityFirstMeasurement(float ringDensityFirstMeasurement) {
        this.ringDensityFirstMeasurement.set(ringDensityFirstMeasurement);
    }

    public float getRingDensitySecondMeasurement() {
        return ringDensitySecondMeasurement.get();
    }

    public FloatProperty ringDensitySecondMeasurementProperty() {
        return ringDensitySecondMeasurement;
    }

    public void setRingDensitySecondMeasurement(float ringDensitySecondMeasurement) {
        this.ringDensitySecondMeasurement.set(ringDensitySecondMeasurement);
    }

    public float getRingDensityAverage() {
        return ringDensityAverage.get();
    }

    public FloatProperty ringDensityAverageProperty() {
        return ringDensityAverage;
    }

    public void setRingDensityAverage(float ringDensityAverage) {
        this.ringDensityAverage.set(ringDensityAverage);
    }

    public float getRingDrySoilDensity() {
        return ringDrySoilDensity.get();
    }

    public FloatProperty ringDrySoilDensityProperty() {
        return ringDrySoilDensity;
    }

    public void setRingDrySoilDensity(float ringDrySoilDensity) {
        this.ringDrySoilDensity.set(ringDrySoilDensity);
    }

    public float getVoidRatio() {
        return voidRatio.get();
    }

    public FloatProperty voidRatioProperty() {
        return voidRatio;
    }

    public void setVoidRatio(float voidRatio) {
        this.voidRatio.set(voidRatio);
    }

    public float getFullWaterContent() {
        return fullWaterContent.get();
    }

    public FloatProperty fullWaterContentProperty() {
        return fullWaterContent;
    }

    public void setFullWaterContent(float fullWaterContent) {
        this.fullWaterContent.set(fullWaterContent);
    }

    public float getSaturationRatio() {
        return saturationRatio.get();
    }

    public FloatProperty saturationRatioProperty() {
        return saturationRatio;
    }

    public void setSaturationRatio(float saturationRatio) {
        this.saturationRatio.set(saturationRatio);
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
}
