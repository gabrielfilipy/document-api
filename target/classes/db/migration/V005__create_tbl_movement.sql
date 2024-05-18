CREATE TABLE TBL_MOVEMENT (
    movement_id bigint NOT NULL AUTO_INCREMENT,
    date_time_final datetime DEFAULT NULL,
    date_time_create datetime NOT NULL,
    pessoa_recebedora_id bigint NOT NULL,
    subscritor_id bigint NOT NULL,
    tipo_movimentacao int NOT NULL,
    mobil_mobil_id bigint NOT NULL,

    PRIMARY KEY (document_id)
) engine=InnoDB default charset=utf8;

alter table TBL_MOVEMENT add constraint fk_mobil_movement
foreign key (mobil_mobil_id) references tbl_mobil (mobil_id);
