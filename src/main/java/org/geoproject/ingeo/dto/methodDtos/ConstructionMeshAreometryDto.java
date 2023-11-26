package org.geoproject.ingeo.dto.methodDtos;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

public class ConstructionMeshAreometryDto {

    @Getter
    @Setter
    private String laborNumber;
    @Getter
    @Setter
    private String surveyPointNumber;
    @Getter
    @Setter
    private Float depthMin;
    @Getter
    @Setter
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
    private FloatProperty mass_X_25_125 = new SimpleFloatProperty();
    private FloatProperty mass_X_125_063 = new SimpleFloatProperty();
    private FloatProperty mass_X_063_0315 = new SimpleFloatProperty();
    private FloatProperty mass_X_0315_016= new SimpleFloatProperty();
    private FloatProperty mass_X_016_01= new SimpleFloatProperty();
    private FloatProperty particleDensity= new SimpleFloatProperty();
    private FloatProperty drySoilSubsample= new SimpleFloatProperty();
    private FloatProperty reading= new SimpleFloatProperty();
    private FloatProperty correction= new SimpleFloatProperty();
    private StringProperty HCl = new SimpleStringProperty();
    private BooleanProperty isSand = new SimpleBooleanProperty();


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

    public float getMass_X_25_125() {
        return mass_X_25_125.get();
    }

    public FloatProperty mass_X_25_125Property() {
        return mass_X_25_125;
    }

    public void setMass_X_25_125(float mass_X_25_125) {
        this.mass_X_25_125.set(mass_X_25_125);
    }

    public float getMass_X_125_063() {
        return mass_X_125_063.get();
    }

    public FloatProperty mass_X_125_063Property() {
        return mass_X_125_063;
    }

    public void setMass_X_125_063(float mass_X_125_063) {
        this.mass_X_125_063.set(mass_X_125_063);
    }

    public float getMass_X_063_0315() {
        return mass_X_063_0315.get();
    }

    public FloatProperty mass_X_063_0315Property() {
        return mass_X_063_0315;
    }

    public void setMass_X_063_0315(float mass_X_063_0315) {
        this.mass_X_063_0315.set(mass_X_063_0315);
    }

    public float getMass_X_0315_016() {
        return mass_X_0315_016.get();
    }

    public FloatProperty mass_X_0315_016Property() {
        return mass_X_0315_016;
    }

    public void setMass_X_0315_016(float mass_X_0315_016) {
        this.mass_X_0315_016.set(mass_X_0315_016);
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

    public float getParticleDensity() {
        return particleDensity.get();
    }

    public FloatProperty particleDensityProperty() {
        return particleDensity;
    }

    public void setParticleDensity(float particleDensity) {
        this.particleDensity.set(particleDensity);
    }

    public float getDrySoilSubsample() {
        return drySoilSubsample.get();
    }

    public FloatProperty drySoilSubsampleProperty() {
        return drySoilSubsample;
    }

    public void setDrySoilSubsample(float drySoilSubsample) {
        this.drySoilSubsample.set(drySoilSubsample);
    }

    public float getReading() {
        return reading.get();
    }

    public FloatProperty readingProperty() {
        return reading;
    }

    public void setReading(float reading) {
        this.reading.set(reading);
    }

    public float getCorrection() {
        return correction.get();
    }

    public FloatProperty correctionProperty() {
        return correction;
    }

    public void setCorrection(float correction) {
        this.correction.set(correction);
    }
}