package com.br.domain.service;

import java.util.UUID;

import com.br.domain.model.Mark;
import com.br.domain.model.enums.TipoMarca;

public interface MarkService {
	
	Mark save(Mark mark);
	Mark findById(UUID markId);
	Mark findByCode(UUID code);
	Mark findByTypeMark(TipoMarca tipoMarca);

}
