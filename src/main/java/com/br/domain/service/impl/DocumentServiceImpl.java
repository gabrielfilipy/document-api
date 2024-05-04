package com.br.domain.service.impl;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.core.config.Generator;
import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.*;
import com.br.domain.model.enums.TypeMovement;
import com.br.domain.repository.*;
import com.br.domain.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService{
	
	private static final int TAMANHO_SIGLA = 2;

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private MobilRepository mobilRepository;
	
	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	private MarkRepository markRepository;
	
	@Autowired
	private MovementRepository movementRepository;
	
	@Autowired
	private Generator generator;
	
	@Override
	public Document save(Document document, Long modelId, Long markId) {
		System.out.println(modelId+markId);
        Model model = modelRepository.findById(modelId)
                                     .orElseThrow(() -> new EntidadeNaoExisteException("Model não encontrado"));
        Mark mark = markRepository.findById(markId)
                .orElseThrow(() -> new EntidadeNaoExisteException("Marca não encontrada"));
        document.setModel(model);
        Mobil mobil = new Mobil();
        mobil.setDateCreate(LocalDateTime.now());
        mobil.getMark().add(mark);
        Movement movimentacao = new Movement();
        movimentacao.setMobil(mobil);
        movimentacao.setTypeMovement(TypeMovement.CRIACAO);	
        mobil = mobilRepository.save(mobil); 
        movimentacao = movementRepository.save(movimentacao);
        movimentacao = movementRepository.findFirstByMobilIdOrderByDataHora(mobil.getMobilId()).get();
        mobil.setUltimaMovimentacaoNaoCancelada(movimentacao);
        mobilRepository.save(mobil);
        document.setMobil(mobil);
	    Document savedDocument = documentRepository.save(document);
	    mobil.setSigla(getSiglaTemporario());
	    mobil.getDocumento().add(savedDocument);
        mobilRepository.save(mobil);
        savedDocument.setMobil(mobil);
        return savedDocument;
	}	
	

	private String getSiglaFinalizado(Document document) {
		String modelo = document.getModel().getSiglaModel();
		String[] palavras = modelo.split("\\s+");
        int indiceUltimaPalavra = palavras.length - 1;
        String ultimaPalavra = palavras[indiceUltimaPalavra].substring(0,  2);
        String primeiraPalavra = modelo.substring(0,  2);
        String nomeDocumento = primeiraPalavra + ultimaPalavra;
        generator.sigla(document.getDocumentId());
		return generator.getSiglaFinalizado(nomeDocumento);
	}
	
	private String getSiglaTemporario() {
		return generator.getSiglaTemporario();
	}
	
	@Override
	public List<Document> findAll() {
		
		return documentRepository.findAll();
	}

	@Override
	public Document findById(Long id) {
		Optional<Document> document = documentRepository.findById(id);
		if (document.isEmpty()) {
			throw new EntidadeNaoExisteException("Documento ");
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
