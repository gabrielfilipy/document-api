package com.br.api.v1.model;

import java.util.UUID;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class MobilModel {
	
	  private UUID mobilId;
	  private UUID ultimaMovimentacaoId;
	  private String siglaMobil;
	  @NotNull
	  @NotBlank
	  private UUID subscritorId;
	    
}
