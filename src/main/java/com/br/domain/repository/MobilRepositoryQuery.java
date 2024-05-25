package com.br.domain.repository;

import com.br.domain.model.Mobil;
import com.br.domain.model.enums.TypeMovement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MobilRepositoryQuery {

	Page<Mobil> buscarMobilsFiltro(Long subscritorId, Long pessoaRecebedoraId, TypeMovement typeMovement, Pageable pageable);

}
