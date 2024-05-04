package com.br.api.v1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.br.api.v1.mapper.*;
import com.br.api.v1.model.ModelModel;
import com.br.api.v1.model.input.*;
import com.br.domain.model.Model;
import com.br.domain.service.ModelService;
import com.br.domain.service.TemplateSpec.TemplateSpec;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/model")
public class ModelController {

	@Autowired
	private ModelService modelService;
	
	@Autowired
	private ModelModelMapper modelModelMapper;
	
	@Autowired
	private ModelModelMapperBack modelModelMapperBack;
	
	@PostMapping("/salvar")
	public ResponseEntity<ModelModel> salve(@RequestBody ModelModelInput modelModelInput){
	    Model salvar = modelModelMapperBack.toModel(modelModelInput);
	    ModelModel modelSavar = modelModelMapper.toModel(modelService.salvar(salvar));
	    return ResponseEntity.status(HttpStatus.CREATED).body(modelSavar);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<ModelModel> getUser(@PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(modelModelMapper.toModel(modelService.findById(id)));
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Model>> getDepartamentos(TemplateSpec.ModelSpec spec) {
		
		return ResponseEntity.status(HttpStatus.OK).body(modelService.findAll(spec));
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<ModelModel> editar(@RequestBody ModelModelInput modelModelInput,
			@PathVariable(name = "id") Long id) {
		Model modelAtual = modelService.findById(id);
		modelModelMapperBack.copyToDomainObject(modelModelInput, modelAtual);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelModelMapper.toModel(modelService.salvar(modelAtual)));
	}
	
	@PatchMapping("/ativar-desativar/{id}")
    public ResponseEntity<ModelModel> activateModel(@RequestBody ModelActiveModelInput modelActiveModelInput,
																  @PathVariable(name = "id") Long id ) {
		return ResponseEntity.status(HttpStatus.CREATED).body(
				modelModelMapper.toModel(modelService.activeModel(id, modelActiveModelInput.isActive())));
 	}
	
    @PutMapping("/desativar/{id}")
    public ResponseEntity<ModelModel> desactiveModel(@RequestBody ModelActiveModelInput modelActiveModelInput, @PathVariable(name = "id") Long id ) {
		return ResponseEntity.status(HttpStatus.CREATED).body(modelModelMapper.toModel(modelService.desactiveModel(id)));
	}
}
