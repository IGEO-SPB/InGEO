package org.geoproject.ingeo.models;

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

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "ring_density")
public class RingDensity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

    @Column(name = "ring_number_first_measurement")
    private String ringNumberFirstMeasurement;

    @Column(name = "ring_number_second_measurement")
    private String ringNumberSecondMeasurement;

    @Column(name = "empty_ring_mass_first_measurement")
    private Float emptyRingMassFirstMeasurement;

    @Column(name = "empty_ring_mass_second_measurement")
    private Float emptyRingMassSecondMeasurement;

    @Column(name = "ring_with_wet_soil_mass_first_measurement")
    private Float ringWithWetSoilMassFirstMeasurement;

    @Column(name = "ring_with_wet_soil_mass_second_measurement")
    private Float ringWithWetSoilMassSecondMeasurement;

    @Column(name = "ring_volume_first_measurement")
    private Float ringVolumeFirstMeasurement;

    @Column(name = "ring_volume_second_measurement")
    private Float ringVolumeSecondMeasurement;

    @Column(name = "ring_density_first_measurement")
    private Float ringDensityFirstMeasurement;

    @Column(name = "ring_density_second_measurement")
    private Float ringDensitySecondMeasurement;

    @Column(name = "ring_density_average")
    private Float ringDensityAverage;

    @Column(name = "ring_dry_soil_density")
    private Float ringDrySoilDensity;

    @Column(name = "full_water_content")
    private Float fullWaterContent;

    @Column(name = "saturation_ratio")
    private Float saturationRatio;

    @Column(name = "void_ratio")
    private Float voidRatio;
}
