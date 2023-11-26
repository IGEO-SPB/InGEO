CREATE TABLE water_sample
(
    id              int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    labor_number    varchar,
    survey_point_id int NOT NULL REFERENCES survey_point (id) ON DELETE SET NULL,

    is_archive      boolean,
    blocked_from_water_sample_result   boolean,

-- коэффициенты разбавления:

    RHCO3           float4,
    RCO3            float4,
    RCL             float4,
    RNO2            float4,
    RNO3            float4,
    RCa             float4,
    RMg             float4,
    RNH4            float4,
    RFe             float4,
    RSiO2           float4,
    RO2             float4,
    RCO2sv          float4,
    RCO2ag          float4,
    RH2S            float4,

-- ручной ввод:
    HCO3_1          float4,
    HCO3_2          float4,
    OHa_1           float4,
    OHa_2           float4,
    OHb_1           float4,
    OHb_2           float4,
    CO3_1           float4,
    CO3_2           float4,
    CL_1            float4,
    CL_2            float4,
    SO4_1           float4,
    SO4_2           float4,
    NO2             float4,
    NO3             float4,
    Ca_1            float4,
    Ca_2            float4,
    pH              float4,
    Mg_izm_1        float4,
    Mg_izm_2        float4,
    NH4             float4,
    Fe              float4,
    Dry_1           float4,

    Dry_2           float4,
    O2_1            float4,
    O2_2            float4,
    CO2sv_1         float4,
    CO2sv_2         float4,
    CO2ag_1         float4,
    CO2ag_2         float4,


    SiO2_1          float4,
    SiO2_2          float4,
    H2S_1           float4,
    H2S_2           float4,

-- взвешенные вещества ПОСЛЕ опыта
    cem_slag        float4,

-- взвешенные вещества ДО опыта
    cem_sul_res     float4,

    cl_coef         float4
);


COMMENT ON COLUMN water_sample.RHCO3 IS 'Это и прочие поля, начинающиеся с буквы R - коэффициенты разбавления. По умолчанию - "1". [14.08.2023]';
COMMENT ON COLUMN water_sample.cem_slag IS 'Взвешенные вещества ПОСЛЕ опыта. [14.08.2023]';
COMMENT ON COLUMN water_sample.cem_sul_res IS 'Взвешенные вещества ДО опыта. [14.08.2023]';
COMMENT ON COLUMN water_sample.cl_coef IS 'Коэффициент для расчета хлоридов. [14.08.2023]';

ALTER TABLE water_sample
    ADD COLUMN survey_point_id int NOT NULL REFERENCES survey_point (id) ON DELETE SET NULL;

ALTER TABLE water_sample
    ADD COLUMN labor_number varchar NOT NULL;

ALTER TABLE water_sample
    ADD COLUMN is_archive boolean;



ALTER TABLE water_sample
    ADD COLUMN SiO2_1 float4;
ALTER TABLE water_sample
    ADD COLUMN SiO2_2 float4;
ALTER TABLE water_sample
    ADD COLUMN H2S_1 float4;
ALTER TABLE water_sample
    ADD COLUMN H2S_2 float4;

ALTER TABLE water_sample
    ADD COLUMN blocked_from_water_sample_result boolean;



ALTER TABLE water_sample
    ALTER COLUMN CO2ag_2 TYPE float4;

ALTER TABLE water_sample
    ALTER COLUMN cem_slag TYPE float4;

ALTER TABLE water_sample
    ALTER COLUMN cem_sul_res TYPE float4;

ALTER TABLE water_sample
    ALTER COLUMN O2_2 TYPE float4;

ALTER TABLE water_sample
    ALTER COLUMN CO2sv_1 TYPE float4;

ALTER TABLE water_sample
    ALTER COLUMN CO2sv_2 TYPE float4;

ALTER TABLE water_sample
    ALTER COLUMN CO2ag_1 TYPE float4;


-- ALTER TABLE water_sample
--     ADD CONSTRAINT c_name UNIQUE (survey_point_id, labor_number);

ALTER TABLE water_sample
    DROP COLUMN is_active;

ALTER TABLE water_sample
    ALTER COLUMN HCO3_1 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN HCO3_2 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN OHa_1 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN OHa_2 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN OHb_1 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN OHb_2 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN CO3_1 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN CO3_2 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN CL_1 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN CL_2 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN SO4_1 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN SO4_2 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN NO2 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN NO3 TYPE float4;

ALTER TABLE water_sample
    ALTER COLUMN Ca_1 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN Ca_2 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN pH TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN Mg_izm_1 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN Mg_izm_2 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN NH4 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN Fe TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN Dry_1 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN Dry_2 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN O2_1 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN O2_2 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN CO2sv_1 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN CO2sv_2 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN CO2ag_1 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN CO2ag_2 TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN cem_slag TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN cem_sul_res TYPE float4;
ALTER TABLE water_sample
    ALTER COLUMN cl_coef TYPE float4;