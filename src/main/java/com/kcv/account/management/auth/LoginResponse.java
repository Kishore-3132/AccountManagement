package com.kcv.account.management.auth;

import com.kcv.account.management.dto.common.CommonResponse;
import lombok.Data;

@Data
public class LoginResponse extends CommonResponse {

    private String username;
    private String role;

}
