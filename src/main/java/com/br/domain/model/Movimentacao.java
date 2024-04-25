 package com.br.domain.model;

import com.br.domain.model.enums.TipoMovimentacao;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "TBL_MOV_DOCUMENT")
@Entity
public class Movimentacao {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pessoaRecebedora")
    private Long pessoaRecebedora;

    @Column(name = "subscritor")
    private Long subscritor;

    @Column(name = "date_time")
    @CreationTimestamp
    private LocalDateTime dataHora;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Mobil mobil;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_movimentacao")
    private TipoMovimentacao tipoMovimentacao;

}
