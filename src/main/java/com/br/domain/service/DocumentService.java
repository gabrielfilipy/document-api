package com.br.domain.service;

import java.util.List;

import com.br.domain.model.Document;

public interface DocumentService {

	Document save(Document document, Long mobilId, Long modelId, Long marcaId);
	List<Document> findAll();
	Document findById(Long id);
//	Document deactivateUser(Long id);
//	Document activateUser(Long id);
}
