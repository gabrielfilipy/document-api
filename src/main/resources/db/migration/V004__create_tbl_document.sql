CREATE TABLE TBL_DOCUMENT (
    document_id bigint NOT NULL AUTO_INCREMENT,
    descricao TEXT,
    file TEXT,
    mobil_mobil_id bigint NOT NULL,
    model_id bigint NOT NULL,

    PRIMARY KEY (document_id)
) engine=InnoDB default charset=utf8;

alter table TBL_DOCUMENT add constraint fk_mobil_documento
foreign key (mobil_mobil_id) references tbl_mobil (mobil_id);

alter table TBL_DOCUMENT add constraint fk_model_documento
foreign key (model_id) references tbl_model (model_id);