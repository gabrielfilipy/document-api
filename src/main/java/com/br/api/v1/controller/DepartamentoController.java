package com.br.api.v1.controller;

import java.util.*;

import javax.validation.Valid;

import com.br.api.v1.mapper.DepartamentoModelMapeerBack;
import com.br.api.v1.mapper.DepartamentoModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.br.api.v1.model.DepartamentoModel;
import com.br.api.v1.model.input.DepartamentoActiveModelInput;
import com.br.api.v1.model.input.DepartamentoModelInput;
import com.br.domain.model.Departamento;
import com.br.domain.repository.spec.TemplateSpec;
import com.br.domain.service.DepartamentoService;

import io.swagger.annotations.Api;

@Api(tags ="departamento")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/department")
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;

	@Autowired
	private DepartamentoModelMapper departamentoModelMapper;
	
	@Autowired
	private DepartamentoModelMapeerBack departamentoModelMapeerBack;
	
	
	@PostMapping("/cadastrar")
	public ResponseEntity<DepartamentoModel> cadastrar(@RequestBody @Valid DepartamentoModelInput departamentoModelInput) {
		Departamento departamento = departamentoModelMapeerBack.toModel(departamentoModelInput);
		DepartamentoModel departamentoModel = departamentoModelMapper.toModel(departamentoService.save(departamento));
		return ResponseEntity.status(HttpStatus.CREATED).body(departamentoModel);
	}

    @GetMapping("/buscar/{id}")
    public ResponseEntity<DepartamentoModel> getDepartamento(@PathVariable(name = "id") UUID id) {
        Departamento departamento = departamentoService.findById(id);
        DepartamentoModel departamentoModel = departamentoModelMapper.toModel(departamento);
        return ResponseEntity.status(HttpStatus.OK).body(departamentoModel);
    }
    
	@GetMapping("/listar")
	public ResponseEntity<Page<Departamento>> getDepartamentos(TemplateSpec.DepartmentSpec spec,
																	@PageableDefault(page = 0, size = 5) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findAll(spec, pageable));
	}
	 
	@PatchMapping("/ativar-desativar/{id}")
    public ResponseEntity<DepartamentoModel> activateDepartamento(@RequestBody DepartamentoActiveModelInput departamentoActiveModelInput,
																  @PathVariable(name = "id") UUID id ) {
		return ResponseEntity.status(HttpStatus.CREATED).body(
				departamentoModelMapper.toModel(departamentoService.activaDepartamento(id, departamentoActiveModelInput.isActive())));
 	}

    @PutMapping("/desativar/{id}")
    public ResponseEntity<DepartamentoModel> deactivateDepartamento(@RequestBody DepartamentoActiveModelInput departamentoActiveModelInput, @PathVariable(name = "id") UUID id ) {
		return ResponseEntity.status(HttpStatus.CREATED).body(departamentoModelMapper.toModel(departamentoService.deactivateDepartamento(id)));
	}

}
