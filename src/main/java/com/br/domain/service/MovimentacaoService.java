package com.br.domain.service;

import com.br.domain.model.Movimentacao;

public interface MovimentacaoService {
	
	Movimentacao save(Movimentacao movimentacao);
	Movimentacao findById(Long movimentacaoId);

}
