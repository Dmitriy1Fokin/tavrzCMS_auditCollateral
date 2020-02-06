package ru.fds.tavrzauditcollateral.repository.impl;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.fds.tavrzauditcollateral.domain.sql.ObjectAudit;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditPledgeAgreement;
import ru.fds.tavrzauditcollateral.rowmapper.AgreementAuditCountWrapper;
import ru.fds.tavrzauditcollateral.rowmapper.AgreementAuditDateWrapper;
import ru.fds.tavrzauditcollateral.rowmapper.AgreementAuditLowCostWrapper;

import java.util.Collection;
import java.util.Optional;

@Repository
public class RepositoryAuditPledgeAgreementImpl implements RepositoryAuditPledgeAgreement {

    private static final String PARAM_PA_ID = "pledgeAgreementId";
    private static final String QUERY_PA_WITH_DATE_CLOSED_OVERDUE = "select d.dz_id, d.num_dz, d.date_begin_dz, d.date_end_dz\n" +
                                                                    "from dz d\n" +
                                                                    "where d.date_end_dz < now()\n" +
                                                                    "and\n" +
                                                                    "d.status = 'открыт'";
    private static final String QUERY_IS_PA_DATE_END_OVERDUE = "select d.dz_id, d.num_dz, d.date_begin_dz, d.date_end_dz\n" +
                                                                "from dz d\n" +
                                                                "where d.date_end_dz < now()\n" +
                                                                "and\n" +
                                                                "d.status = 'открыт'\n" +
                                                                "and\n" +
                                                                "d.dz_id = :pledgeAgreementId";
    private static final String QUERY_PA_WITHOUT_LA = "select d.dz_id, d.num_dz, d.date_end_dz, count(kddz.kd_id)\n" +
                                                        "from dz d\n" +
                                                        "left join kd_dz kddz on d.dz_id = kddz.dz_id\n" +
                                                        "where d.status = 'открыт'\n" +
                                                        "group by 1\n" +
                                                        "having count(kddz.kd_id) = 0\n";
    private static final String QUERY_IS_PA_WITHOUT_LA = "select d.dz_id, d.num_dz, d.date_end_dz, count(kddz.kd_id)\n" +
                                                            "from dz d\n" +
                                                            "left join kd_dz kddz on d.dz_id = kddz.dz_id\n" +
                                                            "where d.dz_id = :pledgeAgreementId\n" +
                                                            "group by 1\n" +
                                                            "having count(kddz.kd_id) = 0";
    private static final String QUERY_PA_WITH_ZERO_ZS_DZ = "select d.dz_id, d.num_dz, d.date_end_dz, d.zs_dz\n" +
                                                            "from dz d\n" +
                                                            "where d.status = 'открыт'\n" +
                                                            "and d.zs_dz = 0";
    private static final String QUERY_IS_PA_WITH_ZERO_ZS_DZ = "select d.dz_id, d.num_dz, d.date_end_dz, d.zs_dz\n" +
                                                                "from dz d\n" +
                                                                "where d.zs_dz = 0\n" +
                                                                "and d.dz_id = :pledgeAgreementId";
    private static final String QUERY_PA_WITH_ZERO_ZS_ZZ = "select d.dz_id, d.num_dz, d.date_end_dz, d.zs_zz\n" +
                                                            "from dz d\n" +
                                                            "where d.status = 'открыт'\n" +
                                                            "and d.zs_zz = 0";
    private static final String QUERY_IS_PA_WITH_ZERO_ZS_ZZ = "select d.dz_id, d.num_dz, d.date_end_dz, d.zs_zz\n" +
                                                                "from dz d\n" +
                                                                "where d.status = 'открыт'\n" +
                                                                "and d.zs_zz = 0\n" +
                                                                "and d.dz_id = :pledgeAgreementId";
    private static final String QUERY_PA_WITHOUT_PS = "select d.dz_id, d.num_dz, d.date_end_dz, count(dp.pledge_subject_id)\n" +
                                                        "from dz d\n" +
                                                        "left join dz_ps dp on d.dz_id = dp.dz_id\n" +
                                                        "where d.status = 'открыт'\n" +
                                                        "group by 1\n" +
                                                        "having count(dp.pledge_subject_id) = 0";
    private static final String QUERY_IS_PA_WITHOUT_PS = "select d.dz_id, d.num_dz, d.date_end_dz, count(dp.pledge_subject_id)\n" +
                                                            "from dz d\n" +
                                                            "left join dz_ps dp on d.dz_id = dp.dz_id\n" +
                                                            "where d.status = 'открыт'\n" +
                                                            "and d.dz_id = :pledgeAgreementId\n" +
                                                            "group by 1\n" +
                                                            "having count(dp.pledge_subject_id) = 0";

    private final NamedParameterJdbcTemplate template;
    private final AgreementAuditDateWrapper agreementAuditDateWrapper;
    private final AgreementAuditLowCostWrapper agreementAuditLowCostWrapper;
    private final AgreementAuditCountWrapper agreementAuditCountWrapper;

    public RepositoryAuditPledgeAgreementImpl(NamedParameterJdbcTemplate template,
                                              AgreementAuditDateWrapper agreementAuditDateWrapper,
                                              AgreementAuditLowCostWrapper agreementAuditLowCostWrapper,
                                              AgreementAuditCountWrapper agreementAuditCountWrapper) {
        this.template = template;
        this.agreementAuditDateWrapper = agreementAuditDateWrapper;
        this.agreementAuditLowCostWrapper = agreementAuditLowCostWrapper;
        this.agreementAuditCountWrapper = agreementAuditCountWrapper;
    }

    @Override
    public Collection<ObjectAudit> getPledgeAgreementWithDateClosedOverdue() {
        return template.query(QUERY_PA_WITH_DATE_CLOSED_OVERDUE, agreementAuditDateWrapper);
    }

    @Override
    public Optional<ObjectAudit> isDateClosedOverDue(Long pledgeAgreementId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PA_ID, pledgeAgreementId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_PA_DATE_END_OVERDUE,
                parameterSource,
                agreementAuditDateWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeAgreementsWithoutLoanAgreement() {
        return template.query(QUERY_PA_WITHOUT_LA, agreementAuditCountWrapper);
    }

    @Override
    public Optional<ObjectAudit> isHaveNotLoanAgreements(Long pledgeAgreementId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PA_ID, pledgeAgreementId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_PA_WITHOUT_LA,
                parameterSource,
                agreementAuditCountWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeAgreementsWithZeroZsDZ() {
        return template.query(QUERY_PA_WITH_ZERO_ZS_DZ, agreementAuditLowCostWrapper);
    }

    @Override
    public Optional<ObjectAudit> isZeroZsDz(Long pledgeAgreementId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PA_ID, pledgeAgreementId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_PA_WITH_ZERO_ZS_DZ,
                parameterSource,
                agreementAuditLowCostWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeAgreementsWithZeroZsZZ() {
        return template.query(QUERY_PA_WITH_ZERO_ZS_ZZ, agreementAuditLowCostWrapper);
    }

    @Override
    public Optional<ObjectAudit> isZeroZsZZ(Long pledgeAgreementId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PA_ID, pledgeAgreementId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_PA_WITH_ZERO_ZS_ZZ,
                parameterSource,
                agreementAuditLowCostWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeAgreementsWithoutPledgeSubjects() {
        return template.query(QUERY_PA_WITHOUT_PS, agreementAuditCountWrapper);
    }

    @Override
    public Optional<ObjectAudit> isHaveNotPledgeSubjects(Long pledgeAgreementId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PA_ID, pledgeAgreementId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_PA_WITHOUT_PS,
                parameterSource,
                agreementAuditCountWrapper)));
    }
}
