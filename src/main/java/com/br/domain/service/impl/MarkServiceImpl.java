package com.br.domain.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.*;
import com.br.domain.model.enums.TypeMovement;
import com.br.domain.repository.*;
import com.br.domain.service.MarkService;

@Service
public class MarkServiceImpl implements MarkService {
	
	@Autowired
	MarkRepository markRepository;
	
	@Autowired
	MobilRepository mobilRepository;
	
	@Autowired
	MovementRepository movementRepository;

	@Override
	public Mark save(Mark mark) {
	        Mark marcaSalvar = markRepository.save(mark);
	        Movement movimentacao = new Movement();
	        movimentacao.setTypeMovement(TypeMovement.CRIACAO);	 
	        movementRepository.save(movimentacao);
		    return  marcaSalvar;
	}

	@Override
	public Mark findById(Long markId) {
			Optional<Mark> mark = markRepository.findById(markId);
			if(mark.isEmpty()) {
				throw new EntidadeNaoExisteException("Marca informada não existe: " + markId);
			}
			return mark.get();
		}
}
