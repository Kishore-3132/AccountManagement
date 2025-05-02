package com.kcv.account.management.controller;

import com.kcv.account.management.dto.payments.PaymentsRequest;
import com.kcv.account.management.dto.payments.PaymentsResponse;
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
public class PaymentsController {
    @Autowired
    private IPaymentsService paymentsService;

    @PostMapping("/addPayments")
    public ResponseEntity<PaymentsResponse> addPayments(@RequestBody PaymentsRequest request) {
        PaymentsResponse response = paymentsService.addPayments(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllPayments")
    public ResponseEntity<List<PaymentsResponse>> getAllDetails() {
        List<PaymentsResponse> response = paymentsService.getAllPayments();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PutMapping("/editPayments/{id}")
//    public ResponseEntity<PaymentsResponse> editPayments(@RequestBody PaymentsRequest request,@PathVariable Integer id) {
//        request.setPaymentsId(id);
//        PaymentsResponse response = paymentsService.editPayments(request);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}
