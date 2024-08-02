package com.br.domain.service.impl;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import com.br.infrastructure.external.service.governmentagency.GovernmentAgencyFeignClient;
import com.br.infrastructure.external.service.governmentagency.model.Orgao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.br.core.config.Generator;
import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.*;
import com.br.domain.model.enums.*;
import com.br.domain.repository.*;
import com.br.domain.service.DepartamentoService;
import com.br.domain.service.DocumentService;
import com.br.domain.service.UserService;


@Service
public class DocumentServiceImpl implements DocumentService {

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
	private DepartamentoService departmentService;

	@Autowired
	private UserService userService;

	@Autowired
	private GovernmentAgencyFeignClient governmentAgencyFeignClient;

	@Transactional
	@Override
	public Document save(Document document, UUID subscritorId) {
		// Verificar e obter o Model
		Model model = modelRepository.findById(document.getModel().getModelId())
				.orElseThrow(() -> new EntidadeNaoExisteException("Model não encontrado"));
		document.setModel(model);

		// Salvar o documento primeiro para obter o ID
		Document savedDocument = documentRepository.save(document);

		// Criar e salvar Mobil associando o documento salvo
		Mobil mobil = new Mobil();
		mobil.setDateCreate(OffsetDateTime.now());
		mobil.setSubscritorId(subscritorId);
		mobil.setDocumento(savedDocument);
		mobil = mobilRepository.save(mobil);

		// Criar e salvar Movimentação associando o Mobil salvo
		Movement movimentacao = new Movement();
		movimentacao.setSubscritorId(subscritorId);
		movimentacao.setMobil(mobil);
		//TODO: Verificar o por que não está salvando o tipo da movimentação.
		movimentacao.setTypeMovement(TypeMovement.CRIACAO);

		movimentacao = movementRepository.save(movimentacao);
		//mobil.getMovimentacoes().add(movimentacao);

		// Atualizar o Mobil com a última movimentação e salvar novamente
		mobil.setUltimaMovimentacaoId(movimentacao.getMovementId());
		mobil = mobilRepository.save(mobil);

		// Atualizar o Documento com o Mobil associado e salvar novamente
		savedDocument.setMobil(mobil);
		preencherModeloDocumento(savedDocument);
		savedDocument = documentRepository.save(savedDocument);
		generator.sigla(savedDocument.getDocumentId());

		// Atualizar o Mobil com a sigla e salvar novamente
		mobil.setSiglaMobil(getSiglaTemporario());
		mobilRepository.save(mobil);

		return savedDocument;
	}


	public void preencherModeloDocumento(Document document) {
		String content = document.getModel().getHtmlModelDoc();
		Optional<Movement> ultMovimentacaoAssinada = movementRepository
				.ultimaMovimentacaoAssinada(document.getMobil().getUltimaMovimentacaoId());
		String descricaoDocumento = document.getDescricao();
		User user = userService.findById(document.getMobil().getSubscritorId());
		Departamento department = departmentService.findById(user.getDepartmentId());
		Orgao orgao = governmentAgencyFeignClient.getOrgaoId(department.getOrgaoId());
		String dataCriacao = document.getMobil().getDateCreate() + "";

		descricaoDocumento += "#DataCriacaoDocumento=" + formatarData(dataCriacao);
		descricaoDocumento += "#Orgao=" + orgao.getNome();
		descricaoDocumento += "#SecretariaPessoaCriadora=" + department.getNome();

		if(ultMovimentacaoAssinada.isPresent()) {
			User subscritor = userService.findById(ultMovimentacaoAssinada.get().getSubscritorId());
			Departamento departmentSubscritor = departmentService.findById(subscritor.getDepartmentId());
			descricaoDocumento += "#PessoaAssinante=" + subscritor.getNome();
			descricaoDocumento += "#SecretariaPessoaAssinante=" + departmentSubscritor.getNome();
			descricaoDocumento += "#DataAssinatura=" + formatarData(ultMovimentacaoAssinada.get().getDataHoraCricao() + "");
		} else {
			//Se nâo possuir data de assinatura então grave as informações do subscritor o qual criou o documento.
			descricaoDocumento += "#PessoaAssinante=" + user.getNome();
			descricaoDocumento += "#SecretariaPessoaAssinante=" + department.getNome();
			descricaoDocumento += "#DataAssinatura=" + formatarData(dataCriacao);
		}

		if (department != null) {
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

		String[] pairs = str.split("#");

		for (String pair : pairs) {
			int indexEquals = pair.indexOf("=");
			if (indexEquals != -1) {
				String key = pair.substring(0, indexEquals).trim();
				String value = pair.substring(indexEquals + 1).trim();

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
		String ultimaPalavra = palavras[indiceUltimaPalavra].substring(0, 2);
		String primeiraPalavra = modelo.substring(0, 2);
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
	public Document findById(UUID id) {
		Optional<Document> document = documentRepository.findById(id);
		if (document.isEmpty()) {
			throw new EntidadeNaoExisteException("Documento não existe.");
		}
		return document.get();
	}
}
