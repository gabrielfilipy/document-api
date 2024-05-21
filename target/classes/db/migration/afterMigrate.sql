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
VALUES (NULL, NULL, 'Memorando é um tipo de documento que trata assuntos internos.', '<input/>', '<!DOCTYPE html>
                                                                                                  <html lang="en">
                                                                                                  <head>
                                                                                                      <meta charset="UTF-8">
                                                                                                      <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                                                                                      <title>Modelo Memorando</title>
                                                                                                      <style>

                                                                                                          * {
                                                                                                              margin: 0;
                                                                                                              box-sizing: border-box;
                                                                                                          }

                                                                                                          .VisualizadorDocumento {
                                                                                                              width: 100%;
                                                                                                              padding: 10px;
                                                                                                              border: 1px solid #5c5c5c;
                                                                                                              margin: 0 auto;
                                                                                                          }

                                                                                                          .MargemDocumento {
                                                                                                              max-width: 900px;
                                                                                                              margin: 0 auto;
                                                                                                          }

                                                                                                          .LogoDocumento {
                                                                                                              text-align: center;
                                                                                                              margin-top: 50px;
                                                                                                              margin-bottom: 50px;
                                                                                                          }

                                                                                                          .LogoDocumento img {
                                                                                                              max-width: 100px;
                                                                                                          }

                                                                                                          .DataLocalCriacaoDocumento {
                                                                                                              text-align: right;
                                                                                                          }

                                                                                                          .InformacoesDocumento {
                                                                                                              text-align: center;
                                                                                                              margin-top: 100px;
                                                                                                              margin-bottom: 100px;
                                                                                                          }

                                                                                                          .InformacoesDocumento b,
                                                                                                          .InformacoesDocumento p {
                                                                                                              margin: 0;
                                                                                                              padding: 0;
                                                                                                          }

                                                                                                          .CorpoDocumento {
                                                                                                              text-align: left;
                                                                                                          }

                                                                                                          .CorpoDocumento p {
                                                                                                              margin: 0;
                                                                                                              padding: 0;
                                                                                                              white-space: pre-wrap;
                                                                                                              overflow-wrap: break-word;
                                                                                                          }

                                                                                                          .RodapeDocumento {
                                                                                                              text-align: center;
                                                                                                              margin-top: 80px;
                                                                                                              margin-bottom: 80px;
                                                                                                          }

                                                                                                          .RodapeDocumento p {
                                                                                                              margin: 0;
                                                                                                              padding: 0;
                                                                                                              white-space: pre-wrap;
                                                                                                              overflow-wrap: break-word;
                                                                                                          }

                                                                                                      </style>
                                                                                                  </head>
                                                                                                  <body>
                                                                                                      <div class="VisualizadorDocumento" id="VisualizadorDocumento">
                                                                                                          <div class="MargemDocumento" id="MargemDocumento">
                                                                                                              <div class="LogoDocumento" id="LogoDocumento">
                                                                                                                  <img src="https://seeklogo.com/images/P/prefeitura-de-manacapuru-logo-2FBDF8B79C-seeklogo.com.png" alt="Logo do documento">
                                                                                                              </div><!--LogoDocumento-->

                                                                                                              <div class="DataLocalCriacaoDocumento" id="DataLocalCriacaoDocumento">
                                                                                                                  <p>#DataLocalCriacaoDocumento</p>
                                                                                                              </div><!--DataLocalCriacaoDocumento-->

                                                                                                              <div class="InformacoesDocumento" id="InformacoesDocumento">
                                                                                                                  <b>#Orgao</b>
                                                                                                                  <p>#SecretariaCriacaoDocumento</p>
                                                                                                              </div><!--DataLocalCriacaoDocumento-->

                                                                                                              <div class="CorpoDocumento" id="CorpoDocumento">
                                                                                                                  <p><b>Número de referência: </b>#NumeroReferencia</p>
                                                                                                                  <p><b>Interessado: </b>#Interessado</p>
                                                                                                                  <p><b>Descrição: </b>#Descricao</p>
                                                                                                              </div><!--CorpoDocumento-->

                                                                                                              <div class="RodapeDocumento" id="RodapeDocumento">
                                                                                                                  <b>#PessoaAssinante</b>
                                                                                                                  <p>#SecretariaPessoaAssinante</p>
                                                                                                                  <p>#DataAssinatura</p>
                                                                                                              </div><!--RodapeDocumento-->
                                                                                                          </div><!--MargemDocumento-->
                                                                                                      </div><!--VisualizadorDocumento-->

                                                                                                  </body>
                                                                                                  </html>
', 'Memorando', 'MEM');

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
