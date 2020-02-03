package ru.fds.tavrzauditcollateral.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;

public interface RepositoryAuditResult extends MongoRepository<AuditResult, String> {
}
