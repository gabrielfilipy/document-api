package com.br.api.v1.controller;
 
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.br.api.v1.mapper.*;
import com.br.api.v1.model.DocumentModel;
import com.br.api.v1.model.input.DocumentModelInput;
import com.br.domain.model.Document;
import com.br.domain.service.DocumentService;
import io.swagger.annotations.*;

@Api(tags = "Documento")
@RestController
@RequestMapping("/v1/documento")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private DocumentModelMapper documentModelMapper;
	
	@Autowired
	private DocumentModelMapperBack documentModelMapperBack;
	
	@Autowired
	private DocumentEditModelMapperBack documentEditModelMapperBack;
	
	@ApiOperation("Retorna uma lista de documento.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "documento listados sucesso."),
        @ApiResponse(code = 500, message = "Ocorreu um erro interno.")
    })
	@GetMapping("/listar")
	public ResponseEntity<List<Document>> getUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(documentService.findAll());
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Document> getUser(@PathVariable(name = "id") Long id) {
		Document document = documentService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(document);
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<DocumentModel> cadastrar(@RequestBody @Valid 
		DocumentModelInput documentModelInput, 
		@RequestParam Long mobilId,
        @RequestParam Long modelId,
        @RequestParam Long marcaId) {
        Document document = documentModelMapperBack.toDocument(documentModelInput);
        Document savedDocument = documentService.save(document, mobilId, modelId, marcaId);
        DocumentModel documentModel = documentModelMapper.toDocument(savedDocument);
        return ResponseEntity.status(HttpStatus.CREATED).body(documentModel);
    }
	
//	@PutMapping("/editar/{id}")
//	public ResponseEntity<DocumentModel> editar(@RequestBody DocumentModelInput documentModelInput, 
//			@PathVariable(name = "id") Long id) {
//		Document documentAtual = documentService.findById(id);
//		documentEditModelMapperBack.copyToDomainObject(documentModelInput, documentAtual);
//		return ResponseEntity.status(HttpStatus.CREATED).body(documentModelMapper.toModel(documentService.save(documentAtual)));
//	}
	
//	@PutMapping("/ativar/{id}")
//    public ResponseEntity<DocumentModel> activateUser(@RequestBody DocumentModelInput documentModelInput ,
//    		@PathVariable (name = "id") Long id) {
//		Document document = documentService.findById(id);
//		documentEditModelMapperBack.copyToDomainObject(documentModelInput, document);
//		return ResponseEntity.status(HttpStatus.CREATED).body(documentModelMapper.toModel(documentService.activateUser(id)));
//	}
//	
//    @PutMapping("/desativar/{id}")
//    public ResponseEntity<DocumentModel> deactivateUser( @RequestBody DocumentModelInput documentModelInput, 
//    		@PathVariable (name = "id") Long id) {
//	    Document document = documentService.findById(id);
//	    documentEditModelMapperBack.copyToDomainObject(documentModelInput, document);
//		return ResponseEntity.status(HttpStatus.CREATED).body(documentModelMapper.toModel(documentService.deactivateUser(id)));
//	}
}
