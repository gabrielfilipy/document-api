package com.br.api.v1.model;

import com.br.domain.model.Mobil;
import com.br.domain.model.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentModel {

	private Long id;
	private Model model;
	private Long subscritor;
	private String descricao;
	private Mobil mobil;
	private String file;
}
