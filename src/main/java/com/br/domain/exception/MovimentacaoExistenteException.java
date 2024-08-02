package com.br.domain.exception;

import java.util.UUID;

public class MovimentacaoExistenteException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public MovimentacaoExistenteException(String mensagem) {
		super(mensagem);
	}

	public MovimentacaoExistenteException(UUID movementId) {
		this("A Movimentação informada já existe: " + movementId);
	}

}
