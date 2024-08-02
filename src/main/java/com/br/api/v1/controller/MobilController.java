package com.br.api.v1.controller;

import com.br.domain.model.Mobil;
import com.br.domain.model.enums.TypeMovement;
import com.br.domain.service.MobilService;
import io.swagger.annotations.Api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Mobil")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/mobil")
public class MobilController {

    @Autowired
    private MobilService mobilService;

    @GetMapping("/filtro")
    public ResponseEntity<Page<Mobil>> getFiltro(
            @RequestParam(value = "subscritorId", required = false) UUID subscritorId,
            @RequestParam(value = "pessoaRecebedoraId", required = false) UUID pessoaRecebedoraId,
            @RequestParam(value = "typeMovement", required = false) TypeMovement typeMovement,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(mobilService.filtro(subscritorId, pessoaRecebedoraId, typeMovement, pageable));
    }

    @GetMapping("/buscar/{mobilId}")
    public ResponseEntity<Mobil> getUser(@PathVariable(name = "mobilId") UUID mobilId) {
        Mobil mobil = mobilService.buscarMobil(mobilId);
        return ResponseEntity.status(HttpStatus.OK).body(mobil);
    }

    @GetMapping("/buscar/{siglaMobil}/sigla")
    public ResponseEntity<Mobil> getUser(@PathVariable(name = "siglaMobil") String siglaMobil) {
        Mobil mobil = mobilService.buscarMobil(siglaMobil);
        return ResponseEntity.status(HttpStatus.OK).body(mobil);
    }

}
