package com.br.domain.repository;

import com.br.domain.model.Mobil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MobilRepositoryQuery {

    Page<Mobil> buscarMobilsFiltro(Pageable pageable);

}
