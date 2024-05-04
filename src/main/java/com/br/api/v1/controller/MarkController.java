package com.br.api.v1.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.br.domain.model.Mark;
import com.br.domain.service.MarkService;

@RestController
@RequestMapping("/v1/marca")
public class MarkController {
	
	@Autowired
	MarkService markService;
	
	 @PostMapping("/cadastrar")
	    public ResponseEntity<Mark> cadastrarMarcaEAssociarMovimentacao(@RequestBody @Valid Mark mark
	    ) {
	    Mark savedMarca = markService.save(mark);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedMarca);
}
	 
}
