package com.br.domain.service.impl;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.br.core.config.Generator;
import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.*;
import com.br.domain.model.enums.*;
import com.br.domain.repository.*;
import com.br.domain.service.DocumentService;
import com.br.infrastructure.external.service.departament.DepartmentFeignClient;
import com.br.infrastructure.external.service.departament.model.Department;
import com.br.infrastructure.external.service.user.UserFeignClient;
import com.br.infrastructure.external.service.user.model.User;


@Service
public class DocumentServiceImpl implements DocumentService{
	
	private static final int TAMANHO_SIGLA = 2;

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private MobilRepository mobilRepository;
	
	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	private MarkRepository markRepository;
	
	@Autowired
	private MovementRepository movementRepository;
	
	@Autowired
	private Generator generator;
	
	@Autowired
	private DepartmentFeignClient departmentFeignClient;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Transactional
	@Override
	public Document save(Document document, Long subscritorId) {
        Model model = modelRepository.findById(document.getModel().getModelId())
                                     .orElseThrow(() -> new EntidadeNaoExisteException("Model não encontrado"));
        Mark mark = markRepository.findByCode(TypeMark.CRIACAO_MARCA.getCode())
        		.orElseThrow(() -> new EntidadeNaoExisteException("Marca não encontrado"));  
        document.setModel(model);
        Mobil mobil = new Mobil();
        Movement movimentacao = new Movement();
        mobil.setDateCreate(OffsetDateTime.now());
        mobil.getMarcas().add(mark);
        mobil.setSubscritorId(subscritorId);
        movimentacao.getSubscritorId();
        movimentacao.setMobil(mobil);
        movimentacao.setPessoaRecebedoraId(movimentacao.getPessoaRecebedoraId());
        movimentacao.setSubscritorId(movimentacao.getSubscritorId());
        movimentacao.setTypeMovement(TypeMovement.CRIACAO);	
        //mobil = mobilRepository.save(mobil); 
        movimentacao = movementRepository.save(movimentacao);
        movimentacao = movementRepository.findFirstByMobilIdOrderByDataHora(mobil.getMobilId()).get();
        mobil.setUltimaMovimentacaoId(movimentacao.getMovementId());
        mobil = mobilRepository.save(mobil);
        document.setMobil(mobil);
        preencherModeloDocumento(document);
        Document savedDocument = documentRepository.save(document);
        generator.sigla(savedDocument.getDocumentId());
	    mobil.setSiglaMobil(getSiglaTemporario());
	    mobil.setDocumento(savedDocument);
        mobilRepository.save(mobil);
        savedDocument.setMobil(mobil);
        savedDocument = documentRepository.save(savedDocument);
        return savedDocument;
	}

	  
	public void preencherModeloDocumento(Document document) {
	    String content = document.getModel().getHtmlModelDoc();
	    Optional<Movement> ultMovimentacaoAssinada = movementRepository.ultimaMovimentacaoAssinada(document.getMobil().getUltimaMovimentacaoId());
	    String descricaoDocumento = document.getDescricao();
	    User user = userFeignClient.getUserId(document.getMobil().getSubscritorId());
	    Department department = departmentFeignClient.getDepartment(user.getDepartmentId());
	    
	    String dataCriacao = document.getMobil().getDateCreate() + "";
	    descricaoDocumento += "#DataCriacaoDocumento=" + formatarData(dataCriacao);
	    
	    if(ultMovimentacaoAssinada.isPresent()) { 
	    	descricaoDocumento += "#DataAssinatura=" + ultMovimentacaoAssinada.get().getDataHoraCricao() ;
	    }
	    
	    if(department != null) {
	    	descricaoDocumento += "#SecretariaCriacaoDocumento=" + department.getNome();
	    }
	    
	    Map<String, String> keyValueMap = parseKeyValueString(descricaoDocumento);
	    
	    for (Map.Entry<String, String> entry : keyValueMap.entrySet()) {
	        String key = entry.getKey();
	        String value = entry.getValue();
	        content = content.replace(key, value);
	    }
	    
	    document.setFile(content);
	}

	private String formatarData(String dataIso) {
        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(dataIso);
            LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return localDateTime.format(formatter);
        } catch (DateTimeParseException e) {
            return dataIso;
        }
    }

	 public static Map<String, String> parseKeyValueString(String str) {
	        Map<String, String> map = new HashMap<>();

	        // Dividir a string em pares chave-valor usando ";"
	        String[] pairs = str.split("#");

	        // Percorrer cada par
	        for (String pair : pairs) {
	            // Dividir cada par em chave e valor usando "="
	            int indexEquals = pair.indexOf("=");
	            if (indexEquals != -1) {
	                String key = pair.substring(0, indexEquals).trim();
	                String value = pair.substring(indexEquals + 1).trim();
	                
	                // Remover o último ponto e vírgula do valor, se existir
	                if (value.endsWith("#")) {
	                    value = value.substring(0, value.length() - 1).trim();
	                }
	                
	                map.put(key, value);
	            }
	        }

	        return map;
	    }
	      
	private String getSiglaFinalizado(Document document) {
		String modelo = "";
		String[] palavras = modelo.split("\\s+");
        int indiceUltimaPalavra = palavras.length - 1;
        String ultimaPalavra = palavras[indiceUltimaPalavra].substring(0,  2);
        String primeiraPalavra = modelo.substring(0,  2);
        String nomeDocumento = primeiraPalavra + ultimaPalavra;
        generator.sigla(document.getDocumentId());
		return generator.getSiglaFinalizado(nomeDocumento);
	}
	
	private String getSiglaTemporario() {
		return generator.getSiglaTemporario();
	}
	
	@Override
	public List<Document> findAll() {
		
		return documentRepository.findAll();
	}

	@Override
	public Document findById(Long id) {
		Optional<Document> document = documentRepository.findById(id);
		if (document.isEmpty()) {
			throw new EntidadeNaoExisteException("Documento não existe.");
		}
		return document.get();
	}
	
}
