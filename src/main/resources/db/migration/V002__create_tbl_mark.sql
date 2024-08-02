CREATE TABLE tbl_mark (
    mark_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    code INT NOT NULL,
    desc_detalhada TEXT,
    nome VARCHAR(255),
    tipo_marca INT
);