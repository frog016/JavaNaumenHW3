package org.example.JavaNaumenHW3.CriteriaQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.example.JavaNaumenHW3.Entity.Hall;
import org.example.JavaNaumenHW3.Entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SessionRepositoryCustomImplementation implements SessionRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    public SessionRepositoryCustomImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Session> findByHall(String hallDisplay) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Session> criteriaQuery = criteriaBuilder.createQuery(Session.class);

        Root<Session> sessionRoot = criteriaQuery.from(Session.class);
        Join<Session, Hall> hall = sessionRoot.join("hall", JoinType.INNER);
        Predicate hallPredicate = criteriaBuilder.equal(hall.get("display"), hallDisplay);

        criteriaQuery.select(sessionRoot).where(hallPredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
