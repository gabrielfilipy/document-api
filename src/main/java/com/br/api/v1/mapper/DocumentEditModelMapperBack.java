package com.br.api.v1.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.api.v1.model.input.DocumentModelInput;
import com.br.domain.model.Document;

@Component
public class DocumentEditModelMapperBack {

	@Autowired
	ModelMapper modelMapper;

	public void copyToDomainObject(DocumentModelInput documentModelInput, Document document) {
		modelMapper.map(documentModelInput, document);
	}
}
