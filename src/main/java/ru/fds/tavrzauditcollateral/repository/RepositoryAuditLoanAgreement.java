package ru.fds.tavrzauditcollateral.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RepositoryAuditLoanAgreement {
    private final EntityManager entityManager;

    public RepositoryAuditLoanAgreement(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public String stringTest(){
        return entityManager.createNativeQuery("select name\n" +
                                                    "from pledge_subject\n" +
                                                    "limit 1")
                .getSingleResult().toString();

    }
}
