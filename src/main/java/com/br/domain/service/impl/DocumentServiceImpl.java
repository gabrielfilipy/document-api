package com.br.domain.service.impl;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.*;
import com.br.domain.model.Movimentacao;
import com.br.domain.model.enums.TipoMovimentacao;
import com.br.domain.repository.*;
import com.br.domain.repository.MovimentacaoRepository;
import com.br.domain.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService{

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private MobilRepository mobilRepository;
	
	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	

	@Override
	public Document save(Document document, Long mobilId, Long modelId, Long marcaId) {
        Mobil mobil = mobilRepository.findById(mobilId)
                                     .orElseThrow(() -> new EntidadeNaoExisteException("Mobil não encontrado"));
        Model model = modelRepository.findById(modelId)
                                     .orElseThrow(() -> new EntidadeNaoExisteException("Model não encontrado"));
        Marca marca = marcaRepository.findById(marcaId)
                                     .orElseThrow(() -> new EntidadeNaoExisteException("Marca não encontrada"));
        marca.setMobil(mobil);
        document.setModel(model);
        Document savedDocument = documentRepository.save(document);
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setMobil(mobil);
        movimentacao.setTipoMovimentacao(TipoMovimentacao.CRIACAO_DOCUMENTO);
        movimentacaoRepository.save(movimentacao);
        
		return savedDocument;
	}
	
	@Override
	public List<Document> findAll() {
		
		return documentRepository.findAll();
	}

	@Override
	public Document findById(Long id) {
		Optional<Document> document = documentRepository.findById(id);
		if (document.isEmpty()) {
			throw new EntidadeNaoExisteException("Documento informado não existe: " + id);
		}
		return document.get();
	}
	
//	@Override
//	public Document deactivateUser(Long id) {
//		Document document = documentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
//		document.setActive(false);
//        return documentRepository.save(document);
//	}
//
//	@Override
//	public Document activateUser(Long id) {
//		Document document = documentRepository.findById(id)
//			.orElseThrow(() -> new RuntimeException("Documento não encontrado!"));
//		document.setActive(true);
//		return documentRepository.save(document);
//	}
	
}
