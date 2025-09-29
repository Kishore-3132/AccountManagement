package com.kcv.account.management.controller;

import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.dto.payments.PaymentsResponse;
import com.kcv.account.management.dto.users.UserDetailsRequest;
import com.kcv.account.management.dto.users.UserDetailsResponse;
import com.kcv.account.management.exception.ErrorResponseMapper;
import com.kcv.account.management.service.ICommonService;
import com.kcv.account.management.service.IUserLoginProfileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Log4j2
@RequestMapping("/secure/user")
public class UserDetailsController {
    @Autowired
    private IUserLoginProfileService userService;

    @Autowired
    private ErrorResponseMapper errorResponseMapper;

    @PostMapping("/addUser")
    public ResponseEntity<UserDetailsResponse> addUser(@RequestBody UserDetailsRequest request) {
        UserDetailsResponse response = userService.addUser(request);

        if(response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return errorResponseMapper.buildErrorResponse(response.getResponseCode(), UserDetailsResponse.class);
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<UserDetailsResponse> getAllDetails() {
        UserDetailsResponse response = userService.getAllUsers();
        if(response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return errorResponseMapper.buildErrorResponse(response.getResponseCode(), UserDetailsResponse.class);
        }
    }
    @PostMapping("/editUser")
    public ResponseEntity<UserDetailsResponse> editUser(@RequestBody UserDetailsRequest request) {
        UserDetailsResponse response = userService.editUser(request);
        if(response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return errorResponseMapper.buildErrorResponse(response.getResponseCode(), UserDetailsResponse.class);
        }
    }

}
