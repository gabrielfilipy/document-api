package com.br.domain.service;

import com.br.domain.model.Mark;

public interface MarkService {
	
	Mark save(Mark mark);
	Mark findById(Long markId);

}
