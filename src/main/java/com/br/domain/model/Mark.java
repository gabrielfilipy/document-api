package com.br.domain.model;

import lombok.*;
import java.io.Serializable;
import javax.persistence.*;
import com.br.domain.model.enums.TypeMark;

@Getter
@Setter
@Table(name = "TBL_MARK_MOBIL")
@Entity
public class Mark implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long markId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "desc_detalhada")
    private String descricaoDetalhada;
    
    @Column(name = "code")
    private Long code;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movement_id")
	private Movement movimentacao;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_marca")
    private TypeMark typeMark;

}
