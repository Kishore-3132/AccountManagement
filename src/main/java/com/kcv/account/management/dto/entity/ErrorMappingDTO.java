package com.kcv.account.management.dto.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ERROR_MAPPING", uniqueConstraints={@UniqueConstraint(columnNames={"CODE"})})
public class ErrorMappingDTO {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;
}
