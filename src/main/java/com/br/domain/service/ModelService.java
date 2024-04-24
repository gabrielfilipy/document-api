package com.br.domain.service;

import com.br.domain.model.Model;

public interface ModelService {
	
	Model save(Model model);
	Model findById(Long modelId);

}
