package com.br.api.v1.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.api.v1.model.input.ModelActiveModelInput;
import com.br.api.v1.model.input.ModelModelInput;
import com.br.domain.model.Model;

@Component
public class ModelModelMapperBack {

	@Autowired
	private ModelMapper modelMapper;

	public Model toModel(ModelModelInput modelModelInput) {
		Model model =
				modelMapper.map(modelModelInput, Model.class);
		return model;
	}
	
	public Model toModel(ModelActiveModelInput modelActiveModelInput) {
		Model model =
				modelMapper.map(modelActiveModelInput, Model.class);
		return model;
	}

	public void copyToDomainObject(ModelModelInput modelModelInput, Model model) {
		modelMapper.map(modelModelInput, model);
	}
}
