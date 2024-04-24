package com.br.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.domain.model.Mobil;
import com.br.domain.service.MobilService;

@RestController
@RequestMapping("/v1/mobil")
public class MobilController {
	
	@Autowired
	MobilService mobilService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Mobil> cadastrar( @Valid Mobil mobil) {
		Mobil MobilSave = mobilService.save(mobil);
        return ResponseEntity.status (HttpStatus.CREATED).body(MobilSave);
    }

}
