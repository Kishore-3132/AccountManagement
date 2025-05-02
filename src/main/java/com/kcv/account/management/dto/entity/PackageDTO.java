package com.kcv.account.management.dto.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "PACKAGE")
public class PackageDTO {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "PACKAGE_NAME", nullable = false)
    private String packageName;

    @Column(name = "PACKAGE_DESCRIPTION", nullable = false)
    private String packageDescription;

    @Column(name = "PACKAGE_SPEED", nullable = false)
    private Integer packageSpeed;

    @Column(name = "PACKAGE_AMOUNT", nullable = false)
    private BigDecimal packageAmount;

    @Column(name = "PACKAGE_AMOUNT_INCL_GST", nullable = false)
    private BigDecimal packageAmountIncludingGST;
}
