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

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "construction_mesh_areometry")
public class ConstructionMeshAreometry {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

    //Лабораторный номер образца
    @Column(name = "labor_number")
    private String laborNumber;


    //Общая навеска, g1
    @Column(name = "total_subsample")
    private Float totalSubsample;

    //Навеска влажного грунта, g2
    @Column(name = "sieve_dropper_subsample")
    private Float sieveDropperSubsample;

//    Объем цилиндра / cylinder volume
//    Объем пипетки / dropper volume

    //Номер тигля из БД
    @Column(name = "pot_number")
    private String potNumber;

    //Вес тигля (авто), gam2
    @Column(name = "pot_mass")
    private Float potMass;

    //Вес тигля с осадком, gam1
    @Column(name = "pot_mass_with_sediment")
    private Float potMassWithSediment;

    //Размер частиц, вес частиц, Y70
    @Column(name = "mass_X_more_70")
    private Float mass_X_more_70;

    @Column(name = "mass_X_70_40")
    private Float mass_X_70_40;

    @Column(name = "mass_X_40_20")
    private Float mass_X_40_20;

    @Column(name = "mass_X_20_10")
    private Float mass_X_20_10;

    @Column(name = "mass_X_10_5")
    private Float mass_X_10_5;

    @Column(name = "mass_X_5_25")
    private Float mass_X_5_25;

    @Column(name = "mass_X_25_125")
    private Float mass_X_25_125;

    @Column(name = "mass_X_125_063")
    private Float mass_X_125_063;

    @Column(name = "mass_X_063_0315")
    private Float mass_X_063_0315;

    @Column(name = "mass_X_0315_016")
    private Float mass_X_0315_016;

    @Column(name = "mass_X_016_01")
    private Float mass_X_016_01;

    @Column(name = "particle_density")
    private Float particleDensity;

    @Column(name = "dry_soil_subsample")
    private Float drySoilSubsample;

    @Column(name = "reading")
    private Float reading;

    @Column(name = "correction")
    private Float correction;

    @Column(name = "HCl")
    private String HCl;
}


