package com.br.domain.service.impl;

import com.br.domain.exception.MarkNaoExisteException;
import com.br.domain.model.enums.TipoMarca;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.domain.model.*;
import com.br.domain.model.enums.TypeMovement;
import com.br.domain.repository.*;
import com.br.domain.service.MarkService;

@Service
public class MarkServiceImpl implements MarkService {
	
	@Autowired
	MarkRepository markRepository;
	
	@Autowired
	MobilRepository mobilRepository;
	
	@Autowired
	MovementRepository movementRepository;

	@Override
	public Mark save(Mark mark) {
		Mark marcaSalvar = markRepository.save(mark);
		Movement movimentacao = new Movement();
		movimentacao.setTypeMovement(TypeMovement.CRIACAO);
		movementRepository.save(movimentacao);
		return  marcaSalvar;
	}

	@Override
	public Mark findById(UUID markId) {
		return markRepository.findById(markId).orElseThrow(() -> new MarkNaoExisteException(markId));
	}

	@Override
	public Mark findByCode(UUID code) {
		return null;
	}

	@Override
	public Mark findByTypeMark(TipoMarca tipoMarca) {
		return markRepository.findByTypeMark(tipoMarca)
				.orElseThrow(() -> new MarkNaoExisteException("O tipo de Marca informado não foi encontrado: " + tipoMarca.name()));
	}

}
