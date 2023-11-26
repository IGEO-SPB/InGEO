package org.geoproject.ingeo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.geoproject.ingeo.models.labor.Areometry;
import org.geoproject.ingeo.models.labor.BoychenkoCone;
import org.geoproject.ingeo.models.labor.ConstructionMesh;
import org.geoproject.ingeo.models.labor.Density;
import org.geoproject.ingeo.models.labor.GranCompositionAreometry;
import org.geoproject.ingeo.models.labor.GranCompositionConstructionMesh;
import org.geoproject.ingeo.models.labor.OrganicMatter;
import org.geoproject.ingeo.models.labor.PhysicalProperties;
import org.geoproject.ingeo.models.labor.RingDensity;
import org.geoproject.ingeo.models.labor.Shear;
import org.geoproject.ingeo.models.labor.WaterContent;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sample")
public class Sample {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "point_id", referencedColumnName = "id")
    private SurveyPoint surveyPoint;

    //Лабораторный номер образца
    @Column(name = "labor_number")
    private String laborNumber;

    //Мин. глубина отбора образца
    @Column(name = "depth_min")
    private Float depthMin;

    //Макс. глубина отбора образца
    @Column(name = "depth_max")
    private Float depthMax;

    //Дата отбора образца
    @Column(name = "sampling_date")
    private LocalDate samplingDate;

    //Средняя плотность (по двум записям из табл Density)
    @Column(name = "average_density")
    private Float averageDensity;

    //Уровень грунтовых вод, ground_water
    @Column(name = "subterranean_waters_level")
    private Float subterraneanWatersLevel;

    //Структура
    @Column(name = "structure")
    private String structure;

    @Column(name = "is_sand")
    private Boolean isSand;

    //Плотность частиц грунта (Density), возможно дублирующее поле
    @Column(name = "particle_density")
    private String particleDensity;

    //Плотность грунта (Cut | Paraffin), возможно дублирующее поле
    @Column(name = "bulk_density")
    private String bulkDensity;

    //Плотность скелета грунта (Cut | Paraffin)
    @Column(name = "dry_soil_density")
    private Float drySoilDensity;

    //Плотность скелета грунта в пл. сложении
    @Column(name = "dry_soil_density_for_sand_maximal")
    private Float drySoilDensityForSandMaximal;

    //Плотность скелета грунта в рыхлом сложении
    @Column(name = "dry_soil_density_for_sand_minimal")
    private Float drySoilDensityForSandMinimal;

    //Коэффициент пористости (Cut | Paraffin)
    @Column(name = "void_ratio")
    private Float voidRatio;

    /**
     * Природная влажность(Cut | Paraffin)
     * Ранее называлось "Wb, Влажность по бюксу, moistureByWeighingBottle"
     * На форме ВА Влажность по бюксу - то, что в одной таблице с остальными типами влажности,
     * а Природная влажность - в отдельной таблице сверху по центру
     *
     * Поле Влажность по бюксу удалено.
     */
    @Column(name = "natural_water_content")
    private Float naturalWaterContent;

    //Sr, Коэффициент водонасыщения (Cut | Paraffin)
    @Column(name = "saturation_ratio")
    private Float saturationRatio;

    //Wsat, Полная влагоемкость(Cut | Paraffin)
    @Column(name = "full_water_content")
    private Float fullWaterContent;

    //WL, Влажность на границе текучести (Humidity)
    @Column(name = "average_liquid_limit")
    private Float averageLiquidLimit;

    //Wp, Влажность на границе пластичности (Humidity)
    @Column(name = "average_plastic_limit")
    private Float averagePlasticLimit;

    //J, Число пластичности (Humidity)
    @Column(name = "plasticity_index")
    private Float plasticityIndex;

    //IL, Показатель текучести (Humidity)
    @Column(name = "liquidity_index")
    private Float liquidityIndex;

    //Bs, Глубина погружения конуса Бойченко при ненарушенной стр-ре (Cut | Paraffin)
    @Column(name = "boychenko_cone_undisturbed_str_immersion_depth_average")
    private Float boychenkoConeUndisturbedStrImmersionDepthAverage;

    //Bn, Глубина погружения конуса Бойченко при нарушенной стр-ре (Cut | Paraffin)
    @Column(name = "boychenko_cone_broken_str_immersion_depth_average")
    private Float boychenkoConeBrokenStrImmersionDepthAverage;


    //RoMax, Max плотность грунта (Consolidation)
    @Column(name = "maximal_bulk_density")
    private Float maximalBulkDensity;

    //RodMax, Max плотность скелета грунта (Consolidation)
    @Column(name = "maximal_dry_soil_density")
    private Float maximalDrySoilDensity;

    //Wopt, Оптимальная влажность (Consolidation)
    @Column(name = "optimal_moisture")
    private Float optimalMoisture;

    //Kf, Коэффициент фильтрации
    @Column(name = "coefficient_permeability")
    private Float coefficientPermeability;

    //todo
    // При плотности скелета грунта (г/см3)
    // термин вырван из контекста испытания,
    // необходима корректировка во время написания метода
    @Column(name = "rodkf")
    private Float rodKf;

    //Fi0s, Угол естественного откоса в сухом состоянии
    @Column(name = "repose_angle_when_dry")
    private Float reposeAngleWhenDry;

    //Fi0m, Угол естественного откоса под водой
    @Column(name = "repose_angle_under_water")
    private Float reposeAngleUnderWater;

    //TODO: предположительно нужна таблица-классификатор Наименование по отн. содержанию орг. в-ва
    //OrgConz, Отн. содержание орг. в-ва
    @Column(name = "organic_content")
    private Float organicContent;

    //TODO: предположительно нужна таблица-классификатор Наименование по степени разложения торфа
    //Torf_r, Степень разложения торфа
    @Column(name = "decomposition_degree")
    private Float decompositionDegree;

    //TODO: предположительно нужна таблица-классификатор Наименование по степени зольности торфа
    //Torf_z, Степень зольности торфа
    @Column(name = "ash_content")
    private Float ashContent;

    //Gum, Гумус по Тюрингу
    @Column(name = "turing_humus")
    private Float turingHumus;

    //TODO: предположительно нужна таблица-классификатор Наименование по коэффициенту разгоняемости
    //Ksof, Коэффициент разгоняемости
    @Column(name = "soil_softenging_ratio")
    private Float soilSoftengingRatio;

    //TODO: предположительно нужна таблица-классификатор Наименование по коэффициенту выветриваемости
    //Kwr, Коэффициент выветриваемости
    @Column(name = "rock_decomposition_index")
    private Float rockDecompositionIndex;

    //todo проверить, не совпадает ли с чем-нибудь. Возможно, нужна связь с классификатором
    // Судя по таблице Антона связь с классификатором Наименование по коэффициенту выветриваемости
    //KwrType, Вид грунта
    @Column(name = "soil_type")
    private String soilType;

    //ProDro, Прочность по дробимости
    @Column(name = "crushability")
    private Float crushability;

    //ProIst, Прочность по истираемости
    @Column(name = "abrasion_resistance")
    private Float abrasionResistance;

    //Moroz1, Марка по морозостойкости (зам - отт.)
    @Column(name = "frost_resistance_grade_one")
    private Float frostResistanceGradeOne;

    //Moroz2, Марка по морозостойкости (Na2SO4)
    @Column(name = "frost_resistance_grade_two")
    private Float frostResistanceGradeTwo;

    //UESG, Корроз. агр. грунта, Om*m
    @Column(name = "soil_specific_electrical_resistance")
    private Float soilSpecificElectricalResistance;

    //PKT, Корроз. агр. грунта, A/m^2
    @Column(name = "cathode_current_density")
    private Float cathodeCurrentDensity;

    //паспорт-приложение №
    @Column(name = "passport_attachment")
    private String passportAttachment;

    //паспорт -лист№
    @Column(name = "passport_list_number")
    private Integer passportListNumber;

    //для паспорта стабилометра
    @Column(name = "pass_list_CFIKN")
    private String passListCFIKN;

    //для паспорта стабилометра
    @Column(name = "pass_list_CFIKD")
    private String passListCFIKD;

    //для паспорта стабилометра
    @Column(name = "pass_attach_CFIKN")
    private String passAttachCFIKN;

    //для паспорта стабилометра
    @Column(name = "pass_attach_CFIKD")
    private String passAttachCFIKD;

    //тип грунта для паспорта
    @Column(name = "soil_type_for_passport")
    private String soilTypeForPassport;

    @OneToOne(mappedBy = "sample")
    private Areometry areometry;

    @OneToOne(mappedBy = "sample")
    private GranCompositionAreometry granCompositionAreometry;

    @OneToOne(mappedBy = "sample")
    private BoychenkoCone boychenkoCone;

    @OneToOne(mappedBy = "sample")
    private ConstructionMesh constructionMesh;

    @OneToOne(mappedBy = "sample")
    private GranCompositionConstructionMesh granCompositionConstructionMesh;

    @OneToOne(mappedBy = "sample")
    private Density density;

    @OneToOne(mappedBy = "sample")
    private OrganicMatter organicMatter;

    @OneToOne(mappedBy = "sample")
    private PhysicalProperties physicalProperties;

    @OneToOne(mappedBy = "sample")
    private RingDensity ringDensity;

    @OneToOne(mappedBy = "sample")
    private WaterContent waterContent;

//    @OneToMany(mappedBy = "sample", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "sample", fetch = FetchType.EAGER)
    private List<Shear> shearList;

//    @OneToOne(mappedBy = "sample")
//    private WaterExtractPartial waterExtractPartial;
//
//    @OneToOne(mappedBy = "sample")
//    private WaterExtractPartialResult waterExtractPartialResult;

    @Column(name = "is_archive")
    private Boolean isArchive;
}