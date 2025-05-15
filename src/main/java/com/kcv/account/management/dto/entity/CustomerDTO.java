package com.kcv.account.management.dto.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;


import lombok.Data;

@Data
@Entity
@Table(name = "CUSTOMER")
public class CustomerDTO {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String customerName;

    @Column(name = "GENDER", nullable = false)
    private String gender;

    @Column(name = "MOBILE_NUMBER", nullable = false)
    private String mobileNumber;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private String customerId;

    @Column(name = "INSTALLATION_DATE", nullable = false)
    private LocalDate installationDate;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "Address", nullable = false)
    private String address;
//
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<PaymentsDTO> payments;
}
