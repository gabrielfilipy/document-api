package com.br.api.v1.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.br.api.v1.mapper.MovementModelMapper;
import com.br.domain.model.Movement;
import com.br.domain.service.MovementService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/movimentacao")
public class MovementController {
	
	@Autowired
	MovementService movementService;
	
	@Autowired
	MovementModelMapper movementModelMapper;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Movement> cadastrar( @Valid Movement movimentacao) {
		Movement MovimentacaoSave = movementService.save(movimentacao);
        return ResponseEntity.status (HttpStatus.CREATED).body(MovimentacaoSave);
    }

	@GetMapping("/listar")
	public ResponseEntity<List<Movement>> listar(@Valid Movement movimentacao) {
		return ResponseEntity.status (HttpStatus.OK).body(movementService.findAll());
	}

	@GetMapping("/buscar-movimentacoes/{mobilId}")
	public ResponseEntity<?> findbyMovimentacoesDoMobil(@PathVariable(name = "mobilId") Long mobilId) {
		return ResponseEntity.status (HttpStatus.OK).body(movementService.buscarMovimentacoesDoMobil(mobilId));
	}

}