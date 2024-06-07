package com.br.api.v1.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MovementModel {
	  
    private Long movementId;
    private Long subscritor;
    private LocalDateTime dataHora;
    private ModelModel model;

}
