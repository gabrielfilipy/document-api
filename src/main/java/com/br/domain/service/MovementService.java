package com.br.domain.service;

import java.util.List;
import java.util.Optional;

import com.br.domain.model.Mobil;
import com.br.domain.model.Movement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface MovementService {
	
	Movement save(Movement movimentacao);
	Movement findById(Long movimentacaoId);
	Page<Movement> findAll(Specification<Movement> spec, Pageable pageable);
	Page<Movement> buscarMovimentacoesDoMobilFiltro(Long mobilId, Pageable pageable);
  
}
