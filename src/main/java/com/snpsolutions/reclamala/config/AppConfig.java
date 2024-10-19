package com.snpsolutions.reclamala.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        Dotenv dotenv = Dotenv.load();
        String dbHost = dotenv.get("DB_HOST");
        String dbPort = dotenv.get("DB_PORT");
        String dbName = dotenv.get("DB_NAME");
        String dbUrl = String.format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbName);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dbUrl);
        dataSource.setUsername(dotenv.get("DB_USERNAME"));
        dataSource.setPassword(dotenv.get("DB_PASSWORD"));

        return dataSource;
    }

    @Bean
    public Dotenv dotenv() {
        return Dotenv.load();
    }

}
