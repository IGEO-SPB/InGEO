package org.geoproject.ingeo.dto.methodDtos;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class BoychenkoConeDTO {

    private String laborNumber;

    private FloatProperty undisturbedStrImmersionDepthFirstMeasur = new SimpleFloatProperty();
    private FloatProperty undisturbedStrImmersionDepthSecondMeasur = new SimpleFloatProperty();
    private FloatProperty undisturbedStrImmersionDepthThirdMeasur = new SimpleFloatProperty();
    private FloatProperty brokenStrImmersionDepthFirstMeasur = new SimpleFloatProperty();
    private FloatProperty brokenStrImmersionDepthSecondMeasur = new SimpleFloatProperty();
    private FloatProperty brokenStrImmersionDepthThirdMeasur = new SimpleFloatProperty();
    private FloatProperty undisturbedStrImmersionDepthAverage = new SimpleFloatProperty();
    private FloatProperty brokenStrImmersionDepthAverage = new SimpleFloatProperty();

    public String getLaborNumber() {
        return laborNumber;
    }

    public void setLaborNumber(String laborNumber) {
        this.laborNumber = laborNumber;
    }

    public float getUndisturbedStrImmersionDepthFirstMeasur() {
        return undisturbedStrImmersionDepthFirstMeasur.get();
    }

    public FloatProperty undisturbedStrImmersionDepthFirstMeasurProperty() {
        return undisturbedStrImmersionDepthFirstMeasur;
    }

    public void setUndisturbedStrImmersionDepthFirstMeasur(float undisturbedStrImmersionDepthFirstMeasur) {
        this.undisturbedStrImmersionDepthFirstMeasur.set(undisturbedStrImmersionDepthFirstMeasur);
    }

    public float getUndisturbedStrImmersionDepthSecondMeasur() {
        return undisturbedStrImmersionDepthSecondMeasur.get();
    }

    public FloatProperty undisturbedStrImmersionDepthSecondMeasurProperty() {
        return undisturbedStrImmersionDepthSecondMeasur;
    }

    public void setUndisturbedStrImmersionDepthSecondMeasur(float undisturbedStrImmersionDepthSecondMeasur) {
        this.undisturbedStrImmersionDepthSecondMeasur.set(undisturbedStrImmersionDepthSecondMeasur);
    }

    public float getUndisturbedStrImmersionDepthThirdMeasur() {
        return undisturbedStrImmersionDepthThirdMeasur.get();
    }

    public FloatProperty undisturbedStrImmersionDepthThirdMeasurProperty() {
        return undisturbedStrImmersionDepthThirdMeasur;
    }

    public void setUndisturbedStrImmersionDepthThirdMeasur(float undisturbedStrImmersionDepthThirdMeasur) {
        this.undisturbedStrImmersionDepthThirdMeasur.set(undisturbedStrImmersionDepthThirdMeasur);
    }

    public float getBrokenStrImmersionDepthFirstMeasur() {
        return brokenStrImmersionDepthFirstMeasur.get();
    }

    public FloatProperty brokenStrImmersionDepthFirstMeasurProperty() {
        return brokenStrImmersionDepthFirstMeasur;
    }

    public void setBrokenStrImmersionDepthFirstMeasur(float brokenStrImmersionDepthFirstMeasur) {
        this.brokenStrImmersionDepthFirstMeasur.set(brokenStrImmersionDepthFirstMeasur);
    }

    public float getBrokenStrImmersionDepthSecondMeasur() {
        return brokenStrImmersionDepthSecondMeasur.get();
    }

    public FloatProperty brokenStrImmersionDepthSecondMeasurProperty() {
        return brokenStrImmersionDepthSecondMeasur;
    }

    public void setBrokenStrImmersionDepthSecondMeasur(float brokenStrImmersionDepthSecondMeasur) {
        this.brokenStrImmersionDepthSecondMeasur.set(brokenStrImmersionDepthSecondMeasur);
    }

    public float getBrokenStrImmersionDepthThirdMeasur() {
        return brokenStrImmersionDepthThirdMeasur.get();
    }

    public FloatProperty brokenStrImmersionDepthThirdMeasurProperty() {
        return brokenStrImmersionDepthThirdMeasur;
    }

    public void setBrokenStrImmersionDepthThirdMeasur(float brokenStrImmersionDepthThirdMeasur) {
        this.brokenStrImmersionDepthThirdMeasur.set(brokenStrImmersionDepthThirdMeasur);
    }

    public float getUndisturbedStrImmersionDepthAverage() {
        return undisturbedStrImmersionDepthAverage.get();
    }

    public FloatProperty undisturbedStrImmersionDepthAverageProperty() {
        return undisturbedStrImmersionDepthAverage;
    }

    public void setUndisturbedStrImmersionDepthAverage(float undisturbedStrImmersionDepthAverage) {
        this.undisturbedStrImmersionDepthAverage.set(undisturbedStrImmersionDepthAverage);
    }

    public float getBrokenStrImmersionDepthAverage() {
        return brokenStrImmersionDepthAverage.get();
    }

    public FloatProperty brokenStrImmersionDepthAverageProperty() {
        return brokenStrImmersionDepthAverage;
    }

    public void setBrokenStrImmersionDepthAverage(float brokenStrImmersionDepthAverage) {
        this.brokenStrImmersionDepthAverage.set(brokenStrImmersionDepthAverage);
    }

    //    public void setFieldValue(BoychenkoConeDTOFieldsEnum field, Object value) {
//        switch (field) {
//            case LABOR_NUMBER:
//                laborNumber = (String) value;
//                break;
//            case UNDISTURBED_STR_IMMERSION_DEPTH_FIRST_MEASUR:
//                undisturbedStrImmersionDepthFirstMeasur = (Float) value;
//                break;
//            case UNDISTURBED_STR_IMMERSION_DEPTH_SECOND_MEASUR:
//                undisturbedStrImmersionDepthSecondMeasur = (Float) value;
//                break;
//            case UNDISTURBED_STR_IMMERSION_DEPTH_THIRD_MEASUR:
//                undisturbedStrImmersionDepthThirdMeasur = (Float) value;
//                break;
//            case BROKEN_STR_IMMERSION_DEPTH_FIRST_MEASUR:
//                brokenStrImmersionDepthFirstMeasur = (Float) value;
//                break;
//            case BROKEN_STR_IMMERSION_DEPTH_SECOND_MEASUR:
//                brokenStrImmersionDepthSecondMeasur = (Float) value;
//                break;
//            case BROKEN_STR_IMMERSION_DEPTH_THIRD_MEASUR:
//                brokenStrImmersionDepthThirdMeasur = (Float) value;
//                break;
//            case UNDISTURBED_STR_IMMERSION_DEPTH_AVERAGE:
//                undisturbedStrImmersionDepthAverage = (Float) value;
//                break;
//            case BROKEN_STR_IMMERSION_DEPTH_AVERAGE:
//                brokenStrImmersionDepthAverage = (Float) value;
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid field: " + field);
//        }
//    }
}
