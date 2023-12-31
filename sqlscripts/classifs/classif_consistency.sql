CREATE TABLE classif_consistency (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    consistency_type varchar,
    robur_number int
);

COMMENT ON TABLE classif_consistency IS 'ВА: T_CNS. Консистенция 08.12.2023';

COMMENT ON COLUMN classif_consistency.id IS 'ВА: N_tip, № типа по CREDO 08.12.2023';
COMMENT ON COLUMN classif_consistency.consistency_type IS 'ВА: T_CNS, тип опробования 08.12.2023';
COMMENT ON COLUMN classif_consistency.robur_number IS 'ВА: R_CNS, № типа по ROBUR 08.12.2023';
