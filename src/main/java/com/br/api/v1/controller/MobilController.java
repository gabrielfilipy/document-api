package com.br.api.v1.controller;

import com.br.domain.model.Mobil;
import com.br.domain.service.MobilService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Mobil")
@RestController
@RequestMapping("/v1/mobil")
public class MobilController {

    @Autowired
    private MobilService mobilService;

    @GetMapping("/filtro")
    public ResponseEntity<Page<Mobil>> getFiltro(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(mobilService.filtro(pageable));
    }

    @GetMapping("/buscar/{mobilId}")
    public ResponseEntity<Mobil> getUser(@PathVariable(name = "mobilId") Long mobilId) {
        Mobil mobil = mobilService.buscarMobil(mobilId);
        return ResponseEntity.status(HttpStatus.OK).body(mobil);
    }

}
