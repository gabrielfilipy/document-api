package com.br.domain.exception;

import java.util.UUID;

public class DocumentoAssinadoException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public DocumentoAssinadoException(String mensagem) {
		super(mensagem);
	}

	public DocumentoAssinadoException(UUID id) {
		this("Foi identificado que esse documento já está assinado e não pode atualizar sua sigla.: " + id);
	}

}
