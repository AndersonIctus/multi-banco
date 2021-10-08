package com.multibanco.multiBanco.config.multi_tenancy.repository;

import com.multibanco.multiBanco.config.multi_tenancy.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, String> {
    Optional<Tenant> findByTenantId(String tenantId);
}
