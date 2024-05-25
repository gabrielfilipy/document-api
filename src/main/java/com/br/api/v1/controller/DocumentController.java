package com.br.api.v1.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.br.api.v1.mapper.*;
import com.br.api.v1.model.DocumentModel;
import com.br.api.v1.model.input.DocumentModelInput;
import com.br.domain.model.Document;
import com.br.domain.service.DocumentService;
import io.swagger.annotations.*;

@Api(tags = "Documento")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/documento")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private DocumentModelMapper documentModelMapper;
	
	@Autowired
	private DocumentModelMapperBack documentModelMapperBack;
	
	@PostMapping("/cadastrar/{subscritorId}")
	public ResponseEntity<DocumentModel> cadastrar(@RequestBody @Valid 
		DocumentModelInput documentModelInput,
		@PathVariable Long subscritorId
        ) { System.out.println(documentModelInput.getSubscritorId());
        Document document = documentModelMapperBack.toModel(documentModelInput);
        Document savedDocument = documentService.save(document, subscritorId);
        DocumentModel documentModel = documentModelMapper.toModel(savedDocument);
        return ResponseEntity.status(HttpStatus.CREATED).body(documentModel);
    }

}
