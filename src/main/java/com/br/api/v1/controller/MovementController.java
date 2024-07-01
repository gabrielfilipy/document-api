package com.br.api.v1.controller;

import com.br.api.v1.mapper.MobilModelMapper;
import com.br.api.v1.model.*;
import com.br.api.v1.model.input.MovementAssSenhaInput;
import com.br.domain.model.Mobil;
import com.br.domain.service.MobilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.br.api.v1.mapper.MovementModelMapper;
import com.br.domain.model.Movement;
import com.br.domain.model.enums.TypeMovement;
import com.br.domain.service.MovementService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/movimentacao/")
public class MovementController {
	
	@Autowired
	private MovementService movementService;

	@Autowired
	private MobilService mobilService;
	
	@Autowired
	private MovementModelMapper movementModelMapper;

	@Autowired
	private MobilModelMapper mobilModelMapper;

	@GetMapping("/filtro")
	public ResponseEntity<Page<?>> findAll(Long mobilId, TypeMovement typeMovement, @PageableDefault(page = 0, size = 10) Pageable pageable) {
		return ResponseEntity.status (HttpStatus.OK).body(movementService.buscarMovimentacoesDoMobilFiltro(mobilId, typeMovement, pageable));
	}

	@PostMapping("/assinar-com-senha/{siglaDocumento}")
	public ResponseEntity<MovimentacaoAssinadaModel> assinarComSenha(@PathVariable(name = "siglaDocumento") String siglaDocumento,
			@RequestBody MovementAssSenhaInput movementAssSenhaInput) {
		Movement movement = movementService.criarMovimentacaoAssinarComSenha(siglaDocumento, movementAssSenhaInput.getSubscritorId());
		Mobil mobil = mobilService.buscarMobil(movement.getMobil().getMobilId());
		MovimentacaoAssinadaModel movimentacaoAssinadaModel = movementModelMapper.toModelMovAssinada(movement);
		MobilModel mobilModel = mobilModelMapper.toModel(mobil);
		movimentacaoAssinadaModel.setMobilModel(mobilModel);
		return ResponseEntity.status(HttpStatus.OK).body(movimentacaoAssinadaModel);
	}

	@PostMapping("/incluir-cossignatario/{siglaDocumento}")
	public ResponseEntity<MovimentacaoAssinadaModel> incluirCossinatario(@PathVariable(name = "siglaDocumento") String siglaDocumento,
			@RequestBody MovementAssSenhaInput movementAssSenhaInput) {
		Movement movement = movementService.criarMovimentacaoIncluirCossignatario(siglaDocumento, movementAssSenhaInput.getSubscritorId(), movementAssSenhaInput.getPessoaRecebedoraId());
		Mobil mobil = mobilService.buscarMobil(movement.getMobil().getMobilId());
		MovimentacaoAssinadaModel movimentacaoAssinadaModel = movementModelMapper.toModelMovAssinada(movement);
		MobilModel mobilModel = mobilModelMapper.toModel(mobil);
		movimentacaoAssinadaModel.setMobilModel(mobilModel);
		return ResponseEntity.status(HttpStatus.OK).body(movimentacaoAssinadaModel);
	}
	
	@PostMapping("/tramitar-documento/{siglaDocumento}")
	public ResponseEntity<MovimentacaoAssinadaModel> tramitarDocumento(@PathVariable(name = "siglaDocumento") String siglaDocumento,
			@RequestBody MovementAssSenhaInput movementAssSenhaInput) {
		Movement movement = movementService.criarMovimentacaoTramitarDocumento(siglaDocumento, movementAssSenhaInput.getSubscritorId(), movementAssSenhaInput.getPessoaRecebedoraId());
		Mobil mobil = mobilService.buscarMobil(movement.getMobil().getMobilId());
		MovimentacaoAssinadaModel movimentacaoAssinadaModel = movementModelMapper.toModelMovAssinada(movement);
		MobilModel mobilModel = mobilModelMapper.toModel(mobil);
		movimentacaoAssinadaModel.setMobilModel(mobilModel);
		return ResponseEntity.status(HttpStatus.OK).body(movimentacaoAssinadaModel);
	}
	
	@PostMapping("/finalizacao-documento/{siglaDocumento}")
	public ResponseEntity<MovimentacaoAssinadaModel> finalizarDocumento(@PathVariable(name = "siglaDocumento") String siglaDocumento,
			@RequestBody MovementAssSenhaInput movementAssSenhaInput) {
		Movement movement = movementService.criarMovimentacaoFinalizacaoDocumento(siglaDocumento, movementAssSenhaInput.getSubscritorId());
		Mobil mobil = mobilService.buscarMobil(movement.getMobil().getMobilId());
		MovimentacaoAssinadaModel movimentacaoAssinadaModel = movementModelMapper.toModelMovAssinada(movement);
		MobilModel mobilModel = mobilModelMapper.toModel(mobil);
		movimentacaoAssinadaModel.setMobilModel(mobilModel);
		return ResponseEntity.status(HttpStatus.OK).body(movimentacaoAssinadaModel);
	}

} 