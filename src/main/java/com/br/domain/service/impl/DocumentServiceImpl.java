package com.br.domain.service.impl;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.core.config.Generator;
import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.*;
import com.br.domain.model.enums.*;
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
	public Document save(Document document, Long subscritorId) {
        Model model = modelRepository.findById(document.getModel().getModelId())
                                     .orElseThrow(() -> new EntidadeNaoExisteException("Model não encontrado"));
        Mark mark = markRepository.findByCode(TypeMark.CRIACAO_MARCA.getCode())
        		.orElseThrow(() -> new EntidadeNaoExisteException("Marca não encontrado"));  
        document.setModel(model);
        Mobil mobil = new Mobil();
        Movement movimentacao = new Movement();
        mobil.setDateCreate(LocalDateTime.now());
        mobil.getMarcas().add(mark);
        mobil.setSubscritorId(subscritorId);
        movimentacao.getSubscritorId();
        movimentacao.setMobil(mobil);
        movimentacao.setPessoaRecebedoraId(movimentacao.getPessoaRecebedoraId());
        movimentacao.setSubscritorId(movimentacao.getSubscritorId());
        movimentacao.setTypeMovement(TypeMovement.CRIACAO);	
        mobil = mobilRepository.save(mobil); 
        movimentacao = movementRepository.save(movimentacao);
        movimentacao = movementRepository.findFirstByMobilIdOrderByDataHora(mobil.getMobilId()).get();
        mobil.setUltimaMovimentacaoId(movimentacao.getMovementId());
        mobilRepository.save(mobil);
        document.setMobil(mobil);
	    Document savedDocument = documentRepository.save(document);
	    mobil.setSiglaMobil(getSiglaTemporario());
	    mobil.setDocumento(savedDocument);
        mobilRepository.save(mobil);
        savedDocument.setMobil(mobil);
        return savedDocument;
	}

	public void preencherModeloDocumento(Document document) {

		document.setFile("NOVO HTML DOCUMENTO");
	}

	private String getSiglaFinalizado(Document document) {
		String modelo = "";
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
			throw new EntidadeNaoExisteException("Documento não existe.");
		}
		return document.get();
	}
	
}
