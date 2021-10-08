package com.multibanco.multiBanco.config.multi_tenancy.configuration.tenant.flyway;

import com.multibanco.multiBanco.config.multi_tenancy.model.Tenant;
import com.multibanco.multiBanco.config.multi_tenancy.repository.TenantRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;
import java.util.Collection;

@Getter
@Setter
@Slf4j
public class DynamicSchemaBasedMultiTenantSpringFlyway implements InitializingBean {
    @Autowired
    private TenantRepository masterTenantRepository;

    @Autowired
    private DataSource dataSource;

    @Value("${multitenancy.tenant.flyway.locations}")
    private String[] flywayLocations;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Schema based multitenancy enabled");
        this.runOnAllSchemas(dataSource, masterTenantRepository.findAll());
    }

    protected void runOnAllSchemas(DataSource dataSource, Collection<Tenant> tenants) throws FlywayException {
        for (Tenant tenant : tenants) {
            log.info("Initializing Flyway for tenant " + tenant.getTenantId());
            Flyway flyway = this.getSpringFlyway(dataSource, tenant.getSchema());
            flyway.migrate();

            log.info("Flyway ran for tenant " + tenant.getTenantId());
        }
    }

    private Flyway getSpringFlyway(DataSource dataSource, String schema) {
        return Flyway.configure()
                .locations(flywayLocations)
                .dataSource(dataSource)
                .schemas(schema)
                .load();
    }
}
