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

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "gran_composition_areometry")
public class GranCompositionAreometry {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

//    //Лабораторный номер образца
//    @Column(name = "labor_number")
//    private String laborNumber;

//    @Column(name = "survey_point_number")
//    private String surveyPointNumber;

//    @Column(name = "is_sand")
//    private Boolean isSand;
//
//    @Column(name = "particle_density")
//    private Float particleDensity;
//
//    @Column(name = "total_subsample")
//    private Float totalSubsample;
//
//    @Column(name = "undisturbed_sample_water_content")
//    private Float undisturbedSampleWaterContent;
//
//    @Column(name = "subsample_wet_soil")
//    private Float subsampleWetSoil;
//
//    @Column(name = "weighing_bottle_number")
//    private String weighingBottleNumber;
//
//    @Column(name = "empty_weighing_bottle_mass")
//    private Float emptyWeighingBottleMass;
//
//    @Column(name = "weighing_bottle_with_wet_soil_mass")
//    private Float weighingBottleWithWetSoilMass;
//
//    @Column(name = "weighing_bottle_with_dry_soil_mass")
//    private Float weighingBottleWithDrySoilMass;
//
//    @Column(name = "particle_mass_over_10_mm")
//    private Float particleMassOver10mm;
//
//    @Column(name = "particle_mass_between_10_and_5_mm")
//    private Float particleMassBetween10and5mm;
//
//    @Column(name = "particle_mass_between_5_and_2_mm")
//    private Float particleMassBetween5and2mm;
//
//    @Column(name = "particle_mass_between_2_and_1_mm")
//    private Float particleMassBetween2and1mm;
//
//    @Column(name = "particle_mass_between_1_and_05_mm")
//    private Float particleMassBetween1and05mm;
//
//    @Column(name = "particle_mass_between_05_and_025_mm")
//    private Float particleMassBetween05and025mm;
//
//    @Column(name = "particle_mass_between_025_and_01_mm")
//    private Float particleMassBetween025and01mm;
//
//    @Column(name = "total_reading_for_particle_size_between_005_and_001")
//    private Float totalReadingForParticleSizeBetween005and001;
//
//    @Column(name = "total_reading_for_particle_size_between_001_and_0002")
//    private Float totalReadingForParticleSizeBetween001and0002;
//
//    @Column(name = "total_reading_for_particle_size_between_less_0002")
//    private Float totalReadingForParticleSizeBetweenLess0002;
//
//    @Column(name = "total_reading_for_particle_size_number_three")
//    private Float totalReadingForParticleSizeNumberThree;
//
//    @Column(name = "first_amendment")
//    private Float firstAmendment;
//
//    @Column(name = "second_amendment")
//    private Float secondAmendment;
//
//    @Column(name = "third_amendment")
//    private Float thirdAmendment;
//
//    @Column(name = "fourth_amendment")
//    private Float fourthAmendment;



    @Column(name = "included_more_2")
    private Float included_more_2;

    @Column(name = "X_more_10")
    private Float X_more_10;

    @Column(name = "X_10_5_old_10_2")
    private Float X_10_5_old_10_2;

    @Column(name = "X_5_2")
    private Float X_5_2;

    @Column(name = "X_2_1")
    private Float X_2_1;

    @Column(name = "X_1_05")
    private Float X_1_05;

    @Column(name = "X_05_025")
    private Float X_05_025;

    @Column(name = "X_025_01")
    private Float X_025_01;

    @Column(name = "X_01_005")
    private Float X_01_005;

    @Column(name = "X_005_001")
    private Float X_005_001;

    @Column(name = "X_001_0002_old_001_0005")
    private Float X_001_0002_old_001_0005;

    @Column(name = "X_0005_0002")
    private Float X_0005_0002;

    @Column(name = "X_less_0002")
    private Float X_less_0002;

    @Column(name = "sum_2_005")
    private Float sum_2_005;

    @Column(name = "sum_005_0002_old_005_0005")
    private Float sum_005_0002_old_005_0005;

    @Column(name = "sum_less_0002_old_less_0005")
    private Float sum_less_0002_old_less_0005;

    @Column(name = "soil_kind")
    private String soilKind;

    @Column(name = "uniformity_coefficient")
    private Float uniformityCoefficient;

    @Column(name = "uniformity_degree")
    private String uniformityDegree;

    @Column(name = "HCl")
    private Float HCl;

    @Column(name = "dispersity_index")
    private Float dispersityIndex;

    @Column(name = "heaving_degree")
    private String heavingDegree;


}
