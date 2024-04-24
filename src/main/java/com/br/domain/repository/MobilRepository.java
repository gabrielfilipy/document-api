package com.br.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.domain.model.Mobil;

public interface MobilRepository extends JpaRepository<Mobil, Long> {

	Optional<Mobil> findById(Long mobilId);
}
