package com.br.domain.service;

import java.util.Optional;
import com.br.domain.model.Movement;

public interface MovementService {
	
	Movement save(Movement movimentacao);
	Movement findById(Long movimentacaoId);
    Optional<Movement> buscarUltimaMovimentacaoMobilFilho(Long mobilId);
  
}
