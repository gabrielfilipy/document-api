package com.br.domain.service;

import com.br.domain.model.Mobil;
import com.br.domain.model.Movement;
import com.br.domain.model.enums.TypeMovement;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

public interface MovementService {
	
	Movement save(Movement movimentacao);
	Movement verificaAssinaturaDoSubscritor(String siglaMobil, Long subscritorId);
	Movement verificaFinalizacaoDoDocumento(String siglaMobil);
	Movement buscarPorCossignatario(String siglaMobil, Long pessoaRecebedoraId);
	Movement criarMovimentacaoAssinarComSenha(String siglaMobil, Long subscritorId);
	Movement criarMovimentacaoIncluirCossignatario(String siglaMobil, Long subscritorId, Long pessoaRecebedoraId);
	Movement criarMovimentacaoTramitarDocumento(String siglaMobil, Long subscritorId, Long pessoaRecebedoraId);
	Movement criarMovimentacaoTramitarDocumentoParaLotacao(String siglaMobil, Long subscritorId, Long departmentId);
	Movement criarMovimentacaoFinalizacaoDocumento(String siglaMobil, Long subscritorId);
	Movement findById(Long movimentacaoId);
	Movement criarMovimentacao(TypeMovement typeMovement, Long subscritorId, Long pessoaRecebedoraId, Mobil mobil);
	Page<Movement> findAll(Specification<Movement> spec, Pageable pageable);
	Page<Movement> buscarMovimentacoesDoMobilFiltro(Long mobilId, TypeMovement typeMovement, Pageable pageable);
  
}
