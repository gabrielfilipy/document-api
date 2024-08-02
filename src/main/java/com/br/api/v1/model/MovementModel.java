package com.br.api.v1.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class MovementModel {
	  
    private UUID movementId;
    private UUID subscritor;
    private LocalDateTime dataHora;
    private ModelModel model;

}
