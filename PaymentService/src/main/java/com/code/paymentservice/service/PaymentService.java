package com.code.paymentservice.service;

import com.code.paymentservice.model.PaymentRequest;
import com.code.paymentservice.model.PaymentResponse;

public interface PaymentService {
    PaymentResponse getPaymentDetailsByOrderId(Long orderId);

    Long doPayment(PaymentRequest paymentRequest);
}
