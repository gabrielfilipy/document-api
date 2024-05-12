package com.br.domain.model;

import com.br.domain.model.enums.TypeMovement;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "TBL_MOV_DOCUMENT")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Movement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long movementId;

    @Column(name = "pessoaRecebedora")
    private Long pessoaRecebedora;

    @Column(name = "subscritor")
    private Long subscritor;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "date_time")
    @CreationTimestamp
    private LocalDateTime dataHora;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mobil_id", nullable = false)
    private Mobil mobil;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_movimentacao")
    private TypeMovement typeMovement;
    
    @Column(name = "cancelada")
    private boolean cancelada;

}
