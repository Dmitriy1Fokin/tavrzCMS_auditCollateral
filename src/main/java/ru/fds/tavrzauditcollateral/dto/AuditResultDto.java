package ru.fds.tavrzauditcollateral.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fds.tavrzauditcollateral.dictionary.AuditLevel;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.dictionary.TypeOfAudit;
import ru.fds.tavrzauditcollateral.dictionary.TypeOfObject;

import java.time.LocalDate;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditResultDto {

    private String id;

    private LocalDate date;

    private TypeOfObject typeOfObject;

    private Long objectId;

    private String nameOfObject;

    private TypeOfAudit typeOfAudit;

    private String valueInField;

    private AuditLevel auditLevel;

    private String descriptionResult;

    private Collection<String> advice;

    private AuditStatus auditStatus;
}
