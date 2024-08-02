CREATE TABLE tbl_model (
    model_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    date_time_final TIMESTAMPTZ DEFAULT NULL,
    descricao_detalhada TEXT,
    html_form TEXT,
    html_model_doc TEXT,
    label VARCHAR(255),
    sigla VARCHAR(10)
);