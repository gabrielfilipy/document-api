package com.br.api.v1.model.input;

import java.util.UUID;

import lombok.*;

@Getter
@Setter
public class MovementAssSenhaInput {

    private UUID subscritorId;
    private UUID pessoaRecebedoraId;

}
