package org.geoproject.ingeo.dto.methodDtos;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConstructionMeshDTO {
    private String laborNumber;
    private String surveyPointNumber;
    private Float depthMin;
    private Float depthMax;

    private FloatProperty totalSubsample = new SimpleFloatProperty();
    private FloatProperty sieveDropperSubsample = new SimpleFloatProperty();

    private StringProperty potNumber = new SimpleStringProperty();
    private FloatProperty potMass = new SimpleFloatProperty();
    private FloatProperty potMassWithSediment = new SimpleFloatProperty();
    private FloatProperty mass_X_more_70 = new SimpleFloatProperty();
    private FloatProperty mass_X_70_40 = new SimpleFloatProperty();
    private FloatProperty mass_X_40_20 = new SimpleFloatProperty();
    private FloatProperty mass_X_20_10 = new SimpleFloatProperty();
    private FloatProperty mass_X_10_5 = new SimpleFloatProperty();
    private FloatProperty mass_X_5_25 = new SimpleFloatProperty();
    private FloatProperty mass_X_25_2 = new SimpleFloatProperty();
    private FloatProperty mass_X_2_125 = new SimpleFloatProperty();
    private FloatProperty mass_X_125_1 = new SimpleFloatProperty();
    private FloatProperty mass_X_1_063 = new SimpleFloatProperty();
    private FloatProperty mass_X_063_050 = new SimpleFloatProperty();
    private FloatProperty mass_X_050_0315 = new SimpleFloatProperty();
    private FloatProperty mass_X_0315_025 = new SimpleFloatProperty();
    private FloatProperty mass_X_025_016 = new SimpleFloatProperty();
    private FloatProperty mass_X_016_01 = new SimpleFloatProperty();
    private StringProperty HCl = new SimpleStringProperty();
    private BooleanProperty isSand = new SimpleBooleanProperty();


//    private float X_more_70;
//    private float X_70_40;
//    private float X_40_20;
//    private float X_20_10;
//    private float X_10_5;
//    private float X_5_25;
//    private float X_25_2;
//    private float X_2_125;
//    private float X_125_1;
//    private float X_1_063;
//    private float X_063_050;
//    private float X_050_0315;
//    private float X_0315_025;
//    private float X_025_016;
//    private float X_016_01;
//    private float X_01_005;
//    private float X_less_005;
//    private float correction;
//    private String soilKind;
//    private float uniformityCoefficient;
//    private String uniformityDegree;
//    private float finenessModulus;
//    private String sandGroupByFinenessModulus;
//    private String lensesAndSeams;



    public String getLaborNumber() {
        return laborNumber;
    }

    public void setLaborNumber(String laborNumber) {
        this.laborNumber = laborNumber;
    }

    public String getSurveyPointNumber() {
        return surveyPointNumber;
    }

    public void setSurveyPointNumber(String surveyPointNumber) {
        this.surveyPointNumber = surveyPointNumber;
    }

    public Float getDepthMin() {
        return depthMin;
    }

    public void setDepthMin(Float depthMin) {
        this.depthMin = depthMin;
    }

    public Float getDepthMax() {
        return depthMax;
    }

    public void setDepthMax(Float depthMax) {
        this.depthMax = depthMax;
    }

    public float getTotalSubsample() {
        return totalSubsample.get();
    }

    public FloatProperty totalSubsampleProperty() {
        return totalSubsample;
    }

    public void setTotalSubsample(float totalSubsample) {
        this.totalSubsample.set(totalSubsample);
    }

    public float getSieveDropperSubsample() {
        return sieveDropperSubsample.get();
    }

    public FloatProperty sieveDropperSubsampleProperty() {
        return sieveDropperSubsample;
    }

    public void setSieveDropperSubsample(float sieveDropperSubsample) {
        this.sieveDropperSubsample.set(sieveDropperSubsample);
    }

    public String getPotNumber() {
        return potNumber.get();
    }

    public StringProperty potNumberProperty() {
        return potNumber;
    }

    public void setPotNumber(String potNumber) {
        this.potNumber.set(potNumber);
    }

    public float getPotMass() {
        return potMass.get();
    }

    public FloatProperty potMassProperty() {
        return potMass;
    }

    public void setPotMass(float potMass) {
        this.potMass.set(potMass);
    }

    public float getPotMassWithSediment() {
        return potMassWithSediment.get();
    }

    public FloatProperty potMassWithSedimentProperty() {
        return potMassWithSediment;
    }

    public void setPotMassWithSediment(float potMassWithSediment) {
        this.potMassWithSediment.set(potMassWithSediment);
    }

    public float getMass_X_more_70() {
        return mass_X_more_70.get();
    }

    public FloatProperty mass_X_more_70Property() {
        return mass_X_more_70;
    }

    public void setMass_X_more_70(float mass_X_more_70) {
        this.mass_X_more_70.set(mass_X_more_70);
    }

    public float getMass_X_70_40() {
        return mass_X_70_40.get();
    }

    public FloatProperty mass_X_70_40Property() {
        return mass_X_70_40;
    }

    public void setMass_X_70_40(float mass_X_70_40) {
        this.mass_X_70_40.set(mass_X_70_40);
    }

    public float getMass_X_40_20() {
        return mass_X_40_20.get();
    }

    public FloatProperty mass_X_40_20Property() {
        return mass_X_40_20;
    }

    public void setMass_X_40_20(float mass_X_40_20) {
        this.mass_X_40_20.set(mass_X_40_20);
    }

    public float getMass_X_20_10() {
        return mass_X_20_10.get();
    }

    public FloatProperty mass_X_20_10Property() {
        return mass_X_20_10;
    }

    public void setMass_X_20_10(float mass_X_20_10) {
        this.mass_X_20_10.set(mass_X_20_10);
    }

    public float getMass_X_10_5() {
        return mass_X_10_5.get();
    }

    public FloatProperty mass_X_10_5Property() {
        return mass_X_10_5;
    }

    public void setMass_X_10_5(float mass_X_10_5) {
        this.mass_X_10_5.set(mass_X_10_5);
    }

    public float getMass_X_5_25() {
        return mass_X_5_25.get();
    }

    public FloatProperty mass_X_5_25Property() {
        return mass_X_5_25;
    }

    public void setMass_X_5_25(float mass_X_5_25) {
        this.mass_X_5_25.set(mass_X_5_25);
    }

    public float getMass_X_25_2() {
        return mass_X_25_2.get();
    }

    public FloatProperty mass_X_25_2Property() {
        return mass_X_25_2;
    }

    public void setMass_X_25_2(float mass_X_25_2) {
        this.mass_X_25_2.set(mass_X_25_2);
    }

    public float getMass_X_2_125() {
        return mass_X_2_125.get();
    }

    public FloatProperty mass_X_2_125Property() {
        return mass_X_2_125;
    }

    public void setMass_X_2_125(float mass_X_2_125) {
        this.mass_X_2_125.set(mass_X_2_125);
    }

    public float getMass_X_125_1() {
        return mass_X_125_1.get();
    }

    public FloatProperty mass_X_125_1Property() {
        return mass_X_125_1;
    }

    public void setMass_X_125_1(float mass_X_125_1) {
        this.mass_X_125_1.set(mass_X_125_1);
    }

    public float getMass_X_1_063() {
        return mass_X_1_063.get();
    }

    public FloatProperty mass_X_1_063Property() {
        return mass_X_1_063;
    }

    public void setMass_X_1_063(float mass_X_1_063) {
        this.mass_X_1_063.set(mass_X_1_063);
    }

    public float getMass_X_063_050() {
        return mass_X_063_050.get();
    }

    public FloatProperty mass_X_063_050Property() {
        return mass_X_063_050;
    }

    public void setMass_X_063_050(float mass_X_063_050) {
        this.mass_X_063_050.set(mass_X_063_050);
    }

    public float getMass_X_050_0315() {
        return mass_X_050_0315.get();
    }

    public FloatProperty mass_X_050_0315Property() {
        return mass_X_050_0315;
    }

    public void setMass_X_050_0315(float mass_X_050_0315) {
        this.mass_X_050_0315.set(mass_X_050_0315);
    }

    public float getMass_X_0315_025() {
        return mass_X_0315_025.get();
    }

    public FloatProperty mass_X_0315_025Property() {
        return mass_X_0315_025;
    }

    public void setMass_X_0315_025(float mass_X_0315_025) {
        this.mass_X_0315_025.set(mass_X_0315_025);
    }

    public float getMass_X_025_016() {
        return mass_X_025_016.get();
    }

    public FloatProperty mass_X_025_016Property() {
        return mass_X_025_016;
    }

    public void setMass_X_025_016(float mass_X_025_016) {
        this.mass_X_025_016.set(mass_X_025_016);
    }

    public float getMass_X_016_01() {
        return mass_X_016_01.get();
    }

    public FloatProperty mass_X_016_01Property() {
        return mass_X_016_01;
    }

    public void setMass_X_016_01(float mass_X_016_01) {
        this.mass_X_016_01.set(mass_X_016_01);
    }

    public String getHCl() {
        return HCl.get();
    }

    public StringProperty HClProperty() {
        return HCl;
    }

    public void setHCl(String HCl) {
        this.HCl.set(HCl);
    }

    public boolean isIsSand() {
        return isSand.get();
    }

    public BooleanProperty isSandProperty() {
        return isSand;
    }

    public void setIsSand(boolean isSand) {
        this.isSand.set(isSand);
    }

//
//    public float getX_more_70() {
//        return X_more_70;
//    }
//
//    public void setX_more_70(float x_more_70) {
//        X_more_70 = x_more_70;
//    }
//
//    public float getX_70_40() {
//        return X_70_40;
//    }
//
//    public void setX_70_40(float x_70_40) {
//        X_70_40 = x_70_40;
//    }
//
//    public float getX_40_20() {
//        return X_40_20;
//    }
//
//    public void setX_40_20(float x_40_20) {
//        X_40_20 = x_40_20;
//    }
//
//    public float getX_20_10() {
//        return X_20_10;
//    }
//
//    public void setX_20_10(float x_20_10) {
//        X_20_10 = x_20_10;
//    }
//
//    public float getX_10_5() {
//        return X_10_5;
//    }
//
//    public void setX_10_5(float x_10_5) {
//        X_10_5 = x_10_5;
//    }
//
//    public float getX_5_25() {
//        return X_5_25;
//    }
//
//    public void setX_5_25(float x_5_25) {
//        X_5_25 = x_5_25;
//    }
//
//    public float getX_25_2() {
//        return X_25_2;
//    }
//
//    public void setX_25_2(float x_25_2) {
//        X_25_2 = x_25_2;
//    }
//
//    public float getX_2_125() {
//        return X_2_125;
//    }
//
//    public void setX_2_125(float x_2_125) {
//        X_2_125 = x_2_125;
//    }
//
//    public float getX_125_1() {
//        return X_125_1;
//    }
//
//    public void setX_125_1(float x_125_1) {
//        X_125_1 = x_125_1;
//    }
//
//    public float getX_1_063() {
//        return X_1_063;
//    }
//
//    public void setX_1_063(float x_1_063) {
//        X_1_063 = x_1_063;
//    }
//
//    public float getX_063_050() {
//        return X_063_050;
//    }
//
//    public void setX_063_050(float x_063_050) {
//        X_063_050 = x_063_050;
//    }
//
//    public float getX_050_0315() {
//        return X_050_0315;
//    }
//
//    public void setX_050_0315(float x_050_0315) {
//        X_050_0315 = x_050_0315;
//    }
//
//    public float getX_0315_025() {
//        return X_0315_025;
//    }
//
//    public void setX_0315_025(float x_0315_025) {
//        X_0315_025 = x_0315_025;
//    }
//
//    public float getX_025_016() {
//        return X_025_016;
//    }
//
//    public void setX_025_016(float x_025_016) {
//        X_025_016 = x_025_016;
//    }
//
//    public float getX_016_01() {
//        return X_016_01;
//    }
//
//    public void setX_016_01(float x_016_01) {
//        X_016_01 = x_016_01;
//    }
//
//    public float getX_01_005() {
//        return X_01_005;
//    }
//
//    public void setX_01_005(float x_01_005) {
//        X_01_005 = x_01_005;
//    }
//
//    public float getX_less_005() {
//        return X_less_005;
//    }
//
//    public void setX_less_005(float x_less_005) {
//        X_less_005 = x_less_005;
//    }
//
//    public float getCorrection() {
//        return correction;
//    }
//
//    public void setCorrection(float correction) {
//        this.correction = correction;
//    }
//
//    public String getSoilKind() {
//        return soilKind;
//    }
//
//    public void setSoilKind(String soilKind) {
//        this.soilKind = soilKind;
//    }
//
//    public float getUniformityCoefficient() {
//        return uniformityCoefficient;
//    }
//
//    public void setUniformityCoefficient(float uniformityCoefficient) {
//        this.uniformityCoefficient = uniformityCoefficient;
//    }
//
//    public String getUniformityDegree() {
//        return uniformityDegree;
//    }
//
//    public void setUniformityDegree(String uniformityDegree) {
//        this.uniformityDegree = uniformityDegree;
//    }
//
//    public float getFinenessModulus() {
//        return finenessModulus;
//    }
//
//    public void setFinenessModulus(float finenessModulus) {
//        this.finenessModulus = finenessModulus;
//    }
//
//    public String getSandGroupByFinenessModulus() {
//        return sandGroupByFinenessModulus;
//    }
//
//    public void setSandGroupByFinenessModulus(String sandGroupByFinenessModulus) {
//        this.sandGroupByFinenessModulus = sandGroupByFinenessModulus;
//    }
//
//    public String getLensesAndSeams() {
//        return lensesAndSeams;
//    }
//
//    public void setLensesAndSeams(String lensesAndSeams) {
//        this.lensesAndSeams = lensesAndSeams;
//    }
}