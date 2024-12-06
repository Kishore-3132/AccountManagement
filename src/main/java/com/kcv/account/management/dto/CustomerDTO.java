package com.kcv.account.management.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


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

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "INSTALLATION_DATE", nullable = false)
    private LocalDate installationDate;

    @Column(name = "STATUS", nullable = false)
    private String status;


}
