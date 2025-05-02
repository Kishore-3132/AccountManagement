package com.kcv.account.management.dto;

import java.time.LocalDate;

import com.kcv.account.management.dto.enums.AccountStatusEnum;
import com.kcv.account.management.dto.enums.GenderEnum;
import lombok.Data;

@Data
public class CustomerResponse {

    private Integer id;
    private String customerName;
    private GenderEnum gender;
    private String mobileNumber;
    private String customerId;
    private LocalDate installationDate;
    private AccountStatusEnum status;

}
