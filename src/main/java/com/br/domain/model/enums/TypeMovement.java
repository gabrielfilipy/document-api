package com.br.domain.model.enums;

public enum TypeMovement{

	CRIACAO(1, "CRIACAO"),
	ASSINATURA_COM_SENHA(2, "ASSINATURA_COM_SENHA"),
	INCLUSAO_DE_COSIGNATARIO(3, "INCLUSAO_DE_COSSIGNATARIO"),
	TRAMITAR(4, "TRAMITAÇÃO"),
	FINALIZACAO(5, "FINALIZACAO");
	
	private final int id;
	private final String descr;

	TypeMovement(int id, String descr) {
		this.id = id;
		this.descr = descr;
	}

	public int getId() {
		return id;
	}
}
