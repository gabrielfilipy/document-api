package com.br.api.v1.model;

import com.br.domain.model.enums.TypeMovement;
import lombok.*;

import javax.persistence.Column;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class MovimentacaoAssinadaModel {

    private UUID movementId;
    private TypeMovement typeMovement;
    private OffsetDateTime dataHoraCricao;
    private UUID pessoaRecebedoraId;
    private UUID subscritorId;
    private MobilModel mobilModel;

}
