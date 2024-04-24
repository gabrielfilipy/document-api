package com.br.domain.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Table(name = "TBL_MARCA_MOBIL")
@Entity
public class Marca {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "desc_detalhada")
    private String descricaoDetalhada;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Mobil mobil;

}
