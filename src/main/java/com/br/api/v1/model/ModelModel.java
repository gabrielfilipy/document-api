package com.br.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelModel {
	
	private Long id;
	private String siglaModel;
	private String name;
	private String descricaoCompleta;
	private String html;
	private Boolean active;
}
