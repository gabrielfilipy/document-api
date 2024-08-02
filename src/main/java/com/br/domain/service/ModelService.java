package com.br.domain.service;

import com.br.domain.model.Model;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

public interface ModelService {

	Model salvar(Model model);
	Model desactiveModel(UUID id);
	Model activeModel(UUID id, Boolean ativo);
	List<Model> findAll();
	Model findById(UUID modelId);
	
}
