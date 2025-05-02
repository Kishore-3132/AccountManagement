package com.kcv.account.management.repository;

import com.kcv.account.management.dto.UserDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDetailsRepository extends JpaRepository<UserDetailsDTO, Integer>{

	
}
