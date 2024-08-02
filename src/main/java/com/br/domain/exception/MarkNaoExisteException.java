package com.br.domain.exception;

import java.util.UUID;

public class MarkNaoExisteException extends EntidadeNaoExisteException {

	private static final long serialVersionUID = 1L;

	public MarkNaoExisteException(String mensagem) {
		super(mensagem);
	}

	public MarkNaoExisteException(UUID markId) {
		this("A Marca informada não foi encontrada: " + markId);
	}

}
