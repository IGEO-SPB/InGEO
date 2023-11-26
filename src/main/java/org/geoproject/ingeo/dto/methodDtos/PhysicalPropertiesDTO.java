package org.geoproject.ingeo.dto.methodDtos;

import org.geoproject.ingeo.enums.dtoenums.PhysicalPropertiesDTOFieldsEnum;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhysicalPropertiesDTO {

    private String laborNumber;

    private String surveyPointNumber;

    private float depthMin;

    private float depthMax;

    private float sum_2_005;
    private float sum_005_0002_old_005_0005;
    private float sum_less_0002_old_less_0005;

    private boolean isSand;


    //Плотность частиц грунта (г/см3)
//    private float averageDensity;
    private FloatProperty averageDensity = new SimpleFloatProperty();

    //Плотность грунта (г/см3)
//    private float ringDensityAverage;
    private FloatProperty ringDensityAverage = new SimpleFloatProperty();

    //Плотность скелета грунта (г/см3)
//    private float ringDrySoilDensity;
    private FloatProperty ringDrySoilDensity = new SimpleFloatProperty();

    //Коэффициент пористости (доли ед.)
//    private float voidRatio;
    private FloatProperty voidRatio = new SimpleFloatProperty();

    //Природная влажность (доли ед.)
//    private float naturalAverageWaterContent;
    private FloatProperty naturalAverageWaterContent = new SimpleFloatProperty();

    //Коэффициент водонасыщения
//    private float saturationRatio;
    private FloatProperty saturationRatio = new SimpleFloatProperty();

    //Полная влагоемкость (доли ед.)
//    private float fullWaterContent;
    private FloatProperty fullWaterContent = new SimpleFloatProperty();

    //Влажность на границе текучести (доли ед.)
//    private float averageLiquidLimit;
    private FloatProperty averageLiquidLimit = new SimpleFloatProperty();

    //Влажность на границе пластичности (доли ед.)
//    private float plasticLimit;
    private FloatProperty plasticLimit = new SimpleFloatProperty();

    //Число пластичности (доли ед.)
//    private float plasticityIndex;
    private FloatProperty plasticityIndex = new SimpleFloatProperty();

    //Разновидность
    private String plasticitySoilSubType;

    //Показатель текучести (доли ед.)
//    private float liquidityIndex;
    private FloatProperty liquidityIndex = new SimpleFloatProperty();

    //Разновидность (консистенция)
    private String liquiditySoilSubType;

    //    Разновидность глинистых грунтов
    private String claySoilSubType;

    //Глубина погружения конуса Бойченко при ненарушенной стр-ре (мм)
    private float undisturbedStrBoychenkoConeImmersionDepthAverage;

    //Показатель текучести по Cb при ненарушенной стр-ре
    private String undisturbedStructureCbLiquidityIndexName;

    //    Консистенция по Cb при ненарушенной стр-ре (Cut | Paraffin)
    private float undisturbedStructureCbConsistency;

    //Глубина погружения конуса Бойченко при нарушенной стр-ре (мм)
    private float brokenStrBoychenkoConeImmersionDepthAverage;

    //Показатель текучести по Cb при нарушенной стр-ре
    private String brokenStructureCbLiquidityIndexName;

    //Консистенция по Cb при нарушенной стр-ре (Cut | Paraffin)
    private float brokenStructureCbConsistency;

    //Разница показателей текучести (используется, но не нашел в базе)
    private float cbConsistencyDifference;

    //Степень выраженности структурных свойств (используется, но не нашел в базе)
    private String structuralStrengthDegree;

    //Отн. содержание орг. в-ва (Потери при прокаливании (среднее))
    private float ignitionLossAverageMass;

    //Наименование по отн. содержанию орг. в-ва
    private String ignitionLossAverageName;

    //Степень разложения торфа (%)
    private float decompositionDegree;

    //Наименование по степени разложения торфа
    private String decompositionDegreeName;

    //todo нейминг + коррозионный метод
    //Корроз. агр. грунта, Om*m
    //Корроз. агр. грунта, Om*m
    //Корроз. агр. грунта, A/m^2
    //Корроз. агр. грунта, A/m^2


    public void setFieldValue(PhysicalPropertiesDTOFieldsEnum field, Object value) {
        switch (field) {
            case LABOR_NUMBER -> laborNumber = (String) value;
            case SURVEY_POINT_NUMBER -> surveyPointNumber = (String) value;
            case DEPTH_MIN -> depthMin = (Float) value;
            case DEPTH_MAX -> depthMax = (Float) value;
//            case AVERAGE_DENSITY -> averageDensity = (Float) value;
            case AVERAGE_DENSITY -> setAverageDensity((Float) value);
            case RING_DENSITY_AVERAGE -> setRingDensityAverage((Float) value);
            case RING_DRY_SOIL_DENSITY -> setRingDrySoilDensity((Float) value);
            case VOID_RATIO -> setVoidRatio((Float) value);
            case NATURAL_AVERAGE_WATER_CONTENT -> setNaturalAverageWaterContent((Float) value);
            case SATURATION_RATIO -> setSaturationRatio((Float) value);
            case FULL_WATER_CONTENT -> setFullWaterContent((Float) value);
            case AVERAGE_LIQUID_LIMIT -> setAverageLiquidLimit((Float) value);
            case AVERAGE_PLASTIC_LIMIT -> setPlasticLimit((Float) value);
            case PLASTICITY_INDEX -> setPlasticityIndex((Float) value);
            case LIQUIDITY_INDEX -> setLiquidityIndex((Float) value);
            case UNDISTURBED_STR_BOYCHENKO_CONE_IMMERSION_DEPTH_AVERAGE ->
                    undisturbedStrBoychenkoConeImmersionDepthAverage = (Float) value;
            case UNDISTURBED_STRUCTURE_CB_LIQUIDITY_INDEX_NAME ->
                    undisturbedStructureCbLiquidityIndexName = (String) value;
            case UNDISTURBED_STRUCTURE_CB_CONSISTENCY -> undisturbedStructureCbConsistency = (Float) value;
            case BROKEN_STR_BOYCHENKO_CONE_IMMERSION_DEPTH_AVERAGE ->
                    brokenStrBoychenkoConeImmersionDepthAverage = (Float) value;
            case BROKEN_STRUCTURE_CB_LIQUIDITY_INDEX_NAME -> brokenStructureCbLiquidityIndexName = (String) value;
            case BROKEN_STRUCTURE_CB_CONSISTENCY -> brokenStructureCbConsistency = (Float) value;
            case CB_CONSISTENCY_DIFFERENCE -> cbConsistencyDifference = (Float) value;
            case STRUCTURAL_STRENGTH_DEGREE -> structuralStrengthDegree = (String) value;
            case IGNITION_LOSS_AVERAGE_MASS -> ignitionLossAverageMass = (Float) value;
            case IGNITION_LOSS_AVERAGE_NAME -> ignitionLossAverageName = (String) value;
            case DECOMPOSITION_DEGREE -> decompositionDegree = (Float) value;
            case DECOMPOSITION_DEGREE_NAME -> decompositionDegreeName = (String) value;
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        }
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

    public float getNaturalAverageWaterContent() {
        return naturalAverageWaterContent.get();
    }

    public FloatProperty naturalAverageWaterContentProperty() {
        return naturalAverageWaterContent;
    }

    public void setNaturalAverageWaterContent(float naturalAverageWaterContent) {
        this.naturalAverageWaterContent.set(naturalAverageWaterContent);
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

    public float getFullWaterContent() {
        return fullWaterContent.get();
    }

    public FloatProperty fullWaterContentProperty() {
        return fullWaterContent;
    }

    public void setFullWaterContent(float fullWaterContent) {
        this.fullWaterContent.set(fullWaterContent);
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

    public float getPlasticLimit() {
        return plasticLimit.get();
    }

    public FloatProperty plasticLimitProperty() {
        return plasticLimit;
    }

    public void setPlasticLimit(float plasticLimit) {
        this.plasticLimit.set(plasticLimit);
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
}
