package com.br.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.Mobil;
import com.br.domain.repository.MobilRepository;
import com.br.domain.service.*;

@Service
public class MobilServiceImpl implements MobilService{
	
	@Autowired
	MobilRepository mobilRepository;
	
	@Autowired
	MovementService movementService;
	
	@Override
	public Mobil save(Mobil mobil) {
		return mobilRepository.save(mobil);
	}
	
	@Override
	public Mobil fyndById(Long mobilId) {
		Optional<Mobil> mobil = mobilRepository.findById(mobilId);
		if(mobil.isEmpty()) {
			throw new EntidadeNaoExisteException("Mobil informado não existe: " + mobilId);
		}
		return mobil.get();
	}

}
