package com.br.domain.service;

import com.br.domain.model.Mobil;
import com.br.domain.model.Movement;
import com.br.domain.model.enums.TypeMovement;

import java.util.UUID;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

public interface MovementService {
	
	Movement save(Movement movimentacao);
	Movement verificaAssinaturaDoSubscritor(String siglaMobil, UUID subscritorId);
	Movement verificaFinalizacaoDoDocumento(String siglaMobil);
	Movement verificaTramitacaoDocumento(String siglaMobil);
	Movement verificaInclusaoDeCossignatario(String siglaMobil, UUID pessoaRecebedoraId);
	Movement buscarPorCossignatario(String siglaMobil, UUID pessoaRecebedoraId);
	Movement criarMovimentacaoExcluirDocumento(String siglaMobil, UUID subscritorId);
	Movement criarMovimentacaoAssinarComSenha(String siglaMobil, UUID subscritorId);
	Movement criarMovimentacaoIncluirCossignatario(String siglaMobil, UUID subscritorId, UUID pessoaRecebedoraId);
	Movement criarMovimentacaoTramitarDocumento(String siglaMobil, UUID subscritorId, UUID pessoaRecebedoraId);
	Movement criarMovimentacaoTramitarDocumentoParaLotacao(String siglaMobil, UUID subscritorId, UUID departmentId);
	Movement criarMovimentacaoFinalizacaoDocumento(String siglaMobil, UUID subscritorId);
	Movement criacaoMovimentacaoRecebimentoDocumento(String siglaMobil, UUID subscritorId, UUID pessoaRecebedoraId);
	Movement findById(UUID movimentacaoId);
	void verificarEExcluirMovimentacao(String siglaMobil, UUID movimentacaoId);
	Movement criarMovimentacao(TypeMovement typeMovement, UUID subscritorId, UUID pessoaRecebedoraId, Mobil mobil);
	Page<Movement> findAll(Specification<Movement> spec, Pageable pageable);
	Page<Movement> buscarMovimentacoesDoMobilFiltro(UUID mobilId, TypeMovement typeMovement, Pageable pageable);
	Boolean buscarMovimentacoesDoMobilFiltroBoolean(UUID mobilId, TypeMovement typeMovement);

}
