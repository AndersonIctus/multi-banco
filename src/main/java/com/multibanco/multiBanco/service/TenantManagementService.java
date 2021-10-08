package com.multibanco.multiBanco.service;

import com.multibanco.multiBanco.config.multi_tenancy.model.Tenant;
import com.multibanco.multiBanco.config.multi_tenancy.repository.TenantRepository;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class TenantManagementService {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    private final TenantRepository tenantRepository;

    @Value("${multitenancy.tenant.flyway.locations}")
    private String[] flywayLocations;

    @Autowired
    public TenantManagementService(DataSource dataSource, JdbcTemplate jdbcTemplate, TenantRepository tenantRepository) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.tenantRepository = tenantRepository;
    }

    private static final String VALID_SCHEMA_NAME_REGEXP = "[A-Za-z0-9_]*";

    public void createTenant(String tenantId, String schema) {
        // Verify schema string to prevent SQL injection
        if (!schema.matches(VALID_SCHEMA_NAME_REGEXP)) {
            throw new RuntimeException("Invalid schema name: " + schema);
        }

        try {
            createSchema(schema);
            migrateFlyway(dataSource, schema);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error when creating schema: " + schema, e);
        } catch (FlywayException e) {
            throw new RuntimeException("Error when populating schema: ", e);
        }
        Tenant tenant = Tenant.builder()
                .tenantId(tenantId)
                .schema(schema)
                .build();
        tenantRepository.save(tenant);
    }

    private void createSchema(String schema) {
        jdbcTemplate.execute(
                (StatementCallback<Boolean>) stmt -> stmt.execute("CREATE SCHEMA " + schema)
        );
    }

    private void migrateFlyway(DataSource dataSource, String schema) throws FlywayException {
        Flyway flyway = Flyway.configure()
                .locations(flywayLocations)
                .dataSource(dataSource)
                .schemas(schema)
                .load();
        flyway.migrate();
    }
}
