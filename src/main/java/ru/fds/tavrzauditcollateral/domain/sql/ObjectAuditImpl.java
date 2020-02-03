package ru.fds.tavrzauditcollateral.domain.sql;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ObjectAuditImpl implements ObjectAudit{

    private Long id;
    private String name;
    private String valueInField;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getNameObject() {
        return name;
    }

    @Override
    public String getWrongValueInField() {
        return valueInField;
    }
}
