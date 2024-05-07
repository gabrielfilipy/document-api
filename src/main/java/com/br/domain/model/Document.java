package com.br.domain.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Table(name = "TBL_DOCUMENT")
@Entity
public class Document implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long documentId;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "model_id")
	 private Model model;
	
	@NotNull
	@Column(name = "subscritor")
	private Long subscritorId;
	
	@NotNull
	@NotBlank
	@Column(name = "descricao")
	private String descricao;

	@NotNull
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "mobil_id", unique = true)
	private Mobil mobil;

	@Column(name = "file")
	private String file;
	
}
