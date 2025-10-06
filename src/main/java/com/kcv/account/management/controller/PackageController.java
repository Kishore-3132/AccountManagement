package com.kcv.account.management.controller;

import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.dto.customer.CustomerResponse;
import com.kcv.account.management.dto.packages.PackageRequest;
import com.kcv.account.management.dto.packages.PackageResponse;
import com.kcv.account.management.exception.ErrorResponseMapper;
import com.kcv.account.management.service.ICommonService;
import com.kcv.account.management.service.IPackageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/secure/package")
public class PackageController {
    @Autowired
    private IPackageService packageService;

    @Autowired
    private ErrorResponseMapper errorResponseMapper;

    @PostMapping("/addPackage")
    public ResponseEntity<PackageResponse> addPackage(@RequestBody PackageRequest request) {
        PackageResponse response = packageService.addPackage(request);
        if(response.getSuccess())  {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return errorResponseMapper.buildErrorResponse(response.getResponseCode(), PackageResponse.class);
        }
    }

    @GetMapping("/getAllPackages")
    public ResponseEntity<PackageResponse> getAllDetails() {
        log.info("JSON REQUEST: GET method called for /getAllPackages");
        PackageResponse response = packageService.getAllPackages();
        if(response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return errorResponseMapper.buildErrorResponse(response.getResponseCode(), PackageResponse.class);
        }
    }
    @PutMapping("/editPackage/{id}")
    public ResponseEntity<PackageResponse> editPackage(@RequestBody PackageRequest request,@PathVariable Integer id) {
        request.setPackageId(Long.valueOf(id));
        PackageResponse response = packageService.editPackage(request);
        if(response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return errorResponseMapper.buildErrorResponse(response.getResponseCode(), PackageResponse.class);
        }
    }

}
