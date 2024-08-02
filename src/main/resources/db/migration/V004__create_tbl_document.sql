CREATE TABLE tbl_document (
    document_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    descricao TEXT,
    file TEXT,
    mobil_mobil_id UUID UNIQUE,
    model_id UUID NOT NULL,
    FOREIGN KEY (model_id) REFERENCES tbl_model(model_id)
);