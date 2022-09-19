package com.code.paymentservice.service;

import com.code.paymentservice.entity.TransactionDetails;
import com.code.paymentservice.model.PaymentRequest;
import com.code.paymentservice.repository.TransactionDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private TransactionDetailsRepository repository;

    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording payment details: {}", paymentRequest);

        TransactionDetails transactionDetails = TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();

        repository.save(transactionDetails);
        log.info("transaction completed with Id; {}", transactionDetails.getId());

        return transactionDetails.getId();
    }
}
