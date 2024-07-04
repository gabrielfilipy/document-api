package com.br.infrastructure.external.service.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.infrastructure.external.service.departament.model.Department;
import com.br.infrastructure.external.service.user.model.User;

import java.util.List;

@Component
@FeignClient(value = "User", url = "http://localhost:8082/v1/user")
public interface UserFeignClient {

	
	@RequestMapping(method = RequestMethod.GET, value = "/buscar/{id}")
	User getUserId(@PathVariable("id") Long id);

	@RequestMapping(method = RequestMethod.GET, value = "/department/{departamentId}")
	List<User> buscarTodosUsuarioDeDeterminadoDepartamento(@PathVariable("departamentId") Long departamentId);

}
