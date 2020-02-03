package ru.fds.tavrzauditcollateral.rowmapper;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.fds.tavrzauditcollateral.domain.sql.LoanAgreementAuditDateClosed;
import ru.fds.tavrzauditcollateral.domain.sql.LoanAgreementName;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LoanAgreementAuditDateClosedWrapper implements RowMapper<LoanAgreementAuditDateClosed> {
    @Override
    public LoanAgreementAuditDateClosed mapRow(ResultSet rs, int rowNum) throws SQLException {
        return LoanAgreementAuditDateClosed.builder()
                .loanAgreementId(rs.getLong(1))
                .loanAgreementName(new LoanAgreementName(rs.getString(2), rs.getDate(3).toLocalDate()))
                .dateEndLA(rs.getDate(4).toLocalDate())
                .build();
    }
}
