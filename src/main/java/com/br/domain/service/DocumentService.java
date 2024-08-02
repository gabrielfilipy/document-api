package com.br.domain.service;

import java.util.List;
import java.util.UUID;

import com.br.domain.model.Document;

public interface DocumentService {

	Document save(Document document, UUID subscritorId );
	List<Document> findAll();
	Document findById(UUID id);
//	Document activateUser(Long id);
	
}
