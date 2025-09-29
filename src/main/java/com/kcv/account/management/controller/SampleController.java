package com.kcv.account.management.controller;

import java.util.List;

import com.kcv.account.management.dto.users.UserDetailsRequest;
import com.kcv.account.management.dto.users.UserDetailsResponse;
import com.kcv.account.management.exception.ErrorResponseMapper;
import com.kcv.account.management.service.IUserLoginProfileService;
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
@RequestMapping("/unsecure/sample")
public class SampleController {
    @Autowired
    private ISampleService demoProjectService;

    @Autowired
    private IUserLoginProfileService userLoginProfileService;

    @Autowired
    ErrorResponseMapper errorResponseMapper;

    @PostMapping("/addData")
    public ResponseEntity<SampleResponse> addData(@RequestBody SampleRequest request) {
        SampleResponse response = demoProjectService.addData(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllDetails")
    public ResponseEntity<List<SampleDTO>> getAllDetails() {
        List<SampleDTO> response = demoProjectService.getAllDetails();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/deleteData")
    public ResponseEntity<SampleResponse> deleteData(@RequestBody SampleRequest request) {
        SampleResponse response = demoProjectService.deleteData(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/updateUserPassword")
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
