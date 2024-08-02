CREATE TABLE marca_mobil_adicionada (
    mobil_id UUID NOT NULL,
    mark_id UUID NOT NULL,
    PRIMARY KEY (mobil_id, mark_id),
    FOREIGN KEY (mobil_id) REFERENCES tbl_mobil(mobil_id),
    FOREIGN KEY (mark_id) REFERENCES tbl_mark(mark_id)
);