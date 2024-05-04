package com.br.api.v1.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MobilModel {
	
	  private Long mobilId;
	  private MovementModel ultimaMovimentacaoNaoCancelada;
	  private String sigla;
	  @NotNull
	  @NotBlank
	  private Long userId;
	    
}
