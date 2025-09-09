package com.kcv.account.management.auth;

import com.kcv.account.management.dto.common.CommonRequest;
import lombok.Data;

@Data
public class LoginRequest extends CommonRequest {
    private String username;
    private String password;
}
