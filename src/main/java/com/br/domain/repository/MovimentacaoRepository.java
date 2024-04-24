package com.br.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.domain.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{
	
	Optional<Movimentacao> findById(Long movimentacaoId);

}
