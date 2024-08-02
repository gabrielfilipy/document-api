package com.br.infrastructure.external.service.governmentagency.model;

import java.util.UUID;

import lombok.*;

@Getter
@Setter
public class Orgao {

    private UUID orgaoId;
    private String nome;
    private boolean active;
    private Endereco endereco;

}
