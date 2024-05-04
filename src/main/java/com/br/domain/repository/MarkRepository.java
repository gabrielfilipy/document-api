package com.br.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.domain.model.Mark;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
	
	Optional<Mark> findById(Long markId);

}
