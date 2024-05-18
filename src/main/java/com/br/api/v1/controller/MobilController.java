package com.br.api.v1.controller;

import com.br.domain.model.Mobil;
import com.br.domain.service.MobilService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Mobil")
@RestController
@RequestMapping("/v1/mobil")
public class MobilController {

    @Autowired
    private MobilService mobilService;

    @GetMapping("/buscar/{mobilId}")
    public ResponseEntity<Mobil> getUser(@PathVariable(name = "mobilId") Long mobilId) {
        Mobil mobil = mobilService.buscarMobil(mobilId);
        return ResponseEntity.status(HttpStatus.OK).body(mobil);
    }

}
