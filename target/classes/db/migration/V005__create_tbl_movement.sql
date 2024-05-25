CREATE TABLE tbl_movement (
    movement_id bigint NOT NULL AUTO_INCREMENT,
    date_time_final datetime DEFAULT NULL,
    date_time_create datetime NOT NULL,
    pessoa_recebedora_id bigint DEFAULT NULL,
    subscritor_id bigint NOT NULL,
    tipo_movimentacao int NOT NULL,
    mobil_mobil_id bigint NOT NULL,

    PRIMARY KEY (movement_id)
);

alter table tbl_movement add constraint fk_mobil_movement
foreign key (mobil_mobil_id) references tbl_mobil (mobil_id);
