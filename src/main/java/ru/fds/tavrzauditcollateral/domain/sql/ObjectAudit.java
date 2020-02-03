package ru.fds.tavrzauditcollateral.domain.sql;

import ru.fds.tavrzauditcollateral.dictionary.TypeOfObject;

public interface ObjectAudit {
    TypeOfObject getTypeOfObject();
    Long getId();
    String getNameObject();
    String fieldNameWithError();
    String getValueInField();
}
