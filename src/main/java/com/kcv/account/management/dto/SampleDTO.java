package com.kcv.account.management.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DEMO_PROJECT")
public class SampleDTO {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNo;

    @Column(name = "AGE")
    private String age;


}
