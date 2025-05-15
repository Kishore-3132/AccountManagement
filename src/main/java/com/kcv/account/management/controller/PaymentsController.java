package com.kcv.account.management.controller;

import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.dto.customer.CustomerDetail;
import com.kcv.account.management.dto.payments.PaymentsRequest;
import com.kcv.account.management.dto.payments.PaymentsResponse;
import com.kcv.account.management.service.ICommonService;
import com.kcv.account.management.service.IPaymentsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Log4j2
@RequestMapping("/payments")
public class PaymentsController {
    @Autowired
    private IPaymentsService paymentsService;

    @Autowired
    private ICommonService commonService;

    @PostMapping("/addPayments")
    public ResponseEntity<PaymentsResponse> addPayments(@RequestBody PaymentsRequest request) {
        PaymentsResponse response = paymentsService.addPayments(request);
        if(response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            PaymentsResponse errorResponse = new PaymentsResponse();
            CommonResponse error = commonService.getErrorCodeDescription(response.getResponseCode());
            errorResponse.setResponseMessage(error.getResponseMessage());
            errorResponse.setResponseCode(error.getResponseCode());
            errorResponse.setSuccess(false);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/getAllPayments")
    public ResponseEntity<PaymentsResponse> getAllDetails() {
        PaymentsResponse response = paymentsService.getAllPayments();
        if(response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            PaymentsResponse errorResponse = new PaymentsResponse();
            CommonResponse error = commonService.getErrorCodeDescription(response.getResponseCode());
            errorResponse.setResponseMessage(error.getResponseMessage());
            errorResponse.setResponseCode(error.getResponseCode());
            errorResponse.setSuccess(false);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    @DeleteMapping("/deletePayments/{id}")
    public ResponseEntity<PaymentsResponse> deletePayment(@PathVariable Integer id) {
        PaymentsRequest paymentsRequest = new PaymentsRequest();
        paymentsRequest.setPaymentId(id);
        PaymentsResponse response = paymentsService.deletePayments(paymentsRequest);

        if(response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            PaymentsResponse errorResponse = new PaymentsResponse();
            CommonResponse error = commonService.getErrorCodeDescription(response.getResponseCode());
            errorResponse.setResponseMessage(error.getResponseMessage());
            errorResponse.setResponseCode(error.getResponseCode());
            errorResponse.setSuccess(false);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/customerPayments")
    public ResponseEntity<PaymentsResponse> customerPayments(@RequestParam("id") Integer id) {
        PaymentsRequest paymentsRequest = new PaymentsRequest();
        CustomerDetail customer = new CustomerDetail();
        customer.setId(id);
        paymentsRequest.setCustomer(customer);
        PaymentsResponse response = paymentsService.getCustomerPayments(paymentsRequest);
        if(response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            PaymentsResponse errorResponse = new PaymentsResponse();
            CommonResponse error = commonService.getErrorCodeDescription(response.getResponseCode());
            errorResponse.setResponseMessage(error.getResponseMessage());
            errorResponse.setResponseCode(error.getResponseCode());
            errorResponse.setSuccess(false);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

//    @PutMapping("/editPayments/{id}")
//    public ResponseEntity<PaymentsResponse> editPayments(@RequestBody PaymentsRequest request,@PathVariable Integer id) {
//        request.setPaymentsId(id);
//        PaymentsResponse response = paymentsService.editPayments(request);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}
