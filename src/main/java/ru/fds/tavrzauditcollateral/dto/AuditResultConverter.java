package ru.fds.tavrzauditcollateral.dto;

import org.springframework.stereotype.Component;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;

@Component
public class AuditResultConverter implements ConverterDto<AuditResult, AuditResultDto> {
    @Override
    public AuditResult toEntity(AuditResultDto dto) {
        return AuditResult.builder()
                .id(dto.getId())
                .date(dto.getDate())
                .typeOfObject(dto.getTypeOfObject())
                .objectId(dto.getObjectId())
                .nameOfObject(dto.getNameOfObject())
                .typeOfAudit(dto.getTypeOfAudit())
                .valueInField(dto.getValueInField())
                .auditLevel(dto.getAuditLevel())
                .descriptionResult(dto.getDescriptionResult())
                .advice(dto.getAdvice())
                .auditStatus(dto.getAuditStatus())
                .build();
    }

    @Override
    public AuditResultDto toDto(AuditResult entity) {
        return AuditResultDto.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .typeOfObject(entity.getTypeOfObject())
                .objectId(entity.getObjectId())
                .nameOfObject(entity.getNameOfObject())
                .typeOfAudit(entity.getTypeOfAudit())
                .valueInField(entity.getValueInField())
                .auditLevel(entity.getAuditLevel())
                .descriptionResult(entity.getDescriptionResult())
                .advice(entity.getAdvice())
                .auditStatus(entity.getAuditStatus())
                .build();
    }
}
