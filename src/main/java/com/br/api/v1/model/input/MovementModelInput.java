package com.br.api.v1.model.input;

import java.time.LocalDateTime;
import java.util.UUID;

import com.br.api.v1.model.ModelModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovementModelInput {
  
    private UUID movementId;
    private UUID subscritor;
    private LocalDateTime dataHora;
    private ModelModel siglaModel;
}
