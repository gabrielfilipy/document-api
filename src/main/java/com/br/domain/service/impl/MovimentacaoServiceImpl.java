package com.br.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.domain.model.Movimentacao;
import com.br.domain.repository.MovimentacaoRepository;
import com.br.domain.service.MovimentacaoService;

@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {
	
	@Autowired
	MovimentacaoRepository movimentacaoRepository;

	@Override
	public Movimentacao save(Movimentacao movimentacao) {
		return movimentacaoRepository.save(movimentacao);
	}

	@Override
	public Movimentacao findById(Long movimentacaoId) {
		// TODO Auto-generated method stub
		return null;
	}

}
