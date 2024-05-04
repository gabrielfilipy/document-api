package com.br.domain.model;

import lombok.*;
import javax.persistence.*;
import org.hibernate.annotations.BatchSize;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Table(name = "TBL_MOBIL_DOCUMENT")
@Entity
public class Mobil implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long mobilId;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @OneToMany(mappedBy = "mobil", fetch = FetchType.LAZY )
    @BatchSize(size = 1)
    private List<Movement> movimentacaos = new ArrayList<>();
    
    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ult_mov")
	private Movement ultimaMovimentacaoNaoCancelada;
    
    @Column(name = "user_id")
    private Long userId;
   
	@Column(name = "sigla")
	private String sigla;
    
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "mobil_mark",
    joinColumns = @JoinColumn(name = "mobil_id"),
    inverseJoinColumns = @JoinColumn(name = "marca_id")
    )
    private Set<Mark> mark = new HashSet<>();
    
    @OneToMany(mappedBy = "mobil", fetch = FetchType.LAZY)
    private List<Document> documento  = new ArrayList<>();

}
