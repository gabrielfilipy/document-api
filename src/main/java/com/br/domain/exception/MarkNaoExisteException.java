package com.br.domain.exception;

public class MarkNaoExisteException extends EntidadeNaoExisteException {

	private static final long serialVersionUID = 1L;

	public MarkNaoExisteException(String mensagem) {
		super(mensagem);
	}

	public MarkNaoExisteException(Long markId) {
		this("A Marca informada não foi encontrada: " + markId);
	}

}
