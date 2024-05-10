package com.br.api.v1.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.api.v1.model.MovementModel;
import com.br.domain.model.Movement;

@Component
public class MovementModelMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public MovementModel toModelMovement(List<Movement> movimentacoes) {
		MovementModel movementModel =
				modelMapper.map( movimentacoes, MovementModel.class);
				return movementModel;
	}

}
