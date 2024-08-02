package com.br.infrastructure.notification.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Mensagem {

    private String destinatario;
	private String userLogin;
	private String userPassword;
	private String userName;

}
