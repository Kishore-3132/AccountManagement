package com.kcv.account.management.repository;

import com.kcv.account.management.dto.entity.ErrorMappingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IErrorMappingRepository extends JpaRepository<ErrorMappingDTO, Integer>{

	public ErrorMappingDTO findByCode(String code);
}
