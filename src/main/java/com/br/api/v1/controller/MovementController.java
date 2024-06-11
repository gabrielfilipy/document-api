package com.br.api.v1.controller;

import com.br.api.v1.mapper.MobilModelMapper;
import com.br.api.v1.model.MobilModel;
import com.br.api.v1.model.MovementModel;
import com.br.api.v1.model.MovimentacaoAssinadaModel;
import com.br.api.v1.model.TramiteDocPessoaModel;
import com.br.api.v1.model.input.MovementAssSenhaInput;
import com.br.api.v1.model.input.TramiteDocPessoaModelInput;
import com.br.domain.model.Mobil;
import com.br.domain.service.MobilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
	private MovementService movementService;

	@Autowired
	private MobilService mobilService;
	
	@Autowired
	private MovementModelMapper movementModelMapper;

	@Autowired
	private MobilModelMapper mobilModelMapper;

	@GetMapping("/filtro")
	public ResponseEntity<Page<?>> findAll(Long mobilId, @PageableDefault(page = 0, size = 10) Pageable pageable) {
		return ResponseEntity.status (HttpStatus.OK).body(movementService.buscarMovimentacoesDoMobilFiltro(mobilId, pageable));
	}

	@PostMapping("/assinar-com-senha/{siglaDocumento}")
	public ResponseEntity<MovimentacaoAssinadaModel> verificarAssinatura(
			@PathVariable(name = "siglaDocumento") String siglaDocumento,
			@RequestBody MovementAssSenhaInput movementAssSenhaInput) {
		Movement movement = movementService.criarMovimentacaoAssinarComSenha(siglaDocumento, movementAssSenhaInput.getSubscritorId());
		Mobil mobil = mobilService.buscarMobil(movement.getMobil().getMobilId());

		MovimentacaoAssinadaModel movimentacaoAssinadaModel1 = movementModelMapper.toModelMovAssinada(movement);

		MobilModel mobilModel = mobilModelMapper.toModel(mobil);
		movimentacaoAssinadaModel1.setMobilModel(mobilModel);
		return ResponseEntity.status(HttpStatus.OK).body(movimentacaoAssinadaModel1);
	}

	@PostMapping("/tramitar/{siglaDocumento}/pessoa")
	public ResponseEntity<TramiteDocPessoaModel> tramitaDocumento(
			@PathVariable(name = "siglaDocumento") String siglaDocumento,
			@RequestBody TramiteDocPessoaModelInput tramiteDocPessoaModelInput) {
		Movement movement = movementService.criarMovimentacaoTramitarDocumentoPessoa(
														siglaDocumento,
														tramiteDocPessoaModelInput.getSubscritorId(),
														tramiteDocPessoaModelInput.getPessoaRecebedoraId());
		Mobil mobil = mobilService.buscarMobil(movement.getMobil().getSiglaMobil());

		TramiteDocPessoaModel TramiteDocPessoaModel1 = movementModelMapper.toModelTramiteDocPessoa(movement);

		MobilModel mobilModel = mobilModelMapper.toModel(mobil);
		TramiteDocPessoaModel1.setMobilModel(mobilModel);
		return ResponseEntity.status(HttpStatus.OK).body(TramiteDocPessoaModel1);
	}

}