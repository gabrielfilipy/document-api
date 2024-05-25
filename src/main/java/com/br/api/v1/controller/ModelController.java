package com.br.api.v1.controller;

import com.br.domain.model.Mobil;
import com.br.domain.model.Model;
import com.br.domain.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
