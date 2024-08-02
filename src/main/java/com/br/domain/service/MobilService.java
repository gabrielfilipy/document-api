package com.br.domain.service;

import com.br.domain.model.Mobil;
import com.br.domain.model.enums.TipoMarca;
import com.br.domain.model.enums.TypeMovement;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MobilService {

	Mobil save(Mobil mobil);
	Page<Mobil> filtro(UUID subscritorId, UUID pessoaRecebedoraId, TypeMovement typeMovement, Pageable pageable);
	Mobil buscarMobil(UUID mobilId);
	Mobil buscarMobil(String sigla);
	Mobil atribuirMarcaAoMobil(Mobil mobil, TipoMarca tipoMarca);
	Mobil atualizarSiglaDoMobil(Mobil mobil);

}
