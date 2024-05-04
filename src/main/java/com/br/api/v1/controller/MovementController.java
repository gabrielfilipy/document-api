package com.br.api.v1.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.br.domain.model.Movement;
import com.br.domain.service.MovementService;

@RestController
@RequestMapping("/v1/movimentacao")
public class MovementController {
	
	@Autowired
	MovementService movementService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Movement> cadastrar( @Valid Movement movimentacao) {
		Movement MovimentacaoSave = movementService.save(movimentacao);
        return ResponseEntity.status (HttpStatus.CREATED).body(MovimentacaoSave);
    }

}
