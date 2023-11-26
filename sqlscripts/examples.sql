-- constraints:

SELECT *
FROM information_schema.constraint_table_usage
WHERE table_name = 'ege';

SELECT *
FROM information_schema.constraint_table_usage
WHERE table_name = 'sample';

ALTER TABLE ege
    ADD CONSTRAINT number_not_null UNIQUE (number);

ALTER TABLE gran_composition_areometry DROP CONSTRAINT gran_composition_areometry_sample_id_fkey;
ALTER TABLE sample DROP CONSTRAINT sample_gran_composition_areometry_id_fkey;


ALTER TABLE CL_SOIL_CLASS RENAME COLUMN sc_id TO id;



ALTER TABLE ege
    ALTER COLUMN number SET NOT NULL;

-- ALTER TABLE survey_points
--     RENAME TO survey_point;

ALTER TABLE sample
    ALTER COLUMN point_id SET NOT NULL;

ALTER TABLE water_extract_partial_result ADD COLUMN NO3_txt float4;