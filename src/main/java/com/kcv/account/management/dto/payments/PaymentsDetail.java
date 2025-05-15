package com.kcv.account.management.dto.payments;

import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.dto.packages.PackageResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentsDetail extends CommonResponse {

    private Integer paymentId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private String modeOfPayment;
    private String referenceNumber;
    private PackageResponse packageInfo;

}
