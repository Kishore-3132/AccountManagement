package com.kcv.account.management.service;

import com.kcv.account.management.dto.common.ErrorCodeConstants;
import com.kcv.account.management.dto.customer.CustomerDetail;
import com.kcv.account.management.dto.entity.PaymentsDTO;
import com.kcv.account.management.dto.packages.PackageDetail;
import com.kcv.account.management.dto.payments.PaymentsDetail;
import com.kcv.account.management.dto.payments.PaymentsRequest;
import com.kcv.account.management.dto.payments.PaymentsResponse;
import com.kcv.account.management.repository.IPaymentsRepository;
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
    public PaymentsResponse getAllPayments() {
        log.info("::: Fetching Payments Start :::");
        PaymentsResponse response = new PaymentsResponse();
        response.setPayments(new ArrayList<>());
        try {
            List<PaymentsDTO> listOfPayments = paymentsRepository.findAll();
            if(listOfPayments != null && listOfPayments.size() > 0) {
                listOfPayments.forEach(payments -> {
                    PaymentsDetail paymentsResponse = new PaymentsDetail();

                    PackageDetail packageResponse = new PackageDetail();
                    packageResponse.setPackageId(payments.getPackageInfo().getId());
                    packageResponse.setPackageName(payments.getPackageInfo().getPackageName());
                    packageResponse.setPackageAmount(payments.getPackageInfo().getPackageAmount());
                    packageResponse.setPackageAmountIncludingGST(payments.getPackageInfo().getPackageAmountIncludingGST());
                    packageResponse.setPackageDescription(payments.getPackageInfo().getPackageDescription());
                    packageResponse.setPackageSpeed(payments.getPackageInfo().getPackageSpeed());
                    paymentsResponse.setPackageInfo(packageResponse);

                    CustomerDetail customer = new CustomerDetail();
                    customer.setId(payments.getCustomer().getId());
                    customer.setCustomerId(payments.getCustomer().getCustomerId());
                    customer.setCustomerName(payments.getCustomer().getCustomerName());
                    paymentsResponse.setCustomer(customer);

                    paymentsResponse.setPaymentId(payments.getId());
                    BeanUtils.copyProperties(payments, paymentsResponse);
                    response.getPayments().add(paymentsResponse);

                });
                response.setResponseMessage("SUCCESS");
                response.setResponseCode("000");
                response.setSuccess(true);
            }
            else {
                response.setResponseMessage("No Payments Available at the moment");
                response.setResponseCode(ErrorCodeConstants.PaymentErrorCode.NO_PAYMENT_AVAILABLE);
                response.setSuccess(false);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            log.info("::: Error Occurred while Fetching the Payments :::");
            response.setResponseMessage(e.getMessage());
            response.setResponseCode(ErrorCodeConstants.PaymentErrorCode.FETCH_PAYMENT_FAILED);
            response.setSuccess(false);
        }
        log.info("::: Fetching Payments End :::");
        return response;
    }

    @Override
    public PaymentsResponse deletePayments(PaymentsRequest request) {
        log.info("::: Payment Deletion Start :::");
        PaymentsResponse paymentsResponse = new PaymentsResponse();
        try {
            paymentsRepository.deleteById(request.getPaymentId());
            paymentsResponse.setResponseMessage("SUCCESS");
            paymentsResponse.setResponseCode("000");
            paymentsResponse.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("::: Error Occurred while Fetching the Payments :::");
            paymentsResponse.setResponseMessage(e.getMessage());
            paymentsResponse.setResponseCode(ErrorCodeConstants.PaymentErrorCode.PAYMENT_DELETION_FAILED);
            paymentsResponse.setSuccess(false);
        }
        log.info("::: Payment Deletion End :::");
        return paymentsResponse;
    }
}
