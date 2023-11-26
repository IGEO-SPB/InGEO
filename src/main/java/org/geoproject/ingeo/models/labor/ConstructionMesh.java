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
@Table(name = "construction_mesh")
public class ConstructionMesh {

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

    @Column(name = "mass_X_25_2")
    private Float mass_X_25_2;

    @Column(name = "mass_X_2_125")
    private Float mass_X_2_125;

    @Column(name = "mass_X_125_1")
    private Float mass_X_125_1;

    @Column(name = "mass_X_1_063")
    private Float mass_X_1_063;

    @Column(name = "mass_X_063_050")
    private Float mass_X_063_050;

    @Column(name = "mass_X_050_0315")
    private Float mass_X_050_0315;

    @Column(name = "mass_X_0315_025")
    private Float mass_X_0315_025;

    @Column(name = "mass_X_025_016")
    private Float mass_X_025_016;

    @Column(name = "mass_X_016_01")
    private Float mass_X_016_01;

//    @Column(name = "mass_X_01_005")
//    private Float mass_X_01_005;
//
//    @Column(name = "mass_X_less_005")
//    private Float mass_X_less_005;

    //Реакция с HCl
    @Column(name = "HCl")
    private String HCl;

//    //расчетные
//
//    //Зерновой состав в % при размере частиц в мм, Y70
//    @Column(name = "X_more_70")
//    private Float X_more_70;
//
//    @Column(name = "X_70_40")
//    private Float X_70_40;
//
//    @Column(name = "X_40_20")
//    private Float X_40_20;
//
//    @Column(name = "X_20_10")
//    private Float X_20_10;
//
//    @Column(name = "X_10_5")
//    private Float X_10_5;
//
//    @Column(name = "X_5_25")
//    private Float X_5_25;
//
//    @Column(name = "X_25_2")
//    private Float X_25_2;
//
//    @Column(name = "X_2_125")
//    private Float X_2_125;
//
//    @Column(name = "X_125_1")
//    private Float X_125_1;
//
//    @Column(name = "X_1_063")
//    private Float X_1_063;
//
//    @Column(name = "X_063_050")
//    private Float X_063_050;
//
//    @Column(name = "X_050_0315")
//    private Float X_050_0315;
//
//    @Column(name = "X_0315_025")
//    private Float X_0315_025;
//
//    @Column(name = "X_025_016")
//    private Float X_025_016;
//
//    @Column(name = "X_016_01")
//    private Float X_016_01;
//
//    @Column(name = "X_01_005")
//    private Float X_01_005;
//
//    @Column(name = "X_less_005")
//    private Float X_less_005;
//
//    //нет в таблице на UI
//    @Column(name = "correction")
//    private Float correction;
//
//    @Column(name = "soil_kind")
//    private String soilKind;
//
//    //Коэффициент неоднородности, Cn
//    @Column(name = "uniformity_coefficient")
//    private Float uniformityCoefficient;
//
//    //Степень неоднородности, Stn
//    @Column(name = "uniformity_degree")
//    private String uniformityDegree;
//
//    //Модуль крупности песка, Mk
//    @Column(name = "fineness_modulus")
//    private Float finenessModulus;
//
//    //Группа песка по модулю крупности, GMk
//    @Column(name = "sand_group_by_fineness_modulus")
//    private String sandGroupByFinenessModulus;
//
//    //Линзы и прослои, LP
//    @Column(name = "lenses_and_seams")
//    private String lensesAndSeams;

//    @Column(name = "is_sand")
//    private boolean isSand;
}

//    laborNumber;
//
//
//    totalSubsample;
//
//    sieveDropperSubsample;
//
////    cylinderVolume
////    dropperVolume
//
//            potNumber
//
//    potMass;
//
//    potMassWithSediment;
//
//    mass_X_more_70;
//
//    mass_X_70_40;
//
//    mass_X_40_20;
//
//    mass_X_20_10;
//
//    mass_X_10_5;
//
//    mass_X_5_25;
//
//    mass_X_25_2;
//
//    mass_X_2_125;
//
//    mass_X_125_1;
//
//    mass_X_1_063;
//
//    mass_X_063_050;
//
//    mass_X_050_0315;
//
//    mass_X_0315_025;
//
//    mass_X_025_016;
//
//    mass_X_016_01;
//
//    mass_X_01_005;
//
//    mass_X_less_005;
//
//    String HCl;
//
//
//    X_more_70;
//
//    X_70_40;
//
//    X_40_20;
//
//    X_20_10;
//
//    X_10_5;
//
//    X_5_25;
//
//    X_25_2;
//
//    X_2_125;
//
//    X_125_1;
//
//    X_1_063;
//
//    X_063_050;
//
//    X_050_0315;
//
//    X_0315_025;
//
//    X_025_016;
//
//    X_016_01;
//
//    X_01_005;
//
//    X_less_005;
//
//    correction;
//
//    String soilKind;
//
//    uniformityCoefficient;
//
//    String uniformityDegree;
//
//    finenessModulus;
//
//    String sandGroupByFinenessModulus;
//
//    String lensesAndSeams;

