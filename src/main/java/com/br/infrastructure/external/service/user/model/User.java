package com.br.infrastructure.external.service.user.model;

import lombok.*;

@Data
public class User {

	private Long id;
	private String nome;
	private Boolean active;
	private String password;
	private String matricula;
	private String email;
	private Long departmentId;

}
