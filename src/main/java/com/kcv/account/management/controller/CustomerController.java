package com.kcv.account.management.controller;

import com.kcv.account.management.exception.ErrorResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kcv.account.management.dto.customer.CustomerRequest;
import com.kcv.account.management.dto.customer.CustomerResponse;
import com.kcv.account.management.service.ICustomerService;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/secure/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ErrorResponseMapper errorResponseMapper;

    @PostMapping("/addCustomer")
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.addCustomer(request);

        if (response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return errorResponseMapper.buildErrorResponse(response.getResponseCode(), CustomerResponse.class);
        }
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<CustomerResponse> getAllDetails() {
        log.info("JSON REQUEST: GET method called for /getAllCustomers");
        CustomerResponse response = customerService.getAllCustomers();

        if (response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return errorResponseMapper.buildErrorResponse(response.getResponseCode(), CustomerResponse.class);
        }
    }
    @PutMapping("/editCustomer/{id}")
    public ResponseEntity<CustomerResponse> editCustomer(@RequestBody CustomerRequest request,@PathVariable Integer id) {
        request.setId(Long.valueOf(id));
        CustomerResponse response = customerService.editCustomer(request);
        if (response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return errorResponseMapper.buildErrorResponse(response.getResponseCode(), CustomerResponse.class);
        }
    }

}
