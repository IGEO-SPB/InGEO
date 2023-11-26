package org.geoproject.ingeo.models.labor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.geoproject.ingeo.models.Sample;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "water_content")
public class WaterContent {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

    @Column(name = "natural_water_content_weighing_bottle_number_first_measurement")
    private String naturalWaterContentWeighingBottleNumberFirstMeasurement;

    @Column(name = "natural_water_content_weighing_bottle_number_second_measurement")
    private String naturalWaterContentWeighingBottleNumberSecondMeasurement;

    @Column(name = "liquidity_weighing_bottle_number_first_measurement")
    private String liquidityWeighingBottleNumberFirstMeasurement;

    @Column(name = "liquidity_weighing_bottle_number_second_measurement")
    private String liquidityWeighingBottleNumberSecondMeasurement;

    @Column(name = "plastic_weighing_bottle_number_first_measurement")
    private String plasticWeighingBottleNumberFirstMeasurement;

    @Column(name = "plastic_weighing_bottle_number_second_measurement")
    private String plasticWeighingBottleNumberSecondMeasurement;

    @Column(name = "natural_water_content_empty_weighing_bottle_mass_first_measur")
    private Float naturalWaterContentEmptyWeighingBottleMassFirstMeasurement;

    @Column(name = "natural_water_content_empty_weighing_bottle_mass_second_measur")
    private Float naturalWaterContentEmptyWeighingBottleMassSecondMeasurement;

    @Column(name = "liquidity_empty_weighing_bottle_mass_first_measurement")
    private Float liquidityEmptyWeighingBottleMassFirstMeasurement;

    @Column(name = "liquidity_empty_weighing_bottle_mass_second_measurement")
    private Float liquidityEmptyWeighingBottleMassSecondMeasurement;

    @Column(name = "plastic_empty_weighing_bottle_mass_first_measurement")
    private Float plasticEmptyWeighingBottleMassFirstMeasurement;

    @Column(name = "plastic_empty_weighing_bottle_mass_second_measurement")
    private Float plasticEmptyWeighingBottleMassSecondMeasurement;

    @Column(name = "natural_water_content_weighing_bottle_with_wet_soil_mass_first")
    private Float naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement;

    @Column(name = "natural_water_content_weighing_bottle_with_wet_soil_mass_second")
    private Float naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement;

    @Column(name = "liquidity_weighing_bottle_with_wet_soil_mass_first")
    private Float liquidityWeighingBottleWithWetSoilMassFirstMeasurement;

    @Column(name = "liquidity_weighing_bottle_with_wet_soil_mass_second")
    private Float liquidityWeighingBottleWithWetSoilMassSecondMeasurement;

    @Column(name = "plastic_weighing_bottle_with_wet_soil_mass_first")
    private Float plasticWeighingBottleWithWetSoilMassFirstMeasurement;

    @Column(name = "plastic_weighing_bottle_with_wet_soil_mass_second")
    private Float plasticWeighingBottleWithWetSoilMassSecondMeasurement;

    @Column(name = "natural_water_content_weighing_bottle_with_dry_soil_mass_first")
    private Float naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement;

    @Column(name = "natural_water_content_weighing_bottle_with_dry_soil_mass_second")
    private Float naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement;

    @Column(name = "liquidity_weighing_bottle_with_dry_soil_mass_first")
    private Float liquidityWeighingBottleWithDrySoilMassFirstMeasurement;

    @Column(name = "liquidity_weighing_bottle_with_dry_soil_mass_second")
    private Float liquidityWeighingBottleWithDrySoilMassSecondMeasurement;

    @Column(name = "plastic_weighing_bottle_with_dry_soil_mass_first")
    private Float plasticWeighingBottleWithDrySoilMassFirstMeasurement;

    @Column(name = "plastic_weighing_bottle_with_dry_soil_mass_second")
    private Float plasticWeighingBottleWithDrySoilMassSecondMeasurement;

    @Column(name = "natural_water_content_weighing_bottle_water_content_first")
    private Float naturalWaterContentWeighingBottleWaterContentFirstMeasurement;

    @Column(name = "natural_water_content_weighing_bottle_water_content_second")
    private Float naturalWaterContentWeighingBottleWaterContentSecondMeasurement;

    @Column(name = "liquidity_weighing_bottle_water_content_first")
    private Float liquidityWeighingBottleWaterContentFirstMeasurement;

    @Column(name = "liquidity_weighing_bottle_water_content_second")
    private Float liquidityWeighingBottleWaterContentSecondMeasurement;

    @Column(name = "plastic_weighing_bottle_water_content_first")
    private Float plasticWeighingBottleWaterContentFirstMeasurement;

    @Column(name = "plastic_weighing_bottle_water_content_second")
    private Float plasticWeighingBottleWaterContentSecondMeasurement;

    @Column(name = "natural_water_content_average_water_content")
    private Float naturalAverageWaterContent;

    @Column(name = "average_liquid_limit")
    private Float averageLiquidLimit;

    @Column(name = "average_plastic_limit")
    private Float averagePlasticLimit;

    @Column(name = "plasticity_index")
    private Float plasticityIndex;

    @Column(name = "liquidity_index")
    private Float liquidityIndex;
}
