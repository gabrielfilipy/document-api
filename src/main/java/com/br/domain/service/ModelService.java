package com.br.domain.service;


import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import com.br.domain.model.Model;


public interface ModelService {

	Model salvar(Model model);
	Model desactiveModel(Long id);
	Model activeModel(Long id, Boolean ativo);
	List<Model> findAll(Specification<Model> spec);
	Model findById(Long id);
	
}
