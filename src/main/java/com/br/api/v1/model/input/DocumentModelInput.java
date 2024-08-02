package com.br.api.v1.model.input;

import java.util.UUID;

import com.br.api.v1.model.ModelModel;
import lombok.*;

@Getter
@Setter
public class DocumentModelInput {

	private UUID documentId;
	private UUID subscritorId;
	private String descricao;
    private ModelModel model;

}
