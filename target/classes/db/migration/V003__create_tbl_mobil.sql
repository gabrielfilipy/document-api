CREATE TABLE TBL_MOBIL (
    mobil_id bigint NOT NULL AUTO_INCREMENT,
    date_create datetime NOT NULL,
    desc_detalhada TEXT,
    sigla_mobil varchar(20),
    ult_movimentacao_id bigint DEFAULT NULL,
    documento_document_id bigint DEFAULT NULL,

    PRIMARY KEY (mobil_id)
) engine=InnoDB default charset=utf8;

alter table TBL_MOBIL add constraint fk_ultm_movimentacao_documento
foreign key (ult_movimentacao_id) references tbl_movement (movement_id);

alter table TBL_MOBIL add constraint fk_documento
foreign key (documento_document_id) references tbl_document (document_id);