CREATE TABLE shear
(
    id                                                              int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    sample_id                                                       int NOT NULL REFERENCES sample (id) ON DELETE SET NULL,
    is_archive                                                      boolean,

    depth                                                           float4,

--     TODO: уточнить, что это:
    kd                                                              boolean,

    shear_point_number                                              int,
    vertical_loading                                                float4,
    shear_strength                                                  float4,

    --     TODO: уточнить, что это:
    is_excluded                                                     boolean,

    density_before                                                  float4,

    water_content_before                                            float4,
    water_content_after                                             float4,
    soil_description                                                varchar,

    average_density_before                                          float4,
    average_water_content_before                                    float4,
    average_water_content_after                                     float4,

    shear_natural_water_content_weighing_bottle_number_first        varchar,
    shear_natural_water_content_weighing_bottle_number_second       varchar,
    shear_natural_water_content_empty_weighing_bottle_mass_first    float4,
    shear_natural_water_content_empty_weighing_bottle_mass_second   float4,
    shear_natural_water_c_weighing_bottle_with_wet_soil_mass_first  float4,
    shear_natural_water_c_weighing_bottle_with_wet_soil_mass_second float4,
    shear_natural_water_c_weighing_bottle_with_dry_soil_mass_first  float4,
    shear_natural_water_c_weighing_bottle_with_dry_soil_mass_second float4,
    shear_natural_water_content_weighing_bottle_first               float4,
    shear_natural_water_content_weighing_bottle_second              float4,
    natural_shear_average_water_content_first_measurement           float4,

    shear_ring_number_first_measurement                             varchar,
    shear_ring_number_second_measurement                            varchar,
    shear_empty_ring_mass_first_measurement                         float4,
    shear_empty_ring_mass_second_measurement                        float4,
    shear_ring_with_wet_soil_mass_first_measurement                 float4,
    shear_ring_with_wet_soil_mass_second_measurement                float4,
    shear_ring_volume_first_measurement                             float4,
    shear_ring_volume_second_measurement                            float4,
    shear_ring_density_first_measurement                            float4,
    shear_ring_density_second_measurement                           float4,
    shear_ring_dry_soil_density_first_measurement                   float4,
    shear_ring_dry_soil_density_second_measurement                  float4,
    shear_ring_density_average_first_measurement                    float4,
    shear_ring_dry_soil_density_average_first_measurement           float4,

--     diagram block
    x_sigma_sum                                                       float4,
    y_tau_sum                                                         float4,
    xy_production_sum                                                 float4,
    x_squared_sum                                                     float4,
    tg_fi                                                            float4,
    fi                                                              float4,
    degrees                                                         float4,
    c                                                               float4
);

ALTER TABLE shear
    ADD COLUMN natural_water_content_weighing_bottle_number_first_measurement varchar;
ALTER TABLE shear
    ADD COLUMN natural_water_content_weighing_bottle_number_second_measurement varchar;

ALTER TABLE shear
    ADD COLUMN natural_water_content_empty_weighing_bottle_mass_first_measur float4;
ALTER TABLE shear
    ADD COLUMN natural_water_content_empty_weighing_bottle_mass_second_measur float4;
ALTER TABLE shear
    ADD COLUMN natural_water_content_weighing_bottle_with_wet_soil_mass_first float4;
ALTER TABLE shear
    ADD COLUMN natural_water_content_weighing_bottle_with_wet_soil_mass_second float4;
ALTER TABLE shear
    ADD COLUMN natural_water_content_weighing_bottle_with_dry_soil_mass_first float4;
ALTER TABLE shear
    ADD COLUMN natural_water_content_weighing_bottle_with_dry_soil_mass_second float4;
ALTER TABLE shear
    ADD COLUMN natural_water_content_weighing_bottle_water_content_first float4;
ALTER TABLE shear
    ADD COLUMN natural_water_content_weighing_bottle_water_content_second float4;

ALTER TABLE shear
    ADD COLUMN natural_shear_average_water_content_first_measurement float4;

ALTER TABLE shear
    ADD COLUMN ring_number_first_measurement varchar;
ALTER TABLE shear
    ADD COLUMN ring_number_second_measurement varchar;

ALTER TABLE shear
    ADD COLUMN empty_ring_mass_first_measurement float4;
ALTER TABLE shear
    ADD COLUMN empty_ring_mass_second_measurement float4;
ALTER TABLE shear
    ADD COLUMN ring_with_wet_soil_mass_first_measurement float4;

ALTER TABLE shear
    ADD COLUMN ring_with_wet_soil_mass_second_measurement float4;
ALTER TABLE shear
    ADD COLUMN ring_volume_first_measurement float4;
ALTER TABLE shear
    ADD COLUMN ring_volume_second_measurement float4;
ALTER TABLE shear
    ADD COLUMN ring_density_first_measurement float4;
ALTER TABLE shear
    ADD COLUMN ring_density_second_measurement float4;
ALTER TABLE shear
    ADD COLUMN ring_dry_soil_density_first_measurement float4;
ALTER TABLE shear
    ADD COLUMN ring_dry_soil_density_second_measurement float4;

ALTER TABLE shear
    ADD COLUMN shear_ring_density_average_first_measurement float4;
ALTER TABLE shear
    ADD COLUMN shear_ring_dry_soil_density_first_measurement float4;


ALTER TABLE shear
    ADD COLUMN x_sigma_sum float4;
ALTER TABLE shear
    ADD COLUMN y_tau_sum float4;
ALTER TABLE shear
    ADD COLUMN xy_production_sum float4;
ALTER TABLE shear
    ADD COLUMN x_squared_sum float4;
ALTER TABLE shear
    ADD COLUMN tg_fi float4;
ALTER TABLE shear
    ADD COLUMN fi float4;
ALTER TABLE shear
    ADD COLUMN degrees float4;
ALTER TABLE shear
    ADD COLUMN c float4;





