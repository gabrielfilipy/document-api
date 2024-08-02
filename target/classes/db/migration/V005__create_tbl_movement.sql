CREATE TABLE tbl_movement (
    movement_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    date_time_final TIMESTAMPTZ DEFAULT NULL,
    date_time_create TIMESTAMPTZ NOT NULL,
    pessoa_recebedora_id UUID DEFAULT NULL,
    subscritor_id UUID NOT NULL,
    tipo_movimentacao INT NOT NULL,
    mobil_mobil_id UUID NOT NULL,
    FOREIGN KEY (mobil_mobil_id) REFERENCES tbl_mobil(mobil_id)
);
