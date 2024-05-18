CREATE TABLE tbl_document (
    document_id bigint NOT NULL AUTO_INCREMENT,
    descricao TEXT,
    file TEXT,
    mobil_mobil_id int UNIQUE,
    model_id bigint NOT NULL,

    PRIMARY KEY (document_id)
);

alter table tbl_document add constraint fk_model_documento
foreign key (model_id) references tbl_model (model_id);