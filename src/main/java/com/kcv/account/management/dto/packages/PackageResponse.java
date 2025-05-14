package com.kcv.account.management.dto.packages;

import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.dto.enums.AccountStatusEnum;
import com.kcv.account.management.dto.enums.GenderEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PackageResponse extends CommonResponse {

    private Integer packageId;
    private String packageName;
    private String packageDescription;
    private Integer packageSpeed;
    private BigDecimal packageAmount;
    private BigDecimal packageAmountIncludingGST;
}
