package com.br.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.domain.model.Model;

@Repository
public interface ModelRepository  extends JpaRepository<Model, Long>{
	
	Optional<Model> findById(Long modelId);

}
