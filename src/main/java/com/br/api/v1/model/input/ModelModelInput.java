package com.br.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelModelInput {
	
	private String siglaModel;
	private String name;
	private String descricaoCompleta;
	private String html;
	private Boolean active;
}
