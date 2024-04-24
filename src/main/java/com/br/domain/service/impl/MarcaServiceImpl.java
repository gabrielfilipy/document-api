package com.br.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.Marca;
import com.br.domain.model.Mobil;
import com.br.domain.repository.MarcaRepository;
import com.br.domain.repository.MobilRepository;
import com.br.domain.service.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService {
	
	@Autowired
	MarcaRepository marcaRepository;
	
	@Autowired
	MobilRepository mobilRepository;

	@Override
	public Marca save(Marca marca,Long mobilId) {
		 if (marca.getMobil() != null) {
	            Mobil mobil = mobilRepository.findById(mobilId)
	                                          .orElseThrow(() -> new EntidadeNaoExisteException("Mobil não encontrado"));
	            marca.setMobil(mobil);
	        } else {
	            throw new EntidadeNaoExisteException("ID do Mobil não pode ser nulo");
	        }
		return marcaRepository.save(marca);
	}

	@Override
	public Marca findById(Long marcaId) {
		// TODO Auto-generated method stub
		return null;
	}

}
