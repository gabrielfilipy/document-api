package com.br.api.v1.model.input;

import com.br.domain.model.Mobil;
import com.br.domain.model.Model;
import lombok.*;

@Getter
@Setter
public class DocumentModelInput {

	private Model model;
	private Long subscritor;
	private String descricao;
	private Mobil mobil;
	private String file;
}
