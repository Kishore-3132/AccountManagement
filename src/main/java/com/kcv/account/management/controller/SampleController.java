package com.kcv.account.management.controller;

import java.util.List;

import com.kcv.account.management.dto.common.CommonRequest;
import com.kcv.account.management.dto.users.UserDetailsRequest;
import com.kcv.account.management.dto.users.UserDetailsResponse;
import com.kcv.account.management.exception.ErrorResponseMapper;
import com.kcv.account.management.security.SecurityUtils;
import com.kcv.account.management.service.IUserLoginProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kcv.account.management.dto.SampleDTO;
import com.kcv.account.management.dto.SampleRequest;
import com.kcv.account.management.dto.SampleResponse;
import com.kcv.account.management.service.ISampleService;

@CrossOrigin
@RestController
@Slf4j
public class SampleController {
    @Autowired
    private ISampleService sampleService;

    @Autowired
    private IUserLoginProfileService userLoginProfileService;

    @Autowired
    ErrorResponseMapper errorResponseMapper;

    @PostMapping("/unsecure/sample/addData")
    public ResponseEntity<SampleResponse> addData(@RequestBody SampleRequest request) {
        SampleResponse response = sampleService.addData(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/unsecure/sample/getAllDetails")
    public ResponseEntity<List<SampleDTO>> getAllDetails() {
        log.info("JSON REQUEST: GET method called for /getAllDetails");
        List<SampleDTO> response = sampleService.getAllDetails();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/unsecure/sample/hello")
    public CommonRequest hello() {
        CommonRequest commonRequest = new CommonRequest();
        return commonRequest;
    }

    @GetMapping("/secure/sample/hello")
    public CommonRequest dummyMethod() {
        CommonRequest commonRequest = new CommonRequest();
        return commonRequest;
    }

    @PostMapping("/unsecure/sample/deleteData")
    public ResponseEntity<SampleResponse> deleteData(@RequestBody SampleRequest request) {
        SampleResponse response = sampleService.deleteData(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/unsecure/sample/updateUserPassword")
    public ResponseEntity<UserDetailsResponse> updateUserPassword(@RequestBody UserDetailsRequest request) {
        UserDetailsResponse userDetailsResponse = userLoginProfileService.findByUsername(request.getUsername());
        if (userDetailsResponse.getSuccess()) {
            request.setUserId(userDetailsResponse.getUserid());
            UserDetailsResponse response = userLoginProfileService.editUser(request);
            if(response.getSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return errorResponseMapper.buildErrorResponse(response.getResponseCode(), UserDetailsResponse.class);
            }

        } else {
            return errorResponseMapper.buildErrorResponse(userDetailsResponse.getResponseCode(), UserDetailsResponse.class);
        }
    }
}
