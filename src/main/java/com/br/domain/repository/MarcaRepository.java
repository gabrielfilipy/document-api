package com.br.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.domain.model.Marca;


public interface MarcaRepository extends JpaRepository<Marca, Long> {
	
	Optional<Marca> findById(Long marcaId);

}
