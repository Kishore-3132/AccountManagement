package com.kcv.account.management.dto.common;

import lombok.Data;

@Data
public class CommonResponse {
    private String responseCode;
    private String responseMessage;
    private Boolean success;

}
