package com.br.api.v1.controller;

import com.br.domain.model.Model;
import com.br.domain.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @GetMapping("/listar")
    public ResponseEntity<List<Model>> getListar() {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.findAll());
    }

}
