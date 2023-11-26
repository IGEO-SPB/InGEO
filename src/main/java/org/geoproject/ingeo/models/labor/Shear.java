package org.geoproject.ingeo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shear")
public class Shear {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

    @Column(name = "is_archive")
    private Boolean isArchive;

    @Column(name = "depth")
    private Float depth;

    @Column(name = "kd")
    private Boolean kd;

    @Column(name = "shear_point_number")
    private Integer shearPointNumber;

    @Column(name = "vertical_loading")
    private Float verticalLoading;

    @Column(name = "shear_strength")
    private Float shearStrength;

    @Column(name = "is_excluded")
    private Boolean isExcluded;

    //то же, что shearRingDensityFirstMeasurement
    @Column(name = "density_before")
    private Float densityBefore;

    //то же, что shearNaturalWaterContentWeighingBottleFirstMeasurement
    @Column(name = "water_content_before")
    private Float waterContentBefore;

    //то же, что shearNaturalWaterContentWeighingBottleSecondMeasurement
    @Column(name = "water_content_after")
    private Float waterContentAfter;

//    @Column(name = "average_density_before")
//    private Float averageDensityBefore;
//
//    @Column(name = "average_water_content_before")
//    private Float averageWaterContentBefore;

    @Column(name = "average_water_content_after")
    private Float averageWaterContentAfter;

    @Column(name = "soil_description")
    private String soilDescription;

    //Следующие десять полей - показатели для каждой точки сдвиговых испытаний в рамках данного образца
    //Т.е. для каждого экземпляра класса Shear (строки в таблице БД) значения свои
    @Column(name = "shear_natural_water_content_weighing_bottle_number_first")
    private String shearNaturalWaterContentWeighingBottleNumberFirstMeasurement;
    @Column(name = "shear_natural_water_content_weighing_bottle_number_second")
    private String shearNaturalWaterContentWeighingBottleNumberSecondMeasurement;
    @Column(name = "shear_natural_water_content_empty_weighing_bottle_mass_first")
    private Float shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement;
    @Column(name = "shear_natural_water_content_empty_weighing_bottle_mass_second")
    private Float shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement;
    @Column(name = "shear_natural_water_c_weighing_bottle_with_wet_soil_mass_first")
    private Float shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement;
    @Column(name = "shear_natural_water_c_weighing_bottle_with_wet_soil_mass_second")
    private Float shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement;
    @Column(name = "shear_natural_water_c_weighing_bottle_with_dry_soil_mass_first")
    private Float shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement;
    @Column(name = "shear_natural_water_c_weighing_bottle_with_dry_soil_mass_second")
    private Float shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement;
    @Column(name = "shear_natural_water_content_weighing_bottle_first")
    private Float shearNaturalWaterContentWeighingBottleFirstMeasurement;
    @Column(name = "shear_natural_water_content_weighing_bottle_second")
    private Float shearNaturalWaterContentWeighingBottleSecondMeasurement;

    //Среднее значение влажности рассчитанное для первого измерения (до опыта)
    //для всех точек сдвиговых испытаний в рамках данного образца
    //Т.е. для всех точек сдвиговых испытаний в рамках данного образца этот показатель одинаковый
    @Column(name = "natural_shear_average_water_content_first_measurement")
    private Float naturalShearAverageWaterContentFirstMeasurement;

    //Следующие двенадцать полей - показатели для каждой точки сдвиговых испытаний в рамках данного образца
    //Т.е. для каждого экземпляра класса Shear (строки в таблице БД) значения свои
    @Column(name = "shear_ring_number_first_measurement")
    private String shearRingNumberFirstMeasurement;
    @Column(name = "shear_ring_number_second_measurement")
    private String shearRingNumberSecondMeasurement;
    @Column(name = "shear_empty_ring_mass_first_measurement")
    private Float shearEmptyRingMassFirstMeasurement;
    @Column(name = "shear_empty_ring_mass_second_measurement")
    private Float shearEmptyRingMassSecondMeasurement;
    @Column(name = "shear_ring_with_wet_soil_mass_first_measurement")
    private Float shearRingWithWetSoilMassFirstMeasurement;
    @Column(name = "shear_ring_with_wet_soil_mass_second_measurement")
    private Float shearRingWithWetSoilMassSecondMeasurement;
    @Column(name = "shear_ring_volume_first_measurement")
    private Float shearRingVolumeFirstMeasurement;
    @Column(name = "shear_ring_volume_second_measurement")
    private Float shearRingVolumeSecondMeasurement;
    @Column(name = "shear_ring_density_first_measurement")
    private Float shearRingDensityFirstMeasurement;
    @Column(name = "shear_ring_density_second_measurement")
    private Float shearRingDensitySecondMeasurement;
    @Column(name = "shear_ring_dry_soil_density_first_measurement")
    private Float shearRingDrySoilDensityFirstMeasurement;
    @Column(name = "shear_ring_dry_soil_density_second_measurement")
    private Float shearRingDrySoilDensitySecondMeasurement;

    //Среднее значение плотности грунта, рассчитанное для первого измерения (до опыта)
    //для всех точек сдвиговых испытаний в рамках данного образца
    //Т.е. для всех точек сдвиговых испытаний в рамках данного образца этот показатель одинаковый
    @Column(name = "shear_ring_density_average_first_measurement")
    private Float shearRingDensityAverageFirstMeasurement;
    //Среднее значение плотности скелета грунта, рассчитанное для первого измерения (до опыта)
    //для всех точек сдвиговых испытаний в рамках данного образца
    //Т.е. для всех точек сдвиговых испытаний в рамках данного образца этот показатель одинаковый
    @Column(name = "shear_ring_dry_soil_density_average_first_measurement")
    private Float shearRingDrySoilDensityAverageFirstMeasurement;


    // diagram block
    @Column(name = "x_sigma_sum")
    private Float xSigmaSum;
    @Column(name = "y_tau_sum")
    private Float yTauSum;
    @Column(name = "xy_production_sum")
    private Float xyProductionSum;
    @Column(name = "x_squared_sum")
    private Float xSquaredSum;
    @Column(name = "tg_fi")
    private Float tgFi;
    @Column(name = "fi")
    private Float Fi;
    @Column(name = "degrees")
    private Float degrees;
    @Column(name = "c")
    private Float c;


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Shear shear = (Shear) o;
//        return Objects.equals(sample, shear.sample) && Objects.equals(shearPointNumber, shear.shearPointNumber);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(sample, shearPointNumber);
//    }
}
