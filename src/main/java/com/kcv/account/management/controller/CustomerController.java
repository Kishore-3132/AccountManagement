package com.kcv.account.management.controller;

import java.util.List;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kcv.account.management.dto.CustomerRequest;
import com.kcv.account.management.dto.CustomerResponse;
import com.kcv.account.management.service.ICustomerService;

@CrossOrigin
@RestController
@Log4j2
public class CustomerController 
{
	@Autowired
	private ICustomerService customerService;
	
	@PostMapping("/addCustomer")
	public ResponseEntity<CustomerResponse> addData(@RequestBody CustomerRequest request) 
	{
		CustomerResponse response = customerService.addCustomer(request);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<CustomerResponse>> getAllDetails() 
	{
		List<CustomerResponse> response = customerService.getAllCustomers();
		log.info("Fetching all the Customers");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
