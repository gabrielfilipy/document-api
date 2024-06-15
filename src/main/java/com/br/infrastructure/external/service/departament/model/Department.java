package com.br.infrastructure.external.service.departament.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Department {

	private Long id;
	private Long orgaoId;
	private String nome;
	private String sigla;
	private String unidadePai;
	private String localidade;
	
}
