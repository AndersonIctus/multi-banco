package com.multibanco.multiBanco.config.multi_tenancy.configuration.tenant;

import com.multibanco.multiBanco.config.multi_tenancy.configuration.tenant.flyway.DynamicSchemaBasedMultiTenantSpringFlyway;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Lazy(false)
@Configuration
@ConditionalOnProperty(name = "multitenancy.tenant.flyway.enabled", havingValue = "true", matchIfMissing = true)
public class TenantFlywayConfig {

    @Bean
    public DynamicSchemaBasedMultiTenantSpringFlyway tenantFlyway() {
        return new DynamicSchemaBasedMultiTenantSpringFlyway();
    }
}
