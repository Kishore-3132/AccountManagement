package com.kcv.account.management.service;

import com.kcv.account.management.dto.common.ErrorCodeConstants;
import com.kcv.account.management.dto.entity.PackageDTO;
import com.kcv.account.management.dto.packages.PackageDetail;
import com.kcv.account.management.dto.packages.PackageRequest;
import com.kcv.account.management.dto.packages.PackageResponse;
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
        log.info("::: Creating Package Start :::");
        PackageResponse  response = new PackageResponse();
        try {
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
            packageDTO = packageRepository.save(packageDTO);

            BeanUtils.copyProperties(packageDTO, response);
            response.setPackageId(packageDTO.getId());
            response.setResponseMessage("SUCCESS");
            response.setResponseCode("000");
            response.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("::: Error Occurred while Creating the Package :::");
            response.setResponseMessage(e.getMessage());
            response.setResponseCode(ErrorCodeConstants.PackageErrorCode.PACKAGE_CREATION_FAILED);
            response.setSuccess(false);
        }
        log.info("::: Creating Package End :::");
        return response;
    }

    @Override
    public PackageResponse deletePackage(PackageDTO request) {
        return null;
    }

    @Override
    public PackageResponse getAllPackages() {
        PackageResponse packageResponse = new PackageResponse();
        log.info("::: Fetching Packages Start :::");
        try {
            List<PackageDTO> listOfPackages = packageRepository.findAll();
            if(listOfPackages != null && listOfPackages.size() > 0 ) {
                listOfPackages.forEach(packageDTO -> {
                    PackageDetail packageList = new PackageDetail();
                    BeanUtils.copyProperties(packageDTO, packageList);
                    packageList.setPackageId(packageDTO.getId());
                    packageResponse.getPackages().add(packageList);
                });
                packageResponse.setResponseMessage("SUCCESS");
                packageResponse.setResponseCode("000");
                packageResponse.setSuccess(true);
            }
            else
            {
                packageResponse.setResponseMessage("No Packages Available at the moment");
                packageResponse.setResponseCode(ErrorCodeConstants.PackageErrorCode.NO_PACKAGE_AVAILABLE);
                packageResponse.setSuccess(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.info("::: Error Occurred while Fetching Packages :::");
            packageResponse.setResponseMessage(e.getMessage());
            packageResponse.setResponseCode(ErrorCodeConstants.PackageErrorCode.FETCH_PACKAGE_FAILED);
            packageResponse.setSuccess(false);
        }
        log.info("::: Fetching Packages End :::");
        return packageResponse;
    }

    @Override
    public PackageResponse editPackage(PackageRequest request) {
        log.info("::: Editing Package Start :::");
        PackageResponse  response = new PackageResponse();
        try {
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
            packageDTO = packageRepository.save(packageDTO);

            BeanUtils.copyProperties(packageDTO, response);
            response.setPackageId(packageDTO.getId());
            response.setResponseMessage("SUCCESS");
            response.setResponseCode("000");
            response.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("::: Error Occurred while Editing Packages :::");
            response.setResponseMessage(e.getMessage());
            response.setResponseCode(ErrorCodeConstants.PackageErrorCode.PACKAGE_EDITING_FAILED);
            response.setSuccess(false);
        }
        log.info("::: Editing Package End :::");
        return response;
    }
}
