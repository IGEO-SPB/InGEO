CREATE TABLE classif_construction_type
(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    type varchar
);

COMMENT ON TABLE classif_construction_type IS 'Тип строительства (новая строка, реконструкция и т.д.';