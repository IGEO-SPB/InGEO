ALTER TABLE classif_pot
    ADD CONSTRAINT classif_pot_unique_number UNIQUE ( number  );

ALTER TABLE classif_pot
DROP CONSTRAINT classif_pot_unique_number;