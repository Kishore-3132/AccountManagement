package com.kcv.account.management.dto.customer;

import com.kcv.account.management.dto.enums.AccountStatusEnum;
import com.kcv.account.management.dto.enums.GenderEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerDetail {

    private Integer id;
    private String customerName;
    private GenderEnum gender;
    private String mobileNumber;
    private String customerId;
    private LocalDate installationDate;
    private AccountStatusEnum status;
    private String address;

}
