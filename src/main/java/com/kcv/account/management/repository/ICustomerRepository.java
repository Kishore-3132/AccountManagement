package com.kcv.account.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcv.account.management.dto.CustomerDTO;
@Repository
public interface ICustomerRepository extends JpaRepository<CustomerDTO, Integer>{

	
}
