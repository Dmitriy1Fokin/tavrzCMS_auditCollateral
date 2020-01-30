package ru.fds.tavrzauditcollateral.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.fds.tavrzauditcollateral.domain.AuditResult;

public interface RepositoryAuditResult extends MongoRepository<AuditResult, String> {
}
