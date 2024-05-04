package com.br.api.v1.model;

import lombok.*;

@Getter
@Setter
public class DocumentModel {

	private Long documentId;
	private ModelModel model;
	private Long subscritor;
	private String descricao;
	private MobilModel mobil;
	private String file;	
	
}
