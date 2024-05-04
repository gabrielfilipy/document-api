package com.br.api.v1.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.br.domain.model.Model;
import com.br.domain.service.ModelService;

@RestController
@RequestMapping("/v1/model")
public class ModelController {
	
	@Autowired
	ModelService modelService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Model> cadastrar(@RequestBody @Valid Model model) {
		Model ModelSave = modelService.save(model);
        return ResponseEntity.status (HttpStatus.CREATED).body(ModelSave);
    }

}
