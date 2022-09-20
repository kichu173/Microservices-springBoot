package com.code.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long orderId;
    private Instant orderDate;
    private String orderStatus;
    private Long amount;
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductDetails {
        private Long productId;
        private String productName;
        private Long price;
        private Long quantity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PaymentDetails {
        private Long paymentId;
        private String status;
        private PaymentMode paymentMode;
        private Long amount;
        private Instant paymentDate;
        private Long orderId;
    }
}
