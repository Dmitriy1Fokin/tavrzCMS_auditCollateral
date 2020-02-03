package ru.fds.tavrzauditcollateral.domain.sql;


public interface ObjectAudit {
    Long getId();
    String getNameObject();
    String getWrongValueInField();
}
