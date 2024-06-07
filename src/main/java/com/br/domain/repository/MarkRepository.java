package com.br.domain.repository;

import java.util.Optional;

import com.br.domain.model.enums.TipoMarca;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.br.domain.model.Mark;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
	
	Optional<Mark> findById(Long markId);

	@Query("SELECT m FROM Mark m WHERE m.tipoMarca = :tipoMarca")
	Optional<Mark> findByTypeMark(@Param("tipoMarca") TipoMarca tipoMarca);

	@Query("SELECT m FROM Mark m WHERE m.code = :code")
	Optional<Mark> findByCode(@Param("code") Long code);

}
