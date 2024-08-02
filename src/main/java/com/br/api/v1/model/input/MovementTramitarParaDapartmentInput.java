package com.br.api.v1.model.input;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovementTramitarParaDapartmentInput {

    private UUID subscritorId;
    private UUID departmentId;

}
