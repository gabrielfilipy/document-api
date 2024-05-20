package com.br.domain.repository;

import com.br.domain.model.Movement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovementRepositoryQuery {

	Page<Movement> buscarMovimentacoesDoMobilFiltro(Long mobilId, Pageable pageable);

}
