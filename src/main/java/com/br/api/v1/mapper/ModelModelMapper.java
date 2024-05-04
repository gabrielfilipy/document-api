package com.br.api.v1.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.api.v1.model.ModelModel;
import com.br.domain.model.Model;

@Component
public class ModelModelMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ModelModel toModel(Model model) {
		ModelModel modelModel =
				modelMapper.map(model, ModelModel.class);
		return modelModel;
	}
}
