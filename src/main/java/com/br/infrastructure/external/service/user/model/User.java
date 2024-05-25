package com.br.infrastructure.external.service.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private String nome;
	private Boolean active;
    private Long id;
	private String password;
	private String matricula;
	private String email;
	private Long departmentId;
}
