package com.br.domain.service;

import com.br.domain.model.Mobil;

public interface MobilService {
	
	Mobil save(Mobil mobil);
	Mobil fyndById(Long mobilId);
}
