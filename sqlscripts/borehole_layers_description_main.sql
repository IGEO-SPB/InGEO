CREATE TABLE borehole_layers_description_main
    -- FROM (CREDO_SKV_GRN INNER JOIN CREDO_SKV ON CREDO_SKV_GRN.SKV_IND=CREDO_SKV.[No])
-- LEFT JOIN CREDO_GRN ON CREDO_SKV_GRN.GRN_KOD=CREDO_GRN.GRN_KOD
-- ORDER BY CREDO_SKV_GRN.Gl_POD WITH OWNERACCESS OPTION;

-- на вьюхе также из таблицы servey_point (CREDO_SKV):
-- depth (D), глубина
-- absolute_mark_archive (Z), абс.отм., 'Абс.отм.арх. в-р.
-- survey_point_type_id (i_BCD), тип выр-ки
-- point_number (NOMER) выработки, Номер точки исследования в рамках проекта
-- absolute_mark (Z2), абс.отм. новая, 'PointZ'
-- i_NFC (i_NFC), статус  выр-ки

-- из таблицы soil_kind (CREDO_GRN):
--     short_name (NAME_KRAT)
-- grn_code_string (GRN_KOD) + ege.code

(
    id                         int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    number                     int,
    point_id                   int REFERENCES survey_point (id) ON DELETE SET NULL,
    ege_id                     int REFERENCES ege (id) ON DELETE SET NULL,
    layer_bottom_depth         float4 NOT NULL,
    absolute_top_mark          float4,
    layer_power                float4,
    layer_top_depth            float4,
    first_layer_top            float4,
    first_layer_bottom         float4,
--     first_layer_type           varchar,
    first_layer_consistency_id           int REFERENCES classif_consistency (id) ON DELETE SET NULL,
    second_layer_bottom        float4,
--     second_layer_type          varchar,
    second_layer_consistency_id          int REFERENCES classif_consistency (id) ON DELETE SET NULL,
--     hatching_name_credo_autocad varchar,

    hatching_id                int REFERENCES classif_hatching (id) ON DELETE SET NULL,

    color_id                      int REFERENCES cl_color (id) ON DELETE SET NULL,
    water                      float4,

    is_editable_from_ege_list  boolean,

    description_credo_formular varchar,
    description_kga            varchar,

--todo.txt что дублируют следующие поля?
    depth_from                 float4,
    depth_to                   float4,
--     todo.txt разве thickness не может считаться автоматически?
    thickness                  float4,
    abs_mark_from              float4,
    abs_mark_to                float4,

    credo_color                int,
    hatching_credo             varchar,

    is_archive boolean,

    SSA1                         int,
    SSA2                         int,
    SSA3                         int,
    SSA4                         int,
    SSA5                         int,
    SSA6                         int,
    SSA7                         int,
    SSA8                         int,
    SSA9                         int,
    SSA10                        int,
    SSA11                        int,
    SSA12                        int,

    SS1                          int,
    SS2                          int,
    SS3                          int,
    SS4                          int,
    SS5                          int,
    SS6                          int,
    SS7                          int,
    SS8                          int,
    SS9                          int,
    SS10                         int,
    SS11                         int,
    SS12                         int,
    SS13                         int,
    SS14                         int,
    SS15                         int,
    SS16                         int,
    SS17                         int,
    SS18                         int,
    SS19                         int,
    SS20                         int
);

COMMENT ON TABLE borehole_layers_description_main
    IS 'ВА: CREDO_SKV_GRN, Колонка -> Литология и консистенция (послойное описание выработки). '
        'В таблице ВА куча непонятных полей, которые не включены в настоящую таблицу [23.02.2023]';

COMMENT ON COLUMN borehole_layers_description_main.number IS 'BA: Nopp, № п/п [23.02.2023]';
COMMENT ON COLUMN borehole_layers_description_main.layer_bottom_depth IS 'BA: Gl_POD, глубина подошвы слоя (GRN_KOD) [23.02.2023]';

COMMENT ON COLUMN borehole_layers_description_main.absolute_top_mark IS 'BA: ABS_KROV, абс. отметка кровли [23.02.2023]';
COMMENT ON COLUMN borehole_layers_description_main.layer_power IS 'BA: POWER, глубина подошвы слоя (GRN_KOD) [23.02.2023]';
COMMENT ON COLUMN borehole_layers_description_main.layer_top_depth IS 'BA: Gl_KROV, глубина кровли слоя (GRN_KOD) [23.02.2023]';

COMMENT ON COLUMN borehole_layers_description_main.first_layer_top IS 'BA: CNS_Up, верх консистенции [23.02.2023]';
COMMENT ON COLUMN borehole_layers_description_main.first_layer_bottom IS 'BA: CNS_Dn, низ  консистенции [23.02.2023]';
-- COMMENT ON COLUMN borehole_layers_description_main.first_layer_type IS 'BA: TCNS, тип  консистенции [23.02.2023]';
COMMENT ON COLUMN borehole_layers_description_main.second_layer_bottom IS 'BA: CNS_Dn2, низ  консистенции [23.02.2023]';
-- COMMENT ON COLUMN borehole_layers_description_main.second_layer_type IS 'BA: TCNS2, тип  консистенции 2 слоя [23.02.2023]';

-- COMMENT ON COLUMN borehole_layers_description_main.hatching_name_credo_autocad IS 'BA: TCNS2, тип  консистенции 2 слоя [23.02.2023]';
COMMENT ON COLUMN borehole_layers_description_main.color_id IS 'BA: COLOR [23.02.2023]';
COMMENT ON COLUMN borehole_layers_description_main.water IS 'BA: f_G, с гл.хх м -насыщ водой [23.02.2023]';
COMMENT ON COLUMN borehole_layers_description_main.is_editable_from_ege_list IS 'BA: TypName, да-наименование грунта приурочено к скв, '
    'галочка защиты - не переписывать слова и коды из лок списка [23.02.2023]';
COMMENT ON COLUMN borehole_layers_description_main.description_credo_formular IS 'BA: NAME_SL_SKV, наименование грунта полное уточненное для скв. [23.02.2023]';
COMMENT ON COLUMN borehole_layers_description_main.description_kga IS 'BA: Opis, наименование грунта по классиф. [23.02.2023]';

ALTER TABLE borehole_layers_description_main
    ADD COLUMN credo_color int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN hatching_credo varchar;

ALTER TABLE borehole_layers_description_main
    DROP COLUMN hatching_name_credo_autocad;
ALTER TABLE borehole_layers_description_main
    DROP COLUMN first_layer_type;
ALTER TABLE borehole_layers_description_main
    DROP COLUMN second_layer_type;
ALTER TABLE borehole_layers_description_main
    DROP COLUMN color;


ALTER TABLE borehole_layers_description_main
    ADD COLUMN first_layer_consistency_id int REFERENCES classif_consistency (id) ON DELETE SET NULL;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN second_layer_consistency_id int REFERENCES classif_consistency (id) ON DELETE SET NULL;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN hatching_id  int REFERENCES classif_hatching (id) ON DELETE SET NULL;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN color_id  int REFERENCES cl_color (id) ON DELETE SET NULL;

ALTER TABLE borehole_layers_description_main ADD COLUMN is_archive boolean;

ALTER TABLE borehole_layers_description_main ALTER COLUMN layer_bottom_depth SET NOT NULL;

ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS1 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS2 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS3 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS4 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS5 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS6 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS7 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS8 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS9 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS10 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS11 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS12 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS13 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS14 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS15 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS16 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS17 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS18 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS19 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SS20 int;



ALTER TABLE borehole_layers_description_main
    ADD COLUMN SSA1 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SSA2 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SSA3 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SSA4 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SSA5 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SSA6 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SSA7 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SSA8 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SSA9 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SSA10 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SSA11 int;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN SSA12 int;



ALTER TABLE borehole_layers_description_main
    ADD COLUMN soil_class_id int REFERENCES cl_soil_class (id) ON DELETE SET NULL;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN soil_kind_id int REFERENCES cl_soil_kind (id) ON DELETE SET NULL;
ALTER TABLE borehole_layers_description_main
    ADD COLUMN soil_class_kind_group int REFERENCES classif_soil_class_kind_group (id) ON DELETE SET NULL;
