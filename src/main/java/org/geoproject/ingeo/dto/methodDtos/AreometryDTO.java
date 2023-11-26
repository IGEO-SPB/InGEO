package org.geoproject.ingeo.dto.methodDtos;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AreometryDTO {
    private String laborNumber;
    private Float voidRatio;

    private StringProperty structure = new SimpleStringProperty();

    private FloatProperty particleDensity = new SimpleFloatProperty();
    private FloatProperty totalSubsample = new SimpleFloatProperty();
    private FloatProperty undisturbedSampleWaterContent = new SimpleFloatProperty();
    private FloatProperty subsampleWetSoil = new SimpleFloatProperty();

    private StringProperty weighingBottleNumber = new SimpleStringProperty();
    private FloatProperty emptyWeighingBottleMass = new SimpleFloatProperty();
    private FloatProperty weighingBottleWithWetSoilMass = new SimpleFloatProperty();
    private FloatProperty weighingBottleWithDrySoilMass = new SimpleFloatProperty();

    private FloatProperty particleMassOver10mm = new SimpleFloatProperty();
    private FloatProperty particleMassBetween10and5mm = new SimpleFloatProperty();
    private FloatProperty particleMassBetween5and2mm = new SimpleFloatProperty();
    private FloatProperty particleMassBetween2and1mm = new SimpleFloatProperty();
    private FloatProperty particleMassBetween1and05mm = new SimpleFloatProperty();
    private FloatProperty particleMassBetween05and025mm = new SimpleFloatProperty();
    private FloatProperty particleMassBetween025and01mm = new SimpleFloatProperty();
    private FloatProperty totalReadingForParticleSizeBetween005and001 = new SimpleFloatProperty();
    private FloatProperty totalReadingForParticleSizeBetween001and0002 = new SimpleFloatProperty();
    private FloatProperty totalReadingForParticleSizeBetweenLess0002 = new SimpleFloatProperty();
    private FloatProperty totalReadingForParticleSizeNumberThree = new SimpleFloatProperty();
    private FloatProperty firstAmendment = new SimpleFloatProperty();
    private FloatProperty secondAmendment = new SimpleFloatProperty();
    private FloatProperty thirdAmendment = new SimpleFloatProperty();
    private FloatProperty fourthAmendment = new SimpleFloatProperty();

    private BooleanProperty isSand = new SimpleBooleanProperty();

//    private Float X_more_10;
//    private Float X_10_5_old_10_2;
//    private Float X_5_2;
//    private Float X_2_1;
//    private Float X_1_05;
//    private Float X_05_025;
//    private Float X_025_01;
//    private Float X_01_005;
//    private Float X_005_001;
//    private Float X_001_0002_old_001_0005;
//    private Float X_0005_0002;
//    private Float X_less_0002;
//    private Float sum_2_005;
//    private Float sum_005_0002_old_005_0005;
//    private Float sum_less_0002_old_less_0005;
//    private String soilKind;
//    private Float uniformityCoefficient;
//    private String uniformityDegree;
//    private Float HCl;
//    private Float dispersityIndex;
//    private String heavingDegree;


    public String getLaborNumber() {
        return laborNumber;
    }

    public void setLaborNumber(String laborNumber) {
        this.laborNumber = laborNumber;
    }

    public Float getVoidRatio() {
        return voidRatio;
    }

    public void setVoidRatio(Float voidRatio) {
        this.voidRatio = voidRatio;
    }

    public String getStructure() {
        return structure.get();
    }

    public StringProperty structureProperty() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure.set(structure);
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

    public float getTotalSubsample() {
        return totalSubsample.get();
    }

    public FloatProperty totalSubsampleProperty() {
        return totalSubsample;
    }

    public void setTotalSubsample(float totalSubsample) {
        this.totalSubsample.set(totalSubsample);
    }

    public float getUndisturbedSampleWaterContent() {
        return undisturbedSampleWaterContent.get();
    }

    public FloatProperty undisturbedSampleWaterContentProperty() {
        return undisturbedSampleWaterContent;
    }

    public void setUndisturbedSampleWaterContent(float undisturbedSampleWaterContent) {
        this.undisturbedSampleWaterContent.set(undisturbedSampleWaterContent);
    }

    public float getSubsampleWetSoil() {
        return subsampleWetSoil.get();
    }

    public FloatProperty subsampleWetSoilProperty() {
        return subsampleWetSoil;
    }

    public void setSubsampleWetSoil(float subsampleWetSoil) {
        this.subsampleWetSoil.set(subsampleWetSoil);
    }

    public String getWeighingBottleNumber() {
        return weighingBottleNumber.get();
    }

    public StringProperty weighingBottleNumberProperty() {
        return weighingBottleNumber;
    }

    public void setWeighingBottleNumber(String weighingBottleNumber) {
        this.weighingBottleNumber.set(weighingBottleNumber);
    }

    public float getEmptyWeighingBottleMass() {
        return emptyWeighingBottleMass.get();
    }

    public FloatProperty emptyWeighingBottleMassProperty() {
        return emptyWeighingBottleMass;
    }

    public void setEmptyWeighingBottleMass(float emptyWeighingBottleMass) {
        this.emptyWeighingBottleMass.set(emptyWeighingBottleMass);
    }

    public float getWeighingBottleWithWetSoilMass() {
        return weighingBottleWithWetSoilMass.get();
    }

    public FloatProperty weighingBottleWithWetSoilMassProperty() {
        return weighingBottleWithWetSoilMass;
    }

    public void setWeighingBottleWithWetSoilMass(float weighingBottleWithWetSoilMass) {
        this.weighingBottleWithWetSoilMass.set(weighingBottleWithWetSoilMass);
    }

    public float getWeighingBottleWithDrySoilMass() {
        return weighingBottleWithDrySoilMass.get();
    }

    public FloatProperty weighingBottleWithDrySoilMassProperty() {
        return weighingBottleWithDrySoilMass;
    }

    public void setWeighingBottleWithDrySoilMass(float weighingBottleWithDrySoilMass) {
        this.weighingBottleWithDrySoilMass.set(weighingBottleWithDrySoilMass);
    }

    public float getParticleMassOver10mm() {
        return particleMassOver10mm.get();
    }

    public FloatProperty particleMassOver10mmProperty() {
        return particleMassOver10mm;
    }

    public void setParticleMassOver10mm(float particleMassOver10mm) {
        this.particleMassOver10mm.set(particleMassOver10mm);
    }

    public float getParticleMassBetween10and5mm() {
        return particleMassBetween10and5mm.get();
    }

    public FloatProperty particleMassBetween10and5mmProperty() {
        return particleMassBetween10and5mm;
    }

    public void setParticleMassBetween10and5mm(float particleMassBetween10and5mm) {
        this.particleMassBetween10and5mm.set(particleMassBetween10and5mm);
    }

    public float getParticleMassBetween5and2mm() {
        return particleMassBetween5and2mm.get();
    }

    public FloatProperty particleMassBetween5and2mmProperty() {
        return particleMassBetween5and2mm;
    }

    public void setParticleMassBetween5and2mm(float particleMassBetween5and2mm) {
        this.particleMassBetween5and2mm.set(particleMassBetween5and2mm);
    }

    public float getParticleMassBetween2and1mm() {
        return particleMassBetween2and1mm.get();
    }

    public FloatProperty particleMassBetween2and1mmProperty() {
        return particleMassBetween2and1mm;
    }

    public void setParticleMassBetween2and1mm(float particleMassBetween2and1mm) {
        this.particleMassBetween2and1mm.set(particleMassBetween2and1mm);
    }

    public float getParticleMassBetween1and05mm() {
        return particleMassBetween1and05mm.get();
    }

    public FloatProperty particleMassBetween1and05mmProperty() {
        return particleMassBetween1and05mm;
    }

    public void setParticleMassBetween1and05mm(float particleMassBetween1and05mm) {
        this.particleMassBetween1and05mm.set(particleMassBetween1and05mm);
    }

    public float getParticleMassBetween05and025mm() {
        return particleMassBetween05and025mm.get();
    }

    public FloatProperty particleMassBetween05and025mmProperty() {
        return particleMassBetween05and025mm;
    }

    public void setParticleMassBetween05and025mm(float particleMassBetween05and025mm) {
        this.particleMassBetween05and025mm.set(particleMassBetween05and025mm);
    }

    public float getParticleMassBetween025and01mm() {
        return particleMassBetween025and01mm.get();
    }

    public FloatProperty particleMassBetween025and01mmProperty() {
        return particleMassBetween025and01mm;
    }

    public void setParticleMassBetween025and01mm(float particleMassBetween025and01mm) {
        this.particleMassBetween025and01mm.set(particleMassBetween025and01mm);
    }

    public float getTotalReadingForParticleSizeBetween005and001() {
        return totalReadingForParticleSizeBetween005and001.get();
    }

    public FloatProperty totalReadingForParticleSizeBetween005and001Property() {
        return totalReadingForParticleSizeBetween005and001;
    }

    public void setTotalReadingForParticleSizeBetween005and001(float totalReadingForParticleSizeBetween005and001) {
        this.totalReadingForParticleSizeBetween005and001.set(totalReadingForParticleSizeBetween005and001);
    }

    public float getTotalReadingForParticleSizeBetween001and0002() {
        return totalReadingForParticleSizeBetween001and0002.get();
    }

    public FloatProperty totalReadingForParticleSizeBetween001and0002Property() {
        return totalReadingForParticleSizeBetween001and0002;
    }

    public void setTotalReadingForParticleSizeBetween001and0002(float totalReadingForParticleSizeBetween001and0002) {
        this.totalReadingForParticleSizeBetween001and0002.set(totalReadingForParticleSizeBetween001and0002);
    }

    public float getTotalReadingForParticleSizeBetweenLess0002() {
        return totalReadingForParticleSizeBetweenLess0002.get();
    }

    public FloatProperty totalReadingForParticleSizeBetweenLess0002Property() {
        return totalReadingForParticleSizeBetweenLess0002;
    }

    public void setTotalReadingForParticleSizeBetweenLess0002(float totalReadingForParticleSizeBetweenLess0002) {
        this.totalReadingForParticleSizeBetweenLess0002.set(totalReadingForParticleSizeBetweenLess0002);
    }

    public float getTotalReadingForParticleSizeNumberThree() {
        return totalReadingForParticleSizeNumberThree.get();
    }

    public FloatProperty totalReadingForParticleSizeNumberThreeProperty() {
        return totalReadingForParticleSizeNumberThree;
    }

    public void setTotalReadingForParticleSizeNumberThree(float totalReadingForParticleSizeNumberThree) {
        this.totalReadingForParticleSizeNumberThree.set(totalReadingForParticleSizeNumberThree);
    }

    public float getFirstAmendment() {
        return firstAmendment.get();
    }

    public FloatProperty firstAmendmentProperty() {
        return firstAmendment;
    }

    public void setFirstAmendment(float firstAmendment) {
        this.firstAmendment.set(firstAmendment);
    }

    public float getSecondAmendment() {
        return secondAmendment.get();
    }

    public FloatProperty secondAmendmentProperty() {
        return secondAmendment;
    }

    public void setSecondAmendment(float secondAmendment) {
        this.secondAmendment.set(secondAmendment);
    }

    public float getThirdAmendment() {
        return thirdAmendment.get();
    }

    public FloatProperty thirdAmendmentProperty() {
        return thirdAmendment;
    }

    public void setThirdAmendment(float thirdAmendment) {
        this.thirdAmendment.set(thirdAmendment);
    }

    public float getFourthAmendment() {
        return fourthAmendment.get();
    }

    public FloatProperty fourthAmendmentProperty() {
        return fourthAmendment;
    }

    public void setFourthAmendment(float fourthAmendment) {
        this.fourthAmendment.set(fourthAmendment);
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
}
