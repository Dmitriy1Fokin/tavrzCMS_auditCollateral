package ru.fds.tavrzauditcollateral.domain.nosql;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.fds.tavrzauditcollateral.dictionary.AuditLevel;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.dictionary.TypeOfObject;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@ToString
@Builder
@Document(collection = "audit_actual")
public class AuditResult {

    @Id
    private String id;

    private LocalDate date;

    private TypeOfObject typeOfObject;

    private Long objectId;

    private String nameOfObject;

    private String fieldNameWithError;

    private String valueInField;

    private AuditLevel auditLevel;

    private String descriptionResult;

    private Collection<String> advice;

    private AuditStatus auditStatus;
}
