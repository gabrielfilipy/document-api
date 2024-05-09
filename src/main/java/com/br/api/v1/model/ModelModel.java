package com.br.api.v1.model;

import lombok.*;

@Getter
@Setter
public class ModelModel {

	private Long modelId;
	private String siglaModel;
	private String html;
	private String label;
	private String descricaoCompleta;
	private Boolean active;
}
