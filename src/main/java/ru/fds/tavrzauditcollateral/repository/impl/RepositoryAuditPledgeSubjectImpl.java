package ru.fds.tavrzauditcollateral.repository.impl;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.fds.tavrzauditcollateral.domain.sql.ObjectAudit;
import ru.fds.tavrzauditcollateral.repository.RepositoryAuditPledgeSubject;
import ru.fds.tavrzauditcollateral.rowmapper.PledgeSubjectAuditCostWrapper;
import ru.fds.tavrzauditcollateral.rowmapper.PledgeSubjectAuditDateWrapper;
import ru.fds.tavrzauditcollateral.rowmapper.PledgeSubjectAuditTextWrapper;
import ru.fds.tavrzauditcollateral.utils.DateUtils;

import java.util.Collection;
import java.util.Optional;

@Repository
public class RepositoryAuditPledgeSubjectImpl implements RepositoryAuditPledgeSubject {

    private static final String PARAM_PS_ID = "pledgeSubjectId";
    private static final String PARAM_DATE_NOW = "dateNow";
    private static final String PARAM_DATE_OVERDUE = "dateOverdue";
    private static final String QUERY_PS_WITH_LOW_LIQUIDITY_AND_NOT_ZERO_SS = "select distinct ps.pledge_subject_id, ps.name, ps.ss\n  " +
                                                                                "from pledge_subject ps\n " +
                                                                                "join dz_ps dp on ps.pledge_subject_id = dp.pledge_subject_id\n " +
                                                                                "join dz d on dp.dz_id = d.dz_id\n" +
                                                                                "where d.status = 'открыт'\n" +
                                                                                "and ps.liquidity = 'низкая'\n" +
                                                                                "and ps.ss > 0";
    private static final String QUERY_IS_LOW_LIQUIDITY_AND_NOT_ZERO_SS = "select ps.pledge_subject_id, ps.name, ps.ss\n  " +
                                                                            "from pledge_subject ps\n " +
                                                                            "where ps.liquidity = 'низкая'\n" +
                                                                            "and ps.ss > 0\n" +
                                                                            "and ps.pledge_subject_id = :pledgeSubjectId";

    private static final String QUERY_PS_WITH_ZERO_ZSDZ = "select distinct ps.pledge_subject_id, ps.name, ps.zs_dz\n" +
                                                            "from pledge_subject ps\n  " +
                                                            "join dz_ps dp on ps.pledge_subject_id = dp.pledge_subject_id\n " +
                                                            "join dz d on dp.dz_id = d.dz_id\n" +
                                                            "where d.status = 'открыт'\n" +
                                                            "and ps.zs_dz = 0";
    private static final String QUERY_IS_ZERO_ZSDZ = "select ps.pledge_subject_id, ps.name, ps.zs_dz\n" +
                                                        "from pledge_subject ps\n  " +
                                                        "where ps.zs_dz = 0\n" +
                                                        "and ps.pledge_subject_id  = :pledgeSubjectId";

    private static final String QUERY_PS_WITH_ZERO_ZSZZ = "select distinct ps.pledge_subject_id, ps.name, ps.zs_zz\n" +
                                                            "from pledge_subject ps\n   " +
                                                            "join dz_ps dp on ps.pledge_subject_id = dp.pledge_subject_id\n  " +
                                                            "join dz d on dp.dz_id = d.dz_id\n" +
                                                            "where d.status = 'открыт'\n" +
                                                            "and ps.zs_zz = 0";
    private static final String QUERY_IS_ZERO_ZSZZ = "select ps.pledge_subject_id, ps.name, ps.zs_zz\n" +
                                                        "from pledge_subject ps\n   " +
                                                        "where ps.zs_zz = 0\n" +
                                                        "and ps.pledge_subject_id = :pledgeSubjectId  ";

    private static final String QUERY_PS_WITH_ZERO_RSDZ = "select distinct ps.pledge_subject_id, ps.name, ps.rs_dz\n" +
                                                            "from pledge_subject ps \n" +
                                                            "join dz_ps dp on ps.pledge_subject_id = dp.pledge_subject_id\n  " +
                                                            "join dz d on dp.dz_id = d.dz_id\n" +
                                                            "where d.status = 'открыт'\n" +
                                                            "and ps.rs_dz = 0";
    private static final String QUERY_IS_ZERO_RSDZ = "select ps.pledge_subject_id, ps.name, ps.rs_dz\n" +
                                                        "from pledge_subject ps \n" +
                                                        "where ps.rs_dz = 0\n" +
                                                        "and ps.pledge_subject_id = :pledgeSubjectId  ";

    private static final String QUERY_PS_WITH_ZERO_RSZZ = "select distinct ps.pledge_subject_id, ps.name, ps.rs_zz\n" +
                                                            "from pledge_subject ps  \n" +
                                                            "join dz_ps dp on ps.pledge_subject_id = dp.pledge_subject_id \n" +
                                                            "join dz d on dp.dz_id = d.dz_id\n" +
                                                            "where d.status = 'открыт'\n" +
                                                            "and ps.rs_zz = 0";
    private static final String QUERY_IS_ZERO_RSZZ = "select ps.pledge_subject_id, ps.name, ps.rs_zz\n" +
                                                        "from pledge_subject ps  \n" +
                                                        "where ps.rs_zz = 0\n" +
                                                        "and ps.pledge_subject_id = :pledgeSubjectId ";

    private static final String QUERY_PS_WITH_ZERO_SS = "select distinct ps.pledge_subject_id, ps.name, ps.ss\n" +
                                                            "from pledge_subject ps   \n" +
                                                            "join dz_ps dp on ps.pledge_subject_id = dp.pledge_subject_id \n" +
                                                            "join dz d on dp.dz_id = d.dz_id\n" +
                                                            "where d.status = 'открыт'\n" +
                                                            "and ps.ss = 0";
    private static final String QUERY_IS_ZERO_SS = "select ps.pledge_subject_id, ps.name, ps.ss\n" +
                                                    "from pledge_subject ps   \n" +
                                                    "where ps.ss = 0\n" +
                                                    "and ps.pledge_subject_id = :pledgeSubjectId ";

    private static final String QUERY_PS_WITH_MONITORING_OVERDUE = "select distinct ps.pledge_subject_id, ps.name, ps.date_monitoring\n" +
                                                                    "from pledge_subject ps\n" +
                                                                    "join dz_ps dp on ps.pledge_subject_id = dp.pledge_subject_id  \n" +
                                                                    "join dz d on dp.dz_id = d.dz_id\n" +
                                                                    "where d.status = 'открыт'\n" +
                                                                    "and ps.date_monitoring < :dateOverdue";
    private static final String QUERY_IS_MONITORING_OVERDUE = "select ps.pledge_subject_id, ps.name, ps.date_monitoring\n" +
                                                                "from pledge_subject ps\n" +
                                                                "where ps.date_monitoring < :dateOverdue\n" +
                                                                "and ps.pledge_subject_id = :pledgeSubjectId   ";

    private static final String QUERY_PS_WITH_CONCLUSION_OVERDUE = "select distinct ps.pledge_subject_id, ps.name, ps.date_conclusion\n" +
                                                                    "from pledge_subject ps\n" +
                                                                    "join dz_ps dp on ps.pledge_subject_id = dp.pledge_subject_id  \n" +
                                                                    "join dz d on dp.dz_id = d.dz_id\n" +
                                                                    "where d.status = 'открыт'\n" +
                                                                    "and ps.date_conclusion < :dateOverdue";
    private static final String QUERY_IS_CONCLUSION_OVERDUE = "select ps.pledge_subject_id, ps.name, ps.date_conclusion\n" +
                                                                "from pledge_subject ps\n" +
                                                                "where ps.date_conclusion < :dateOverdue\n" +
                                                                "and ps.pledge_subject_id = :pledgeSubjectId";

    private static final String QUERY_PS_LOSING = "select distinct ps.pledge_subject_id, ps.name, ps.status_monitoring\n" +
                                                    "from pledge_subject ps\n" +
                                                    "join dz_ps dp on ps.pledge_subject_id = dp.pledge_subject_id\n   " +
                                                    "join dz d on dp.dz_id = d.dz_id\n" +
                                                    "where d.status = 'открыт'\n" +
                                                    "and ps.status_monitoring = 'утрата'";
    private static final String QUERY_IS_LOSING = "select ps.pledge_subject_id, ps.name, ps.status_monitoring\n" +
                                                    "from pledge_subject ps\n" +
                                                    "where ps.status_monitoring = 'утрата'\n" +
                                                    "and ps.pledge_subject_id = :pledgeSubjectId   ";

    private static final String QUERY_PS_WITHOUT_INSURANCE = "select distinct ps.pledge_subject_id, ps.name, count(i.insurance_id)\n" +
                                                                "from pledge_subject ps\n" +
                                                                "left join insurance i on ps.pledge_subject_id = i.pledgesubject_id\n" +
                                                                "join dz_ps dp on ps.pledge_subject_id = dp.pledge_subject_id\n   " +
                                                                "join dz d on dp.dz_id = d.dz_id\n" +
                                                                "where d.status = 'открыт'\n" +
                                                                "and ps.insurance_obligation = 'да'\n" +
                                                                "group by 1\n" +
                                                                "having count(i.insurance_id) = 0";
    private static final String QUERY_IS_HAVE_NOT_INSURANCE = "select ps.pledge_subject_id, ps.name, count(i.insurance_id)\n" +
                                                                "from pledge_subject ps\n" +
                                                                "left join insurance i on ps.pledge_subject_id = i.pledgesubject_id\n" +
                                                                "where ps.insurance_obligation = 'да'\n" +
                                                                "and ps.pledge_subject_id = :pledgeSubjectId\n" +
                                                                "group by 1\n" +
                                                                "having count(i.insurance_id) = 0";

    private static final String QUERY_PS_WITHOUT_ENCUMBRANCE = "select distinct ps.pledge_subject_id, ps.name, 0\n" +
                                                                "from pledge_subject ps\n" +
                                                                "join dz_ps dp on ps.pledge_subject_id =  dp.pledge_subject_id\n" +
                                                                "join dz d on dp.dz_id = d.dz_id\n" +
                                                                "where d.status = 'открыт'\n" +
                                                                "and ps.pledge_subject_id not in(select e.pledgesubject_id\n" +
                                                                "                                from encumbrance e\n" +
                                                                "                                where e.type_of_encumbrance = 'залог')";
    private static final String QUERY_IS_HAVE_NOT_ENCUMBRANCE = "select distinct ps.pledge_subject_id, ps.name, 0\n" +
                                                                    "from pledge_subject ps\n" +
                                                                    "join encumbrance e on ps.pledge_subject_id = e.encumbrance_id\n" +
                                                                    "where e.type_of_encumbrance != 'залог'\n" +
                                                                    "and ps.pledge_subject_id = :pledgeSubjectId";

    private static final String QUERY_PS_WITH_OTHER_ENCUMBRANCE = "select distinct ps.pledge_subject_id, ps.name, e.type_of_encumbrance\n" +
                                                                    "from pledge_subject ps\n" +
                                                                    "join encumbrance e on ps.pledge_subject_id = e.pledgesubject_id\n" +
                                                                    "join dz_ps dp on ps.pledge_subject_id =  dp.pledge_subject_id\n" +
                                                                    "join dz d on dp.dz_id = d.dz_id\n" +
                                                                    "where d.status = 'открыт'\n" +
                                                                    "and e.type_of_encumbrance = 'арест'";
    private static final String QUERY_IS_HAVE_OTHER_ENCUMBRANCE = "select distinct ps.pledge_subject_id, ps.name, e.type_of_encumbrance\n" +
                                                                    "from pledge_subject ps\n" +
                                                                    "join encumbrance e on ps.pledge_subject_id = e.pledgesubject_id\n" +
                                                                    "and e.type_of_encumbrance = 'арест'\n" +
                                                                    "and ps.pledge_subject_id =  :pledgeSubjectId";

    private static final String QUERY_PS_WITH_INSURANCE_OVERDUE = "select distinct ps.pledge_subject_id, ps.name, i.date_end\n" +
                                                                    "from pledge_subject ps\n" +
                                                                    "join insurance i on ps.pledge_subject_id = i.pledgesubject_id\n" +
                                                                    "join dz_ps dp on ps.pledge_subject_id = dp.pledge_subject_id\n" +
                                                                    "join dz d on dp.dz_id = d.dz_id\n" +
                                                                    "where d.status = 'открыт'\n" +
                                                                    "and ps.insurance_obligation = 'да'\n" +
                                                                    "and i.insurance_id = (select i2.insurance_id\n" +
                                                                    "                        from insurance i2\n" +
                                                                    "                        where i2.pledgesubject_id = ps.pledge_subject_id\n" +
                                                                    "                        order by i2.date_end desc\n" +
                                                                    "                        limit 1)\n" +
                                                                    "and i.date_end < :dateNow";

    private static final String QUERY_IS_INSURANCE_OVERDUE = "select ps.pledge_subject_id, ps.name, i.date_end\n" +
                                                                "from pledge_subject ps\n" +
                                                                "join insurance i on ps.pledge_subject_id = i.pledgesubject_id\n" +
                                                                "where ps.insurance_obligation = 'да'\n" +
                                                                "and i.insurance_id = (select i2.insurance_id\n" +
                                                                "                      from insurance i2\n" +
                                                                "                      where i2.pledgesubject_id = ps.pledge_subject_id\n" +
                                                                "                      order by i2.date_end desc\n" +
                                                                "                      limit 1)\n" +
                                                                "and i.date_end < :dateNow\n" +
                                                                "and ps.pledge_subject_id = :pledgeSubjectId";

    private static final String QUERY_PS_WITH_ENCUMBRANCE_OVERDUE = "select distinct ps.pledge_subject_id, ps.name, e.date_end\n" +
                                                                    "from pledge_subject ps\n" +
                                                                    "join encumbrance e on ps.pledge_subject_id = e.pledgesubject_id\n " +
                                                                    "join dz_ps dp on ps.pledge_subject_id = dp.pledge_subject_id\n" +
                                                                    "join dz d on dp.dz_id = d.dz_id\n" +
                                                                    "where d.status = 'открыт'\n" +
                                                                    "and e.type_of_encumbrance = 'залог'\n" +
                                                                    "and e.date_end < :dateNow";
    private static final String QUERY_IS_ENCUMBRANCE_OVERDUE = "select ps.pledge_subject_id, ps.name, e.date_end\n" +
                                                                "from pledge_subject ps\n" +
                                                                "join encumbrance e on ps.pledge_subject_id = e.pledgesubject_id\n " +
                                                                "where e.date_end < :dateNow\n" +
                                                                "and e.type_of_encumbrance = 'залог'\n" +
                                                                "and ps.pledge_subject_id = :pledgeSubjectId";

    private final NamedParameterJdbcTemplate template;
    private final PledgeSubjectAuditCostWrapper pledgeSubjectAuditCostWrapper;
    private final PledgeSubjectAuditDateWrapper pledgeSubjectAuditDateWrapper;
    private final PledgeSubjectAuditTextWrapper pledgeSubjectAuditTextWrapper;
    private final DateUtils dateUtils;

    public RepositoryAuditPledgeSubjectImpl(NamedParameterJdbcTemplate template,
                                            PledgeSubjectAuditCostWrapper pledgeSubjectAuditCostWrapper,
                                            PledgeSubjectAuditDateWrapper pledgeSubjectAuditDateWrapper,
                                            PledgeSubjectAuditTextWrapper pledgeSubjectAuditTextWrapper,
                                            DateUtils dateUtils) {
        this.template = template;
        this.pledgeSubjectAuditCostWrapper = pledgeSubjectAuditCostWrapper;
        this.pledgeSubjectAuditDateWrapper = pledgeSubjectAuditDateWrapper;
        this.pledgeSubjectAuditTextWrapper = pledgeSubjectAuditTextWrapper;
        this.dateUtils = dateUtils;
    }

    @Override
    public Collection<ObjectAudit> getPledgeSubjectWithLowLiquidityAndNotZeroSs() {
        return template.query(QUERY_PS_WITH_LOW_LIQUIDITY_AND_NOT_ZERO_SS, pledgeSubjectAuditCostWrapper);
    }

    @Override
    public Optional<ObjectAudit> isHaveLowLiquidityAndNotZeroSS(Long pledgeSubjectId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PS_ID, pledgeSubjectId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_LOW_LIQUIDITY_AND_NOT_ZERO_SS,
                parameterSource,
                pledgeSubjectAuditCostWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeSubjectsWithZeroZsDz() {
        return template.query(QUERY_PS_WITH_ZERO_ZSDZ, pledgeSubjectAuditCostWrapper);
    }

    @Override
    public Optional<ObjectAudit> isHaveZeroZsDz(Long pledgeSubjectId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PS_ID, pledgeSubjectId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_ZERO_ZSDZ,
                parameterSource,
                pledgeSubjectAuditCostWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeSubjectsWithZeroZsZz() {
        return template.query(QUERY_PS_WITH_ZERO_ZSZZ, pledgeSubjectAuditCostWrapper);
    }

    @Override
    public Optional<ObjectAudit> isHaveZeroZsZz(Long pledgeSubjectId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PS_ID, pledgeSubjectId);
        return  Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_ZERO_ZSZZ,
                parameterSource,
                pledgeSubjectAuditCostWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeSubjectsWithZeroRsDz() {
        return template.query(QUERY_PS_WITH_ZERO_RSDZ, pledgeSubjectAuditCostWrapper);
    }

    @Override
    public Optional<ObjectAudit> isHaveZeroRsDz(Long pledgeSubjectId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PS_ID, pledgeSubjectId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_ZERO_RSDZ,
                parameterSource,
                pledgeSubjectAuditCostWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeSubjectsWithZeroRsZz() {
        return template.query(QUERY_PS_WITH_ZERO_RSZZ, pledgeSubjectAuditCostWrapper);
    }

    @Override
    public Optional<ObjectAudit> isHaveZeroRsZz(Long pledgeSubjectId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PS_ID, pledgeSubjectId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_ZERO_RSZZ,
                parameterSource,
                pledgeSubjectAuditCostWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeSubjectsWithZeroSs() {
        return template.query(QUERY_PS_WITH_ZERO_SS, pledgeSubjectAuditCostWrapper);
    }

    @Override
    public Optional<ObjectAudit> isHaveZeroSs(Long pledgeSubjectId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PS_ID, pledgeSubjectId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_ZERO_SS,
                parameterSource,
                pledgeSubjectAuditCostWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeSubjectsWithMonitoringOverdue() {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(PARAM_DATE_OVERDUE, dateUtils.getMinusOneYearFromNow());
        return template.query(QUERY_PS_WITH_MONITORING_OVERDUE, parameterSource, pledgeSubjectAuditDateWrapper);
    }

    @Override
    public Optional<ObjectAudit> isMonitoringOverdue(Long pledgeSubjectId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(PARAM_PS_ID, pledgeSubjectId)
                .addValue(PARAM_DATE_OVERDUE, dateUtils.getMinusOneYearFromNow());
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_MONITORING_OVERDUE,
                parameterSource,
                pledgeSubjectAuditDateWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeSubjectsWithConclusionOverdue() {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(PARAM_DATE_OVERDUE, dateUtils.getMinusOneYearFromNow());
        return template.query(QUERY_PS_WITH_CONCLUSION_OVERDUE, parameterSource, pledgeSubjectAuditDateWrapper);
    }

    @Override
    public Optional<ObjectAudit> isConclusionOverdue(Long pledgeSubjectId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(PARAM_PS_ID, pledgeSubjectId)
                .addValue(PARAM_DATE_OVERDUE, dateUtils.getMinusOneYearFromNow());
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_CONCLUSION_OVERDUE,
                parameterSource,
                pledgeSubjectAuditDateWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeSubjectsLosing() {
        return template.query(QUERY_PS_LOSING, pledgeSubjectAuditTextWrapper);
    }

    @Override
    public Optional<ObjectAudit> isLosing(Long pledgeSubjectId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PS_ID, pledgeSubjectId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_LOSING,
                parameterSource,
                pledgeSubjectAuditTextWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeSubjectWithoutInsurance() {
        return template.query(QUERY_PS_WITHOUT_INSURANCE, pledgeSubjectAuditTextWrapper);
    }

    @Override
    public Optional<ObjectAudit> isHaveNotInsurance(Long pledgeSubjectId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PS_ID, pledgeSubjectId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_HAVE_NOT_INSURANCE,
                parameterSource,
                pledgeSubjectAuditTextWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeSubjectWithoutOurEncumbrance() {
        return template.query(QUERY_PS_WITHOUT_ENCUMBRANCE, pledgeSubjectAuditTextWrapper);
    }

    @Override
    public Optional<ObjectAudit> isHaveNotOurEncumbrance(Long pledgeSubjectId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PS_ID, pledgeSubjectId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_HAVE_NOT_ENCUMBRANCE,
                parameterSource,
                pledgeSubjectAuditTextWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeSubjectsWithOtherEncumbrance() {
        return template.query(QUERY_PS_WITH_OTHER_ENCUMBRANCE, pledgeSubjectAuditTextWrapper);
    }

    @Override
    public Optional<ObjectAudit> isHaveOtherEncumbrance(Long pledgeSubjectId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_PS_ID, pledgeSubjectId);
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_HAVE_OTHER_ENCUMBRANCE,
                parameterSource,
                pledgeSubjectAuditTextWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeSubjectsWithEncumbranceOverdue() {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_DATE_NOW, dateUtils.getNow());
        return template.query(QUERY_PS_WITH_ENCUMBRANCE_OVERDUE, parameterSource, pledgeSubjectAuditDateWrapper);
    }

    @Override
    public Optional<ObjectAudit> isEncumbranceOverdue(Long pledgeSubjectId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(PARAM_PS_ID, pledgeSubjectId)
                .addValue(PARAM_DATE_NOW, dateUtils.getNow());
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_ENCUMBRANCE_OVERDUE,
                parameterSource,
                pledgeSubjectAuditDateWrapper)));
    }

    @Override
    public Collection<ObjectAudit> getPledgeSubjectsWithInsuranceOverdue() {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(PARAM_DATE_NOW, dateUtils.getNow());
        return template.query(QUERY_PS_WITH_INSURANCE_OVERDUE, parameterSource, pledgeSubjectAuditDateWrapper);
    }

    @Override
    public Optional<ObjectAudit> isInsuranceOverdue(Long pledgeSubjectId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue(PARAM_PS_ID, pledgeSubjectId)
                .addValue(PARAM_DATE_NOW, dateUtils.getNow());
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(QUERY_IS_INSURANCE_OVERDUE,
                parameterSource,
                pledgeSubjectAuditDateWrapper)));
    }
}
