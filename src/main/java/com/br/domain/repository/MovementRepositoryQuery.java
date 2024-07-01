package com.br.domain.repository;

import com.br.domain.model.Movement;
import com.br.domain.model.enums.TypeMovement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovementRepositoryQuery {

	Page<Movement> buscarMovimentacoesDoMobilFiltro(Long mobilId,TypeMovement typeMovement, Pageable pageable);

}
