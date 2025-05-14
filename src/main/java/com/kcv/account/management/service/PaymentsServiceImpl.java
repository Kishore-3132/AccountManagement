package com.kcv.account.management.service;

import com.kcv.account.management.dto.entity.PaymentsDTO;
import com.kcv.account.management.dto.entity.UserDetailsDTO;
import com.kcv.account.management.dto.enums.AccountStatusEnum;
import com.kcv.account.management.dto.enums.GenderEnum;
import com.kcv.account.management.dto.enums.ROLEEnum;
import com.kcv.account.management.dto.packages.PackageResponse;
import com.kcv.account.management.dto.payments.PaymentsRequest;
import com.kcv.account.management.dto.payments.PaymentsResponse;
import com.kcv.account.management.dto.users.UserDetailsRequest;
import com.kcv.account.management.dto.users.UserDetailsResponse;
import com.kcv.account.management.repository.IPaymentsRepository;
import com.kcv.account.management.repository.IUserDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Log4j2
public class PaymentsServiceImpl implements IPaymentsService {
    @Autowired
    private IPaymentsRepository paymentsRepository;

    @Override
    public PaymentsResponse addPayments(PaymentsRequest request) {
        return null;
    }

    @Override
    public List<PaymentsResponse> getAllPayments() {
        log.info("Fetching all the Payments");
        List<PaymentsResponse> paymentsList = new ArrayList<>();
        List<PaymentsDTO> listOfPayments = paymentsRepository.findAll();
        listOfPayments.forEach(payments -> {
            PaymentsResponse paymentsResponse = new PaymentsResponse();
            PackageResponse packageResponse = new PackageResponse();
            packageResponse.setPackageId(payments.getPackageInfo().getId());
            packageResponse.setPackageName(payments.getPackageInfo().getPackageName());
            packageResponse.setPackageAmount(payments.getPackageInfo().getPackageAmount());
            packageResponse.setPackageAmountIncludingGST(payments.getPackageInfo().getPackageAmountIncludingGST());
            packageResponse.setPackageDescription(payments.getPackageInfo().getPackageDescription());
            packageResponse.setPackageSpeed(payments.getPackageInfo().getPackageSpeed());
            paymentsResponse.setPaymentId(payments.getId());
            paymentsResponse.setPackageInfo(packageResponse);
            BeanUtils.copyProperties(payments, paymentsResponse);
            paymentsList.add(paymentsResponse);
        });
        return paymentsList;
    }

    @Override
    public PaymentsResponse deletePayments(PaymentsRequest request) {
        PaymentsResponse paymentsResponse = new PaymentsResponse();
        paymentsRepository.deleteById(request.getPaymentId());
        return paymentsResponse;
    }
}
