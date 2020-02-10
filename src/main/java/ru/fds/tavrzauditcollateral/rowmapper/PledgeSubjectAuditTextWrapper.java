package ru.fds.tavrzauditcollateral.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.fds.tavrzauditcollateral.domain.sql.ObjectAudit;
import ru.fds.tavrzauditcollateral.domain.sql.ObjectAuditImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PledgeSubjectAuditTextWrapper implements RowMapper<ObjectAudit> {
    @Override
    public ObjectAudit mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ObjectAuditImpl.builder()
                .id(rs.getLong(1))
                .name(rs.getString(2))
                .valueInField(rs.getString(2))
                .build();
    }
}
