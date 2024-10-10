package org.example.JavaNaumenHW3.CriteriaQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.JavaNaumenHW3.Entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepositoryCustomImplementation implements MovieRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    public MovieRepositoryCustomImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Movie> findByGenreAndTitle(String genre, String title) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);

        Root<Movie> movieRoot = criteriaQuery.from(Movie.class);
        Predicate genrePredicate = criteriaBuilder.equal(movieRoot.get("genre"), genre);
        Predicate titlePredicate = criteriaBuilder.equal(movieRoot.get("title"), title);

        criteriaQuery.select(movieRoot).where(genrePredicate, titlePredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
