package com.multibanco.multiBanco.config.multi_tenancy.interceptor;

import com.multibanco.multiBanco.config.multi_tenancy.util.TenantContext;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

@Component
public class TenantInterceptor implements WebRequestInterceptor {

    @Override
    public void preHandle(WebRequest request) throws Exception {
        String tenantId = request.getHeader("tenantId");
        if (!StringUtils.isEmpty(tenantId)) {
            TenantContext.setTenantId(tenantId);
        } else {
            throw new RuntimeException("Tenant Id nao informado na requisição");
        }
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        TenantContext.clear();
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
    }
}
