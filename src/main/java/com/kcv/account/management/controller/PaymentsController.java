package com.kcv.account.management.controller;

import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.dto.customer.CustomerDetail;
import com.kcv.account.management.dto.packages.PackageResponse;
import com.kcv.account.management.dto.payments.PaymentsRequest;
import com.kcv.account.management.dto.payments.PaymentsResponse;
import com.kcv.account.management.exception.ErrorResponseMapper;
import com.kcv.account.management.service.ICommonService;
import com.kcv.account.management.service.IPaymentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/secure/payments")
public class PaymentsController {
    @Autowired
    private IPaymentsService paymentsService;

    @Autowired
    private ErrorResponseMapper errorResponseMapper;

    @PostMapping("/addPayments")
    public ResponseEntity<PaymentsResponse> addPayments(@RequestBody PaymentsRequest request) {
        PaymentsResponse response = paymentsService.addPayments(request);
        if(response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return errorResponseMapper.buildErrorResponse(response.getResponseCode(), PaymentsResponse.class);
        }
    }

    @GetMapping("/getAllPayments")
    public ResponseEntity<PaymentsResponse> getAllDetails() {
        log.info("JSON REQUEST: GET method called for /getAllPayments");
        PaymentsResponse response = paymentsService.getAllPayments();
        if(response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return errorResponseMapper.buildErrorResponse(response.getResponseCode(), PaymentsResponse.class);
        }
    }

    @PostMapping("/secure/deletePayments")
    public ResponseEntity<PaymentsResponse> deletePayment(@RequestBody PaymentsRequest request) {
        PaymentsRequest paymentsRequest = new PaymentsRequest();
        paymentsRequest.setPaymentId(request.getPaymentId());
        PaymentsResponse response = paymentsService.deletePayments(paymentsRequest);

        if(response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return errorResponseMapper.buildErrorResponse(response.getResponseCode(), PaymentsResponse.class);
        }
    }

    @PostMapping("/customerPayments")
    public ResponseEntity<PaymentsResponse> customerPayments(@RequestBody PaymentsRequest request) {
        PaymentsRequest paymentsRequest = new PaymentsRequest();
        CustomerDetail customer = new CustomerDetail();
        customer.setId(request.getCustomer().getId());
        paymentsRequest.setCustomer(customer);
        PaymentsResponse response = paymentsService.getCustomerPayments(paymentsRequest);
        if(response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return errorResponseMapper.buildErrorResponse(response.getResponseCode(), PaymentsResponse.class);
        }
    }

//    @PutMapping("/editPayments/{id}")
//    public ResponseEntity<PaymentsResponse> editPayments(@RequestBody PaymentsRequest request,@PathVariable Integer id) {
//        request.setPaymentsId(id);
//        PaymentsResponse response = paymentsService.editPayments(request);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}
