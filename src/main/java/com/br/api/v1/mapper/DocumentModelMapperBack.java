package com.br.api.v1.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.br.api.v1.model.input.DocumentModelInput;
import com.br.domain.model.Document;

@Component
public class DocumentModelMapperBack {

	@Autowired
	ModelMapper modelMapper;

	public Document toModel(DocumentModelInput documentModelInput) {
		Document document = 
				modelMapper.map(documentModelInput, Document.class);
		return document;
	}
}
