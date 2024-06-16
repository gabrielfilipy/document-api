package com.br.domain.model.enums;

/**
 * Classe responsável por representar a coluna
 * ´tipo_marca´ na tabela ´tbl_mark´. Logo,
 * é preciso informar exatamente o nome da marca
 * e seu código que fica representado na coluna
 * ´tipo_marca.
 *
 * @author Gabriel Filipy
 * @created 6 de junho de 2024
 */

public enum TipoMarca {

	CRIACAO_MARCA(1, "Criação"),
	ASSINAR_COM_SENHA(2, "Assinar com senha"),
	INCLUSAO_COSSIGNATARIO(3, "Inclusão de conssignatário");

	private final int markId;
	private final String nome;

	TipoMarca(int markId, String nome) {
		this.markId = markId;
		this.nome = nome;
	}

	public int getCode() {
		return markId;
	}
}
