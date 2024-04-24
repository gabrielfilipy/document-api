package com.br.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.domain.model.Model;

public interface ModelRepository  extends JpaRepository<Model, Long>{
	
	Optional<Model> findById(Long modelId);

}
