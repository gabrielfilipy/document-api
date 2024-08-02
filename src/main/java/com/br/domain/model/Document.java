package com.br.domain.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Table(name = "tbl_document")
@Entity
public class Document implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private UUID documentId;
	
	@NotNull
	@NotBlank
	@Column(name = "descricao")
	private String descricao;

	@Column(name = "file")
	private String file;

	@ManyToOne()
	@JoinColumn(name = "model_id")
	private Model model;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToOne
	private Mobil mobil;

}
