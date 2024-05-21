package com.br.domain.service;

import com.br.domain.model.Mobil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MobilService {

	Mobil save(Mobil mobil);
	Page<Mobil> filtro(Pageable pageable) ;
	Mobil buscarMobil(Long mobilId);
	Mobil buscarMobil(String sigla);


}
