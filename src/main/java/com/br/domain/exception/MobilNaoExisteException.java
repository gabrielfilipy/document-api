package com.br.domain.exception;

import java.util.UUID;

public class MobilNaoExisteException extends EntidadeNaoExisteException{
	
	private static final long serialVersionUID = 1L;

	public MobilNaoExisteException(String mensagem) {
		super(mensagem);
	}
	
	public MobilNaoExisteException(UUID mobilId, String siglaMobil) {
		this("O documento informado " + siglaMobil + " não existe");
	}

}
