package com.br.api.v1.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.domain.model.Model;
import com.br.domain.service.ModelService;

@RestController
@RequestMapping("/v1/model")
public class ModelController {
	
	@Autowired
	ModelService modelService;
	

	@PostMapping("/cadastrar")
	public ResponseEntity<Model> cadastrar( @Valid Model model) {
		Model ModelSave = modelService.save(model);
        return ResponseEntity.status (HttpStatus.CREATED).body(ModelSave);
    }

}
