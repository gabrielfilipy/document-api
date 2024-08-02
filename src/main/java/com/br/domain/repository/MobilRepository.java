package com.br.domain.repository;

import java.util.*;

import com.br.domain.model.Movement;
import feign.Param;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.br.domain.model.Mobil;

@Repository
public interface MobilRepository extends JpaRepository<Mobil, UUID>,
		JpaSpecificationExecutor<Movement>, MobilRepositoryQuery {

	@Query("SELECT m FROM Mobil m LEFT JOIN FETCH m.movimentacoes WHERE m.mobilId = :mobilId")
	Optional<Mobil> findByIdWithMovimentacoes(@Param("mobilId") UUID mobilId);

	@Query("SELECT m FROM Mobil m LEFT JOIN FETCH m.movimentacoes WHERE m.siglaMobil = :siglaMobil")
	Optional<Mobil> findByMobilPorSigla(@Param("sigla") String siglaMobil);

	Optional<Mobil> findById(UUID mobilId);

}
