package com.br.api.v1.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.br.domain.repository.MovementRepository;
import com.br.domain.repository.spec.TemplateSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
	private MovementRepository movementRepository;
	
	@Autowired
	MovementModelMapper movementModelMapper;

	@GetMapping("/filtro")
	public ResponseEntity<Page<?>> findAll(Long mobilId,
										   @PageableDefault(page = 0, size = 10) Pageable pageable) {
		return ResponseEntity.status (HttpStatus.OK).body(movementService.buscarMovimentacoesDoMobil(mobilId, pageable));
	}

}