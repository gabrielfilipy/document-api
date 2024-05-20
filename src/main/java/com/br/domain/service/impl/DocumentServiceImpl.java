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
	public Document save(Document document) {

        preencherModeloDocumento(document);
		return documentRepository.save(document);
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
