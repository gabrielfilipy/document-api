package com.br.api.v1.model;


import java.util.UUID;

import lombok.*;

@Getter
@Setter
public class DocumentModel {

	private UUID documentId;
	private String descricao;
	private MobilModel mobil;
	private String file;	
	
}
