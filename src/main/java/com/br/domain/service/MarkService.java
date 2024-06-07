package com.br.domain.service;

import com.br.domain.model.Mark;
import com.br.domain.model.enums.TipoMarca;

public interface MarkService {
	
	Mark save(Mark mark);
	Mark findById(Long markId);
	Mark findByCode(Long code);
	Mark findByTypeMark(TipoMarca tipoMarca);

}
