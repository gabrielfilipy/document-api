package com.br.domain.service;

import com.br.domain.model.Model;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public interface ModelService {

	Model salvar(Model model);
	Model desactiveModel(Long id);
	Model activeModel(Long id, Boolean ativo);
	List<Model> findAll();
	Model findById(Long modelId);
	
}
