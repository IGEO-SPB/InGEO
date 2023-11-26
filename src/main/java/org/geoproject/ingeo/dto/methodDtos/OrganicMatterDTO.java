package org.geoproject.ingeo.dto.methodDtos;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class OrganicMatterDTO {

    private String laborNumber;

    private FloatProperty emptyPotMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty emptyPotMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty absolutelyDrySoilPotMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty absolutelyDrySoilPotMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty calcinedSoilPotMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty calcinedSoilPotMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty ignitionLossMassFirstMeasurement = new SimpleFloatProperty();
    private FloatProperty ignitionLossMassSecondMeasurement = new SimpleFloatProperty();
    private FloatProperty dryMatterContentBefore = new SimpleFloatProperty();
    private FloatProperty dryMatterContentAfter = new SimpleFloatProperty();
    private FloatProperty ignitionLossAverageMass = new SimpleFloatProperty();
    private FloatProperty ashContent = new SimpleFloatProperty();
    private FloatProperty P250 = new SimpleFloatProperty();
    private FloatProperty decompositionDegree = new SimpleFloatProperty();

    public String getLaborNumber() {
        return laborNumber;
    }

    public void setLaborNumber(String laborNumber) {
        this.laborNumber = laborNumber;
    }

    public float getEmptyPotMassFirstMeasurement() {
        return emptyPotMassFirstMeasurement.get();
    }

    public FloatProperty emptyPotMassFirstMeasurementProperty() {
        return emptyPotMassFirstMeasurement;
    }

    public void setEmptyPotMassFirstMeasurement(float emptyPotMassFirstMeasurement) {
        this.emptyPotMassFirstMeasurement.set(emptyPotMassFirstMeasurement);
    }

    public float getEmptyPotMassSecondMeasurement() {
        return emptyPotMassSecondMeasurement.get();
    }

    public FloatProperty emptyPotMassSecondMeasurementProperty() {
        return emptyPotMassSecondMeasurement;
    }

    public void setEmptyPotMassSecondMeasurement(float emptyPotMassSecondMeasurement) {
        this.emptyPotMassSecondMeasurement.set(emptyPotMassSecondMeasurement);
    }

    public float getAbsolutelyDrySoilPotMassFirstMeasurement() {
        return absolutelyDrySoilPotMassFirstMeasurement.get();
    }

    public FloatProperty absolutelyDrySoilPotMassFirstMeasurementProperty() {
        return absolutelyDrySoilPotMassFirstMeasurement;
    }

    public void setAbsolutelyDrySoilPotMassFirstMeasurement(float absolutelyDrySoilPotMassFirstMeasurement) {
        this.absolutelyDrySoilPotMassFirstMeasurement.set(absolutelyDrySoilPotMassFirstMeasurement);
    }

    public float getAbsolutelyDrySoilPotMassSecondMeasurement() {
        return absolutelyDrySoilPotMassSecondMeasurement.get();
    }

    public FloatProperty absolutelyDrySoilPotMassSecondMeasurementProperty() {
        return absolutelyDrySoilPotMassSecondMeasurement;
    }

    public void setAbsolutelyDrySoilPotMassSecondMeasurement(float absolutelyDrySoilPotMassSecondMeasurement) {
        this.absolutelyDrySoilPotMassSecondMeasurement.set(absolutelyDrySoilPotMassSecondMeasurement);
    }

    public float getCalcinedSoilPotMassFirstMeasurement() {
        return calcinedSoilPotMassFirstMeasurement.get();
    }

    public FloatProperty calcinedSoilPotMassFirstMeasurementProperty() {
        return calcinedSoilPotMassFirstMeasurement;
    }

    public void setCalcinedSoilPotMassFirstMeasurement(float calcinedSoilPotMassFirstMeasurement) {
        this.calcinedSoilPotMassFirstMeasurement.set(calcinedSoilPotMassFirstMeasurement);
    }

    public float getCalcinedSoilPotMassSecondMeasurement() {
        return calcinedSoilPotMassSecondMeasurement.get();
    }

    public FloatProperty calcinedSoilPotMassSecondMeasurementProperty() {
        return calcinedSoilPotMassSecondMeasurement;
    }

    public void setCalcinedSoilPotMassSecondMeasurement(float calcinedSoilPotMassSecondMeasurement) {
        this.calcinedSoilPotMassSecondMeasurement.set(calcinedSoilPotMassSecondMeasurement);
    }

    public float getIgnitionLossMassFirstMeasurement() {
        return ignitionLossMassFirstMeasurement.get();
    }

    public FloatProperty ignitionLossMassFirstMeasurementProperty() {
        return ignitionLossMassFirstMeasurement;
    }

    public void setIgnitionLossMassFirstMeasurement(float ignitionLossMassFirstMeasurement) {
        this.ignitionLossMassFirstMeasurement.set(ignitionLossMassFirstMeasurement);
    }

    public float getIgnitionLossMassSecondMeasurement() {
        return ignitionLossMassSecondMeasurement.get();
    }

    public FloatProperty ignitionLossMassSecondMeasurementProperty() {
        return ignitionLossMassSecondMeasurement;
    }

    public void setIgnitionLossMassSecondMeasurement(float ignitionLossMassSecondMeasurement) {
        this.ignitionLossMassSecondMeasurement.set(ignitionLossMassSecondMeasurement);
    }

    public float getDryMatterContentBefore() {
        return dryMatterContentBefore.get();
    }

    public FloatProperty dryMatterContentBeforeProperty() {
        return dryMatterContentBefore;
    }

    public void setDryMatterContentBefore(float dryMatterContentBefore) {
        this.dryMatterContentBefore.set(dryMatterContentBefore);
    }

    public float getDryMatterContentAfter() {
        return dryMatterContentAfter.get();
    }

    public FloatProperty dryMatterContentAfterProperty() {
        return dryMatterContentAfter;
    }

    public void setDryMatterContentAfter(float dryMatterContentAfter) {
        this.dryMatterContentAfter.set(dryMatterContentAfter);
    }

    public float getIgnitionLossAverageMass() {
        return ignitionLossAverageMass.get();
    }

    public FloatProperty ignitionLossAverageMassProperty() {
        return ignitionLossAverageMass;
    }

    public void setIgnitionLossAverageMass(float ignitionLossAverageMass) {
        this.ignitionLossAverageMass.set(ignitionLossAverageMass);
    }

    public float getAshContent() {
        return ashContent.get();
    }

    public FloatProperty ashContentProperty() {
        return ashContent;
    }

    public void setAshContent(float ashContent) {
        this.ashContent.set(ashContent);
    }

    public float getP250() {
        return P250.get();
    }

    public FloatProperty p250Property() {
        return P250;
    }

    public void setP250(float p250) {
        this.P250.set(p250);
    }

    public float getDecompositionDegree() {
        return decompositionDegree.get();
    }

    public FloatProperty decompositionDegreeProperty() {
        return decompositionDegree;
    }

    public void setDecompositionDegree(float decompositionDegree) {
        this.decompositionDegree.set(decompositionDegree);
    }

    //    allOrganicMatterTextFieldMap.put(emptyPotMassFirstMeasurement, organicMatterDTO.emptyPotMassFirstMeasurementProperty());
//        allOrganicMatterTextFieldMap.put(emptyPotMassSecondMeasurement, organicMatterDTO.emptyPotMassSecondMeasurementProperty());
//        allOrganicMatterTextFieldMap.put(absolutelyDrySoilPotMassFirstMeasurement, organicMatterDTO.absolutelyDrySoilPotMassFirstMeasurementProperty());


//    public void setFieldMap(OrganicMatterDTOFieldsEnum field, Object value) {
//        switch (field) {
//            case LABOR_NUMBER:
//                laborNumber = (String) value;
//                break;
//            case SURVEY_POINT_NUMBER:
//                surveyPointNumber = (String) value;
//                break;
//            case DEPTH_MIN:
//                depthMin = (Float) value;
//                break;
}
