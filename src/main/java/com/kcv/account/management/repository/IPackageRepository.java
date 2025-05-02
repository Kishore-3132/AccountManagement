package com.kcv.account.management.repository;

import com.kcv.account.management.dto.entity.PackageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPackageRepository extends JpaRepository<PackageDTO, Integer>{

	
}
