package com.br.domain.service;

import com.br.domain.model.Mobil;
import org.springframework.data.domain.Page;

public interface MobilService {
	
	Mobil save(Mobil mobil);
	Mobil fyndById(Long mobilId);
	Mobil buscarMobil(Long mobilId);

}
