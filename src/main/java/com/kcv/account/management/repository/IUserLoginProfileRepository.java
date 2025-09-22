package com.kcv.account.management.repository;

import com.kcv.account.management.dto.entity.UserLoginProfileDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserLoginProfileRepository extends JpaRepository<UserLoginProfileDTO, Integer>{

	
}
