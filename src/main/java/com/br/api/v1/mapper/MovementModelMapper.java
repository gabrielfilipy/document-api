package com.br.api.v1.mapper;

import com.br.api.v1.model.MovimentacaoAssinadaModel;
import com.br.api.v1.model.TramiteDocPessoaModel;
import com.br.domain.model.Movement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovementModelMapper {

	@Autowired
	private ModelMapper modelMapper;

	public MovimentacaoAssinadaModel toModelMovAssinada(Movement movement) {
		MovimentacaoAssinadaModel movimentacaoAssinadaModel = modelMapper.map(movement, MovimentacaoAssinadaModel.class);
		// Aqui você pode fazer ajustes adicionais, se necessário
		return movimentacaoAssinadaModel;
	}

	public TramiteDocPessoaModel toModelTramiteDocPessoa(Movement movement) {
		TramiteDocPessoaModel tramiteDocPessoaModel= modelMapper.map(movement, TramiteDocPessoaModel.class);
		return tramiteDocPessoaModel;
	}

}
