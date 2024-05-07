package com.br.domain.model.enums;

public enum TypeMark {
	
	CRIACAO_MARCA(1L, "Criação"),
	//
	ANEXACAO_MARCA(2L, "Anexação"),
	//
	TRANSIÇÃO_MARCA(3L, "Transição da marca ");
	//

	private final Long code;
	private final String descr;

	TypeMark(Long code, String descr) {
		this.code = code;
		this.descr = descr;
	}

	public Long getCode() {
		return code;
	}
}

