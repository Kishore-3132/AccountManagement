package com.kcv.account.management.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CustomerResponse {
	
	private String customerName;
	private CustomerGenderEnum gender; 
	private String mobileNumber;
	private String 	username;
	private LocalDate installationDate;
	private CustomerStatusEnum status;

}
