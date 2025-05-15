package com.kcv.account.management.service;


import com.kcv.account.management.dto.entity.PackageDTO;
import com.kcv.account.management.dto.packages.PackageRequest;
import com.kcv.account.management.dto.packages.PackageResponse;

import java.util.List;

public interface IPackageService {

    public PackageResponse addPackage(PackageRequest request);

    public PackageResponse getAllPackages();

    public PackageResponse deletePackage(PackageDTO request);

    public PackageResponse editPackage(PackageRequest request);
}
