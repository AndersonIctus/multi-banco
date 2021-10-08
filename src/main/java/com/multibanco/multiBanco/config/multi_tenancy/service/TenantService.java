package com.multibanco.multiBanco.config.multi_tenancy.service;

import com.multibanco.multiBanco.config.multi_tenancy.model.Tenant;
import org.springframework.data.repository.query.Param;

public interface TenantService {
    Tenant findByTenantId(@Param("tenantId") String tenantId);
}
