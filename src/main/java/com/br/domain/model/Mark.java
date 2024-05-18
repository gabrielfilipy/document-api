package com.br.domain.model;

import lombok.*;
import java.io.Serializable;
import javax.persistence.*;
import com.br.domain.model.enums.TypeMark;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbl_mark")
@Entity
public class Mark implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id")
    private Long markId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "desc_detalhada")
    private String descricaoDetalhada;
    
    @Column(name = "code")
    private Long code;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_marca")
    private TypeMark typeMark;

}
