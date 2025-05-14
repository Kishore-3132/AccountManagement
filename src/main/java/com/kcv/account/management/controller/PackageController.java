package com.kcv.account.management.controller;

import com.kcv.account.management.dto.packages.PackageRequest;
import com.kcv.account.management.dto.packages.PackageResponse;
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
@RequestMapping("/package")
public class PackageController {
    @Autowired
    private IPackageService packageService;

    @PostMapping("/addPackage")
    public ResponseEntity<PackageResponse> addPackage(@RequestBody PackageRequest request) {
        PackageResponse response = packageService.addPackage(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllPackages")
    public ResponseEntity<List<PackageResponse>> getAllDetails() {
        List<PackageResponse> response = packageService.getAllPackages();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/editPackage/{id}")
    public ResponseEntity<PackageResponse> editPackage(@RequestBody PackageRequest request,@PathVariable Integer id) {
        request.setPackageId(id);
        PackageResponse response = packageService.editPackage(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
