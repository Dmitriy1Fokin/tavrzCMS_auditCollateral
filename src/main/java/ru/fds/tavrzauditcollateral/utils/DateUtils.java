package ru.fds.tavrzauditcollateral.utils;


import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateUtils {

    public LocalDate getNow(){
        return LocalDate.now();
    }

    public LocalDate getMinusOneYearFromNow(){
        return LocalDate.now().minusYears(1);
    }
}
