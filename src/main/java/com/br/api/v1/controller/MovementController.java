package com.br.api.v1.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.br.api.v1.mapper.MovementModelMapper;
import com.br.domain.model.Movement;
import com.br.domain.service.MovementService;

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

}