package com.br.api.v1.model;

import java.util.UUID;

import com.br.domain.model.Movement;
import com.br.domain.model.enums.TipoMarca;
import lombok.*;

@Getter
@Setter
public class MarkModel {
	
	    private UUID markId;
	    private String nome;
	    private String descricaoDetalhada;
	    private UUID code;
		private Movement movimentacao;
	    private TipoMarca tipoMarca;
}
