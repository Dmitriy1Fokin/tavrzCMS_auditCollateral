package ru.fds.tavrzauditcollateral.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.dictionary.TypeOfAudit;
import ru.fds.tavrzauditcollateral.dictionary.TypeOfObject;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;

import java.util.Collection;
import java.util.Optional;

public interface RepositoryAuditResult extends MongoRepository<AuditResult, String> {
    @Query("{ 'typeOfObject' : ?0, 'objectId' : ?1, 'typeOfAudit' : ?2, 'AuditStatus' : {$in : ['ACTUAL', 'IGNORE']}}}")
    Optional<AuditResult> getExistAuditResult(TypeOfObject typeOfObject, Long objectId, TypeOfAudit typeOfAudit);
    @Query("{ 'typeOfObject' : ?0, 'objectId' : ?1, 'AuditStatus' : {$in : ['ACTUAL', 'IGNORE']}}}")
    Collection<AuditResult> getExistAuditResults(TypeOfObject typeOfObject, Long objectId);
    @Query("{ 'typeOfObject' : ?0, 'objectId' : ?1, 'AuditStatus' : ?3}")
    Collection<AuditResult> getExistAuditResults(TypeOfObject typeOfObject, Long objectId, AuditStatus auditStatus);
}
