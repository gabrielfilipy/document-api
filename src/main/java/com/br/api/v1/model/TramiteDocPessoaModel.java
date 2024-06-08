package com.br.api.v1.model;

import com.br.domain.model.enums.TypeMovement;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class TramiteDocPessoaModel {

    private Long movementId;
    private TypeMovement typeMovement;
    private OffsetDateTime dataHoraCricao;
    private Long pessoaRecebedoraId;
    private Long subscritorId;
    private MobilModel mobilModel;

}
