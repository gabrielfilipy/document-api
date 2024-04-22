package com.br.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "TBL_DOCUMENT")
@Entity
public class Document {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	@OneToOne
	@JoinColumn(name = "model_id", unique = true)
	private Model model;
	
	@NotNull
	@NotBlank
	@Column(name = "subscritor")
	private Long subscritor;
	
	@NotNull
	@NotBlank
	@Column(name = "descricao")
	private String descricao;

	@OneToOne
	@JoinColumn(name = "mobil_id", unique = true)
	private Mobil mobil;

	@Column(name = "file")
	private String file;
	
}
