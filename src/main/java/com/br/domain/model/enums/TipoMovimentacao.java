package com.br.domain.model.enums;

import lombok.Getter;

@Getter
public enum TipoMovimentacao {

    TRAMITACAO_DOCUMENTO(1,  "Tramitação de Documentos."),
    CRIACAO_DOCUMENTO(2,  "Criação de Documento."),
    EDICAO_DOCUMENTO(3, "Edição de Documento");

    private int code;
    private String desc;

    TipoMovimentacao(int code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    public static TipoMovimentacao findByCode(int code) {
        for (TipoMovimentacao tipo : TipoMovimentacao.values()) {
             if (tipo.getCode() == code) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código de movimentação inválido: " + code);
    }

}
