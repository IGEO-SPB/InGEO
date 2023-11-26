package org.geoproject.ingeo.models.labor;

import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.classificators.WaterGroup;
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

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "water_sample_result")
public class WaterSampleResult {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "labor_number")
    private String laborNumber;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @ManyToOne
    @JoinColumn(name = "survey_point_id")
    private SurveyPoint surveyPoint;

    //Вероятно, не используется
    @ManyToOne
//    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "water_group_id")
    private WaterGroup waterGroup;

    //Водоносный горизонт. Заполняется из камералки
    @Column(name = "aquifer")
    private String aquifer;

    @Column(name = "depth")
    private Float depth;

    //дата отбора пробы воды
    @Column(name = "sampling_date")
    private LocalDate samplingDate;

    //дата поступления пробы воды в лабораторию
    @Column(name = "laboratory_acceptance_date")
    private LocalDate laboratoryAcceptanceDate;

    //прозрачность пробы воды
    @Column(name = "transparency")
    private String transparency;

    //цвет пробы воды
    @Column(name = "color")
    private String color;

    //запах пробы воды
    @Column(name = "odor")
    private String odor;

    @Column(name = "CO3_eq")
    private Float CO3_eq;

    @Column(name = "CL_eq")
    private Float Cl_eq;

    @Column(name = "CL_v")
    private Float Cl_v;

    @Column(name = "SO4")
    private Float SO4_v;

    @Column(name = "NO2")
    private Float NO2_v;

    @Column(name = "NO3")
    private Float NO3_v;

    @Column(name = "an_sum_with_oh")
    private Float anSum;

    @Column(name = "Ca_eq")
    private Float Ca_eq;

    @Column(name = "pH")
    private Float pH;

    @Column(name = "Mg_izm")
    private Float Mg_izm;

    @Column(name = "Mg_eq")
    private Float Mg_eq;

    @Column(name = "Mg_v")
    private Float Mg_v;

    @Column(name = "NH4")
    private Float NH4_v;

    @Column(name = "Fe")
    private Float Fe_v;

    @Column(name = "Na_eq")
    private Float Na_eq;

    @Column(name = "Na_v")
    private Float Na_v;

    @Column(name = "dry")
    private Float dry;

    @Column(name = "hdn_gen")
    private Float hdnGen;

    @Column(name = "hdn_tmp")
    private Float hdnTmp;

    @Column(name = "hdn_con")
    private Float hdnCon;

    @Column(name = "SiO2")
    private Float SiO2;

    @Column(name = "O2")
    private Float O2;

    @Column(name = "CO2sv")
    private Float CO2sv;

    @Column(name = "CO2ag_izm")
    private Float CO2ag_izm;

    //углекислота агрессивная
    @Column(name = "CO2ag")
    private Float CO2ag;

    @Column(name = "H2S")
    private Float H2S;

    @Column(name = "gum")
    private Float gum;

    @Column(name = "cem_slag")
    private Float cemSlag;

    @Column(name = "HCO3_eq")
    private Float HCO3_eq;

    @Column(name = "CL_SO4")
    private Float CL_SO4;

    @Column(name = "cat_sum")
    private Float catSum;

    @Column(name = "HCO3_v")
    private Float HCO3_v;

    @Column(name = "CO3_v")
    private Float CO3_v;

    @Column(name = "Ca_v")
    private Float Ca_v;

    @Column(name = "OH_eq")
    private Float OH_eq;

    @Column(name = "OH_v")
    private Float OH_v;


    @Column(name = "HCO3_eq_proc")
    private Float HCO3_eq_proc;

    @Column(name = "CO3_eq_proc")
    private Float CO3_eq_proc;

    @Column(name = "Cl_eq_proc")
    private Float Cl_eq_proc;

    @Column(name = "SO4_eq")
    private Float SO4_eq;

    @Column(name = "SO4_eq_proc")
    private Float SO4_eq_proc;

    @Column(name = "NO2_eq")
    private Float NO2_eq;

    @Column(name = "NO2_eq_proc")
    private Float NO2_eq_proc;

    @Column(name = "NO3_eq")
    private Float NO3_eq;

    @Column(name = "NO3_eq_proc")
    private Float NO3_eq_proc;

    @Column(name = "Ca_eq_proc")
    private Float Ca_eq_proc;

    @Column(name = "Mg_eq_proc")
    private Float Mg_eq_proc;

    @Column(name = "Na_eq_proc")
    private Float Na_eq_proc;

    @Column(name = "NH4_eq")
    private Float NH4_eq;

    @Column(name = "NH4_eq_proc")
    private Float NH4_eq_proc;

    @Column(name = "Fe_eq")
    private Float Fe_eq;

    @Column(name = "OH_eq_proc")
    private Float OH_eq_proc;

    @Column(name = "ansum_eq_proc")
    private Float anSum_eq_proc;

    @Column(name = "catsum_eq_proc")
    private Float catSum_eq_proc;


    @Column(name = "is_archive")
    private Boolean isArchive;
}
