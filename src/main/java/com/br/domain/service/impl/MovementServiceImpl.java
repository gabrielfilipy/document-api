package com.br.domain.service.impl;

import java.util.List;
import java.util.Optional;

import com.br.domain.exception.DocumentoFinalizadoException;
import com.br.domain.exception.MobilNaoExisteException;
import com.br.domain.exception.MovimentacaoExistenteException;
import com.br.domain.model.Mobil;
import com.br.domain.model.enums.TipoMarca;
import com.br.domain.model.enums.TypeMovement;
import com.br.domain.repository.MobilRepository;
import com.br.domain.service.MobilService;
import com.br.infrastructure.external.service.departament.DepartmentFeignClient;
import com.br.infrastructure.external.service.departament.model.Department;
import com.br.infrastructure.external.service.user.UserFeignClient;
import com.br.infrastructure.external.service.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.model.Movement;
import com.br.domain.repository.MovementRepository;
import com.br.domain.service.MovementService;

@Service
public class MovementServiceImpl implements MovementService {

	public static final String MENSAGEM_FOI_TRAMITADO_PARA_DEPARTAMENTO = "documento tramitado para o departamento: ";

	@Autowired
	MovementRepository movementRepository;

	@Autowired
	private MobilRepository mobilRepository;

	@Autowired
	private MobilService mobilService;

	@Autowired
	private DepartmentFeignClient departmentFeignClient;

	@Autowired
	private UserFeignClient userFeignClient;

	@Override
	public Movement save(Movement movimentacao) {
	    return movementRepository.save(movimentacao);
	}

	@Override
	public Movement verificaAssinaturaDoSubscritor(String siglaMobil, Long subscritorId) {
		Optional<Mobil> mobil = mobilRepository.findByMobilPorSigla(siglaMobil);

		if(!mobil.isPresent()) {
			throw new MobilNaoExisteException("Mobil informado não existe.");
		}

		for(Movement movement: mobil.get().getMovimentacoes()) {
			if((movement.getTypeMovement() == TypeMovement.ASSINATURA_COM_SENHA) &&
					(movement.getSubscritorId() == subscritorId)) {
				return movement;
			}
		}
		return null;
	}

	@Override
	public Movement verificaFinalizacaoDoDocumento(String siglaMobil) {
		Optional<Mobil> mobil = mobilRepository.findByMobilPorSigla(siglaMobil);

		if(!mobil.isPresent()) {
			throw new MobilNaoExisteException("Mobil informado não existe.");
		}

		//Verifica se há algum tipo de movimentação do tipo FINALIZACAO.
		for(Movement movement: mobil.get().getMovimentacoes()) {
			if(movement.getTypeMovement() == TypeMovement.FINALIZACAO) {
				return movement;
			}
		}
		return null;
	}

	@Override
	public Movement verificaInclusaoDeCossignatario(String siglaMobil, Long pessoaRecebedoraId) {
		Optional<Mobil> mobil = mobilRepository.findByMobilPorSigla(siglaMobil);

		if(!mobil.isPresent()) {
			throw new MobilNaoExisteException("Mobil informado não existe.");
		}

		for(Movement movement: mobil.get().getMovimentacoes()) {
			if((movement.getTypeMovement() == TypeMovement.INCLUSAO_DE_COSIGNATARIO) &&
					(movement.getPessoaRecebedoraId() == pessoaRecebedoraId)) {
				return movement;
			}
		}
		return null;
	}

	@Override
	public Movement buscarPorCossignatario(String siglaMobil, Long pessoaRecebedoraId) {
		Optional<Mobil> mobil = mobilRepository.findByMobilPorSigla(siglaMobil);

		if(!mobil.isPresent()) {
			throw new MobilNaoExisteException("Mobil informado não existe.");
		}

		for(Movement movement: mobil.get().getMovimentacoes()) {
			if((movement.getTypeMovement() == TypeMovement.INCLUSAO_DE_COSIGNATARIO) &&
					(movement.getSubscritorId() == pessoaRecebedoraId)) {
				return movement;
			}
		}
		return null;
	}

	@Override
	public Movement criarMovimentacaoAssinarComSenha(String siglaMobil, Long subscritorId) {
		Movement movement = verificaAssinaturaDoSubscritor(siglaMobil, subscritorId);

		if(movement != null) {
			throw new MovimentacaoExistenteException(movement.getMovementId());
		}

		Movement verifiacarFinalizacao = verificaFinalizacaoDoDocumento(siglaMobil);
		if(verifiacarFinalizacao == null){
			criarMovimentacaoFinalizacaoDocumento(siglaMobil, subscritorId);
		}

		Optional<Mobil> mobil = mobilRepository.findByMobilPorSigla(siglaMobil);
		mobilService.atribuirMarcaAoMobil(mobil.get(), TipoMarca.ASSINAR_COM_SENHA);
		movement = criarMovimentacao(TypeMovement.ASSINATURA_COM_SENHA, subscritorId, null, mobil.get());
		mobilService.atualizarSiglaDoMobil(mobil.get());

		return movement;
	}

	@Override
	public Movement criarMovimentacaoIncluirCossignatario(String siglaMobil, Long subscritorId, Long pessoaRecebedoraId) {
		Movement movement = verificaInclusaoDeCossignatario(siglaMobil, pessoaRecebedoraId);

		if(movement != null) {
			throw new MovimentacaoExistenteException(movement.getMovementId());
		}

		if(verificaFinalizacaoDoDocumento(siglaMobil) != null) {
			throw new DocumentoFinalizadoException(siglaMobil, null);
		}

		Optional<Mobil> mobil = mobilRepository.findByMobilPorSigla(siglaMobil);
		mobilService.atribuirMarcaAoMobil(mobil.get(), TipoMarca.INCLUSAO_COSSIGNATARIO);
		movement = criarMovimentacao(TypeMovement.INCLUSAO_DE_COSIGNATARIO, subscritorId, pessoaRecebedoraId, mobil.get());
		mobilService.atualizarSiglaDoMobil(mobil.get());

		return movement;
	}
	
	@Override
	public Movement criarMovimentacaoTramitarDocumento(String siglaMobil, Long subscritorId, Long pessoaRecebedoraId) {
		Movement movement = buscarPorCossignatario(siglaMobil, pessoaRecebedoraId);
		
		if(movement != null) {
			throw new MovimentacaoExistenteException(movement.getMovementId());
		}
		
		if (verificaFinalizacaoDoDocumento(siglaMobil) != null) {
			throw new DocumentoFinalizadoException(siglaMobil, null);
		}
		
		Optional<Mobil> mobil = mobilRepository.findByMobilPorSigla(siglaMobil);
		mobilService.atribuirMarcaAoMobil(mobil.get(), TipoMarca.TRAMITACAO_DOCUMENTO);
		movement = criarMovimentacao(TypeMovement.TRAMITAR, subscritorId, pessoaRecebedoraId, mobil.get());
		mobilService.atualizarSiglaDoMobil(mobil.get());
		
		return movement;
	}

	@Override
	public Movement criarMovimentacaoTramitarDocumentoParaLotacao(String siglaMobil, Long subscritorId, Long departmentId) {
		departmentFeignClient.getDepartment(departmentId);
		List<User> usuarios =  userFeignClient.buscarTodosUsuarioDeDeterminadoDepartamento(departmentId);
		Movement movement = null;

		for(User user : usuarios) {
			movement = criarMovimentacaoTramitarDocumento(siglaMobil,subscritorId, user.getId());
		}

		return movement;
	}

	@Override
	public Movement criarMovimentacaoFinalizacaoDocumento(String siglaMobil, Long subscritorId) {
		Movement movement = verificaAssinaturaDoSubscritor(siglaMobil, subscritorId);

		if(movement != null) {
			throw new MovimentacaoExistenteException(movement.getMovementId());
		}

		Optional<Mobil> mobil = mobilRepository.findByMobilPorSigla(siglaMobil);
		mobilService.atribuirMarcaAoMobil(mobil.get(), TipoMarca.FINALIZAR);
		movement = criarMovimentacao(TypeMovement.FINALIZACAO, subscritorId, null, mobil.get());
		mobilService.atualizarSiglaDoMobil(mobil.get());

		return movement;
	}

	@Override
	public Movement findById(Long movimentacaoId) {
		Optional<Movement> movimentacao = movementRepository.findById(movimentacaoId);
		if(movimentacao.isEmpty()) {
			throw new EntidadeNaoExisteException("Movimentação informada não existe: " + movimentacaoId);
		}
		return movimentacao.get();
	}

	@Override
	public Page<Movement> findAll(Specification<Movement> spec, Pageable pageable) {
		return mobilRepository.findAll(spec, pageable);
	}

	@Override
	public Page<Movement> buscarMovimentacoesDoMobilFiltro(Long mobilId, TypeMovement typeMovement, Pageable pageable) {
		return movementRepository.buscarMovimentacoesDoMobilFiltro(mobilId, typeMovement, pageable);
	}

	@Override
	public Movement criarMovimentacao(TypeMovement typeMovement, Long subscritorId, Long pessoaRecebedoraId, Mobil mobil) {
		Movement movimentacao = new Movement();
		movimentacao.setSubscritorId(subscritorId);
		movimentacao.setPessoaRecebedoraId(pessoaRecebedoraId);
		movimentacao.setMobil(mobil);
		movimentacao.setTypeMovement(typeMovement);
		return movementRepository.save(movimentacao);
	}
	
	 @Override
    public void verificarEExcluirMovimentacao(String siglaMobil, Long movimentacaoId) {
        Optional<Movement> movimentacao = movementRepository.findById(movimentacaoId);

        if (movimentacao.isEmpty()) {
            throw new EntidadeNaoExisteException("Movimentação informada não existe: " + movimentacaoId);
        }

        Optional<Mobil> mobil = mobilRepository.findByMobilPorSigla(siglaMobil);

        if (!mobil.isPresent()) {
            throw new MobilNaoExisteException("Mobil informado não existe.");
        }

        Movement finalizacao = verificaFinalizacaoDoDocumento(siglaMobil);

        if (finalizacao != null) {
            throw new DocumentoFinalizadoException(siglaMobil, null);
        }

        if (movimentacao.get().getTypeMovement() == TypeMovement.INCLUSAO_DE_COSIGNATARIO) {
            movementRepository.delete(movimentacao.get());
        }
    }
}
