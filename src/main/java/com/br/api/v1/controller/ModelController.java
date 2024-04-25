package com.br.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.br.api.v1.mapper.ModelModelMapper;
import com.br.api.v1.mapper.ModelModelMapperBack;
import com.br.api.v1.model.ModelModel;
import com.br.api.v1.model.input.ModelModelInput;
import com.br.domain.model.Model;
import com.br.domain.service.ModelService;

@RestController
@RequestMapping("/v1/model")
public class ModelController {
	
	@Autowired
	ModelService modelService;
	
	@Autowired
	ModelModelMapperBack modelModelMapperBack;
	
	@Autowired
	ModelModelMapper modelModelMapper;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<ModelModel> cadastrar(@RequestBody ModelModelInput modelModelInput) {
		Model model = modelModelMapperBack.toModel(modelModelInput);
		ModelModel modelModel = modelModelMapper.toModel(modelService.save(model));
        return ResponseEntity.status (HttpStatus.CREATED).body(modelModel);
    }
}
