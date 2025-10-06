package com.kcv.account.management.dto.common;

import com.kcv.account.management.security.SecurityUtils;
import lombok.Data;

@Data
public class CommonRequest {
    private String username = SecurityUtils.getCurrentUser() != null ? SecurityUtils.getCurrentUser().getUsername() : null;
    private String service;
    private Long ulpId = SecurityUtils.getCurrentUser() != null ? SecurityUtils.getCurrentUser().getUlpId() : null;
}
