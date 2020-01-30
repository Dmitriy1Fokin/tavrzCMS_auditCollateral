package ru.fds.tavrzauditcollateral.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.fds.tavrzauditcollateral.dictionary.AuditLevel;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.dictionary.TypeOfObject;

import java.time.LocalDate;

@Data
@Builder
@Document(collection = "audit_actual")
public class AuditResult {

    @Id
    private String id;

    private LocalDate date;

    private TypeOfObject typeOfObject;

    private Long objectId;

    private AuditLevel auditLevel;

    private String descriptionResult;

    private String fieldWithError;

    private String valueInField;

    private String advice;

    private AuditStatus auditStatus;
}
