package com.br.domain.service;

import java.util.List;
import java.util.Optional;

import com.br.domain.model.Mobil;
import com.br.domain.model.Movement;

public interface MovementService {
	
	Movement save(Movement movimentacao);
	Movement findById(Long movimentacaoId);
    Optional<Movement> buscarUltimaMovimentacaoMobilFilho(Long mobilId);

	Mobil buscarMovimentacoesDoMobil(Long mobilId);

	List<Movement> findAll();
  
}
