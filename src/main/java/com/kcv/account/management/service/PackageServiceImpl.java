package com.kcv.account.management.service;

import com.kcv.account.management.dto.entity.PackageDTO;
import com.kcv.account.management.dto.packages.PackageRequest;
import com.kcv.account.management.dto.packages.PackageResponse;
import com.kcv.account.management.repository.ICustomerRepository;
import com.kcv.account.management.repository.IPackageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Log4j2
public class PackageServiceImpl implements IPackageService {

    @Autowired
    private IPackageRepository packageRepository;

    @Override
    public PackageResponse addPackage(PackageRequest request) {
        PackageDTO packageDTO = new PackageDTO();
        packageDTO.setPackageName(request.getPackageName());
        packageDTO.setPackageAmount(request.getPackageAmount());
        packageDTO.setPackageDescription(request.getPackageDescription());
        packageDTO.setPackageSpeed(request.getPackageSpeed());
        if(request.getPackageAmount() != null) {
            BigDecimal taxRate = new BigDecimal("0.18");
            // Calculate tax amount
            BigDecimal taxAmount = request.getPackageAmount().multiply(taxRate);
            BigDecimal total = request.getPackageAmount().add(taxAmount);
            BigDecimal roundedTotal = total.setScale(2, RoundingMode.HALF_UP);
            packageDTO.setPackageAmountIncludingGST(roundedTotal);
        }

        PackageResponse  response = new PackageResponse();
        packageDTO = packageRepository.save(packageDTO);

        BeanUtils.copyProperties(packageDTO, response);
        response.setPackageId(packageDTO.getId());

        return response;
    }

    @Override
    public PackageResponse deletePackage(PackageDTO request) {
        return null;
    }

    @Override
    public List<PackageResponse> getAllPackages() {
        log.info("Fetching all the Packages");
        List<PackageResponse> packageList = new ArrayList<>();
        List<PackageDTO> listOfPackages = packageRepository.findAll();
        listOfPackages.forEach(packageDTO -> {
            PackageResponse packageResponse = new PackageResponse();
            BeanUtils.copyProperties(packageDTO, packageResponse);
            packageResponse.setPackageId(packageDTO.getId());
            packageList.add(packageResponse);
        });
        return packageList;
    }

    @Override
    public PackageResponse editPackage(PackageRequest request) {
        PackageDTO packageDTO = new PackageDTO();
        packageDTO.setPackageName(request.getPackageName());
        packageDTO.setPackageAmount(request.getPackageAmount());
        packageDTO.setPackageDescription(request.getPackageDescription());
        packageDTO.setPackageSpeed(request.getPackageSpeed());
        packageDTO.setId(request.getPackageId());
        if(request.getPackageAmount() != null) {
            BigDecimal taxRate = new BigDecimal("0.18");
            // Calculate tax amount
            BigDecimal taxAmount = request.getPackageAmount().multiply(taxRate);
            BigDecimal total = request.getPackageAmount().add(taxAmount);
            BigDecimal roundedTotal = total.setScale(2, RoundingMode.HALF_UP);
            packageDTO.setPackageAmountIncludingGST(roundedTotal);
        }

        PackageResponse  response = new PackageResponse();
        packageDTO = packageRepository.save(packageDTO);

        BeanUtils.copyProperties(packageDTO, response);
        response.setPackageId(packageDTO.getId());

        return response;
    }
}
