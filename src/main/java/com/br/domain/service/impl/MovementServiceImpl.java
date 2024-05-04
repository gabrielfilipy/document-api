package com.br.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.Movement;
import com.br.domain.repository.MovementRepository;
import com.br.domain.service.MovementService;

@Service
public class MovementServiceImpl implements MovementService {
	
	@Autowired
	MovementRepository movementRepository;

	@Override
	public Movement save(Movement movimentacao) {
	    return movementRepository.save(movimentacao);
	}

	@Override
	public Movement findById(Long movimentacaoId) {
		Optional<Movement> movimentacao = movementRepository.findById(movimentacaoId);
		if(movimentacao.isEmpty()) {
			throw new EntidadeNaoExisteException("Movimentação informada não existe: " + movimentacaoId);
		}
		return movimentacao.get();
	}
	
	@Override
	public Optional<Movement> buscarUltimaMovimentacaoMobilFilho(Long mobilId) {
		Optional<Movement> movimentacao = movementRepository.findFirstByMobilIdOrderByDataHora(mobilId);
		if(movimentacao.isEmpty()) {
			throw new EntidadeNaoExisteException("Movimentação informada não existe: " + mobilId);
		}
		return movimentacao;
	} 

}
