package com.br.api.v1.model;

import com.br.api.v1.model.input.ModelModelInput;
import lombok.*;

@Getter
@Setter
public class DocumentModel {

	private Long documentId;
	private ModelModelInput model;
	private Long subscritorId;
	private String descricao;
	private MobilModel mobil;
	private String file;	
	
}
