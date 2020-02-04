package ru.fds.tavrzauditcollateral.dto;

import org.springframework.stereotype.Component;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;

@Component
public class AuditResultConverter implements ConverterDto<AuditResult, AuditResultDto> {
    @Override
    public AuditResult toEntity(AuditResultDto dto) {
        return null;
    }

    @Override
    public AuditResultDto toDto(AuditResult entity) {
        return null;
    }
}
