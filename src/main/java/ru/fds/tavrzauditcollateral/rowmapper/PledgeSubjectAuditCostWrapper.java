package ru.fds.tavrzauditcollateral.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.fds.tavrzauditcollateral.domain.sql.ObjectAudit;
import ru.fds.tavrzauditcollateral.domain.sql.ObjectAuditImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PledgeSubjectAuditCostWrapper implements RowMapper<ObjectAudit> {
    @Override
    public ObjectAudit mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ObjectAuditImpl.builder()
                .id(rs.getLong(1))
                .name(rs.getString(2))
                .valueInField(rs.getBigDecimal(3).toString())
                .build();
    }
}
