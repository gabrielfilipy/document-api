package com.br.domain.exception;

public class DocumentoNaoFinalizadoException extends EntidadeNaoExisteException {

	private static final long serialVersionUID = 1L;

	public DocumentoNaoFinalizadoException(String mensagem) {
		super(mensagem);
	}

	public DocumentoNaoFinalizadoException(String siglaMobil, Long id) {
		this("Foi identificado que esse documento ainda não foi finalizado e não é possível continuar com essa operação: " + siglaMobil);
	}

}
