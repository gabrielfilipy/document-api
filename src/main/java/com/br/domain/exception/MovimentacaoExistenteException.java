package com.br.domain.exception;

public class MovimentacaoExistenteException extends EntidadeEmUsoException {

	private static final long serialVersionUID = 1L;

	public MovimentacaoExistenteException(String mensagem) {
		super(mensagem);
	}

	public MovimentacaoExistenteException(Long movementId) {
		this("A Movimentação informada já existe: " + movementId);
	}

}
