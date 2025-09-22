package com.kcv.account.management.repository;

import com.kcv.account.management.dto.entity.CustomerDTO;
import com.kcv.account.management.dto.entity.UserLoginActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserLoginActivityRepository extends JpaRepository<UserLoginActivity, Long>{

	UserLoginActivity findBySessionIdAndSessionStatus(String sessionId, String sessionStatus);
    UserLoginActivity findBySessionId(String sessionId);
}
