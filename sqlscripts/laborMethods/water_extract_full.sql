CREATE TABLE water_extract_full
(
    id            int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    sample_id     int NOT NULL REFERENCES sample (id) ON DELETE SET NULL,

    is_archive    boolean,
    is_blocked    boolean,

    depth_from    float4,
    depth_to      float4,
    sampling_date date,

    HCO3_1        float4,
    HCO3_2        float4,
    CO3_1         float4,
    CO3_2         float4,
    Cl_1          float4,
    Cl_2          float4,
    SO4_1         float4,
    SO4_2         float4,
    Ca_1          float4,
    Ca_2          float4,
    Mg_1          float4,
    Mg_2          float4,
    Dry_1         float4,
    Dry_2         float4,
    O2_1          float4,
    O2_2          float4,

    pH            float4,

    cl_coef       float4
)