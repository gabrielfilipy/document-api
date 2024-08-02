package com.br.domain.model;

import com.br.domain.model.enums.TypeMovement;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Table(name = "tbl_movement")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Movement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID movementId;

    @Column(name = "pessoa_recebedora_id")
    private UUID pessoaRecebedoraId;

    @Column(name = "subscritor_id")
    private UUID subscritorId;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(name = "date_time_create")
    @CreationTimestamp
    private OffsetDateTime dataHoraCricao;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(name = "date_time_final")
    private OffsetDateTime dataHoraFinalizacao;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Mobil mobil;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_movimentacao")
    private TypeMovement typeMovement;

}
