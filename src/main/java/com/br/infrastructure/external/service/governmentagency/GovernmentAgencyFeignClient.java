package com.br.infrastructure.external.service.governmentagency;

import com.br.infrastructure.external.service.governmentagency.model.Orgao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "Department", url = "http://localhost:8089/v1/orgao")
public interface GovernmentAgencyFeignClient {

	
	@RequestMapping(method = RequestMethod.GET, value = "/buscar/{id}")
	Orgao getOrgaoId(@PathVariable("id") Long id);

}
