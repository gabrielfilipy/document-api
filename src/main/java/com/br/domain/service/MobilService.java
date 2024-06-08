package com.br.domain.service;

import com.br.domain.model.Document;
import com.br.domain.model.Mobil;
import com.br.domain.model.enums.TipoMarca;
import com.br.domain.model.enums.TypeMovement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface MobilService {

	Mobil save(Mobil mobil);
	Page<Mobil> filtro(Long subscritorId, Long pessoaRecebedoraId, TypeMovement typeMovement, Pageable pageable);
	Mobil buscarMobil(Long mobilId);
	Mobil buscarMobil(String sigla);
	Mobil atribuirMarcaAoMobil(Mobil mobil, TipoMarca tipoMarca);
	Mobil atualizarSiglaDoMobil(Mobil mobil);

}
