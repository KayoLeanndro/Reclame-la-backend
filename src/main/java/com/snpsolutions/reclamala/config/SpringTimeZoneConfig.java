package com.snpsolutions.reclamala.config;

import java.sql.Time;
import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringTimeZoneConfig {
    
    public void timeZoneConfig(){
        TimeZone.setDefault(TimeZone.getTimeZone("america/Recife"));
    }
}
