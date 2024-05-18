CREATE TABLE tbl_mark (
    mark_id bigint NOT NULL AUTO_INCREMENT,
    code INT NOT NULL,
    desc_detalhada TEXT,
    nome varchar(255),
    tipo_marca int,

    PRIMARY KEY (mark_id)
);