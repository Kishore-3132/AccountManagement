package com.kcv.account.management.dto.customer;

import java.time.LocalDate;
import java.util.List;

import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.dto.enums.AccountStatusEnum;
import com.kcv.account.management.dto.enums.GenderEnum;
import com.kcv.account.management.dto.payments.PaymentsDetail;
import lombok.Data;

@Data
public class CustomerResponse extends CommonResponse {

    private Integer id;
    private String customerName;
    private GenderEnum gender;
    private String mobileNumber;
    private String customerId;
    private LocalDate installationDate;
    private AccountStatusEnum status;
    private String address;
    private List<CustomerDetail> customers;

}
