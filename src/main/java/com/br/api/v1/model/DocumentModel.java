package com.br.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentModel {

	private Long id;
	private Boolean active;
	private String modelo;
	private String meustextosPadroes;
	private String responsavelPelaAssinatura;
	private String nomeCompleto;
	private String interessado;
	private String assunto;
	private String numeroDeReferencia;
}
