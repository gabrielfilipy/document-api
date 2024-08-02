package com.br.domain.service.impl;

import com.br.domain.exception.DocumentoAssinadoException;
import com.br.domain.model.Document;
import com.br.domain.model.Mark;
import com.br.domain.model.Movement;
import com.br.domain.model.enums.TipoMarca;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.domain.exception.MobilNaoExisteException;
import com.br.domain.model.Mobil;
import com.br.domain.model.enums.TypeMovement;
import com.br.domain.repository.MobilRepository;
import com.br.domain.service.*;

@Service
public class MobilServiceImpl implements MobilService {
	
	@Autowired
	private MobilRepository mobilRepository;

	@Autowired
	private MarkService markService;
	
	@Override
	public Mobil save(Mobil mobil) {
		return mobilRepository.save(mobil);
	}

	@Override
	public Page<Mobil> filtro(UUID subscritorId, UUID pessoaRecebedoraId, TypeMovement typeMovement, Pageable pageable) {
		return mobilRepository.buscarMobilsFiltro(subscritorId, pessoaRecebedoraId, typeMovement, pageable);
	}

	@Override
	public Mobil buscarMobil(UUID mobilId) {
		return mobilRepository.findByIdWithMovimentacoes(mobilId)
				.orElseThrow(() -> new RuntimeException("Mobil informado não existe."));
	}

	@Override
	public Mobil buscarMobil(String siglaMobil) {
		return mobilRepository.findByMobilPorSigla(siglaMobil)
				.orElseThrow(() -> new MobilNaoExisteException("O Mobil (" + siglaMobil +") informado não existe."));
	}

	@Override
	public Mobil atribuirMarcaAoMobil(Mobil mobil, TipoMarca tipoMarca) {
		Mark mark = markService.findByTypeMark(tipoMarca);
		mobil.getMarcas().add(mark);
		return mobil;
	}

	@Override
	public Mobil atualizarSiglaDoMobil(Mobil mobil) {

		//Verifica se há alguma movimentação de assinatura.
		for(Movement movement: mobil.getMovimentacoes()) {
			if(movement.getTypeMovement() == TypeMovement.ASSINATURA_COM_SENHA) {
				throw new DocumentoAssinadoException(mobil.getDocumento().getDocumentId());
			}
		}

		String siglaModelo = mobil.getDocumento().getModel().getSiglaModel();
		String siglaMobil = mobil.getSiglaMobil();
		String novaSilga = siglaMobil.replace("TMP", siglaModelo);
		mobil.setSiglaMobil(novaSilga);

		return mobilRepository.save(mobil);
	}

}
