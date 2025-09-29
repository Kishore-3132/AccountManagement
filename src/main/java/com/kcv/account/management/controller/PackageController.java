package com.kcv.account.management.controller;

import com.kcv.account.management.dto.common.CommonResponse;
import com.kcv.account.management.dto.customer.CustomerResponse;
import com.kcv.account.management.dto.packages.PackageRequest;
import com.kcv.account.management.dto.packages.PackageResponse;
import com.kcv.account.management.exception.ErrorResponseMapper;
import com.kcv.account.management.service.ICommonService;
import com.kcv.account.management.service.IPackageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Log4j2
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
