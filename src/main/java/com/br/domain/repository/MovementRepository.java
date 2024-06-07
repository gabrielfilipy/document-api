package com.br.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.br.domain.model.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long>,
		JpaSpecificationExecutor<Movement>, MovementRepositoryQuery {
	
	Optional<Movement> findById(Long movimentacaoId);
	@Query("SELECT m FROM Movement m WHERE m.mobil.id = :mobilId ORDER BY m.dataHoraCricao DESC")
	Optional<Movement> findFirstByMobilIdOrderByDataHora(@Param("mobilId") Long mobilId);

	@Query("SELECT m FROM Movement m WHERE m.mobil.id = :mobilId ORDER BY m.dataHoraCricao DESC")
	Optional<Movement> buscarPorMovimentacoesDoMobil(@Param("mobilId") Long mobilId);

	@Query("SELECT m FROM Movement m WHERE m.typeMovement = 1 AND m.dataHoraFinalizacao != null AND m.movementId = :ultimaMovimentacaoId")
	Optional<Movement> ultimaMovimentacaoAssinada(Long ultimaMovimentacaoId);

}
