package com.br.domain.repository.spec;

import com.br.domain.model.Mobil;
import com.br.domain.model.Movement;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

public class TemplateSpec {

    public static Specification<Movement> movimentacoesDoMobil(Long mobilId) {
        return (root, query, cb) -> {
            Join<Movement, Mobil> mobilJoin = root.join("mobil", JoinType.INNER); // Join com a entidade Mobil
            Predicate predicate = cb.equal(mobilJoin.get("mobilId"), mobilId); // Condição de igualdade para o mobilId

            // Adiciona a condição ao critério de consulta
            query.orderBy(cb.desc(root.get("dataHoraCricao"))); // Ordena por dataHoraCricao DESC
            return predicate;
        };
    }

}
