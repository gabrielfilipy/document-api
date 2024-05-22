package com.br.domain.exception;

public class MobilNaoExisteException extends EntidadeNaoExisteException{
	
	private static final long serialVersionUID = 1L;

	public MobilNaoExisteException(String mensagem) {
		super(mensagem);
	}
	
	public MobilNaoExisteException(Long mobilId, String siglaMobil) {
		this("O documento informado " + siglaMobil + " não existe");
	}

}
