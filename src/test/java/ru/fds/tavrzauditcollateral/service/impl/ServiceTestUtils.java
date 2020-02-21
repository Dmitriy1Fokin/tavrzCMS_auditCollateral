package ru.fds.tavrzauditcollateral.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.fds.tavrzauditcollateral.dictionary.AuditStatus;
import ru.fds.tavrzauditcollateral.domain.nosql.AuditResult;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public final class ServiceTestUtils {

    static AuditResult getTestAuditResult(){
        return AuditResult.builder()
                .id("qwertyuiop")
                .date(LocalDate.now())
                .objectId(1L)
                .nameOfObject("name")
                .auditStatus(AuditStatus.ACTUAL)
                .build();
    }

    static List<AuditResult> getTestListAuditResult(){
        AuditResult auditResult = AuditResult.builder()
                .id("asdfghjkl")
                .date(LocalDate.now())
                .objectId(2L)
                .nameOfObject("name2")
                .auditStatus(AuditStatus.ACTUAL)
                .build();
        return List.of(auditResult, getTestAuditResult());
    }

    static Page<AuditResult> getTestPageAuditResult(){
        Pageable pageable = PageRequest.of(1, 50);
        return new PageImpl<>(
                getTestListAuditResult(),
                pageable,
                2
        );
    }
}
