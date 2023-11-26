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

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "areometry")
public class Areometry {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

    @Column(name = "is_sand")
    private Boolean isSand;

    @Column(name = "particle_density")
    private Float particleDensity;

    @Column(name = "total_subsample")
    private Float totalSubsample;

    @Column(name = "undisturbed_sample_water_content")
    private Float undisturbedSampleWaterContent;

    @Column(name = "subsample_wet_soil")
    private Float subsampleWetSoil;

    @Column(name = "weighing_bottle_number")
    private String weighingBottleNumber;

    @Column(name = "empty_weighing_bottle_mass")
    private Float emptyWeighingBottleMass;

    @Column(name = "weighing_bottle_with_wet_soil_mass")
    private Float weighingBottleWithWetSoilMass;

    @Column(name = "weighing_bottle_with_dry_soil_mass")
    private Float weighingBottleWithDrySoilMass;

    @Column(name = "particle_mass_over_10_mm")
    private Float particleMassOver10mm;

    @Column(name = "particle_mass_between_10_and_5_mm")
    private Float particleMassBetween10and5mm;

    @Column(name = "particle_mass_between_5_and_2_mm")
    private Float particleMassBetween5and2mm;

    @Column(name = "particle_mass_between_2_and_1_mm")
    private Float particleMassBetween2and1mm;

    @Column(name = "particle_mass_between_1_and_05_mm")
    private Float particleMassBetween1and05mm;

    @Column(name = "particle_mass_between_05_and_025_mm")
    private Float particleMassBetween05and025mm;

    @Column(name = "particle_mass_between_025_and_01_mm")
    private Float particleMassBetween025and01mm;

    @Column(name = "total_reading_for_particle_size_between_005_and_001")
    private Float totalReadingForParticleSizeBetween005and001;

    @Column(name = "total_reading_for_particle_size_between_001_and_0002")
    private Float totalReadingForParticleSizeBetween001and0002;

    @Column(name = "total_reading_for_particle_size_between_less_0002")
    private Float totalReadingForParticleSizeBetweenLess0002;

    @Column(name = "total_reading_for_particle_size_number_three")
    private Float totalReadingForParticleSizeNumberThree;

    @Column(name = "first_amendment")
    private Float firstAmendment;

    @Column(name = "second_amendment")
    private Float secondAmendment;

    @Column(name = "third_amendment")
    private Float thirdAmendment;

    @Column(name = "fourth_amendment")
    private Float fourthAmendment;
}
