package com.br.api.v1.model;

import com.br.domain.model.Movement;
import com.br.domain.model.enums.TipoMarca;
import lombok.*;

@Getter
@Setter
public class MarkModel {
	
	    private Long markId;
	    private String nome;
	    private String descricaoDetalhada;
	    private Long code;
		private Movement movimentacao;
	    private TipoMarca tipoMarca;
}
