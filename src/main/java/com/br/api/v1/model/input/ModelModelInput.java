package com.br.api.v1.model.input;

import lombok.*;

@Getter
@Setter
public class ModelModelInput {

	private Long id;
    private String siglaModel;
    private String label;
    private String descricaoCompleta;
    private String html;
    private Boolean active;
}
