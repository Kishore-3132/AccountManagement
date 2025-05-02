package com.kcv.account.management.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_DETAILS")
public class UserDetailsDTO {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "GENDER", nullable = false)
    private String gender;

    @Column(name = "MOBILE_NUMBER", nullable = false)
    private String mobileNumber;

    @Column(name = "USER_FULL_NAME", nullable = false)
    private String userFullName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "ROLE", nullable = false)
    private String role;

}
