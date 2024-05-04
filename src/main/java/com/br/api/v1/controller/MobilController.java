package com.br.api.v1.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
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
