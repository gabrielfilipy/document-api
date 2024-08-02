package com.br.domain.repository;

import com.br.domain.model.Movement;
import com.br.domain.model.enums.TypeMovement;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovementRepositoryQuery {

	Page<Movement> buscarMovimentacoesDoMobilFiltro(UUID mobilId,TypeMovement typeMovement, Pageable pageable);

}
