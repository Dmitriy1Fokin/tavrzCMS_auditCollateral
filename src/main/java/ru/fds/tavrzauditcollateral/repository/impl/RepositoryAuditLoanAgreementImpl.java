package ru.fds.tavrzauditcollateral.repository.impl;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.fds.tavrzauditcollateral.domain.sql.ObjectAudit;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditLoanAgreement;
import ru.fds.tavrzauditcollateral.rowmapper.AgreementAuditDateWrapper;
import ru.fds.tavrzauditcollateral.rowmapper.AgreementAuditCostWrapper;
import ru.fds.tavrzauditcollateral.rowmapper.AgreementAuditCountWrapper;
import ru.fds.tavrzauditcollateral.utils.DateUtils;

import java.util.Collection;
import java.util.Optional;

@Repository
public class RepositoryAuditLoanAgreementImpl implements RepositoryAuditLoanAgreement {

    private static final String PARAM_LA_ID = "loanAgreementId";
    private static final String PARAM_DATE_NOW = "dateNow";
    private static final String QUERY_LA_WITH_DATE_CLOSED_OVERDUE = "select k.kd_id, k.num_kd, k.date_begin_kd, k.date_end_kd\n" +
                                                                    "from kd k\n   " +
                                                                    "where k.date_end_kd < :dateNow\n" +
                                                                    "and\n" +
                                                                    "k.status = 'открыт'";

    private static final String QUERY_IS_LA_DATE_END_OVERDUE = "select k.kd_id, k.num_kd , k.date_begin_kd, k.date_end_kd\n" +
                                                                "from kd k\n " +
                                                                "where k.date_end_kd < :dateNow\n" +
                                                                "and k.status = 'открыт'\n" +
                                                                "and k.kd_id = :loanAgreementId";


    private static final String QUERY_LA_WITH_LOW_COLLATERAL_VALUE = "select k.kd_id, k.num_kd, k.date_begin_kd, k.loan_debt\n" +
                                                                        "from kd k\n " +
                                                                        "join kd_dz kddz on k.kd_id = kddz.kd_id\n" +
                                                                        "join dz d on kddz.dz_id = d.dz_id\n" +
                                                                        "where k.loan_debt > (select coalesce(sum(d1.zs_dz), 0)\n" +
                                                                        "                     from dz d1\n" +
                                                                        "                     where " +
                                                                        "                     d1.status = 'открыт'\n" +
                                                                        "                     and\n" +
                                                                        "                     d1.dz_id in (select kddz1.dz_id\n" +
                                                                        "                                  from kd_dz kddz1\n" +
                                                                        "                                  where kddz1.kd_id = k.kd_id))";


    private static final String QUERY_IS_LA_LOW_COLLATERAL_VALUE = "select k.kd_id, k.num_kd, k.date_begin_kd, k.loan_debt\n" +
                                                                    "from kd k\n" +
                                                                    "         join kd_dz kddz on k.kd_id = kddz.kd_id\n" +
                                                                    "         join dz d on kddz.dz_id = d.dz_id\n" +
                                                                    "where k.loan_debt > (select coalesce(sum(d1.zs_dz), 0)\n" +
                                                                    "                     from dz d1\n" +
                                                                    "                     where\n" +
                                                                    "                     d1.status = 'открыт'\n" +
                                                                    "                     and\n" +
                                                                    "                     d1.dz_id in (select kddz1.dz_id\n" +
                                                                    "                                  from kd_dz kddz1\n" +
                                                                    "                                  where kddz1.kd_id = k.kd_id))\n" +
                                                                    "and\n" +
                                                                    "k.kd_id = :loanAgreementId";

    private static final String QUERY_LA_WITHOUT_PA = "select k.kd_id, k.num_kd, k.date_begin_kd, count(kddz.dz_id)\n" +
                                                        "from kd k\n   " +
                                                        "left join kd_dz kddz on k.kd_id = kddz.kd_id\n" +
                                                        "where k.status = 'открыт'\n" +
                                                        "group by 1\n" +
                                                        "having count(kddz.dz_id) = 0";

    private static final String QUERY_IS_LA_WITHOUT_PA = "select k.kd_id, k.num_kd, k.date_begin_kd, count(kddz.dz_id)\n" +
                                                            "from kd k\n" +
                                                            "left join kd_dz kddz on k.kd_id = kddz.kd_id\n" +
                                                            "where k.kd_id = :loanAgreementId\n" +
                                                            "group by 1\n" +
                                                            "having count(kddz.dz_id) = 0";


    private final NamedParameterJdbcTemplate template;
    private final AgreementAuditDateWrapper agreementAuditDateWrapper;
    private final AgreementAuditCostWrapper agreementAuditCostWrapper;
    private final AgreementAuditCountWrapper agreementAuditCountWrapper;
    private final DateUtils dateUtils;

    public RepositoryAuditLoanAgreementImpl(NamedParameterJdbcTemplate template,
                                            AgreementAuditDateWrapper agreementAuditDateWrapper,
                                            AgreementAuditCostWrapper agreementAuditCostWrapper,
                                            AgreementAuditCountWrapper agreementAuditCountWrapper,
                                            DateUtils dateUtils) {
        this.template = template;
        this.agreementAuditDateWrapper = agreementAuditDateWrapper;
        this.agreementAuditCostWrapper = agreementAuditCostWrapper;
        this.agreementAuditCountWrapper = agreementAuditCountWrapper;
        this.dateUtils = dateUtils;
    }

    @Override
    public Collection<ObjectAudit> getLoanAgreementWithDateClosedOverdue(){
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_DATE_NOW, dateUtils.getNow());
        return template.query(QUERY_LA_WITH_DATE_CLOSED_OVERDUE, parameterSource, agreementAuditDateWrapper);
    }

    @Override
    public Optional<ObjectAudit> isDateClosedOverDue(Long loanAgreementId){
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(PARAM_LA_ID, loanAgreementId)
                .addValue(PARAM_DATE_NOW, dateUtils.getNow());
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_LA_DATE_END_OVERDUE,
                parameterSource,
                agreementAuditDateWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getLoanAgreementWithLowCollateralValue(){
        return template.query(QUERY_LA_WITH_LOW_COLLATERAL_VALUE, agreementAuditCostWrapper);
    }

    @Override
    public Optional<ObjectAudit> isLowCollateralSum(Long loanAgreementId){
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_LA_ID, loanAgreementId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_LA_LOW_COLLATERAL_VALUE,
                parameterSource,
                agreementAuditCostWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getLoanAgreementWithoutPledge(){
        return template.query(QUERY_LA_WITHOUT_PA, agreementAuditCountWrapper);
    }

    @Override
    public Optional<ObjectAudit> isHaveNotPledgeAgreements(Long loanAgreementId){
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_LA_ID , loanAgreementId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_LA_WITHOUT_PA,
                parameterSource,
                agreementAuditCountWrapper)));
    }
}
