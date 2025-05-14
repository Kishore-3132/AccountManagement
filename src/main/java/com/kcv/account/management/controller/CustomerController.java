package com.kcv.account.management.controller;

import java.util.List;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kcv.account.management.dto.customer.CustomerRequest;
import com.kcv.account.management.dto.customer.CustomerResponse;
import com.kcv.account.management.service.ICustomerService;

@CrossOrigin
@RestController
@Log4j2
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.addCustomer(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerResponse>> getAllDetails() {
        List<CustomerResponse> response = customerService.getAllCustomers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/editCustomer/{id}")
    public ResponseEntity<CustomerResponse> editCustomer(@RequestBody CustomerRequest request,@PathVariable Integer id) {
        request.setId(id);
        CustomerResponse response = customerService.editCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
