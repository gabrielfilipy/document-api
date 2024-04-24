package com.br.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.Mobil;
import com.br.domain.model.Movimentacao;
import com.br.domain.model.enums.TipoMovimentacao;
import com.br.domain.repository.MobilRepository;
import com.br.domain.service.MobilService;
import com.br.domain.service.MovimentacaoService;

@Service
public class MobilServiceImpl implements MobilService{
	
	@Autowired
	MobilRepository mobilRepository;
	
	@Autowired
	MovimentacaoService movimentacaoService;
	
	

	@Override
	public Mobil save(Mobil mobil) {
		return mobilRepository.save(mobil);
	}
	

	@Override
	public Mobil fyndById(Long mobilId) {
		// TODO Auto-generated method stub
		return null;
	}



}
