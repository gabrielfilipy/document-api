package com.br.domain.service;

import com.br.domain.model.Mobil;
import com.br.domain.model.Movement;
import com.br.domain.model.enums.TypeMovement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface MovementService {
	
	Movement save(Movement movimentacao);
	Movement verificarSeOSubscritorAssinou(String siglaDocumento, Long subscritorId);
	Movement criarMovimentacaoAssinarComSenha(String siglaDocumento, Long subscritorId);
	Movement findById(Long movimentacaoId);
	Movement criarMovimentacao(TypeMovement typeMovement, Long subscritorId, Long pessoaRecebedoraId, Mobil mobil);
	Page<Movement> findAll(Specification<Movement> spec, Pageable pageable);
	Page<Movement> buscarMovimentacoesDoMobilFiltro(Long mobilId, Pageable pageable);
	Movement criarMovimentacaoTramitarDocumentoPessoa(String siglaMobil, Long pessoaRecebedoraId, Long subscritorId);

}
