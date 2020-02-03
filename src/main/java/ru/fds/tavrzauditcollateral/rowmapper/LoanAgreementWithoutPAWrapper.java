package ru.fds.tavrzauditcollateral.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.fds.tavrzauditcollateral.domain.sql.LoanAgreementName;
import ru.fds.tavrzauditcollateral.domain.sql.LoanAgreementWithoutPA;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LoanAgreementWithoutPAWrapper implements RowMapper<LoanAgreementWithoutPA> {
    @Override
    public LoanAgreementWithoutPA mapRow(ResultSet rs, int rowNum) throws SQLException {
        return LoanAgreementWithoutPA.builder()
                .loanAgreementId(rs.getLong(1))
                .loanAgreementName(new LoanAgreementName(rs.getString(2), rs.getDate(3).toLocalDate()))
                .countOfPledgeAgreements(rs.getInt(4))
                .build();
    }
}
