package com.br.domain.exception;

public class SubscritorNaoPodeSerCossignatarioException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public SubscritorNaoPodeSerCossignatarioException(String message) {
		super(message);
	}
	
	public SubscritorNaoPodeSerCossignatarioException(String messsage, Throwable cases) {
		super(messsage, cases);
	}
}