package com.br.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.domain.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

}
