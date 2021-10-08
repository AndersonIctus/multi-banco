package com.multibanco.multiBanco.config.multi_tenancy.configuration.tenant.hibernate;

import com.multibanco.multiBanco.config.multi_tenancy.util.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component("currentTenantIdentifierResolver")
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {
    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = TenantContext.getTenantId();

        // Allow bootstrapping the EntityManagerFactory, in which case no tenant is needed
        if (StringUtils.isEmpty(tenantId))
            return "BOOTSTRAP";
        else
            return tenantId;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
