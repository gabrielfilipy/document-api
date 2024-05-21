-- Desativar verificação de chave estrangeira temporariamente
SET foreign_key_checks = 0;

-- Apagar dados das tabelas
DELETE FROM tbl_mobil;
DELETE FROM tbl_document;
DELETE FROM tbl_movement;
DELETE FROM marca_mobil_adicionada;
DELETE FROM tbl_mark;
DELETE FROM tbl_model;

-- Reativar verificação de chave estrangeira
SET foreign_key_checks = 1;

-- Reiniciar auto incremento das tabelas
ALTER TABLE tbl_model AUTO_INCREMENT = 1;
ALTER TABLE tbl_mark AUTO_INCREMENT = 1;
ALTER TABLE tbl_mobil AUTO_INCREMENT = 1;
ALTER TABLE tbl_document AUTO_INCREMENT = 1;
ALTER TABLE tbl_movement AUTO_INCREMENT = 1;

-- Inserir novos dados
INSERT INTO tbl_model (model_id, date_time_final, descricao_detalhada, html_form, html_model_doc, label, sigla)
VALUES (NULL, NULL, 'Memorando é um tipo de documento que trata assuntos internos.', '<input/>', '<h1>Visualizar documento<h1/>', 'Memorando', 'MEM');

INSERT INTO tbl_mark (mark_id, code, desc_detalhada, nome, tipo_marca)
VALUES (NULL, 1, 'Descricao', 'Criacao', 1);

INSERT INTO tbl_mobil (mobil_id, date_create, sigla_mobil, subscritor_id)
VALUES (NULL, NOW(), 'MEM', 1);

INSERT INTO tbl_document (document_id, descricao, file, mobil_mobil_id, model_id)
VALUES (NULL, 'Descrição', 'File', 1, 1);

INSERT INTO tbl_movement (movement_id, date_time_final, date_time_create, pessoa_recebedora_id, subscritor_id, tipo_movimentacao, mobil_mobil_id)
VALUES (NULL, NULL, NOW(), 1, 1, 1, 1);

UPDATE tbl_mobil
SET ult_movimentacao_id = 1, documento_document_id = 1
WHERE mobil_id = 1;

INSERT INTO marca_mobil_adicionada (mobil_id, mark_id)
VALUES (1, 1);
