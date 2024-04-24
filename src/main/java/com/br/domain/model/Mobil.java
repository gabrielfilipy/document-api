package com.br.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "TBL_MOBIL_DOCUMENT")
@Entity
public class Mobil {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @OneToMany(mappedBy = "mobil", fetch = FetchType.LAZY )
    private Set<Movimentacao> movimentacoes = new HashSet<>();
 
    @OneToMany(mappedBy = "mobil", fetch = FetchType.LAZY )
    private Set<Marca> marcas = new HashSet<>();

}
