package com.br.domain.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.br.domain.model.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, UUID>,
		JpaSpecificationExecutor<Movement>, MovementRepositoryQuery {
	
	Optional<Movement> findById(UUID movimentacaoId);
	@Query("SELECT m FROM Movement m WHERE m.mobil.id = :mobilId ORDER BY m.dataHoraCricao DESC")
	Optional<Movement> findFirstByMobilIdOrderByDataHora(@Param("mobilId") UUID mobilId);

	@Query("SELECT m FROM Movement m WHERE m.mobil.id = :mobilId ORDER BY m.dataHoraCricao DESC")
	Optional<Movement> buscarPorMovimentacoesDoMobil(@Param("mobilId") UUID mobilId);

	@Query("SELECT m FROM Movement m WHERE m.typeMovement = 1 AND m.dataHoraFinalizacao != null AND m.movementId = :ultimaMovimentacaoId")
	Optional<Movement> ultimaMovimentacaoAssinada(UUID ultimaMovimentacaoId);

}
