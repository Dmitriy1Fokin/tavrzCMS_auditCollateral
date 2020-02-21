package ru.fds.tavrzauditcollateral;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.fds.tavrzauditcollateral.service.AuditResultService;

@SpringBootTest
class TavrzAuditcollateralApplicationTests {

    @Autowired
    AuditResultService auditResultService;

    @Test
    void contextLoads() {
        //
    }

    @Test
    void executeAuditInAllObjects(){
//        auditResultService.executeAuditInAllObjects();
    }


}
