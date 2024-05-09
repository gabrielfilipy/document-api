package com.br.domain.model;

import lombok.*;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Getter
@Setter
@Table(name = "TBL_MODEL_DOCUMENT")
@Entity
public class Model implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long modelId;

    @Column(name = "sigla")
    private String siglaModel;

    @Column(name = "label")
    private String label;

    private String descricaoCompleta;

    @Column(name = "html")
    private String html;

    @Column(name = "active")
    private Boolean active;
    
    @ManyToMany(mappedBy = "model")
    private Set<Document> document = new HashSet<>();
    

}
