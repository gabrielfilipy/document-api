package com.br.domain.service;

import com.br.domain.model.Role;
import com.br.domain.model.enums.RoleType;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByRoleName(RoleType name);

}
