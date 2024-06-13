package com.learn.cqrs.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component
public class MaterializedViewRefresher {

    @PersistenceContext
    private final EntityManager entityManager;

    public MaterializedViewRefresher(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Scheduled(fixedRate = 5000L)
    public void refresh(){
        this.entityManager.createNativeQuery("call refresh_mat_view();").executeUpdate();
    }

}
