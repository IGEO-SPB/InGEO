CREATE TABLE genesis
(
    id          int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    gi_id       int,
    gi_code     varchar,
    gi_code_uni varchar,
--     в представлении ВА gi_name - описание генезиса
    gi_name     varchar,
    gi_color_r  int,
    gi_color_g  int,
    gi_color_b  int,
    gi_note     varchar,
    gi_color    varchar,
    gi_order    varchar
);

COMMENT ON TABLE genesis IS 'ВА: CL_GEO_INDEX, Колонка -> Локальный список грунтов -> Генезис. '
    'Названия полей (кроме id) совпадают с названиями полей ВА, только в нижнем регистре [15.02.2023]';


-- COMMENT ON COLUMN genesis.gi_id IS 'NAME_KRAT, наименование грунта краткое (таблица ВА) [14.02.2023]';

INSERT INTO genesis (gi_id, gi_name, gi_color, gi_order)
VALUES (68, 'Девонские породы', 'H', 'kga_admin');

INSERT INTO genesis (gi_id, gi_name, gi_color_r, gi_color_g, gi_color_b, gi_color, gi_order)
VALUES (69, 'Девонские породы', 94, 94, 174, 'H', 'kga_admin');

INSERT INTO genesis (gi_id, gi_code, gi_code_uni, gi_name, gi_color_r, gi_color_g, gi_color_b, gi_color, gi_order)
VALUES (70, 'D2 el', 'D2 el', 'Девонские породы', 94, 94, 174, 'H', 'kga_admin');

INSERT INTO genesis (gi_id, gi_code, gi_code_uni, gi_name, gi_color_r, gi_color_g, gi_color_b, gi_note, gi_color,
                     gi_order)
VALUES (71, 'D¶ el', 'D2 el', 'Девонские породы', 94, 94, 174, 'Девонские породы', 'A', 'kga_admin');

-- INSERT INTO genesis (gi_id, gi_code, gi_code_uni, gi_name, gi_color_r, gi_color_g, gi_color_b, gi_note, gi_color, gi_order)
-- VALUES ();

-- todo.txt заполнить таблицу genesis