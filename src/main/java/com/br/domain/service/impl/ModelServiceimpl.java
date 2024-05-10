package com.br.domain.service.impl;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.Model;
import com.br.domain.repository.ModelRepository;
import com.br.domain.service.ModelService;

@Service
public class ModelServiceimpl implements ModelService{

	@Autowired
	private ModelRepository modelRepository;

	@Override
	public Model salvar(Model model) {
		if(model.getModelId() == null)
			model.setActive(true);
		
		return modelRepository.save(model);
	}

	@Override
	public Model desactiveModel(Long id) {
		Model model = modelRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Modelo não encontrado."));
		   model.setActive(false);
		return modelRepository.save(model);
	}

	@Override
	public Model activeModel(Long id, Boolean ativo) {
		Model model =  modelRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Modelo não encontrado."));
		model.setActive(ativo);
		return modelRepository.save(model);
	}

	@Override
	public List<Model> findAll(Specification<Model> spec) {
		return modelRepository.findAll(spec);
	}

	@Override
	public Model findById(Long id) {
		Optional<Model> model = modelRepository.findById(id);
		if(model.isEmpty()) {
			throw new EntidadeNaoExisteException("Modelo informado não existe: " + id);
		}
		return model.get();
	}
	
}
