package ru.fds.tavrzauditcollateral.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.fds.tavrzauditcollateral.domain.sql.LoanAgreementAuditLowCollateralValue;
import ru.fds.tavrzauditcollateral.domain.sql.LoanAgreementName;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LoanAgreementAuditLowCollateralWrapper implements RowMapper<LoanAgreementAuditLowCollateralValue> {
    @Override
    public LoanAgreementAuditLowCollateralValue mapRow(ResultSet rs, int rowNum) throws SQLException {
        return LoanAgreementAuditLowCollateralValue.builder()
                .loanAgreementId(rs.getLong(1))
                .loanAgreementName(new LoanAgreementName(rs.getString(2), rs.getDate(3).toLocalDate()))
                .debtLA(rs.getBigDecimal(4))
                .build();
    }
}
