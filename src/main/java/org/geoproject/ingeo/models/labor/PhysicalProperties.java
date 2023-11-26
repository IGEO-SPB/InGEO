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
@Table(name = "physical_properties")
public class PhysicalProperties {

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

    //Плотность частиц грунта (г/см3)
    @Column(name = "average_density")
    private Float averageDensity;

    //Плотность грунта (г/см3)
    @Column(name = "ring_density_average")
    private Float ringDensityAverage;

    //Плотность скелета грунта (г/см3)
    @Column(name = "ring_dry_soil_density")
    private Float ringDrySoilDensity;

    //Коэффициент пористости (доли ед.)
    @Column(name = "void_ratio")
    private Float voidRatio;

    //Природная влажность (доли ед.)
    @Column(name = "natural_average_water_content")
    private Float naturalAverageWaterContent;

    //Коэффициент водонасыщения
    @Column(name = "saturation_ratio")
    private Float saturationRatio;

    //Полная влагоемкость (доли ед.)
    @Column(name = "full_moisture_capacity")
    private Float fullWaterContent;

    //Влажность на границе текучести (доли ед.)
    @Column(name = "average_liquid_limit")
    private Float averageLiquidLimit;

    //Влажность на границе пластичности (доли ед.)
    //todo заменить на другое поле: plasticLimit - заменено (было - average_plastic_limit)
    @Column(name = "plastic_limit")
    private Float plasticLimit;

    //Число пластичности (доли ед.)
    @Column(name = "plasticity_index")
    private Float plasticityIndex;

    //Разновидность
    @Column(name = "plasticity_soil_sub_type")
    private String plasticitySoilSubType;

    //Показатель текучести (доли ед.)
    @Column(name = "liquidity_index")
    private Float liquidityIndex;

    //Разновидность (консистенция)
    @Column(name = "liquidity_soil_sub_type")
    private String liquiditySoilSubType;

    //    Разновидность глинистых грунтов
    //todo написать расчет
    @Column(name = "clay_soil_sub_type")
    private String claySoilSubType;

    //Глубина погружения конуса Бойченко при ненарушенной стр-ре (мм)
    @Column(name = "undisturbed_str_boychenko_cone_immersion_depth_average")
    private Float undisturbedStrBoychenkoConeImmersionDepthAverage;

    //Показатель текучести по Cb при ненарушенной стр-ре
    //todo написать расчет
    @Column(name = "undisturbed_structure_cb_liquidity_index_name")
    private String undisturbedStructureCbLiquidityIndexName;

    //    Консистенция по Cb при ненарушенной стр-ре (Cut | Paraffin)
    //todo написать расчет
    @Column(name = "undisturbed_structure_cb_consistency")
    private Float undisturbedStructureCbConsistency;

    //Глубина погружения конуса Бойченко при нарушенной стр-ре (мм)
    @Column(name = "broken_str_boychenko_cone_immersion_depth_average")
    private Float brokenStrBoychenkoConeImmersionDepthAverage;

    //Показатель текучести по Cb при нарушенной стр-ре
    //todo написать расчет
    @Column(name = "broken_structure_cb_liquidity_index_name")
    private String brokenStructureCbLiquidityIndexName;

    //Консистенция по Cb при нарушенной стр-ре (Cut | Paraffin)
    //todo написать расчет
    @Column(name = "broken_structure_cb_consistency")
    private Float brokenStructureCbConsistency;

    //Разница показателей текучести (используется, но не нашел в базе)
    //todo написать расчет
    @Column(name = "cb_consistency_difference")
    private Float cbConsistencyDifference;

    //Степень выраженности структурных свойств (используется, но не нашел в базе)
    //todo написать расчет
    @Column(name = "structural_strength_degree")
    private String structuralStrengthDegree;

    //Отн. содержание орг. в-ва (Потери при прокаливании (среднее))
    @Column(name = "ignition_loss_average_mass")
    private Float ignitionLossAverageMass;

    //Наименование по отн. содержанию орг. в-ва
    //todo написать расчет
    @Column(name = "ignition_loss_average_name")
    private String ignitionLossAverageName;

    //Степень разложения торфа (%)
    @Column(name = "decomposition_degree")
    private Float decompositionDegree;

    //Наименование по степени разложения торфа
    //todo написать расчет
    @Column(name = "decomposition_degree_name")
    private String decompositionDegreeName;

    //todo нейминг + коррозионный метод
    //Корроз. агр. грунта, Om*m
    //Корроз. агр. грунта, Om*m
    //Корроз. агр. грунта, A/m^2
    //Корроз. агр. грунта, A/m^2
}
