package com.kcv.account.management.service;


import java.util.List;

import com.kcv.account.management.dto.CustomerDTO;
import com.kcv.account.management.dto.CustomerRequest;
import com.kcv.account.management.dto.CustomerResponse;

public interface ICustomerService {

    public CustomerResponse addCustomer(CustomerRequest request);

    public List<CustomerResponse> getAllCustomers();

    public CustomerResponse deleteCustomer(CustomerDTO request);

    public CustomerResponse editCustomer(CustomerRequest request);
}
