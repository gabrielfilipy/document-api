package com.br.domain.repository.impl;

import com.br.domain.model.Mobil;
import com.br.domain.model.Movement;
import com.br.domain.model.enums.TypeMovement;
import com.br.domain.repository.MobilRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MobilRepositoryImpl implements MobilRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Mobil> buscarMobilsFiltro(UUID subscritorId, UUID pessoaRecebedoraId, TypeMovement typeMovement, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Mobil> criteria = builder.createQuery(Mobil.class);
        Root<Mobil> root = criteria.from(Mobil.class);
        root.fetch("movimentacoes", JoinType.LEFT);
        Predicate[] predicates = criarRestricoes(subscritorId, pessoaRecebedoraId, typeMovement, builder, root);
        criteria.where(predicates);
        criteria.select(root).distinct(true);
        TypedQuery<Mobil> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, totalElementos(subscritorId, pessoaRecebedoraId, typeMovement));
    }


    private Predicate[] criarRestricoes(UUID subscritorId, UUID pessoaRecebedoraId, TypeMovement typemovement, CriteriaBuilder builder, Root<Mobil> root) {
        List<Predicate> predicates = new ArrayList<>();
        
        Join<Mobil, Movement> movement = root.join("movimentacoes");

        if (subscritorId != null && typemovement != null) {
            predicates.add(builder.equal(root.get("subscritorId"), subscritorId));
        }

        if (typemovement != null) {
            predicates.add(builder.equal(movement.get("typeMovement"), typemovement));
        }

        if (pessoaRecebedoraId != null) {
            predicates.add(builder.equal(movement.get("pessoaRecebedoraId"), pessoaRecebedoraId));
        }

        return predicates.toArray(new Predicate[0]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Mobil> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }


    private Long totalElementos(UUID subscritorId, UUID pessoaRecebedoraId, TypeMovement typeMovement) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Mobil> root = criteria.from(Mobil.class);
        Predicate[] predicates = criarRestricoes(subscritorId, pessoaRecebedoraId, typeMovement, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
