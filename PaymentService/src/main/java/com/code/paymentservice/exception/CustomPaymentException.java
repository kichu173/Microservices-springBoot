package com.code.paymentservice.exception;

import lombok.Data;

@Data
public class CustomPaymentException extends RuntimeException{
    private String errorCode;

    public CustomPaymentException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
