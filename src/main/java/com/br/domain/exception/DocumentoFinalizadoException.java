package com.br.domain.exception;

import java.util.UUID;

public class DocumentoFinalizadoException extends EntidadeNaoExisteException {

	private static final long serialVersionUID = 1L;

	public DocumentoFinalizadoException(String mensagem) {
		super(mensagem);
	}

	public DocumentoFinalizadoException(String siglaMobil, UUID id) {
		this("Foi identificado que esse documento já foi finalizado e não é possível continuar com essa operação: " + siglaMobil);
	}

}
