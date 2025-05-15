package com.kcv.account.management.dto.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PAYMENTS")
public class PaymentsDTO {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "PAYMENT_DATE", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "MODE_OF_PAYMENT", nullable = false)
    private String modeOfPayment;

    @Column(name = "REFERENCE_NUMBER", nullable = false)
    private String referenceNumber;

    @OneToOne
    @JoinColumn(name = "PACKAGE_ID", nullable = false)
    private PackageDTO packageInfo;

    @OneToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerDTO customer;

}
