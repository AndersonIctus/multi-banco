package com.multibanco.multiBanco.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;

@Lazy(false)
@Configuration
@ConditionalOnProperty(name = "multitenancy.master.flyway.enabled", havingValue = "true", matchIfMissing = true)
public class FlywayConfig {
    static final String DEFAULT_TENANT = "public";

    @Value("${multitenancy.master.flyway.locations}")
    private String[] flywayLocations;

    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .locations(flywayLocations)
                .dataSource(dataSource)
                .schemas(DEFAULT_TENANT)
                .load();
        flyway.migrate();
        return flyway;
    }
}
