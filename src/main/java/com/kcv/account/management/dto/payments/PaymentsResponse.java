package com.kcv.account.management.dto.payments;

import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.dto.customer.CustomerDetail;
import com.kcv.account.management.dto.packages.PackageDetail;
import com.kcv.account.management.dto.packages.PackageResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PaymentsResponse extends CommonResponse{

    private Integer paymentId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private String modeOfPayment;
    private String referenceNumber;
    private PackageDetail packageInfo;
    private CustomerDetail customer;
    private List<PaymentsDetail> payments;

}
