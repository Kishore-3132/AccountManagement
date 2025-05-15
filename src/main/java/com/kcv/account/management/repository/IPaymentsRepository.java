package com.kcv.account.management.repository;

import com.kcv.account.management.dto.entity.CustomerDTO;
import com.kcv.account.management.dto.entity.PaymentsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaymentsRepository extends JpaRepository<PaymentsDTO, Integer>{

    List<PaymentsDTO> findByCustomer(CustomerDTO customer);
}
