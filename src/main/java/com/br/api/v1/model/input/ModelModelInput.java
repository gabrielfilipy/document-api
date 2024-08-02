package com.br.api.v1.model.input;

import java.util.UUID;

import lombok.*;

@Getter
@Setter
public class ModelModelInput {
	
	private UUID modelId;
	private String siglaModel;
	private String html;
	private String label;
	private String descricaoCompleta;
	private Boolean active;
}
