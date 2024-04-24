package com.br.domain.service;

import com.br.domain.model.Marca;

public interface MarcaService {
	
	Marca save(Marca marca,Long mobilId);
	Marca findById(Long marcaId);

}
