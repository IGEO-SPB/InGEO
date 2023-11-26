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
@Table(name = "gran_composition_construction_mesh_areometry")
public class GranCompositionConstructionMeshAreometry {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "sample_id", referencedColumnName = "id")
    private Sample sample;

    //расчетные

    //Зерновой состав в % при размере частиц в мм, Y70
    @Column(name = "X_more_70")
    private Float X_more_70;

    @Column(name = "X_70_40")
    private Float X_70_40;

    @Column(name = "X_40_20")
    private Float X_40_20;

    @Column(name = "X_20_10")
    private Float X_20_10;

    @Column(name = "X_10_5")
    private Float X_10_5;

    @Column(name = "X_5_25")
    private Float X_5_25;

    @Column(name = "X_25_125")
    private Float X_25_125;

    @Column(name = "X_125_063")
    private Float X_125_063;

    @Column(name = "X_063_0315")
    private Float X_063_0315;

    @Column(name = "X_0315_016")
    private Float X_0315_016;

    @Column(name = "X_016_01")
    private Float X_016_01;

    @Column(name = "X_01_005")
    private Float X_01_005;

    @Column(name = "X_less_005")
    private Float X_less_005;

    //нет в таблице на UI
    @Column(name = "correction")
    private Float correction;

    //Коэффициент неоднородности, Cn
    @Column(name = "uniformity_coefficient")
    private Float uniformityCoefficient;

    //Степень неоднородности, Stn
    @Column(name = "uniformity_degree")
    private String uniformityDegree;

    //Модуль крупности песка, Mk
    @Column(name = "fineness_modulus")
    private Float finenessModulus;

    //Группа песка по модулю крупности, GMk
    @Column(name = "sand_group_by_fineness_modulus")
    private String sandGroupByFinenessModulus;
}
