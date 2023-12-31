ALTER TABLE classif_pot
    ADD CONSTRAINT classif_pot_unique_number UNIQUE ( number  );

ALTER TABLE classif_pot
DROP CONSTRAINT classif_pot_unique_number;

create table classif_pot
(
    id        integer generated by default as identity
        primary key,
    number    varchar,
    weight    real,
    is_active boolean
);