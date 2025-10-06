package com.kcv.account.management.dto.common;

import lombok.Data;

@Data
public class CommonRequest {
    private String username;
    private String service;
    private String ulpId;
}
