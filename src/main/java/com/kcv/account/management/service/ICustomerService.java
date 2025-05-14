package com.kcv.account.management.service;


import java.util.List;

import com.kcv.account.management.dto.entity.CustomerDTO;
import com.kcv.account.management.dto.customer.CustomerRequest;
import com.kcv.account.management.dto.customer.CustomerResponse;

public interface ICustomerService {

    public CustomerResponse addCustomer(CustomerRequest request);

    public CustomerResponse getAllCustomers();

    public CustomerResponse deleteCustomer(CustomerDTO request);

    public CustomerResponse editCustomer(CustomerRequest request);
}
