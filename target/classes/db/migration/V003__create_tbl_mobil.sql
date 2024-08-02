CREATE TABLE tbl_mobil (
    mobil_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    subscritor_id UUID NOT NULL,
    date_create TIMESTAMPTZ NOT NULL,
    desc_detalhada TEXT,
    sigla_mobil VARCHAR(20),
    ult_movimentacao_id UUID UNIQUE,
    documento_document_id UUID UNIQUE
);