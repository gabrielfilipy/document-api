package com.br.domain.repository;

import com.br.domain.model.Mobil;
import com.br.domain.model.enums.TypeMovement;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MobilRepositoryQuery {

	Page<Mobil> buscarMobilsFiltro(UUID subscritorId, UUID pessoaRecebedoraId, TypeMovement typeMovement, Pageable pageable);

}
