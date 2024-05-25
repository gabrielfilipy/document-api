package com.br.infrastructure.external.service.departament;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.infrastructure.external.service.departament.model.Department;

@Component
@FeignClient(value = "root", url = "http://localhost:8081/v1/department")
public interface DepartmentFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/buscar/{id}")
	Department getDepartment(@PathVariable("id") Long id);
	
}

