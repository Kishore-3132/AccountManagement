package com.kcv.account.management.repository;

import com.kcv.account.management.dto.entity.ErrorMappingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IErrorMappingRepository extends JpaRepository<ErrorMappingDTO, Long>{

	Optional<ErrorMappingDTO> findByCode(String code);
}
