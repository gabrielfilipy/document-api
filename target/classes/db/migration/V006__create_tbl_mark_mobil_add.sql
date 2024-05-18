CREATE TABLE MARCA_MOBIL_ADICIONADA (
    mobil_id bigint NOT NULL,
    mark_id bigint NOT NULL,

    PRIMARY KEY (document_id)
) engine=InnoDB default charset=utf8;

alter table MARCA_MOBIL_ADICIONADA add constraint fk_mobil_mark
foreign key (mobil_id) references tbl_mobil (mobil_id);

alter table MARCA_MOBIL_ADICIONADA add constraint fk_mark_mobil
foreign key (mark_id) references tbl_mark (mark_id);
