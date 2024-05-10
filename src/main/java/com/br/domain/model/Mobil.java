package com.br.domain.model;

import lombok.*;
import javax.persistence.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dateCreate;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "mobil", cascade = CascadeType.ALL )
    @BatchSize(size = 1)
    private List<Movement> movimentacaos = new ArrayList<>();
    
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ult_mov")
	private Movement ultimaMovimentacaoNaoCancelada;
    
    @Column(name = "user_id")
    private Long userId;
   
	@Column(name = "sigla")
	private String sigla;
    
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    name = "mobil_mark",
    joinColumns = @JoinColumn(name = "mobil_id"),
    inverseJoinColumns = @JoinColumn(name = "marca_id")
    )
    private Set<Mark> mark = new HashSet<>();
    
    @OneToMany(mappedBy = "mobil", fetch = FetchType.EAGER)
    private List<Document> documento  = new ArrayList<>();

}
