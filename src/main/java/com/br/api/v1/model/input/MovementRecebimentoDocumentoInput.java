package com.br.api.v1.model.input;

import java.util.UUID;

import lombok.Data;

@Data
public class MovementRecebimentoDocumentoInput {

    private UUID subscritorId;
    private UUID pessoaRecebedoraId;

}
