package com.multibanco.multiBanco.resource;

import com.multibanco.multiBanco.config.multi_tenancy.model.Tenant;
import com.multibanco.multiBanco.config.multi_tenancy.repository.TenantRepository;
import com.multibanco.multiBanco.config.multi_tenancy.service.TenantService;
import com.multibanco.multiBanco.service.TenantManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenants")
public class TenantResource {
    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private TenantManagementService tenantManagementService;

    @GetMapping()
    public List<Tenant> listar(Sort sort) {
        return tenantRepository.findAll(sort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tenant> listar(String id) {
        Tenant tenant = tenantRepository.findById(id).orElse(null);
        return ResponseEntity.ok().body(tenant);
    }

    //
    @PostMapping()
    public ResponseEntity<Void> createTenant(@RequestParam String tenantId, @RequestParam String schema) {
        this.tenantManagementService.createTenant(tenantId, schema);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
