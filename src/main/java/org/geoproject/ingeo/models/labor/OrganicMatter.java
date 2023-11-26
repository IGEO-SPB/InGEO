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
@Table(name = "organic_matter")
public class OrganicMatter {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

    @Column(name = "empty_pot_mass_first_measurement")
    private Float emptyPotMassFirstMeasurement;

    @Column(name = "empty_pot_mass_second_measurement")
    private Float emptyPotMassSecondMeasurement;

    @Column(name = "absolutely_dry_soil_pot_mass_first_measurement")
    private Float absolutelyDrySoilPotMassFirstMeasurement;

    @Column(name = "absolutely_dry_soil_pot_mass_second_measurement")
    private Float absolutelyDrySoilPotMassSecondMeasurement;

    @Column(name = "calcined_soil_pot_mass_first_measurement")
    private Float calcinedSoilPotMassFirstMeasurement;

    @Column(name = "calcined_soil_pot_mass_second_measurement")
    private Float calcinedSoilPotMassSecondMeasurement;

    @Column(name = "ignition_loss_mass_first_measurement")
    private Float ignitionLossMassFirstMeasurement;

    @Column(name = "ignition_loss_mass_second_measurement")
    private Float ignitionLossMassSecondMeasurement;

    @Column(name = "dry_matter_content_before")
    private Float dryMatterContentBefore;

    @Column(name = "dry_matter_content_after")
    private Float dryMatterContentAfter;

    @Column(name = "ignition_loss_average_mass")
    private Float ignitionLossAverageMass;

    @Column(name = "ash_content")
    private Float ashContent;

    @Column(name = "P250")
    private Float P250;

    @Column(name = "decomposition_degree")
    private Float decompositionDegree;
}