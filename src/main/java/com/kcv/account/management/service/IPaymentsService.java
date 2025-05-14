package com.kcv.account.management.service;



import com.kcv.account.management.dto.payments.PaymentsRequest;
import com.kcv.account.management.dto.payments.PaymentsResponse;

import java.util.List;

public interface IPaymentsService {

    public PaymentsResponse addPayments(PaymentsRequest request);

    public List<PaymentsResponse> getAllPayments();

    public PaymentsResponse deletePayments(PaymentsRequest request);

//    public PaymentsResponse editPayments(PaymentsRequest request);
}
