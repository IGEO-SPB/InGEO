CREATE TABLE water_sample_result
(
    id                         int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    labor_number               varchar,
    is_blocked                 boolean,
    survey_point_id            int NOT NULL REFERENCES survey_point (id) ON DELETE SET NULL,

    water_group_id             int REFERENCES classif_water_group (id) ON DELETE SET NULL,

    aquifer                    varchar,
    depth                      float4,
    sampling_date              date,
    laboratory_acceptance_date date,


    transparency               varchar,
    color                      varchar,
    odor                       varchar,


    CO3_eq                     float4,
    CL_eq                      float4,
    CL_v                       float4,
    SO4                        float4,
    NO2                        float4,
    NO3                        float4,
    an_sum_with_oh             float4,
    Ca_eq                      float4,
    pH                         float4,
    Mg_izm                     float4,
    Mg_eq                      float4,
    Mg_v                       float4,
    NH4                        float4,
    Fe                         float4,
    Na_eq                      float4,
    Na_v                       float4,
    dry                        float4,
    hdn_gen                    float4,
    hdn_tmp                    float4,
    hdn_con                    float4,
    SiO2                       float4,
    O2                         float4,
    CO2sv                      float4,
    CO2ag_izm                  float4,
    CO2ag                      float4,
    H2S                        float4,
    gum                        float4,
    cem_slag                   float4,
    HCO3_eq                    float4,
    CL_SO4                     float4,
    cat_sum                    float4,
    HCO3_v                     float4,
    CO3_v                      float4,
    Ca_v                       float4,
    OH_eq                      float4,
    OH_v                       float4,

    HCO3_eq_proc               float4,
    CO3_eq_proc                float4,
    Cl_eq_proc                 float4,
    SO4_eq                     float4,
    SO4_eq_proc                float4,
    NO2_eq                     float4,
    NO2_eq_proc                float4,
    NO3_eq                     float4,
    NO3_eq_proc                float4,
    Ca_eq_proc                 float4,
    Mg_eq_proc                 float4,
    Na_eq_proc                 float4,
    NH4_eq                     float4,
    NH4_eq_proc                float4,
    Fe_eq                      float4,
    OH_eq_proc                 float4,

    anSum_eq_proc              float4,
    catSum_eq_proc             float4,

    is_archive                 boolean
);

COMMENT
    ON COLUMN water_sample_result.water_group_id IS 'Вероятно, не используется. [14.08.2023]';
COMMENT
    ON COLUMN water_sample_result.aquifer IS 'водоносный горизонт. Заполняется из камералки. [14.08.2023]';
COMMENT
    ON COLUMN water_sample_result.sampling_date IS 'дата отбора пробы воды. [14.08.2023]';
COMMENT
    ON COLUMN water_sample_result.laboratory_acceptance_date IS 'дата поступления пробы воды в лабораторию. [14.08.2023]';

COMMENT
    ON COLUMN water_sample_result.transparency IS 'прозрачность пробы воды. [14.08.2023]';
-- "прозрачная";"слабоопалесцирующая";"опалесцирующая";"мутная"
COMMENT
    ON COLUMN water_sample_result.color IS 'цвет пробы воды. [14.08.2023]';
-- "желтый";"бледно-желтый";"серый";"коричневый";"бесцветная"
COMMENT
    ON COLUMN water_sample_result.odor IS 'запах пробы воды. [14.08.2023]';
-- "без запаха";""

COMMENT
    ON COLUMN water_sample_result.co2ag IS 'углекислота агрессивная. [14.08.2023]';


ALTER TABLE water_sample_result
    ADD COLUMN labor_number varchar NOT NULL;

ALTER TABLE water_sample_result
    ADD COLUMN is_active boolean;

ALTER TABLE water_sample_result
    ADD COLUMN is_archive boolean;

ALTER TABLE water_sample_result
    ADD COLUMN is_blocked boolean;

ALTER TABLE water_sample_result
    ADD COLUMN HCO3_eq_proc float4;

ALTER TABLE water_sample_result
    ADD COLUMN CO3_eq_proc float4;

ALTER TABLE water_sample_result
    ADD COLUMN Cl_eq_proc float4;

ALTER TABLE water_sample_result
    ADD COLUMN SO4_eq float4;

ALTER TABLE water_sample_result
    ADD COLUMN SO4_eq_proc float4;

ALTER TABLE water_sample_result
    ADD COLUMN NO2_eq float4;

ALTER TABLE water_sample_result
    ADD COLUMN NO2_eq_proc float4;

ALTER TABLE water_sample_result
    ADD COLUMN NO3_eq float4;

ALTER TABLE water_sample_result
    ADD COLUMN NO3_eq_proc float4;

ALTER TABLE water_sample_result
    ADD COLUMN Ca_eq_proc float4;

ALTER TABLE water_sample_result
    ADD COLUMN Mg_eq_proc float4;

ALTER TABLE water_sample_result
    ADD COLUMN Na_eq_proc float4;

ALTER TABLE water_sample_result
    ADD COLUMN NH4_eq float4;

ALTER TABLE water_sample_result
    ADD COLUMN NH4_eq_proc float4;

ALTER TABLE water_sample_result
    ADD COLUMN Fe_eq float4;

ALTER TABLE water_sample_result
    ADD COLUMN OH_eq_proc float4;

ALTER TABLE water_sample_result
    ADD COLUMN ansum_eq_proc float4;
ALTER TABLE water_sample_result
    ADD COLUMN catsum_eq_proc float4;



ALTER TABLE water_sample_result
    ALTER COLUMN aquifer TYPE varchar;

-- HCO3_eq_proc
-- CO3_eq_proc
-- Cl_eq_proc
-- SO4_eq
-- SO4_eq_proc
-- NO2_eq
-- NO2_eq_proc
-- NO3_eq
-- NO3_eq_proc
-- Ca_eq_proc
-- Mg_eq_proc
-- Na_eq_proc
-- NH4_eq
-- NH4_eq_proc
-- Fe_eq
-- OH_eq_proc

ALTER TABLE water_sample_result
    ALTER COLUMN depth TYPE float4;

ALTER TABLE water_sample_result
    ALTER COLUMN CO3_eq TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN CL_eq TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN CL_v TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN SO4 TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN NO2 TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN NO3 TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN an_sum_with_oh TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN Ca_eq TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN pH TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN Mg_izm TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN Mg_eq TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN Mg_v TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN NH4 TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN Fe TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN Na_eq TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN Na_v TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN dry TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN hdn_gen TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN hdn_tmp TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN hdn_con TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN SiO2 TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN O2 TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN CO2sv TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN CO2ag_izm TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN CO2ag TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN H2S TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN gum TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN cem_slag TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN HCO3_eq TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN CL_SO4 TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN cat_sum TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN HCO3_v TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN CO3_v TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN Ca_v TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN OH_eq TYPE float4;
ALTER TABLE water_sample_result
    ALTER COLUMN OH_v TYPE float4;