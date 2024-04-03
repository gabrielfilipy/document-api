package com.br.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "TBL_DOCUMENTO")
@Entity
public class Document {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Boolean active;
	
	@NotNull
	@NotBlank
	@Column(name = "modelo")
	private String modelo;
	
	@NotNull
	@NotBlank
	@Column(name = "meustextosPadroes")
	private String meustextosPadroes;
	
	@NotNull
	@NotBlank
	@Column(name = "responsavelPelaAssinatura")
	private String responsavelPelaAssinatura;
	
	@NotNull
	@NotBlank
	@Column(name = "nomeCompleto")
	private String nomeCompleto;
	
	@NotNull
	@NotBlank
	@Column(name = "interessado")
	private String interessado;
	
	@NotNull
	@NotBlank
	@Column(name = "assunto")
	private String assunto;
	
	@NotNull
	@NotBlank
	@Column(name = "numeroDeReferencia")
	private String numeroDeReferencia;
	
}
