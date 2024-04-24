package com.br.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.domain.model.Movimentacao;
import com.br.domain.service.MovimentacaoService;

@RestController
@RequestMapping("/v1/movimentacao")
public class MovimentacaoController {
	
	@Autowired
	MovimentacaoService movimentacaoService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Movimentacao> cadastrar( @Valid Movimentacao movimentacao) {
		Movimentacao MovimentacaoSave = movimentacaoService.save(movimentacao);
        return ResponseEntity.status (HttpStatus.CREATED).body(MovimentacaoSave);
    }

}
