package org.geoproject.ingeo.dto.methodDtos;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DensityDTO {

    private String laborNumber;

    private FloatProperty pycnometerWeightWithDrySoilFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty emptyPycnometerWeightFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty pycnometerWeightWithWaterFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty pycnometerWeightWithSoilAndWaterFirstMeasurement = new SimpleFloatProperty();

    private FloatProperty drySoilWeightFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty soilVolumeFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty soilDensityFirstMeasurement = new SimpleFloatProperty();

    private FloatProperty pycnometerWeightWithDrySoilSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty emptyPycnometerWeightSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty pycnometerWeightWithWaterSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty pycnometerWeightWithSoilAndWaterSecondMeasurement = new SimpleFloatProperty();

    private FloatProperty drySoilWeightSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty soilVolumeSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty soilDensitySecondMeasurement = new SimpleFloatProperty();

    private FloatProperty averageDensity = new SimpleFloatProperty();

    public String getLaborNumber() {
        return laborNumber;
    }

    public void setLaborNumber(String laborNumber) {
        this.laborNumber = laborNumber;
    }

    public float getPycnometerWeightWithDrySoilFirstMeasurement() {
        return pycnometerWeightWithDrySoilFirstMeasurement.get();
    }

    public FloatProperty pycnometerWeightWithDrySoilFirstMeasurementProperty() {
        return pycnometerWeightWithDrySoilFirstMeasurement;
    }

    public void setPycnometerWeightWithDrySoilFirstMeasurement(float pycnometerWeightWithDrySoilFirstMeasurement) {
        this.pycnometerWeightWithDrySoilFirstMeasurement.set(pycnometerWeightWithDrySoilFirstMeasurement);
    }

    public float getEmptyPycnometerWeightFirstMeasurement() {
        return emptyPycnometerWeightFirstMeasurement.get();
    }

    public FloatProperty emptyPycnometerWeightFirstMeasurementProperty() {
        return emptyPycnometerWeightFirstMeasurement;
    }

    public void setEmptyPycnometerWeightFirstMeasurement(float emptyPycnometerWeightFirstMeasurement) {
        this.emptyPycnometerWeightFirstMeasurement.set(emptyPycnometerWeightFirstMeasurement);
    }

    public float getPycnometerWeightWithWaterFirstMeasurement() {
        return pycnometerWeightWithWaterFirstMeasurement.get();
    }

    public FloatProperty pycnometerWeightWithWaterFirstMeasurementProperty() {
        return pycnometerWeightWithWaterFirstMeasurement;
    }

    public void setPycnometerWeightWithWaterFirstMeasurement(float pycnometerWeightWithWaterFirstMeasurement) {
        this.pycnometerWeightWithWaterFirstMeasurement.set(pycnometerWeightWithWaterFirstMeasurement);
    }

    public float getPycnometerWeightWithSoilAndWaterFirstMeasurement() {
        return pycnometerWeightWithSoilAndWaterFirstMeasurement.get();
    }

    public FloatProperty pycnometerWeightWithSoilAndWaterFirstMeasurementProperty() {
        return pycnometerWeightWithSoilAndWaterFirstMeasurement;
    }

    public void setPycnometerWeightWithSoilAndWaterFirstMeasurement(float pycnometerWeightWithSoilAndWaterFirstMeasurement) {
        this.pycnometerWeightWithSoilAndWaterFirstMeasurement.set(pycnometerWeightWithSoilAndWaterFirstMeasurement);
    }

    public float getDrySoilWeightFirstMeasurement() {
        return drySoilWeightFirstMeasurement.get();
    }

    public FloatProperty drySoilWeightFirstMeasurementProperty() {
        return drySoilWeightFirstMeasurement;
    }

    public void setDrySoilWeightFirstMeasurement(float drySoilWeightFirstMeasurement) {
        this.drySoilWeightFirstMeasurement.set(drySoilWeightFirstMeasurement);
    }

    public float getSoilVolumeFirstMeasurement() {
        return soilVolumeFirstMeasurement.get();
    }

    public FloatProperty soilVolumeFirstMeasurementProperty() {
        return soilVolumeFirstMeasurement;
    }

    public void setSoilVolumeFirstMeasurement(float soilVolumeFirstMeasurement) {
        this.soilVolumeFirstMeasurement.set(soilVolumeFirstMeasurement);
    }

    public float getSoilDensityFirstMeasurement() {
        return soilDensityFirstMeasurement.get();
    }

    public FloatProperty soilDensityFirstMeasurementProperty() {
        return soilDensityFirstMeasurement;
    }

    public void setSoilDensityFirstMeasurement(float soilDensityFirstMeasurement) {
        this.soilDensityFirstMeasurement.set(soilDensityFirstMeasurement);
    }

    public float getPycnometerWeightWithDrySoilSecondMeasurement() {
        return pycnometerWeightWithDrySoilSecondMeasurement.get();
    }

    public FloatProperty pycnometerWeightWithDrySoilSecondMeasurementProperty() {
        return pycnometerWeightWithDrySoilSecondMeasurement;
    }

    public void setPycnometerWeightWithDrySoilSecondMeasurement(float pycnometerWeightWithDrySoilSecondMeasurement) {
        this.pycnometerWeightWithDrySoilSecondMeasurement.set(pycnometerWeightWithDrySoilSecondMeasurement);
    }

    public float getEmptyPycnometerWeightSecondMeasurement() {
        return emptyPycnometerWeightSecondMeasurement.get();
    }

    public FloatProperty emptyPycnometerWeightSecondMeasurementProperty() {
        return emptyPycnometerWeightSecondMeasurement;
    }

    public void setEmptyPycnometerWeightSecondMeasurement(float emptyPycnometerWeightSecondMeasurement) {
        this.emptyPycnometerWeightSecondMeasurement.set(emptyPycnometerWeightSecondMeasurement);
    }

    public float getPycnometerWeightWithWaterSecondMeasurement() {
        return pycnometerWeightWithWaterSecondMeasurement.get();
    }

    public FloatProperty pycnometerWeightWithWaterSecondMeasurementProperty() {
        return pycnometerWeightWithWaterSecondMeasurement;
    }

    public void setPycnometerWeightWithWaterSecondMeasurement(float pycnometerWeightWithWaterSecondMeasurement) {
        this.pycnometerWeightWithWaterSecondMeasurement.set(pycnometerWeightWithWaterSecondMeasurement);
    }

    public float getPycnometerWeightWithSoilAndWaterSecondMeasurement() {
        return pycnometerWeightWithSoilAndWaterSecondMeasurement.get();
    }

    public FloatProperty pycnometerWeightWithSoilAndWaterSecondMeasurementProperty() {
        return pycnometerWeightWithSoilAndWaterSecondMeasurement;
    }

    public void setPycnometerWeightWithSoilAndWaterSecondMeasurement(float pycnometerWeightWithSoilAndWaterSecondMeasurement) {
        this.pycnometerWeightWithSoilAndWaterSecondMeasurement.set(pycnometerWeightWithSoilAndWaterSecondMeasurement);
    }

    public float getDrySoilWeightSecondMeasurement() {
        return drySoilWeightSecondMeasurement.get();
    }

    public FloatProperty drySoilWeightSecondMeasurementProperty() {
        return drySoilWeightSecondMeasurement;
    }

    public void setDrySoilWeightSecondMeasurement(float drySoilWeightSecondMeasurement) {
        this.drySoilWeightSecondMeasurement.set(drySoilWeightSecondMeasurement);
    }

    public float getSoilVolumeSecondMeasurement() {
        return soilVolumeSecondMeasurement.get();
    }

    public FloatProperty soilVolumeSecondMeasurementProperty() {
        return soilVolumeSecondMeasurement;
    }

    public void setSoilVolumeSecondMeasurement(float soilVolumeSecondMeasurement) {
        this.soilVolumeSecondMeasurement.set(soilVolumeSecondMeasurement);
    }

    public float getSoilDensitySecondMeasurement() {
        return soilDensitySecondMeasurement.get();
    }

    public FloatProperty soilDensitySecondMeasurementProperty() {
        return soilDensitySecondMeasurement;
    }

    public void setSoilDensitySecondMeasurement(float soilDensitySecondMeasurement) {
        this.soilDensitySecondMeasurement.set(soilDensitySecondMeasurement);
    }

    public float getAverageDensity() {
        return averageDensity.get();
    }

    public FloatProperty averageDensityProperty() {
        return averageDensity;
    }

    public void setAverageDensity(float averageDensity) {
        this.averageDensity.set(averageDensity);
    }

    //    public void setFieldValue(DensityDTOFieldsEnum field, Object value) {
//        switch (field) {
//            case LABOR_NUMBER:
//                laborNumber = (String) value;
//                break;
//            case PYCNOMETER_WEIGHT_WITH_DRY_SOIL_FIRST_MEASUREMENT:
//                pycnometerWeightWithDrySoilFirstMeasurement = (Float) value;
//                break;
//            case EMPTY_PYCNOMETER_WEIGHT_FIRST_MEASUREMENT:
//                emptyPycnometerWeightFirstMeasurement = (Float) value;
//                break;
//            case PYCNOMETER_WEIGHT_WITH_WATER_FIRST_MEASUREMENT:
//                pycnometerWeightWithWaterFirstMeasurement = (Float) value;
//                break;
//            case PYCNOMETER_WEIGHT_WITH_SOIL_AND_WATER_FIRST_MEASUREMENT:
//                pycnometerWeightWithSoilAndWaterFirstMeasurement = (Float) value;
//                break;
//            case DRY_SOIL_WEIGHT_FIRST_MEASUREMENT:
//                drySoilWeightFirstMeasurement = (Float) value;
//                break;
//            case SOIL_VOLUME_FIRST_MEASUREMENT:
//                soilVolumeFirstMeasurement = (Float) value;
//                break;
//            case SOIL_DENSITY_FIRST_MEASUREMENT:
//                soilDensityFirstMeasurement = (Float) value;
//                break;
//            case PYCNOMETER_WEIGHT_WITH_DRY_SOIL_SECOND_MEASUREMENT:
//                pycnometerWeightWithDrySoilSecondMeasurement = (Float) value;
//                break;
//            case EMPTY_PYCNOMETER_WEIGHT_SECOND_MEASUREMENT:
//                emptyPycnometerWeightSecondMeasurement = (Float) value;
//                break;
//            case PYCNOMETER_WEIGHT_WITH_WATER_SECOND_MEASUREMENT:
//                pycnometerWeightWithWaterSecondMeasurement = (Float) value;
//                break;
//            case PYCNOMETER_WEIGHT_WITH_SOIL_AND_WATER_SECOND_MEASUREMENT:
//                pycnometerWeightWithSoilAndWaterSecondMeasurement = (Float) value;
//                break;
//            case DRY_SOIL_WEIGHT_SECOND_MEASUREMENT:
//                drySoilWeightSecondMeasurement = (Float) value;
//                break;
//            case SOIL_VOLUME_SECOND_MEASUREMENT:
//                soilVolumeSecondMeasurement = (Float) value;
//                break;
//            case SOIL_DENSITY_SECOND_MEASUREMENT:
//                soilDensitySecondMeasurement = (Float) value;
//                break;
//            case AVERAGE_DENSITY:
//                averageDensity = (Float) value;
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid field: " + field);
//        }
//    }
}

//    laborNumber
//    pycnometerWeightWithDrySoilFirstMeasurement
//    emptyPycnometerWeightFirstMeasurement
//    pycnometerWeightWithWaterFirstMeasurement
//    pycnometerWeightWithSoilAndWaterFirstMeasurement
//    drySoilWeightFirstMeasurement
//    soilVolumeFirstMeasurement
//    soilDensityFirstMeasurement
//    pycnometerWeightWithDrySoilSecondMeasurement
//    emptyPycnometerWeightSecondMeasurement
//    pycnometerWeightWithWaterSecondMeasurement
//    pycnometerWeightWithSoilAndWaterSecondMeasurement
//    drySoilWeightSecondMeasurement
//    soilVolumeSecondMeasurement
//    soilDensitySecondMeasurement
//    averageDensity