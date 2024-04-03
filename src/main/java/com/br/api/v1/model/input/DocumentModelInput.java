package com.br.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentModelInput {

	private String modelo;
	private String meustextosPadroes;
	private String responsavelPelaAssinatura;
	private String nomeCompleto;
	private String interessado;
	private String assunto;
	private String numeroDeReferencia;
}
