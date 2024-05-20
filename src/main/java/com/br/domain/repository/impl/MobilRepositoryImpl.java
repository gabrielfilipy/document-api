package com.br.domain.repository.impl;

import com.br.domain.model.Mobil;
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

public class MobilRepositoryImpl implements MobilRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Mobil> buscarMobilsFiltro(Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Mobil> criteria = builder.createQuery(Mobil.class);
        Root<Mobil> root = criteria.from(Mobil.class);
        root.fetch("movimentacoes", JoinType.LEFT);
        Predicate[] predicates = criarRestricoes(builder, root);
        criteria.where(predicates);
        criteria.select(root).distinct(true);
        TypedQuery<Mobil> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, totalElementos(predicates));
    }

    private Predicate[] criarRestricoes(CriteriaBuilder builder, Root<Mobil> root) {
        List<Predicate> predicates = new ArrayList<>();
        // Adicione suas restrições aqui, se necessário

        return predicates.toArray(new Predicate[0]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Mobil> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long totalElementos(Predicate[] predicates) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Mobil> root = criteria.from(Mobil.class);
        criteria.where(predicates);
        criteria.select(builder.countDistinct(root)); // Conta os resultados distintos
        return manager.createQuery(criteria).getSingleResult();
    }

}
