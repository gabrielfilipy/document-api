package com.br.domain.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.Model;
import com.br.domain.repository.ModelRepository;
import com.br.domain.service.ModelService;

@Service
public class ModelServiceImpl implements ModelService{
	
	@Autowired
	ModelRepository modelRepository;

	@Override
	public Model save(Model model) {
		return modelRepository.save(model);
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
