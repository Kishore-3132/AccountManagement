package com.kcv.account.management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kcv.account.management.dto.common.ErrorCodeConstants;
import com.kcv.account.management.dto.customer.CustomerDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcv.account.management.dto.entity.CustomerDTO;
import com.kcv.account.management.dto.enums.GenderEnum;
import com.kcv.account.management.dto.customer.CustomerRequest;
import com.kcv.account.management.dto.customer.CustomerResponse;
import com.kcv.account.management.dto.enums.AccountStatusEnum;
import com.kcv.account.management.repository.ICustomerRepository;

@Service
@Slf4j
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public CustomerResponse addCustomer(CustomerRequest request) {
        log.info("::: Creating Customer Start :::");
        CustomerResponse customerResponse = new CustomerResponse();
        try {
            CustomerDTO customer = new CustomerDTO();
            customer.setCustomerName(request.getCustomerName());
            customer.setGender(request.getGender().name());
            customer.setInstallationDate(request.getInstallationDate());
            customer.setMobileNumber(request.getMobileNumber());
            customer.setStatus(request.getStatus().name());
            customer.setCustomerId(request.getCustomerId());
            customer.setAddress(request.getCustomerId());
            customer = customerRepository.save(customer);

            BeanUtils.copyProperties(customer, customerResponse);
            customerResponse.setGender(GenderEnum.valueOf(customer.getGender()));
            customerResponse.setStatus(AccountStatusEnum.valueOf(customer.getStatus()));
            customerResponse.setResponseMessage("SUCCESS");
            customerResponse.setResponseCode("000");
            customerResponse.setSuccess(true);
        } catch (Exception e) {
            log.info("::: Error Occurred while Creating the Customer :::");
            customerResponse.setResponseMessage(e.getMessage());
            customerResponse.setResponseCode(ErrorCodeConstants.CustomerErrorCode.CUSTOMER_CREATION_FAILED);
            customerResponse.setSuccess(false);
        }
        log.info("::: Creating Customer End :::");
        return customerResponse;
    }

    @Override
    public CustomerResponse getAllCustomers() {
        log.info("::: Fetching Customers Start :::");

        CustomerResponse response = new CustomerResponse();
        response.setCustomers(new ArrayList<>());
        try {
            List<CustomerDTO> listOfCustomer = customerRepository.findAll();

            if(listOfCustomer != null && listOfCustomer.size() > 0 ) {
                CustomerDetail customerResponse = new CustomerDetail();
                listOfCustomer.forEach(customer -> {
                    BeanUtils.copyProperties(customer, customerResponse);
                    customerResponse.setGender(GenderEnum.valueOf(customer.getGender()));
                    customerResponse.setStatus(AccountStatusEnum.valueOf(customer.getStatus()));
                    response.getCustomers().add(customerResponse) ;
                });
                response.setResponseMessage("SUCCESS");
                response.setResponseCode("000");
                response.setSuccess(true);
            }
            else
            {
                response.setResponseMessage("No Customer Available at the moment");
                response.setResponseCode(ErrorCodeConstants.CustomerErrorCode.NO_CUSTOMER_AVAILABLE);
                response.setSuccess(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.info("::: Error Occurred while Fetching the Customer :::");
            response.setResponseMessage(e.getMessage());
            response.setResponseCode(ErrorCodeConstants.CustomerErrorCode.FETCH_CUSTOMER_FAILED);
            response.setSuccess(false);
        }
        log.info("::: Fetching Customers End :::");
        return response;
    }

    @Override
    public CustomerResponse deleteCustomer(CustomerRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CustomerResponse editCustomer(CustomerRequest request) {
        log.info("::: Customer Editing Start :::");
        CustomerResponse customerResponse = new CustomerResponse();
        try {
            Optional<CustomerDTO> customerDTO = customerRepository.findById(request.getId());
            if (!customerDTO.isPresent()) {
                customerResponse.setResponseMessage("Customer Not Found with the ID : " + request.getId());
                customerResponse.setResponseCode(ErrorCodeConstants.CustomerErrorCode.CUSTOMER_NOT_FOUND);
                customerResponse.setSuccess(false);
                return customerResponse;
            }
            CustomerDTO customer = new CustomerDTO();
            customer.setId(request.getId());
            customer.setCustomerName(request.getCustomerName() != null ? request.getCustomerName() : customerDTO.get().getCustomerName());
            customer.setGender(request.getGender() != null ? request.getGender().name() : customerDTO.get().getGender());
            customer.setInstallationDate(request.getInstallationDate() != null ? request.getInstallationDate() : customerDTO.get().getInstallationDate());
            customer.setMobileNumber(request.getMobileNumber() != null ? request.getMobileNumber() : customerDTO.get().getMobileNumber());
            customer.setStatus(request.getStatus() != null ? request.getStatus().name() : customerDTO.get().getStatus());
            customer.setCustomerId(request.getCustomerId() != null ? request.getCustomerId() : customerDTO.get().getCustomerId());
            customer.setAddress(request.getAddress() != null ? request.getAddress() : customerDTO.get().getAddress());
            customer = customerRepository.save(customer);

            BeanUtils.copyProperties(customer, customerResponse);
            customerResponse.setResponseMessage("SUCCESS");
            customerResponse.setResponseCode("000");
            customerResponse.setSuccess(true);
            customerResponse.setGender(GenderEnum.valueOf(customer.getGender()));
            customerResponse.setStatus(AccountStatusEnum.valueOf(customer.getStatus()));
        } catch (Exception e) {
            log.info("::: Error Occurred while Editing the Customer :::");
            customerResponse.setResponseMessage(e.getMessage());
            customerResponse.setResponseCode(ErrorCodeConstants.CustomerErrorCode.CUSTOMER_EDITING_FAILED);
            customerResponse.setSuccess(false);
        }
        log.info("::: Customer Editing End :::");
        return customerResponse;
    }

}
