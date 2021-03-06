package ru.fds.tavrzauditcollateral.dictionary;

public enum TypeOfAudit {
    LOAN_AGREEMENT_DATE_CLOSED,
    LOAN_AGREEMENT_AMOUNT_LA,
    LOAN_AGREEMENT_NOT_EXIST_PA,

    PLEDGE_AGREEMENT_DATE_CLOSED,
    PLEDGE_AGREEMENT_NOT_EXIST_LA,
    PLEDGE_AGREEMENT_ZERO_ZS_DZ,
    PLEDGE_AGREEMENT_ZERO_ZS_ZZ,
    PLEDGE_AGREEMENT_NOT_EXIST_PS,

    PLEDGE_SUBJECT_LOW_LIQUIDITY_NOT_ZERO_SS,
    PLEDGE_SUBJECT_ZERO_ZS_DZ,
    PLEDGE_SUBJECT_ZERO_ZS_ZZ,
    PLEDGE_SUBJECT_ZERO_RS_DZ,
    PLEDGE_SUBJECT_ZERO_RS_ZZ,
    PLEDGE_SUBJECT_ZERO_SS,
    PLEDGE_SUBJECT_MONITORING_OVERDUE,
    PLEDGE_SUBJECT_CONCLUSION_OVERDUE,
    PLEDGE_SUBJECT_LOSING,
    PLEDGE_SUBJECT_NOT_EXIST_INSURANCE,
    PLEDGE_SUBJECT_NOT_EXIST_ENCUMBRANCE,
    PLEDGE_SUBJECT_EXIST_OTHER_ENCUMBRANCE,
    PLEDGE_SUBJECT_ENCUMBRANCE_OVERDUE,
    PLEDGE_SUBJECT_INSURANCE_OVERDUE
}
