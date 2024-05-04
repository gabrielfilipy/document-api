package com.br.api.v1.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.br.api.v1.model.DocumentModel;
import com.br.domain.model.Document;

@Component
public class DocumentModelMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public DocumentModel toModel(Document document) {
		DocumentModel documentMatriculaModel = 
				modelMapper.map(document, DocumentModel.class);
		return documentMatriculaModel;
	}
	
}
