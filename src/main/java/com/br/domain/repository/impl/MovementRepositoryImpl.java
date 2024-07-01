package com.br.domain.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import com.br.domain.model.Mobil;
import com.br.domain.model.Movement;
import com.br.domain.model.enums.TypeMovement;

import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

public class MovementRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    public Page<Movement> buscarMovimentacoesDoMobilFiltro(Long mobilId, TypeMovement typeMovement, Pageable pageable) throws Exception {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Movement> criteria = builder.createQuery(Movement.class);
        Root<Movement> root = criteria.from(Movement.class);
        Predicate[] predicates = criarRestricoes(mobilId,typeMovement, builder, root);
        criteria.where(predicates);
        TypedQuery<Movement> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, totalElementos(mobilId,typeMovement));
    }

    private Predicate[] criarRestricoes(Long mobilId, TypeMovement typeMovement, CriteriaBuilder builder, Root<Movement> root) {
        List<Predicate> predicates = new ArrayList<>();
        Join<Movement, Mobil> mobil = root.join("mobil");

        if (mobilId != null) {
            predicates.add(builder.equal(mobil.get("mobilId"), mobilId));
        }
        if (typeMovement != null) {
            predicates.add(builder.equal(root.get("typeMovement"), typeMovement));
        }
        
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Movement> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long totalElementos(Long mobilId, TypeMovement typeMovement) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Movement> root = criteria.from(Movement.class);
        Predicate[] predicates = criarRestricoes(mobilId, typeMovement, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
