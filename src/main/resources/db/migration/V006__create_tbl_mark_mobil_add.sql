CREATE TABLE marca_mobil_adicionada (
    mobil_id bigint NOT NULL,
    mark_id bigint NOT NULL
) engine=InnoDB default charset=utf8;

alter table marca_mobil_adicionada add constraint fk_mobil_mark
foreign key (mobil_id) references tbl_mobil (mobil_id);

alter table marca_mobil_adicionada add constraint fk_mark_mobil
foreign key (mark_id) references tbl_mark (mark_id);
