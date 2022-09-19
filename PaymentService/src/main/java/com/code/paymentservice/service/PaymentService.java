package com.code.paymentservice.service;

import com.code.paymentservice.model.PaymentRequest;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);
}
