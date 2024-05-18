package com.br.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

@Getter
@Setter
@Table(name = "TBL_MODEL")
@Entity
public class Model implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long modelId;

    @Column(name = "sigla")
    private String siglaModel;

    @Column(name = "label")
    private String label;

    private String descricaoDetalhada;

    @Column(name = "html")
    private String html;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "date_time_final")
    private LocalDateTime dataHoraFinalizacao;

}
