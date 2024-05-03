package com.br.api.v1.model;

import lombok.*;

@Getter
@Setter
public class ModelModel {

	private Long id;
    private String siglaModel;
    private String label;
    private String descricaoCompleta;
    private String html;
    private Boolean active;
}
