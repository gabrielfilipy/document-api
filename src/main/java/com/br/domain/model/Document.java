package com.br.domain.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "TBL_DOCUMENT")
@Entity
public class Document implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long documentId;
	
	@NotNull
	@Column(name = "subscritor")
	private Long subscritorId;
	
	@NotNull
	@NotBlank
	@Column(name = "descricao")
	private String descricao;

	@Column(name = "file")
	private String file;

	@ManyToOne()
	@JoinColumn(name = "model_id")
	private Model model;

	@ManyToOne()
	@JoinColumn(name = "mobil_id")
	private Mobil mobil;

}
