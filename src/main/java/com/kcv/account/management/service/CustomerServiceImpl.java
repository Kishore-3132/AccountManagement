package com.kcv.account.management.service;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcv.account.management.dto.CustomerDTO;
import com.kcv.account.management.dto.CustomerGenderEnum;
import com.kcv.account.management.dto.CustomerRequest;
import com.kcv.account.management.dto.CustomerResponse;
import com.kcv.account.management.dto.CustomerStatusEnum;
import com.kcv.account.management.repository.ICustomerRepository;

@Service
@Log4j2
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public CustomerResponse addCustomer(CustomerRequest request) {
        CustomerDTO customer = new CustomerDTO();
        customer.setCustomerName(request.getCustomerName());
        customer.setGender(request.getGender().name());
        customer.setInstallationDate(request.getInstallationDate());
        customer.setMobileNumber(request.getMobileNumber());
        customer.setStatus(request.getStatus().name());
        customer.setUsername(request.getUsername());

        customer = customerRepository.save(customer);

        CustomerResponse customerResponse = new CustomerResponse();

        BeanUtils.copyProperties(customer, customerResponse);
        customerResponse.setGender(CustomerGenderEnum.valueOf(customer.getGender()));
        customerResponse.setStatus(CustomerStatusEnum.valueOf(customer.getStatus()));

        return customerResponse;
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        log.info("Fetching all the Customers");
        List<CustomerResponse> customerList = new ArrayList<CustomerResponse>();

        List<CustomerDTO> listOfCustomer = customerRepository.findAll();

        listOfCustomer.forEach(customer -> {
            CustomerResponse customerResponse = new CustomerResponse();

            BeanUtils.copyProperties(customer, customerResponse);
            customerResponse.setGender(CustomerGenderEnum.valueOf(customer.getGender()));
            customerResponse.setStatus(CustomerStatusEnum.valueOf(customer.getStatus()));

            customerList.add(customerResponse);
        });


        return customerList;
    }

    @Override
    public CustomerResponse deleteCustomer(CustomerDTO request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CustomerResponse editCustomer(CustomerRequest request) {
        CustomerDTO customer = new CustomerDTO();
        customer.setId(request.getId());
        customer.setCustomerName(request.getCustomerName());
        customer.setGender(request.getGender().name());
        customer.setInstallationDate(request.getInstallationDate());
        customer.setMobileNumber(request.getMobileNumber());
        customer.setStatus(request.getStatus().name());
        customer.setUsername(request.getUsername());

        customer = customerRepository.save(customer);

        CustomerResponse customerResponse = new CustomerResponse();

        BeanUtils.copyProperties(customer, customerResponse);
        customerResponse.setGender(CustomerGenderEnum.valueOf(customer.getGender()));
        customerResponse.setStatus(CustomerStatusEnum.valueOf(customer.getStatus()));

        return customerResponse;
    }


}
