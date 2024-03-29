package com.br.domain.service.impl;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.Document;
import com.br.domain.repository.DocumentRepository;
import com.br.domain.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService{

	@Autowired
	private DocumentRepository documentRepository;

	@Override
	public Document save(Document document) {
		return documentRepository.save(document);
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
		return null;
	}

	@Override
	public Document deactivateUser(Long id) {
		Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
		document.setActive(false);
        return documentRepository.save(document);
	}

	@Override
	public Document activateUser(Long id) {
		Document document = documentRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Documento não encontrado!"));
		document.setActive(true);
		return documentRepository.save(document);
	}
	
	
}
