package com.br.api.v1.model;

import com.br.domain.model.enums.TypeMovement;
import lombok.*;

import javax.persistence.Column;
import java.time.OffsetDateTime;

@Getter
@Setter
public class MovimentacaoAssinadaModel {

    private Long movementId;
    private TypeMovement typeMovement;
    private OffsetDateTime dataHoraCricao;
    private Long pessoaRecebedoraId;
    private Long subscritorId;
    private MobilModel mobilModel;

}
