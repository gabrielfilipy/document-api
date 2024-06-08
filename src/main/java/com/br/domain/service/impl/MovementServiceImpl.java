package com.br.domain.service.impl;

import java.util.Optional;

import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.exception.MovimentacaoExistenteException;
import com.br.domain.model.Mobil;
import com.br.domain.model.enums.TipoMarca;
import com.br.domain.model.enums.TypeMovement;
import com.br.domain.repository.MobilRepository;
import com.br.domain.service.MobilService;
import com.br.infrastructure.external.service.user.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.br.domain.model.Movement;
import com.br.domain.repository.MovementRepository;
import com.br.domain.service.MovementService;

@Service
public class MovementServiceImpl implements MovementService {
	
	@Autowired
	MovementRepository movementRepository;

	@Autowired
	private MobilRepository mobilRepository;

	@Autowired
	private MobilService mobilService;

	@Autowired
	private UserFeignClient userFeignClient;

	@Autowired
	private MovementService movementService;

	@Override
	public Movement save(Movement movimentacao) {
	    return movementService.save(movimentacao);
	}

	public Movement verificarSePessoaRecebedoraAssinou(String siglaMobil, Long subscritorId, Long pessoaRecebedoraId) {
		Mobil mobil = buscarMobil(siglaMobil);
		//Percorrer todas movimentações do Mobil.
		for(Movement movement: mobil.getMovimentacoes()) {
			if((movement.getTypeMovement() == TypeMovement.TRAMITE_PARA_PESSOA) &&
					(movement.getSubscritorId() == subscritorId) &&
					(movement.getPessoaRecebedoraId() == pessoaRecebedoraId)) {
				return movement;
			}
		}
		return null;
	}

	@Override
	public Movement verificarSeOSubscritorAssinou(String siglaMobil, Long subscritorId) {
		Mobil mobil = buscarMobil(siglaMobil);
		for(Movement movement: mobil.getMovimentacoes()) {
			if((movement.getTypeMovement() == TypeMovement.ASSINATURA_COM_SENHA) &&
					(movement.getSubscritorId() == subscritorId)) {
				return movement;
			}
		}
		return null;
	}

	@Override
	public Movement criarMovimentacaoAssinarComSenha(String siglaMobil, Long subscritorId) {
		Movement movement = verificarSeOSubscritorAssinou(siglaMobil, subscritorId);

		if(movement != null) {
			throw new MovimentacaoExistenteException(movement.getMovementId());
		}

		Mobil mobil = buscarMobil(siglaMobil);
		mobilService.atribuirMarcaAoMobil(mobil, TipoMarca.ASSINAR_COM_SENHA);
		movement = criarMovimentacao(TypeMovement.ASSINATURA_COM_SENHA, subscritorId, null, mobil);
		mobilService.atualizarSiglaDoMobil(mobil);

		return movement;
	}

	@Override
	public Movement criarMovimentacaoTramitarDocumentoPessoa(String siglaMobil, Long pessoaRecebedoraId, Long subscritorId) {
		Movement movement = verificarSePessoaRecebedoraAssinou(siglaMobil, subscritorId, pessoaRecebedoraId);

		if(movement != null) {
			throw new MovimentacaoExistenteException(movement.getMovementId());
		}

		Mobil mobil = buscarMobil(siglaMobil);
		mobilService.atribuirMarcaAoMobil(mobil, TipoMarca.TRAMITAR_DOCUMENTO_PESSOA);
		return criarMovimentacao(TypeMovement.TRAMITE_PARA_PESSOA, subscritorId, null, mobil);
	}

	@Override
	public Movement findById(Long movimentacaoId) {
		Movement movement = movementService.findById(movimentacaoId);
		throw new EntidadeNaoExisteException("Movimentação não encontrado: " + movimentacaoId);
    }

	@Override
	public Page<Movement> findAll(Specification<Movement> spec, Pageable pageable) {
		return mobilRepository.findAll(spec, pageable);
	}

	@Override
	public Page<Movement> buscarMovimentacoesDoMobilFiltro(Long mobilId, Pageable pageable) {
		return movementService.buscarMovimentacoesDoMobilFiltro(mobilId, pageable);
	}

	@Override
	public Movement criarMovimentacao(TypeMovement typeMovement, Long subscritorId, Long pessoaRecebedoraId, Mobil mobil) {
		Movement movimentacao = new Movement();
		movimentacao.setSubscritorId(subscritorId);
		movimentacao.setPessoaRecebedoraId(pessoaRecebedoraId);
		movimentacao.setMobil(mobil);
		movimentacao.setTypeMovement(typeMovement);
		return movementService.save(movimentacao);
	}

	private Mobil buscarMobil(String siglaMobil) {
		mobilService.buscarMobil(siglaMobil);
				throw new EntidadeNaoExisteException("O Mobil (" + siglaMobil +") informado não existe.");
	}

}