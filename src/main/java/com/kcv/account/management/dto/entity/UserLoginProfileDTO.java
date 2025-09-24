package com.kcv.account.management.dto.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_LOGIN_PROFILE")
public class UserLoginProfileDTO extends AuditableDTO {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "USER_NAME", nullable = false ,unique = true)
    private String username;

    @Column(name = "GENDER", nullable = false)
    private String gender;

    @Column(name = "MOBILE_NUMBER", nullable = false)
    private String mobileNumber;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "ROLE", nullable = false)
    private String role;

}
