package org.geoproject.ingeo.models.labor;

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
import org.geoproject.ingeo.models.SurveyPoint;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "water_sample")
public class WaterSample {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "labor_number")
    private String laborNumber;

    @ManyToOne
    @JoinColumn(name = "survey_point_id")
    private SurveyPoint surveyPoint;

    //region коэффициенты разбавления (названия полей начинаются на 'R')

    @Column(name = "RHCO3")
    private Float RHCO3;

    @Column(name = "RCO3")
    private Float RCO3;

    @Column(name = "RCL")
    private Float RCL;

    @Column(name = "RNO2")
    private Float RNO2;

    @Column(name = "RNO3")
    private Float RNO3;

    @Column(name = "RCa")
    private Float RCa;

    @Column(name = "RMg")
    private Float RMg;

    @Column(name = "RNH4")
    private Float RNH4;

    @Column(name = "RFe")
    private Float RFe;

    @Column(name = "RSiO2")
    private Float RSiO2;

    @Column(name = "RO2")
    private Float RO2;

    @Column(name = "RCO2sv")
    private Float RCO2sv;

    @Column(name = "RCO2ag")
    private Float RCO2ag;

    @Column(name = "RH2S")
    private Float RH2S;

    //endregion

    @Column(name = "HCO3_1")
    private Float HCO3_1;

    @Column(name = "HCO3_2")
    private Float HCO3_2;

    @Column(name = "OHa_1")
    private Float OHa_1;

    @Column(name = "OHa_2")
    private Float OHa_2;

    @Column(name = "OHb_1")
    private Float OHb_1;

    @Column(name = "OHb_2")
    private Float OHb_2;

    @Column(name = "CO3_1")
    private Float CO3_1;

    @Column(name = "CO3_2")
    private Float CO3_2;

    @Column(name = "CL_1")
    private Float Cl_1;

    @Column(name = "CL_2")
    private Float Cl_2;

    @Column(name = "SO4_1")
    private Float SO4_1;

    @Column(name = "SO4_2")
    private Float SO4_2;

    @Column(name = "NO2")
    private Float NO2;

    @Column(name = "NO3")
    private Float NO3;

    @Column(name = "Ca_1")
    private Float Ca_1;

    @Column(name = "Ca_2")
    private Float Ca_2;

    @Column(name = "pH")
    private Float pH;

    @Column(name = "Mg_izm_1")
    private Float Mg_izm_1;

    @Column(name = "Mg_izm_2")
    private Float Mg_izm_2;

    @Column(name = "NH4")
    private Float NH4;

    @Column(name = "Fe")
    private Float Fe;

    //сухое вещество
    @Column(name = "Dry_1")
    private Float dry_1;

    //сухое вещество
    @Column(name = "Dry_2")
    private Float dry_2;

    @Column(name = "O2_1")
    private Float O2_1;

    @Column(name = "O2_2")
    private Float O2_2;

    @Column(name = "CO2sv_1")
    private Float CO2sv_1;

    @Column(name = "CO2sv_2")
    private Float CO2sv_2;

    @Column(name = "CO2ag_1")
    private Float CO2ag_1;

    @Column(name = "CO2ag_2")
    private Float CO2ag_2;

    @Column(name = "SiO2_1")
    private Float SiO2_1;

    @Column(name = "SiO2_2")
    private Float SiO2_2;

    @Column(name = "H2S_1")
    private Float H2S_1;

    @Column(name = "H2S_2")
    private Float H2S_2;

    //взвешенные вещества ПОСЛЕ опыта
    @Column(name = "cem_slag")
    private Float cemSlag;

    //взвешенные вещества ДО опыта
    @Column(name = "cem_sul_res")
    private Float cemSulRes;

    //Коэффициент для расчета хлоридов
    @Column(name = "cl_coef")
    private Float clCoef;

    @Column(name = "is_archive")
    private Boolean isArchive;

    @Column(name = "blocked_from_water_sample_result")
    private Boolean blockedFromWaterSampleResult;
}


