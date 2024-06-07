package com.br.api.v1.mapper;

import com.br.api.v1.model.MobilModel;
import com.br.domain.model.Mobil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MobilModelMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public MobilModel toModel(Mobil mobil) {
		return modelMapper.map(mobil, MobilModel.class);
	}
	
}
