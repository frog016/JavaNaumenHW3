package org.example.JavaNaumenHW3.CriteriaQuery;

import org.example.JavaNaumenHW3.Entity.Session;

import java.util.List;

public interface SessionRepositoryCustom {
    List<Session> findByHall(String hallDisplay);
}

