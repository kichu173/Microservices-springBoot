package com.code.orderservice.external.decoder;

import com.code.orderservice.exception.CustomException;
import com.code.orderservice.external.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {// pass the error response from product service to order service
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();

        log.info("::{}", response.request().url());
        log.info("::{}", response.request().headers());

        try {
            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(),
                    ErrorResponse.class);

            return new CustomException(errorResponse.getErrorMessage(), errorResponse.getErrorCode(),
                    response.status());
        } catch (IOException e) {
            throw new CustomException("Internal Server Error",
                    "INTERNAL_SERVER_ERROR",
                    500);
        }
    }
}
