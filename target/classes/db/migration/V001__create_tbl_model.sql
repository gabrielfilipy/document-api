CREATE TABLE TBL_MODEL (
    model_id bigint NOT NULL AUTO_INCREMENT,
    date_time_final datetime DEFAULT NULL,
    descricao_detalhada TEXT,
    html TEXT,
    label varchar(255),
    sigla varchar(10),

    PRIMARY KEY (model_id)
) engine=InnoDB default charset=utf8;