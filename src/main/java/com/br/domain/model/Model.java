package com.br.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "tbl_model")
@Entity
public class Model implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private UUID modelId;

    @Column(name = "sigla")
    private String siglaModel;

    @Column(name = "label")
    private String label;

    private String descricaoDetalhada;

    @Column(name = "html_form")
    private String htmlForm;

    @Column(name = "html_model_doc")
    private String htmlModelDoc;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(name = "date_time_final")
    private OffsetDateTime dataHoraFinalizacao;

}
