package com.kcv.account.management.dto.customer;

import java.time.LocalDate;

import com.kcv.account.management.dto.enums.AccountStatusEnum;
import com.kcv.account.management.dto.enums.GenderEnum;
import lombok.Data;

@Data
public class CustomerRequest {

    private Integer id;
    private String customerName;
    private GenderEnum gender;
    private String mobileNumber;
    private String customerId;
    private LocalDate installationDate;
    private AccountStatusEnum status;
    private String address;

}
