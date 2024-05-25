CREATE TABLE tbl_mobil (
    mobil_id bigint NOT NULL AUTO_INCREMENT,
    subscritor_id bigint NOT NULL,
    date_create datetime NOT NULL,
    desc_detalhada TEXT,
    sigla_mobil varchar(20),
    ult_movimentacao_id int UNIQUE,
    documento_document_id int UNIQUE,

    PRIMARY KEY (mobil_id)
);