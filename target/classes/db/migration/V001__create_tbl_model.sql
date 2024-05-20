CREATE TABLE tbl_model (
    model_id bigint NOT NULL AUTO_INCREMENT,
    date_time_final datetime DEFAULT NULL,
    descricao_detalhada TEXT,
    html_form TEXT,
    html_model_doc TEXT,
    label varchar(255),
    sigla varchar(10),

    PRIMARY KEY (model_id)
);