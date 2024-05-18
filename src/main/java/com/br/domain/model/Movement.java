package com.br.domain.model;

import com.br.domain.model.enums.TypeMovement;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "tbl_movement")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Movement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long movementId;

    @Column(name = "pessoa_recebedora_id")
    private Long pessoaRecebedoraId;

    @Column(name = "subscritor_id")
    private Long subscritorId;

    @Column(name = "date_time_create")
    @CreationTimestamp
    private LocalDateTime dataHoraCricao;

    @Column(name = "date_time_final")
    private LocalDateTime dataHoraFinalizacao;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Mobil mobil;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_movimentacao")
    private TypeMovement typeMovement;

}
