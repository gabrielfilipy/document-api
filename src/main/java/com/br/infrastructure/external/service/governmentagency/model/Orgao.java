package com.br.infrastructure.external.service.governmentagency.model;

import lombok.*;

@Getter
@Setter
public class Orgao {

    private Long orgaoId;
    private String nome;
    private boolean active;
    private Endereco endereco;

}
