package com.br.api.v1.model.input;

import java.time.LocalDateTime;
import com.br.api.v1.model.ModelModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovementModelInput {
  
    private Long movementId;
    private Long subscritor;
    private LocalDateTime dataHora;
    private ModelModel siglaModel;
}
