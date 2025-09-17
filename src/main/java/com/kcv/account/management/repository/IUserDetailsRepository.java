package com.kcv.account.management.repository;

import com.kcv.account.management.dto.entity.UserDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserDetailsRepository extends JpaRepository<UserDetailsDTO, Long>{

    Optional<UserDetailsDTO> findByUsername(String username);

}
