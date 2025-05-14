package com.kcv.account.management.dto.payments;

import com.kcv.account.management.dto.common.CommonRequest;
import com.kcv.account.management.dto.packages.PackageResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PaymentsRequest extends CommonRequest {

    private Integer paymentId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private String modeOfPayment;
    private String referenceNumber;
    private PackageResponse packageInfo;

}
