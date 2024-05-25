package com.br.api.v1.model;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class MobilModel {
	
	  private Long mobilId;
	  private Long ultimaMovimentacaoId;
	  private String sigla;
	  @NotNull
	  @NotBlank
	  private Long subscritorId;
	    
}
