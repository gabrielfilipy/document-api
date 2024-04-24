package com.br.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.br.domain.model.Marca;
import com.br.domain.service.MarcaService;

@RestController
@RequestMapping("/v1/marca")
public class MarcaController {
	
	@Autowired
	MarcaService marcaService;
	
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Marca> cadastrar( @Valid Marca marca,@RequestParam Long mobilId) {
		Marca MarcaSave = marcaService.save(marca,mobilId);
        return ResponseEntity.status (HttpStatus.CREATED).body(MarcaSave);
    }

}
