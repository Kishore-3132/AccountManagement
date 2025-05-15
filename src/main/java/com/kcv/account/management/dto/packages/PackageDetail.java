package com.kcv.account.management.dto.packages;

import com.kcv.account.management.dto.common.CommonResponse;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PackageDetail{

    private Integer packageId;
    private String packageName;
    private String packageDescription;
    private Integer packageSpeed;
    private BigDecimal packageAmount;
    private BigDecimal packageAmountIncludingGST;
}
